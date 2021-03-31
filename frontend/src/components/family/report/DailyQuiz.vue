<template>
  <v-container class="my-5">

    <div>
      <h1 style="color: #597ED2">가족 오락관</h1>
      <h4>5개의 오늘의 문제 정답 여부를 확인할 수 있어요</h4>
      <h4>오늘은 어떤 문제를 푸셨는지 확인해 보세요</h4>
    </div>

  </v-container>
</template>

<script>

export default {
  name: "DailyQuiz",
  components: {

  },
  props: ['year', 'month', 'day'],
}
</script>

<style>

</style>