<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 가족오락관 컴포넌트 -->
    <div>
      <h1 style="color: #597ED2">가족 오락관</h1>
      <h4>오늘 어르신께서는 어떤 문제를 풀었을까요?</h4>
      <h4>문제별 정답 여부를 볼 수 있어요.</h4>
    </div>

    <!-- 가족 오락관 사용하지 않았을 때 -->
    <v-card
      class="mx-5 my-5"
      flat
      style="text-align: center;"
      v-if="!myDailyReport.question1Id"
    >
      <h2 class="px-5 py-10">오늘 어르신께서 푸신 문제가 없어요😲</h2>
    </v-card>
    <!-- 가족 오락관 사용하셨을 때 -->
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
        qId: [],
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
      this.getQuestionContent();
    },
  },
  methods: {
    // 문제 정보 가져오기
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
          for (let idx = 0; idx < response.data.length; idx++) {
            this.questions[idx].name = response.data[idx].questionContent;
            this.qId[idx] = response.data[idx].questionId;
          }
          this.getQuestionResult();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getQuestionResult: function() {
      if (this.myDailyReport.question1Id) {
        this.arrayWrong = this.myDailyReport.wrongNumbers.split('/')
        this.arrayRight = this.myDailyReport.rightNumbers.split('/')
        for (let i = 1; i < this.arrayWrong.length; i++) {
          let ei = this.arrayWrong[i];
          this.setWrongResult(this.arrayWrong[i]);
          for (let j = 0; j < this.questions.length; j++) {
            if(ei == this.qId[j]){
              this.questions[j].passNonpass = 'X';
            }
          }
        }
        for (let i = 1; i < this.arrayRight.length; i++) {
          let ei = this.arrayRight[i];
          this.setRightResult(this.arrayRight[i]);
          for (let j = 0; j < this.questions.length; j++) {
            if(ei == this.qId[j]){
              this.questions[j].passNonpass = 'O';
            }
          }
        }
      }
    },
    goQuizDetail: function(idx) {
      if(this.qId[idx.No-1] == null){
        alert("삭제된 문제입니다.");
      } else{
        this.$router.push({path: `/family/quiz/detail/${this.qId[idx.No-1]}`})
      }
    },
    setWrongResult: function(wrongQid) {
      axios
        .get(`/wrong`, {
           params: {
              questionId: wrongQid,
              userId: this.$store.state.userId,
           }
        })
        .then((response) => {
          if(response.data == ''){
            this.postWrongQid(wrongQid); 
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    postWrongQid: function(wrongQid) {
      axios
        .post(`/wrong`, {
            questionId: wrongQid,
            userId: this.$store.state.userId,
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setRightResult: function(rightQid) {
      axios
        .get(`/wrong`, {
           params: {
              questionId: rightQid,
              userId: this.$store.state.userId,
           }
        })
        .then((response) => {
          if(response.data != ''){
            this.deleteWrongQid(rightQid);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteWrongQid: function(rightQid) {
      axios
        .delete(`/wrong`, {
          params: {
            questionId: rightQid,
            userId: this.$store.state.userId,
          }
        })
        .then((response) => {
          console.log(response.data)
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
}
</script>

<style>

</style>