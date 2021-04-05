<template>
  <div class="photoDiary">
    <TitleBox title="왼쪽 사진의 동작을 따라한 뒤 웃어주세요!"/>
    <div class="photos">
      <ExampleImage />
      <SeniorPhoto />
    </div>
    <p class="explain">{{ items[i] }}</p>
    <!-- <p v-for="(ment, i) in items"
      :key="i"
      class="explain"
    >{{ ment }}</p> -->
    <hr class="line">
    <Loader />
  </div>
</template>

<script>
import '@/components/css/senior/photoDiary.scss';
import Loader from '@/components/senior/Loader.vue';
import TitleBox from '@/components/senior/TitleBox.vue';
import ExampleImage from '@/components/senior/photo_diary/ExampleImage.vue';
import SeniorPhoto from '@/components/senior/photo_diary/SeniorPhoto.vue';
export default {
  name: "SeniorMain",
  components: {
    Loader,
    TitleBox,
    ExampleImage,
    SeniorPhoto,
  },
  data() {
    return {
      i: 0,
      items: [
      "활짝 웃으면 사진이 찍혀요😄",
      "카메라를 보고 웃어주세요😁"
      ]
    }
  },
  methods: {

  }
}
</script>