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
            .then(res=>this.error = res.data.error)
            .catch(err=>console.log(err))
    }
  }
});

var vm = app.mount("#app");