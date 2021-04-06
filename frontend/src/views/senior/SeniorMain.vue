<template>
  <div class="seniorMain">
    <TitleBox title="000님, 나리를 불러서 원하는 기능을 실행하세요!" />
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
  data () {
    return {
      username: '김싸피'
    }
  },
  mounted() {
    this.$store.commit("TTS", this.username + "님 나리를 불러서 원하는 기능을 실행하세요");
  },
  methods:{
    goDailyExercise(){
      this.$router.go(this.$router.push({name: 'DailyExerciseLoading'}))
    }
  }
}
</script>