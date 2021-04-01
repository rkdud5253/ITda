<template>
  <div class="photoDiaryResult">
    <TitleBox title="사진이 잘 찍혔어요!" />
    <SeniorPhoto />
    <p class="explain">다시 찍으시려면 “나리야”를 불러주세요.</p>
    <hr class="line">
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
}
</script>