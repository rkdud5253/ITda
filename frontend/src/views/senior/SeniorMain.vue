<template>
  <div class="seniorMain">
    <TitleBox title="김싸피님, 나리를 불러서 원하는 기능을 실행하세요!"></TitleBox>
    <div class="menuContainer">
      <DailyExercise />
      <PhotoDiary />
      <FamilyGame />
    </div>
  </div>
</template>

<script>
import "@/components/css/senior/seniorMain.scss";
import TitleBox from '@/components/senior/TitleBox.vue';
import DailyExercise from '@/components/senior/DailyExercise.vue';
import PhotoDiary from '@/components/senior/photo_Diary.vue';
import FamilyGame from '@/components/senior/family_Game.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    DailyExercise,
    PhotoDiary,
    FamilyGame,
  },
}
</script>
