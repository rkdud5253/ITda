<template>
  <div class="dailyExercise">
    <TitleBox title="오늘의 체조를 따라해보세요." />
    <div class="videos">
      <div class="video1">오늘의 체조 예시 영상</div>
      <div class="video2">어르신 실시간 영상</div>
    </div>
    <p class="explain">잠시 후 오늘의 체조를 시작합니다.</p>
    <hr class="line">
  </div>
</template>

<script>
import '@/components/css/senior/dailyExercise.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
export default {
  name: "DailyExercise",
  components: {
    TitleBox,
  },
  mounted() {
    this.$store.commit("TTS", "잠시 후 오늘의 체조를 시작합니다. 영상을 잘 보며 체조를 따라해보세요.");
  },
}
</script>