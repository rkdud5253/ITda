<template>
  <div class="dailyExerciseResult">
    <TitleBox title="체조가 모두 끝났습니다." />
    <img class="seniorExercise"
      src="@/assets/senior/SeniorExercise.jpg"
    >
    <p class="explain">오늘의 체조하느라 고생하셨습니다!</p>
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
}
</script>