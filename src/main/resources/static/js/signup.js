var app = Vue.createApp({
  data() {
    return {
        username: null,
        password: null,
        error: null
    }
  },
  methods: {
    signup(){
        axios
            .post('./signup',{
                        username:this.username,
                        password:this.password
                    }
                )
            .then(res=>{
                console.log(res.data)
                this.error = null
                window.location.replace("./login")
            })
            .catch(err=>this.error=err.request.status==403 ? "Пользователь с таким именем уже существует" : null)
    }
  }
});

var vm = app.mount("#app");