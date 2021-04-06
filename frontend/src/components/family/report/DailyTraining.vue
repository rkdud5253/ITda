<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 오늘일기장 컴포넌트 -->
    <div>
      <h1 style="color: #FC5355">오늘의 체조</h1>
      <h4>오늘 제공된 체조의 평균 정확도와 해당 영상 링크를 확인할 수 있습니다</h4>
      <h4>슬라이드를 넘겨서 부정확한 동작들을 확인하고 어르신의 건강상태를 체크해 보세요</h4>
    </div>

    <v-card
      class="mx-5 my-5"
      elevation="5"
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
                <h1 style="color: #FC5355; font-size: xxx-large;">{{this.exerciseAccuracy}}<br>
                {{this.accuracyAverage}}%</h1>
                <link rel="stylesheet" href="">
                <v-btn
                  class="mt-5"
                  color="#FC5355"
                  dark
                  small
                  @click="goTrainingLink"
                >
                  오늘의 체조 보러가기
                </v-btn>
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
        <v-carousel-item
          v-for="(item,i) in myExercise"
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
  props: ['year', 'month', 'day', 'dailyReport', 'exercise'],
  data () {
      return {
        items: [],
        trainingLink: [],
        exerciseUrl: '',
        exerciseAccuracy: [],
        accuracyAverage: '',
      }
  },
  computed: {
    myDailyReport: function() {
      return this.dailyReport
    },
    myExercise: function() {
      return this.exercise
    },
  },
  watch: {
    myExercise: function() {
      this.getExerciseUrl();
    },
    myDailyReport: function() {
      this.myDailyReport;
      this.getExerciseAccuracy();
    },
  },
  methods: {
    goTrainingLink: function() {
      window.open(this.exerciseUrl)
      // 백에서 매일 체조링크 가져와서 보여주기
    },
    getExerciseUrl: function() {
      axios
        .get(`/exercise`, {
          params: {
            exerciseId: this.myDailyReport.exerciseId,
          }
        })
        .then((response) => {
          this.exerciseUrl = response.data.exerciseUrl;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getExerciseAccuracy: function() {
      this.exerciseAccuracy = this.myDailyReport.exerciseAccuracy.split('/')
      let sum = 0;
      for (let index = 0; index < this.exerciseAccuracy.length; index++) {
        sum += Number(this.exerciseAccuracy[index]);
      }
      this.accuracyAverage = sum / this.exerciseAccuracy.length;
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