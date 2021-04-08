<template>
  <div class="familyQuizLoading">
    <MenuLoading speech="가족들이 직접 출제한
      오늘의 문제를 풀어보세요."
    />
  </div>
</template>

<script>
import '@/components/css/senior/familyQuiz.scss';
import MenuLoading from '@/components/senior/common/MenuLoading.vue';
export default {
  name: "FamilyQuizLoading",
  components: {
    MenuLoading,
  },
  created(){
    setTimeout(()=>this.goFamilyQuiz(), 5000);
  },
  methods:{
    goFamilyQuiz(){
      this.$router.push({name: 'FamilyQuiz'})
    }
  }
}
</script>