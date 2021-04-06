<template>
  <div class="seniorMain">
    <TitleBox :title ="username"/>
    <div class="menuContainer">
      <DailyExerciseMenu />
      <PhotoDiaryMenu />
      <FamilyGameMenu />
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/seniorMain.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import DailyExerciseMenu from '@/components/senior/main/DailyExerciseMenu.vue';
import PhotoDiaryMenu from '@/components/senior/main/PhotoDiaryMenu.vue';
import FamilyGameMenu from '@/components/senior/main/FamilyGameMenu.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    DailyExerciseMenu,
    PhotoDiaryMenu,
    FamilyGameMenu,
  },
  data(){
    return {
      username:"김싸피",
    }
  },
  methods:{
    goDailyExercise(){
      this.$router.go(this.$router.push({name: 'DailyExerciseLoading'}))
    }
  }
}
</script>