/**
 * @ muyue
 * @ date: 2020.6.10
 */



let v = new Vue({
    el: "#student",
    data: {
        student: {},
        page: 1,
        scores: [],
        courseId: "",
        ip: "182.92.224.68:8000"
    },

    mounted: function () {
        let that = this;
        let au = "";
        var cookies = document.cookie.split(";");
        let userId = that.getParamValue(window.location.href, "key");
        au = that.getAu(cookies, userId);
        console.log(au);
        axios
            .get('http://' + that.ip +'/v1/student', {
                headers: {
                    authentication: au
                }
            })
            .then(response => {
                console.log(response.data)
                // that.data.test = response
                that.student = response.data.data.user
                
            })
            .catch(error => {
                console.log(error);
                window.location.href = "login.html"
            });
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
        getAllScores: function () {
            let that = this;
            let au = "";
            var cookies = document.cookie.split(";");
            let userId = that.getParamValue(window.location.href, "key");
            au = that.getAu(cookies, userId);
            console.log(au);
            axios
                .get('http://'+ that.ip +'/v1/student/scores?course=' + "&start=" + that.page + "&pagesize=10", {
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
                    that.scores = response.data.data.scores

                })
                .catch(error => {
                    console.log(error);
                    alert("网络错误，请重试!")
                });
        },
        getScore: function () {
            let that = this;
            let au = "";
            let cookies = document.cookie.split(";");
            let userId = this.getParamValue(window.location.href, "key");
            au = that.getAu(cookies, userId);
            if (that.courseId === "") {
                alert("课程编号不可为空！")
            } else {
                axios
                    .get('http://' + that.ip + '/v1/student/scores?course=' + that.courseId + "&start=" + that.page + "&pagesize=10", {
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
                        if (response.data.data === null)
                            alert("无此结果")
                        else
                            that.scores = response.data.data.scores

                    })
                    .catch(error => {
                        console.log(error);
                        alert("输入错误，请重试!")
                    });
            }

        },
    }
})