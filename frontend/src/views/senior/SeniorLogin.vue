<template>
  <div class="seniorLogin">
    <TitleBox title="잇다에 입장하시려면 성함을 말씀해주세요." />
    <div class="nameBox">
      <div>
        <!-- username은 STT로 받아와야 합니다. -->
        <p class="seniorName">{{ seniorName }}</p>
        <hr class="line">
      </div>
      <p class="explain">님</p>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from "@/service/axios.service.js";
import Sha256 from "@/lib/sha256.js"
import '@/components/css/senior/seniorLogin.scss';
import TitleBox from '@/components/senior/TitleBox.vue';

export default {
  name: "SeniorLogin",
  components: {
    TitleBox,
  },
  data(){
    return{
      seniorName : ""
    }
  },
  created() {
    this.$store.commit("TTS", "잇다에 오신 걸 환영합니다. 입장하시려면 성함을 말씀해주세요.");
    this.connect();
  },
  methods:{ 
    getIpAddress(){
      return fetch('https://api.ipify.org?format=json')
      .then(x => x.json())
      .then(({ ip }) => {
          console.log(Sha256(ip));
          this.ip = Sha256(ip);
      });
    },
    connect() {
        const serverURL = "http://localhost:8000/itda/vuejs";
        
        let recordSocket = new SockJS(serverURL);
        this.recordStompClient = Stomp.over(recordSocket);
        this.recordStompClient.debug = () => {};
        this.recordStompClient.connect(
            {},
            (frame) => {
              // 소켓 연결 성공
              this.connected = true;
              frame;
              
              this.getIpAddress();
              
              this.recordStompClient.subscribe(
                  "/socket/{" + this.$store.state.ipHash + "}/send",
                  (res) => {
                    console.log(res.body);
                    // 어르신 이름이 제대로 들어왔다면
                    // AccessCheck에서 확인 후,
                    // AccessCheck 제거 및 userAdmin 생성
                    // this.$store.state.userId = 요거;
                    // this.$store.state.adminId = 요거;
                    // 그리고 SeniorMain으로 이동
                    if(res.body == "오늘의 체조")
                      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}));
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
    login(){
      axios.get("/AccessCheck", {
        params: {
          userName:this.seniorName
        }
      }).then((res) => {
        console.log(res.data);
        // 존재하면 로그인 절차를 밟고
        if(res.data.userName == this.seniorName)
        {
          axios.get("/user/count", { 
          }).then((res2) => {
            const userId = res2.data + 1;
            
            // 유저 정보 만들고
            // userAdmin 등록
            axios.post("/user",{
              userId : userId,
              userName : this.seniorName
            }).then(() => {
            }).catch(error => {
              console.log(error);
            })

            axios.post("/useradmin",{
              userId : userId,
              adminId : res.data.adminId
            }).then(() => {
            }).catch(error => {
              console.log(error);
            })

            console.log("등록 완료");
            
          }).catch(error => {
            console.log(error);
          })

        }
        
        // 존재하지 않으면 확인 부탁드린다고
        else{
          console.log("인증 절차가 존재하지 않아요~");
        }
      }).catch(error => {
        console.log(error);
      })
  
    }   
  }
}
</script>
