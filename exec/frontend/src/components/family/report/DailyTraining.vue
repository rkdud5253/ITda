<template>
  <v-container class="my-5">
    <!-- 보고서 상세보기 오늘일기장 컴포넌트 -->
    <div>
      <h1 style="color: #FC5355">오늘의 체조</h1>
      <h4>오늘의 체조 결과를 분석해 신체 부위별 정확도와 전체 평균 정확도를 제공합니다.</h4>
      <h4>슬라이드를 넘겨서 오늘의 체조 동작을 살펴보세요.</h4>
    </div>
    
    <!-- 오늘의 체조 사용하지 않았을 때 -->
    <v-card
      class="mx-5 my-5"
      flat
      style="text-align: center;"
      v-if="!myDailyReport.exerciseAccuracy"
    >
      <h2 class="px-5 py-10">오늘 어르신께서 체조 기능을 사용하지 않으셨어요😮</h2>
    </v-card>
    <!-- 오늘의 체조 사용하셨을 때 -->
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
            <v-row>
              <v-col class="my-8" cols="12"></v-col>
              <v-col class="mt-10" style="text-align: center;" cols="12">
                <v-icon class="mb-3" color="#FEA601" size="xxx-large">mdi-trophy</v-icon>
                <h2 style="color: black">{{ year }}년 {{ month }}월 {{ day }}일</h2>
                <h1 style="color: black">평균 정확도</h1>
                <h1 style="color: #FC5355; font-size: xxx-large;">{{this.accuracyAverage}}%</h1>
              </v-col>
              <v-col cols="12">
                <!-- 정확도 표 -->
                <v-simple-table class="mx-15" light>
                  <template v-slot:default>
                    <tbody style="text-align: center;">
                      <tr>
                        <td
                          class="mx-1"
                          v-for="item in results"
                          :key="item.bodyPart"
                        >
                          {{ item.bodyPart }}
                        </td>
                      </tr>
                      <tr>
                        <td
                          class="mx-1"
                          v-for="item in results"
                          :key="item.bodyPart"
                        >
                          <v-chip
                            :color="getColor(item.accuracy)"
                            dark
                          >
                            {{ item.accuracy }}
                          </v-chip>
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-col>
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
        results: [
          {
            bodyPart: '어깨',
            accuracy: '',
          },
          {
            bodyPart: '위팔(좌)',
            accuracy: '',
          },
          {
            bodyPart: '옆구리(좌)',
            accuracy: '',
          },
          {
            bodyPart: '위팔(우)',
            accuracy: '',
          },
          {
            bodyPart: '옆구리(우)',
            accuracy: '',
          },
          {
            bodyPart: '아래팔(좌)',
            accuracy: '',
          },
          {
            bodyPart: '아래팔(우)',
            accuracy: '',
          },
          {
            bodyPart: '엉덩이',
            accuracy: '',
          },
          {
            bodyPart: '허벅지(좌)',
            accuracy: '',
          },
          {
            bodyPart: '허벅지(우)',
            accuracy: '',
          },
          {
            bodyPart: '종아리(좌)',
            accuracy: '',
          },
          {
            bodyPart: '종아리(우)',
            accuracy: '',
          },
        ]
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
    getExerciseAccuracy: function() {
      // 신체부위별 정확도 가져오기
      if (this.myDailyReport.exerciseAccuracy) {
        this.exerciseAccuracy = this.myDailyReport.exerciseAccuracy.split('/');
        let sum = 0;
        for (let index = 0; index < this.exerciseAccuracy.length; index++) {
          sum += Number(this.exerciseAccuracy[index]);
          this.results[index].accuracy = this.exerciseAccuracy[index]
        }
        this.accuracyAverage = sum / this.exerciseAccuracy.length;
        // 정확도 반올림
        this.accuracyAverage = Math.round(this.accuracyAverage)
      }
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
    // 정확도별 색상 다르게 표시 (0~39:빨강, 40~69:주황, 70~100:초록)
    getColor (accuracy) {
        if (accuracy < 40) return 'red'
        else if (accuracy < 70) return 'orange'
        else return 'green'
    },
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