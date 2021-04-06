<template>
  <div class="seniorLogin">
    <TitleBox title="잇다에 입장하시려면 성함을 말씀해주세요." />
    <div class="nameBox">
      <div>
        <!-- username은 STT로 받아와야 합니다. -->
        <p class="seniorName">{{ username }}</p>
        <hr class="line">
      </div>
      <p class="explain">님</p>
      <!-- STT로 로그인 기능 구현 이후에 아래 버튼은 지워주세요. -->
      <button @click="login"></button>
    </div>
  </div>
</template>

<script>

import axios from 'axios';
import '@/components/css/senior/seniorLogin.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
export default {
  name: "SeniorLogin",
  components: {
    TitleBox,
  },
  data () {
    return {
      username: '김싸피'
    }
  },
  mounted() {
    this.$store.commit("TTS", "잇다에 오신 걸 환영합니다. 입장하시려면 성함을 말씀해주세요.");
  },
  methods:{
    login(){
      const id = 1;
      axios.get("/itda/user", {
        params: {
          userId:id
      },}).then((res) => {
        console.log(res.data);
    })
  
    }   
  }
}
</script>
