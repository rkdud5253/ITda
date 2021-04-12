<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 오늘일기장 컴포넌트 -->
    <div>
      <h1 style="color: #FC5355">오늘의 체조</h1>
      <h4>오늘의 체조 결과를 분석해 신체 부위별 정확도와 전체 평균 정확도를 제공합니다.</h4>
      <h4>슬라이드를 넘겨서 오늘의 체조 동작을 살펴보세요.</h4>
    </div>

    <v-card
      class="mx-5 my-5"
      elevation="5"
      v-if="myDailyReport.exerciseAccuracy" 
    >
      <v-carousel
        cycle
        height="600"
        hide-delimiter-background
        show-arrows-on-hover
        interval="5000"
      >
        <v-carousel-item>
          <v-sheet
            height="105%"
            color="#FFFFFF"
          >
            <v-row
              class="fill-height"
              align="center"
              justify="center"
            >
              <div style="color: black; text-align: center;">
                <v-icon class="mb-3" color="#FEA601" size="xxx-large">mdi-trophy</v-icon>
                <h2>{{ year }}년 {{ month }}월 {{ day }}일</h2>
                <h1>평균 정확도</h1>
                <h1 style="color: #FC5355; font-size: xxx-large;">{{this.accuracyAverage}}%</h1>
                <h5 style="color: #FC5355;">[12가지의 신체 부위별 평균 정확도]</h5>
                <h5 style="color: #FC5355;">{{this.exerciseAccuracy}}</h5>
                <link rel="stylesheet" href="">
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
        <v-carousel-item
          v-for="(item,i) in exercises"
          :key="i"
          :src="item.fileUrl"
        ></v-carousel-item>
      </v-carousel>
    </v-card>

  </v-container>
</template>

<script>
import axios from "@/service/axios.service.js";

export default {
  name: "DailyTraining",
  components: {

  },
  props: ['year', 'month', 'day', 'dailyReport'],
  data () {
      return {
        exerciseAccuracy: [],
        accuracyAverage: '',
        exercises: [],
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
      this.getExerciseAccuracy();
      this.getExercise();
    },
  },
  methods: {
    goTrainingLink: function() {
      window.open(this.exerciseUrl)
      // 백에서 매일 체조링크 가져와서 보여주기
    },
    getExerciseAccuracy: function() {
      this.exerciseAccuracy = this.myDailyReport.exerciseAccuracy.split('/')
      let sum = 0;
      for (let index = 0; index < this.exerciseAccuracy.length; index++) {
        sum += Number(this.exerciseAccuracy[index]);
      }
      this.accuracyAverage = sum / this.exerciseAccuracy.length;
    },
    getExercise() { 
      axios
        .get(`/files/exercise`, {
          params: {
            fileDate : this.$route.query.date,
          }
        })
        .then((response) => {
          this.exercises = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
}
</script>

<style>
  /* .v-window__container {
    position: relative;
    width: 100%;
    height: 0;
    overflow: hidden;
    padding-bottom: 56.26%;
  } */
  .v-img {
    display: block;
    width: 100vw;
    height: 56.25vw;
    max-height: 100vh;
  }
</style>