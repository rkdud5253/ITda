<template>
  <div class="familyQuizResult">
    <TitleBox title="오늘의 뇌 운동이 모두 끝났습니다." />
    <img class="seniorExercise"
      src="@/assets/senior/SeniorQuiz.jpg"
    >
    <p class="explain">참 잘하셨습니다!</p>
    <hr class="line">
  </div>
</template>

<script>
import '@/components/css/senior/familyQuizResult.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
  },
}
</script>