<template>
  <div class="seniorMain">
      <TitleBox title="김싸피님, 나리를 불러서 원하는 기능을 실행하세요!"></TitleBox>
      <MenuBox 
        mainImage="@/assets/senior/SeniorMain1.jpg"
        menuButton="오늘의 체조"
      />
  </div>
</template>

<script>
import TitleBox from '@/components/senior/TitleBox.vue';
import MenuBox from '@/components/senior/MenuBox.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    MenuBox,
  },
}
</script>
