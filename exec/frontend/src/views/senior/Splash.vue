<template>
  <div class="splash">
    <img class="logo"
    src="@/assets/ITdaLogo.png"
    >
    <p>나와 가족을 잇는 다리</p>
  </div>
</template>

<script>
import "@/components/css/senior/splash.scss";
import Sha256 from "@/lib/sha256.js"
import axios from "@/service/axios.service.js";

export default {
  name: "Splash",
  created() {
  },
  mounted() {
    axios.get("/order",{
      params:{
        hashIp:this.$store.state.ipHash
      }
    }).then((res) => {
      console.log(res);
      if(res.data.command != null) {
          axios.delete("/order",{
            params:{
              hashIp:this.$store.state.ipHash
            }
          }).then(() => {
            
            // userId 전달
            axios.post("/order",{
              hashIp:this.$store.state.ipHash,
              command:"userId=" + this.$store.state.userId
            }).then(() => {

            })
          })
        }
        else{
          
         // userId 전달  
          axios.post("/order",{
            hashIp:this.$store.state.ipHash,
            command:"userId=" + this.$store.state.userId
          }).then(() => {

          })
        }
    })

    setTimeout(()=>this.goToNextPage(), 5000);
  },
  methods:{
    getIpAddress(){
      return fetch('https://api.ipify.org?format=json')
      .then(x => x.json())
      .then(({ ip }) => {
          console.log(Sha256(ip));
          this.$store.commit("setIpHash",Sha256(ip));
      });
    },
    goToNextPage() {

      if(this.$store.state.userId > 0)
        this.$router.push({name:"SeniorMain"});
      else
        this.$router.push({name:"SeniorLogin"});
    }
  }
};
</script>
