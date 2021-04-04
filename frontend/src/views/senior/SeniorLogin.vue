<template>
  <div class="seniorLogin">
    <TitleBox title="잇다에 입장하시려면 성함을 말씀해주세요." />
    <div class="nameBox">
      <div>
        <p class="seniorName">김싸피</p>
        <hr class="line">
      </div>
      <p class="explain">님</p>
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
