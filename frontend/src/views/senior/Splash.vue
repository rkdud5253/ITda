<template>
  <div class="splash">
    <img class="logo"
    src="@/assets/ITdaLogo.png"
    >
    <p>홀로 어르신과 가족을 잇는 다리</p>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import "@/components/css/senior/splash.scss";
import Sha256 from "@/lib/sha256.js"

export default {
  name: "Splash",
  created() {
    this.connect();
  },
  mounted() {
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
      send(msg){
        this.StompClient.send("/socket/" + this.$store.state.ipHash + "/receive", JSON.stringify(msg), {});
      },
      connect() {
        const serverURL = "http://j4a404.p.ssafy.io:8000/itda/vuejs";
        
        let Socket = new SockJS(serverURL);
        this.StompClient = Stomp.over(Socket);
        this.StompClient.debug = () => {};
        this.StompClient.connect(
            {},
            (frame) => {
                // 소켓 연결 성공
                this.connected = true;
                frame;
                
                this.getIpAddress();
                this.StompClient.subscribe("/socket/" + this.$store.state.ipHash + "/send",
                  () => {
                  }
              );
            },
            (error) => {
              // 소켓 연결 실패
              console.log("소켓 연결 실패", error);
              this.connected = false;
            }
        );
    },
    goToNextPage() {
      
      this.send({ sttMessage: this.$store.state.userId })

      if(this.$store.state.userId > 0)
        this.$router.push({name:"SeniorMain"});
      else
        this.$router.push({name:"SeniorLogin"});
    }
  }
};
</script>
