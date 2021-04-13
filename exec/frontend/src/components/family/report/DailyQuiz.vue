<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 가족오락관 컴포넌트 -->
    <div>
      <h1 style="color: #597ED2">가족 오락관</h1>
      <h4>오늘 어르신께서는 어떤 문제를 풀었을까요?</h4>
      <h4>문제별 정답 여부를 볼 수 있어요.</h4>
    </div>

    <v-card
      class="mx-5 my-5"
      flat
      style="text-align: center;"
      v-if="!myDailyReport.question1Id"
    >
      <h2 class="px-5 py-10">오늘 어르신께서 푸신 문제가 없어요😲</h2>
    </v-card>
    <v-card
      class="mx-5 my-5"
      elevation="5"
      v-if="myDailyReport.question1Id"
    >
      <v-data-table
        :headers="headers"
        :items="questions"
        hide-default-footer
        @click:row="goQuizDetail"
      ></v-data-table>
    </v-card>
  </v-container>
</template>

<script>
import axios from "@/service/axios.service.js";

export default {
  name: "DailyQuiz",
  components: {

  },
  props: ['year', 'month', 'day', 'dailyReport'],
  data () {
      return {
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
          { 
            text: '정답여부',
            align: 'center',
            sortable: false,
            value: 'passNonpass',
          },
        ],
        questions: [
          {
            No: 1,
            name: '',
            passNonpass: '',
          },
          {
            No: 2,
            name: '',
            passNonpass: '',
          },
          {
            No: 3,
            name: '',
            passNonpass: '',
          },
          {
            No: 4,
            name: '',
            passNonpass: '',
          },
          {
            No: 5,
            name: '',
            passNonpass: '',
          },
        ],
        arrayWrong: [],
        arrayRight: [],
      }
  },
   computed: {
    myDailyReport: function() {
      return this.dailyReport
    },
  },
  watch: {
    myDailyReport: function() {
      this.myDailyReport;
      this.getQuestionId();
      this.getQuestionContent();
      this.getQuestionResult();
    },
  },
  methods: {
    getQuestionContent: function() {
      axios
        .get(`/qna/result`, {
          params: {
            question1Id: this.myDailyReport.question1Id,
            question2Id: this.myDailyReport.question2Id,
            question3Id: this.myDailyReport.question3Id,
            question4Id: this.myDailyReport.question4Id,
            question5Id: this.myDailyReport.question5Id,
          }
        })
        .then((response) => {
          this.questions[0].name = response.data[0].questionContent;
          this.questions[1].name = response.data[1].questionContent;
          this.questions[2].name = response.data[2].questionContent;
          this.questions[3].name = response.data[3].questionContent;
          this.questions[4].name = response.data[4].questionContent;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getQuestionResult: function() {
      if (this.myDailyReport.question1Id) {
        this.arrayWrong = this.myDailyReport.wrongNumbers.split('/')
        this.arrayRight = this.myDailyReport.rightNumbers.split('/')
        for (let i = 0; i < this.arrayWrong.length; i++) {
          let ei = this.arrayWrong[i];
          for (let j = 0; j < this.questions.length; j++) {
            if(ei == this.questions[j].id){
              this.questions[j].passNonpass = 'X';
            }
          }
        }
  
        for (let i = 0; i < this.arrayRight.length; i++) {
          let ei = this.arrayRight[i];
          for (let j = 0; j < this.questions.length; j++) {
            if(ei == this.questions[j].id){
              this.questions[j].passNonpass = 'O';
            }
          }
        }
      }
    },
    getQuestionId: function() {
      this.questions[0].id = this.myDailyReport.question1Id;
      this.questions[1].id = this.myDailyReport.question2Id;
      this.questions[2].id = this.myDailyReport.question3Id;
      this.questions[3].id = this.myDailyReport.question4Id;
      this.questions[4].id = this.myDailyReport.question5Id;
    },
    goQuizDetail: function(idx) {
      this.$router.push({path: `/family/quiz/detail/${idx.id}`})
    },
  },
}
</script>

<style>

</style>