<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 사진일기장 컴포넌트 -->
    <div>
      <h1 style="color: #FEA601">사진 일기장</h1>
      <h4>매일 기록되는 웃음 가득한 사진을 확인해 보세요😊</h4>
    </div>

    <!-- 사진 일기장 사용하지 않았을 때 -->
    <v-card
      class="mx-5 my-5"
      flat
      style="text-align: center;"
      v-if="myImage.fileUrl == undefined"
    >
      <h2 class="px-5 py-10">오늘 어르신께서 찍은 사진이 없어요😯</h2>
    </v-card>
    <!-- 사진 일기장 사용하셨을 때 -->
    <v-card
      class="mx-5 my-5"
      elevation="5"
      v-if="myImage != undefined"
    >
      <v-img
        :src="myImage.fileUrl"
      >
      </v-img>
    </v-card>

  </v-container>
</template>

<script>

export default {
  name: "DailyPhoto",
  components: {

  },
  props: ['year', 'month', 'day', 'image'],
  computed: {
    myImage: function() {
      return this.image
    },
  },
  created() {
  },
  mounted() {
  },
  methods: {
   
  },
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