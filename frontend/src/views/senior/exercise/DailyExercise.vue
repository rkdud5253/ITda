<template>
  <div class="dailyExercise">
    <div class="wrap">
      <TitleBox title="오늘의 체조 시간입니다." />
      <div class="videos">
        <div class="video1">오늘의 체조 동작 사진</div>
        <div class="video2">어르신 실시간 영상</div>
      </div>
      <div class="explainBox">
        <div class="explainLine">
          <p class="explain">왼쪽 사진의 동작을 따라해보세요!</p>
          <hr class="line">
        </div>
      </div>
    </div>
    <GoToMainBlue />
  </div>
</template>

<script>
import '@/components/css/senior/dailyExercise.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import GoToMainBlue from '@/components/senior/common/GoToMainBlue.vue';
export default {
  name: "DailyExercise",
  components: {
    TitleBox,
    GoToMainBlue,
  },
  mounted() {
    this.$store.commit("TTS", "잠시 후 오늘의 체조를 시작합니다. 왼쪽 사진의 동작에 집중하며 체조를 따라해보세요.");
  },
}
</script>