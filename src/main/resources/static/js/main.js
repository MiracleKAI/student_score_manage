/**
 * @ muyue
 * @ date: 2020.6.10
 */



let vm = new Vue({
    el: "#login",
    data: {
        userId: "",
        pwd: "",
        role: 0,
        isStu: false,
        uIdisEmpty: false,
        pwdisEmpty: false,
        error: false,
        isLoading: false
    },
    methods: {
        login: function () {
            let that = this;
            let uid = that.userId;
            if (uid === "") {
                this.uIdisEmpty = true
            } else if (this.pwd === "") {
                this.pwdisEmpty = true
            } else {

                this.isLoading = true
                this.uIdisEmpty = false;
                this.pwdisEmpty = false;
                axios
                    .post('http://182.92.224.68:8080/v1/authorizations', {
                        name: that.userId,//"B17070420",//
                        password: that.pwd,//"12345678",//
                        role: that.role
                    })
                    .then(response => {
                        console.log(response.data.data.authentication)
                        // that.data.test = response
                        if (response.data.data.authentication === null) {
                            that.error = true;
                        } else {
                            let d = new Date();
                            d.setTime(d.getTime() + (24 * 60 * 60 * 1000));
                            let expires = "expires=" + d.toGMTString();
                            document.cookie = "token=" + response.data.data.authentication + "; " + expires;
                            if (that.role === 0) {
                                window.location.href = "student.html";
                            }
                            else {
                                window.location.href = "teacher.html"
                            }
                        }

                    })
                    .catch(error => {
                        console.log(error);
                        that.error = true;
                    });
            }
        }
    }
})



