<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 사진일기장 컴포넌트 -->
    <div>
      <h1 style="color: #FEA601">사진 일기장</h1>
      <h4>매일 기록되는 소중한 사진과 영상을 확인해보세요</h4>
    </div>

    <v-card
      class="mx-5 my-5"
      elevation="5"
    >
      <v-img
        :src="require('@/assets/family/daily1.jpg')"
      ></v-img>
    </v-card>
    <v-card
      class="mx-5 my-5"
      elevation="5"
    >
      <v-img
        :src="require('@/assets/family/daily1.jpg')"
      ></v-img>
    </v-card>

  </v-container>
</template>

<script>

export default {
  name: "DailyPhoto",
  components: {

  },
  props: ['year', 'month', 'day'],
}
</script>

<style>
  .v-img {
    display: block;
    width: 100vw;
    height: 56.25vw;
    max-height: 100vh;
  }
</style>