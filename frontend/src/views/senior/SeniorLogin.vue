<template>
  <div class="seniorLogin">
    <TitleBox title="잇다에 입장하시려면 성함을 말씀해주세요." />
    <div class="nameBox">
      <div>
        <p class="seniorName">김싸피</p>
        <hr class="line">
      </div>
      <p class="explain">님</p>
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/seniorLogin.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
export default {
  name: "SeniorLogin",
  components: {
    TitleBox,
  },
}
</script>
