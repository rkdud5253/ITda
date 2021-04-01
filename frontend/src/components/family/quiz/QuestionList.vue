<template>
  <v-card class="mx-2 mt-1 mb-5">
    <!-- 내가 낸 문제 리스트 컴포넌트 -->
    <v-card-title>
      문제 목록
      <v-spacer></v-spacer>
      <v-text-field
      v-model="search"
      append-icon="mdi-magnify"
      label="문제 제목"
      single-line
      hide-details
      color="#597ED2"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="question"
      :search="search"
    ></v-data-table>
  </v-card>
</template>

<script>

export default {
  name: "QuestionList",
  components: {

  },
  data () {
      return {
        search: '',
        headers: [
          { 
            text: 'No',
            align: 'start',
            value: 'No' 
          },
          {
            text: '문제',
            align: 'start',
            sortable: false,
            value: 'name',
          },
        ],
        question: [
          {
            No: 1,
            name: '작년 9월에 우리 가족이 갔던 바다 이름은?',
          },
          {
            No: 2,
            name: '이 사람의 이름은?',
          },
          {
            No: 3,
            name: '현지가 가장 좋아하는 색깔을 맞춰보세요~!',
          },
          {
            No: 4,
            name: '올해 가영이 나이가 몇 살일까요??',
          },
          {
            No: 5,
            name: '제가 얼만큼 사랑하는지 맞혀보세요>_<',
          },
          {
            No: 6,
            name: '다애가 가장 좋아하는 음식은?',
          },
          {
            No: 7,
            name: '병희는 하루에 간식을 몇 번 먹을까요?',
          },
          {
            No: 8,
            name: '진표랑 같이 갔던 장소는?',
          },
          {
            No: 9,
            name: '경섭이 생일은?',
          },
          {
            No: 10,
            name: '우리 가족이 가장 최근에 여행간 날짜는?',
          },
        ],
      }
  },
}
</script>

<style>

</style>