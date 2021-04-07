<template>
  <div class="seniorMain">
    <div class="wrap">
      <TitleBox :title="username + ment" />
      <div class="menuContainer">
        <DailyExerciseMenu />
        <PhotoDiaryMenu />
        <FamilyGameMenu />
      </div>
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/seniorMain.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
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
      username: '김싸피',
      ment: '님, 나리를 불러서 원하는 기능을 실행하세요!'
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