<template>
  <div class="dailyExerciseResult">
    <TitleBox title="오늘의 체조가 모두 끝났습니다." />
    <img class="seniorExercise"
      src="@/assets/senior/SeniorExercise.jpg"
    >
    <p class="explain">몸도 마음도 한결 튼튼해지셨습니다!</p>
    <hr class="line">
  </div>
</template>

<script>
import '@/components/css/senior/dailyExerciseResult.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
  },
  mounted() {
    this.$store.commit("TTS", "오늘의 체조가 모두 끝났습니다. 몸도 마음도 한결 튼튼해지셨네요!");
  },
}
</script>