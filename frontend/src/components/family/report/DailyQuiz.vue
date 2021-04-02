<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 가족오락관 컴포넌트 -->
    <div>
      <h1 style="color: #597ED2">가족 오락관</h1>
      <h4>5개의 오늘의 문제 정답 여부를 확인할 수 있어요</h4>
      <h4>오늘은 어떤 문제를 푸셨는지 확인해 보세요</h4>
    </div>

    <v-card
      class="mx-5 my-5"
      elevation="5"
    >
      <v-data-table
        :headers="headers"
        :items="question"
        hide-default-footer
      ></v-data-table>
    </v-card>

  </v-container>
</template>

<script>

export default {
  name: "DailyQuiz",
  components: {

  },
  props: ['year', 'month', 'day'],
  data () {
      return {
        headers: [
          { text: 'No',
            align: 'start',
            value: 'No' 
          },
          {
            text: '문제',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: '정답여부',
            align: 'center',
            sortable: false,
            value: 'passNonpass',
          },
        ],
        question: [
          {
            No: 1,
            name: '작년 9월에 우리 가족이 갔던 바다 이름은?',
            passNonpass: 'O',
          },
          {
            No: 2,
            name: '이 사람의 이름은?',
            passNonpass: 'O',
          },
          {
            No: 3,
            name: '수지가 가장 좋아하는 색깔을 맞춰보세요~!',
            passNonpass: 'X',
          },
          {
            No: 4,
            name: '올해 지은이 나이가 몇 살일까요??',
            passNonpass: 'O',
          },
          {
            No: 5,
            name: '제가 얼만큼 사랑하는지 맞춰보세요>_<',
            passNonpass: 'O',
          },
        ],
      }
  },
}
</script>

<style>

</style>