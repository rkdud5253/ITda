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
import TitleBox from '@/components/senior/common/TitleBox.vue';
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
    if(this.$store.state.userId > 0) // 로그인되어 있을 때,
      this.$router.push({name: 'SeniorMain'});
    this.$store.commit("TTS", "잇다에 오신 걸 환영합니다. 입장하시려면 성함을 말씀해주세요.");
    this.connect();
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
    connect() {
        const serverURL = "http://localhost:8000/itda/vuejs";
        
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
              
              this.StompClient.subscribe(
                  "/socket/" + this.$store.state.ipHash + "/send",
                  (res) => {
                    console.log(res.body);

                    let name = res.body;

                    if(name != "")
                      this.login(name);
                  }
              );
            },
            (error) => {
              // 소켓 연결 실패
              console.log("소켓 연결 실패", error);
            }
        );
    },
    login(name){
      // 어르신 이름이 제대로 들어왔다면
      // AccessCheck에서 확인 후,
      // AccessCheck 제거 및 userAdmin 생성
      // this.$store.state.userId = 요거;
      // this.$store.state.adminId = 요거;
      // 그리고 SeniorMain으로 이동

      axios.get("/AccessCheck", {
        params: {
          userName:name
        }
      }).then((res) => {
        console.log(res.data);
        // 존재하면 로그인 절차를 밟고
        if(res.data.userName == name)
        {
          axios.get("/user/count", { 
          }).then((res2) => {
            const userId = res2.data + 1;

            // 유저 정보 만들고
            // userAdmin 등록
            axios.post("/user",{
              userId : userId,
              userName : name
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

            this.$store.commit("userLogin", userId);
            this.$store.commit("adminLogin", res.data.adminId);

            console.log("등록 완료");
            
            this.$router.push({name: 'SeniorMain'});
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
