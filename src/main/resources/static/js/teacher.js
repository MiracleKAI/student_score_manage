/**
 * @ muyue
 * @ date: 2020.6.11 
 */



let qs = Qs
let vu = new Vue({
    el: "#teacher",
    data: {
        teacher: {
            name: "",
            tid: "",
            faculty: "",
            gender: ""
        },
        page: 1,
        flag: 0,
        npro: 30,
        fpro: 70,
        course: "",
        courseId: "",
        className: "全部班级",
        courseName: "",
        courseList: [],
        classList: [],
        scores: [],
        isShown1: false,
        isShown2: false,
        isFound: false,
        ip: "localhost:8080"
    },
    methods: {
        getAu : function(cookies, userId){
            for (let i = 0; i < cookies.length; i++){
                let co = cookies[i].split("=");
                console.log(co);
                if(co[0].trim() === userId){
                    au = co[1];
                }
            }
            return au
        },
        getParamValue : function(url,key){
            var regex = new RegExp(key+"=([^&]*)","i");
            return url.match(regex)[1];

        },
        toggle1: function (e) {
            this.isShown1 = !this.isShown1;
        },
        toggle2: function (e) {
            this.isShown2 = !this.isShown2;
        },
        setAllClass: function () {
            this.className = "全部班级"
            this.isShown1 = !this.isShown1;
        },
        setClass(name) {
            this.className = name;
            this.isShown1 = !this.isShown1;
        },
        setCourse(name, Id) {
            this.courseId = Id;
            this.course = name;
            this.isShown2 = !this.isShown2;
        },
        setScore: function (index) {
            this.scores[index].score.score = (this.scores[index].score.normalScore * this.npro + this.scores[index].score.finalScore * this.fpro) / 100
            if (this.flag == 1) {
                this.scores[index].score.score = ((this.scores[index].score.score - 50) / 10).toFixed(2)
                if (this.scores[index].score.score < 0)
                    this.scores[index].score.score = 0
            }
        },
        compute1() {
            this.fpro = 100 - this.npro;
            this.changeFlag(1)
        },
        compute2() {
            n = 100 - this.fpro
            f = this.fpro
            this.npro = 100 - this.fpro;
            this.changeFlag(1)
        },
        changeFlag(f) {
            console.log(this.flag)
            let index = 0
            for (item in this.scores) {
                console.log("wuwuwu" + index);
                this.scores[index].score.score = (this.scores[index].score.normalScore * this.npro + this.scores[index].score.finalScore * this.fpro) / 100
                if (this.flag == 1) {
                    this.scores[index].score.score = ((this.scores[index].score.score - 50) / 10).toFixed(2)
                    if (this.scores[index].score.score < 0)
                        this.scores[index].score.score = 0
                }
                index += 1
            }
        },
        deleteById(index) {
            let that = this;
            let au = "";
            let cookies = document.cookie.split(";");
            let userId = this.getParamValue(window.location.href, "key");
            au = that.getAu(cookies, userId);
            console.log(au);
            let con = confirm("确认删除该条成绩？")
            if (con) {

                axios
                    .delete('http://'+ that.ip +'/v1/teacher/scores?cid=' + that.scores[index].score.id, {
                        headers: {
                            authentication: au
                        }
                        // params: {
                        //     course: "",
                        //     start: that.page,
                        //     pagesize: 10
                        // }
                    })
                    .then(response => {
                        console.log(response.data)
                        // if (response.data.data === null)
                        //     alert("无此结果")
                        // else
                        that.scores.splice(index, 1)
                        alert("删除成功")
                    })
                    .catch(error => {
                        console.log(error);
                        alert("网络错误，请重试!")
                    });
            }
        },

        getInfo: function () {
            let that = this;
            let au = "";
            let cookies = document.cookie.split(";");
            let userId = this.getParamValue(window.location.href, "key");
            au = that.getAu(cookies, userId);
            console.log(au);
            let classN = (that.className === "全部班级" ? "" : that.className)
            axios
                .get('http://'+ that.ip +'/v1/teacher/scores?course=' + that.courseId + "&class=" + classN + "&start=" + that.page + "&pagesize=20", {
                    headers: {
                        authentication: au
                    }
                    // params: {
                    //     course: "",
                    //     start: that.page,
                    //     pagesize: 10
                    // }
                })
                .then(response => {
                    console.log(response.data)
                    // if (response.data.data === null)
                    //     alert("无此结果")
                    // else
                    that.scores = response.data.data.scores
                    that.isFound = true
                    that.courseName = that.course
                    if(response.data.data.scores[0].score.flag != null)
                          that.flag = response.data.data.scores[0].score.flag
                 })
                .catch(error => {
                    console.log(error);
                    alert("网络错误，请重试!")
                });
        },
        setScores() {
            let that = this;
            let au = "";
            let cookies = document.cookie.split(";");
            let userId = this.getParamValue(window.location.href, "key");
            au = that.getAu(cookies, userId);
            console.log(au);
            let list = []
            // let qs = Qs;
            for (item of that.scores) {
                item.score.flag = that.flag
                list.push(item.score)
            }
            var myHeaders = new Headers();
            myHeaders.append("authentication", au);
            myHeaders.append("Content-Type", "application/json");

            var raw = JSON.stringify(list);

            var requestOptions = {
                method: 'PATCH',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch("http://" + that.ip + "/v1/teacher/scores", requestOptions)
                .then(response => {
                    response.text()
                    alert("提交成功！")
                })
                .then(result => console.log(result))
                .catch(error => {
                    console.log('error', error)
                    alert("错误，请重试！")
                });
        }
    },
    mounted: function () {
        let that = this;
        let au = "";
        let cookies = document.cookie.split(";");
        let userId = this.getParamValue(window.location.href, "key");
        au = that.getAu(cookies, userId);
        console.log(au);
        axios
            .get('http://' + that.ip +'/v1/teacher', {
                headers: {
                    authentication: au
                }
            })
            .then(response => {
                console.log(response.data)
                // that.data.test = response
                that.teacher = response.data.data.user

            })
            .catch(error => {
                console.log(error);
                window.location.href = "login.html"
            });
        axios
            .get('http://' + that.ip +'/v1/teacher/course', {
                headers: {
                    authentication: au
                }
            })
            .then(response => {
                console.log(response.data)
                // that.data.test = response
                that.courseList = response.data.data.courses
                that.course = that.courseList[0].courseName
                that.courseId = that.courseList[0].courseId
            })
            .catch(error => {
                console.log(error);

            });
        axios
            .get('http://' + that.ip + '/v1/teacher/class', {
                headers: {
                    authentication: au
                }
            })
            .then(response => {
                console.log(response.data)
                // that.data.test = response
                that.classList = response.data.data.classes
            })
            .catch(error => {
                console.log(error);

            });
    },
    computed: {


    },

});