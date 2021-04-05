<template>
  <v-app>
    <!-- 보고잇다 상세 페이지 -->
    <FamilyAppBar />
    <v-container>
      <v-row class="mx-2 my-5">
        <v-col
          align-self="center"
          cols="0"
          sm="4"
        >
          <v-spacer></v-spacer>
        </v-col>
        <v-col
          align-self="center"
          cols="6"
          sm="4"
        >
          <h1 class="h1" style="text-align: center;">{{ this.year }}년 {{ this.month }}월 {{ this.day }}일</h1>
        </v-col>
        <v-col
          align-self="center"
          style="text-align: end;"
          cols="6"
          sm="4"
        >
          <v-btn
            color="#FEA601"
            dark
            @click="goCalendar"
          >
            달력보기
          </v-btn>
        </v-col>
      </v-row>
      <v-divider class="my-10"></v-divider>
      <DailyTraining :year="year" :month="month" :day="day" :dailyReport="dailyReport" :exercise="exercise"/>
      <v-divider class="my-10"></v-divider>
      <DailyPhoto :year="year" :month="month" :day="day" :image="image" :video="video"/>
      <v-divider class="my-10"></v-divider>
      <DailyQuiz :year="year" :month="month" :day="day" :dailyReport="dailyReport"/>
    </v-container>
    <div class="mb-5">
      <TopButton />
    </div>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import DailyTraining from '@/components/family/report/DailyTraining.vue'
import DailyPhoto from '@/components/family/report/DailyPhoto.vue'
import DailyQuiz from '@/components/family/report/DailyQuiz.vue'
import TopButton from '@/components/family/TopButton.vue'
import axios from 'axios'

export default {
  name: "BogoItdaDaily",
  components: {
    FamilyAppBar,
    DailyTraining,
    DailyPhoto,
    DailyQuiz,
    TopButton,
  },
  data: function() {
    return {
      arrayDate: '',
      year: '',
      month: '',
      day: '',
      dailyReport: '',
      image: [],
      video: [],
      exercise: [],
    };
  },
  created() {
    this.arrayDate = this.$route.query.date.split('-')
    console.log(this.arrayDate)
    this.year = this.arrayDate[0]
    this.month = this.arrayDate[1]
    this.day = this.arrayDate[2]
    this.getDailyReport()
    this.getImage()
    this.getVideo()
    this.getExercise()
  },
  methods: {
    goCalendar() {
      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}))
    },
    getDailyReport() {
      axios
        .get(`http://localhost:8000/itda/report/` + this.$route.query.date, {
          params: {
            userId : '1',
          }
        })
        .then((response) => {
          this.dailyReport = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getImage() {
      axios
        .get(`http://localhost:8000/itda/files/image`, {
          params: {
            userId : '1',
            fileDate : this.$route.query.date,
          }
        })
        .then((response) => {
          this.image = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getVideo() {
      axios
        .get(`http://localhost:8000/itda/files/video`, {
          params: {
            userId : '1',
            fileDate : this.$route.query.date,
          }
        })
        .then((response) => {
          this.video = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getExercise() {
      axios
        .get(`http://localhost:8000/itda/files/exercise`, {
          params: {
            userId : '1',
            fileDate : this.$route.query.date,
          }
        })
        .then((response) => {
          this.exercise = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
}
</script>

<style>
  @media (max-width: 960px) {
    .h1 {
      font-size: 1.1rem;
    }
  }
</style>