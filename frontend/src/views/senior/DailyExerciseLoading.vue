<template>
  <div class="photoDiaryLoading">
    <MenuLoading speech="몸도 튼튼 마음도 튼튼
      오늘의 체조를 따라해볼까요?"/>
  </div>
</template>

<script>
import MenuLoading from '@/components/senior/MenuLoading.vue';
export default {
  name: "PhotoDiaryLoading",
  components: {
    MenuLoading,
  },
  mounted(){
    setInterval("goDailyExercise", 10000);
  },
  methods:{
    goDailyExercise(){
      this.$router.go(this.$router.push({name: 'DailyExercise'}))
    }
  }
}
</script>