<template>
  <div class="photoDiaryResult">
    <TitleBox title="오늘의 사진이 잘 찍혔어요!" />
    <SeniorPhoto />
    <p class="explain">사진이 마음에 드시면 "나리야 저장"</p>
    <hr class="line1">
    <p class="explain">다시 찍으시려면 “나리야 다시”</p>
    <hr class="line2">
  </div>
</template>

<script>
import '@/components/css/senior/photoDiaryResult.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import SeniorPhoto from '@/components/senior/photo_diary/SeniorPhoto.vue'
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    SeniorPhoto,
  },
  mounted() {
    this.$store.commit("TTS", "오늘의 사진이 잘 찍혔어요!");
  },
}
</script>