<template>
  <div class="dailyExerciseLoading">
    <MenuLoading speech="몸도 튼튼 마음도 튼튼
      오늘의 체조를 따라해볼까요?"/>
  </div>
</template>

<script>
import '@/components/css/senior/dailyExercise.scss';
import MenuLoading from '@/components/senior/MenuLoading.vue';
export default {
  name: "DailyExerciseLoading",
  components: {
    MenuLoading,
  },
}
</script>