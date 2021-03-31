<template>
  <v-container class="my-5">
    
    <div>
      <h1 style="color: #FEA601">사진 일기장</h1>
      <h4>매일 기록되는 소중한 사진과 영상을 확인해보세요</h4>
    </div>

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

</style>