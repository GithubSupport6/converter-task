var app = Vue.createApp({
  data() {
    return {
        defaultSelected: null,
        valutes: ['Data not found'],
        valuteLeft: null,
        valuteRight:null,
        amount: 0,
        amountRes: 0,
        history: []
    }
  },
  mounted() {
        axios
            .get('./getNames')
            .then(res=>{
                this.valutes = res.data
                this.defaultSelected = this.valutes[0]
            })
            .catch(err=>console.log(err))
  },
  methods: {
    convert(){
        axios
            .post('./convert',{
                        from:this.valuteLeft,
                        to:this.valuteRight,
                        amount:this.amount
                    }
                )
            .then(res=>this.amountRes = res.data)
            .catch(err=>console.log(err))
    },
    getHistory(){
        axios
            .get('./getHistory')
            .then(res=>this.history=res.data)
            .catch(err=>console.log(err))
    }
  }
});

var vm = app.mount("#app");