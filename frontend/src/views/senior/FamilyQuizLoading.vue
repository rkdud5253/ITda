<template>
  <div class="familyQuizLoading">
    <MenuLoading speech="가족들이 직접 출제한
      오늘의 문제를 풀어보세요."
    />
  </div>
</template>

<script>
import '@/components/css/senior/familyQuiz.scss';
import MenuLoading from '@/components/senior/MenuLoading.vue';
export default {
  name: "FamilyQuizLoading",
  components: {
    MenuLoading,
  },
}
</script>