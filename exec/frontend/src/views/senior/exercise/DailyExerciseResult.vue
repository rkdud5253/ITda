<template>
  <div class="dailyExerciseResult">
    <div class="wrap">      
      <TitleBox title="오늘의 체조가 모두 끝났습니다." />
      <img class="seniorExercise"
        src="@/assets/senior/SeniorExercise.jpg"
      >
      <p class="explain">몸도 마음도 한결 튼튼해지셨습니다!</p>
      <hr class="line">
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/dailyExerciseResult.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
export default {
  name: "DailyExerciseResult",
  components: {
    TitleBox,
  },
  created() {
    // this.$store.commit("TTS", "오늘의 체조가 모두 끝났습니다. 몸도 마음도 한결 튼튼해지셨네요!");
    // 5초 있다가 메인으로 가기
    // 파이썬에게 끝났다고 알리기

    setTimeout(()=>this.$router.push({name: 'SeniorMain'}), 10000);
  },
  methods:{
  }
}
</script>