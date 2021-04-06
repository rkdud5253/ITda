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

import axios from "@/service/axios.service.js";
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
  mounted() {
    this.$store.commit("TTS", "잇다에 오신 걸 환영합니다. 입장하시려면 성함을 말씀해주세요.");
  },
  methods:{
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
