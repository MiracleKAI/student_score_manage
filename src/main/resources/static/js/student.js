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
        courseId: ""
    },
    mounted: function () {
        let that = this;
        let au = document.cookie.split("=")[1]
        axios
            .get('http://182.92.224.68:8080/v1/student', {
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
        getAllScores: function () {
            let that = this;
            let au = document.cookie.split("=")[1]
            axios
                .get('http://182.92.224.68:8080/v1/student/scores?course=' + "&start=" + that.page + "&pagesize=10", {
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
            let au = document.cookie.split("=")[1]
            if (that.courseId === "") {
                alert("课程编号不可为空！")
            } else {
                axios
                    .get('http://182.92.224.68:8080/v1/student/scores?course=' + that.courseId + "&start=" + that.page + "&pagesize=10", {
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