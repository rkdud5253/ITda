<template>
  <div class="photoDiary">
    <TitleBox title="왼쪽 사진의 동작을 따라해보세요!"/>
    <div class="photos">
      <ExampleImage />
      <SeniorPhoto />
    </div>
    <p class="explain">나리가 “하나, 둘, 셋!”을 외치면 사진이 찍혀요.</p>
    <hr class="line">
  </div>
</template>

<script>
import '@/components/css/senior/photoDiary.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import ExampleImage from '@/components/senior/photo_diary/ExampleImage.vue';
import SeniorPhoto from '@/components/senior/photo_diary/SeniorPhoto.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    ExampleImage,
    SeniorPhoto,
  },
  methods:{
    TTS(){
      
    }
  }
}
</script>