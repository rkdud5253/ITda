<template>
  <v-app>
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
      <DailyTraining :year="year" :month="month" :day="day" />
      <v-divider class="my-10"></v-divider>
      <DailyPhoto :year="year" :month="month" :day="day" />
      <v-divider class="my-10"></v-divider>
      <DailyQuiz :year="year" :month="month" :day="day" />
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
    };
  },
  created() {
    this.arrayDate = this.$route.query.date.split('-')
    this.year = this.arrayDate[0]
    this.month = this.arrayDate[1]
    this.day = this.arrayDate[2]
  },
  methods: {
    goCalendar() {
      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}))
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