import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/family/Main.vue'
import BogoItdaMonth from '@/views/family/BogoItdaMonth.vue'
import BogoItdaDaily from '@/views/family/BogoItdaDaily.vue'
import QuizItdaList from '@/views/family/QuizItdaList.vue'
import QuizItdaMakeQuestion from '@/views/family/QuizItdaMakeQuestion.vue'
import QuizItdaQuestion from '@/views/family/QuizItdaQuestion.vue'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import SeniorLogin from "@/views/senior/SeniorLogin.vue";
import PhotoDiaryLoading from "@/views/senior/PhotoDiaryLoading.vue";
import PhotoDiary from "@/views/senior/PhotoDiary.vue";
import PhotoDiaryResult from "@/views/senior/PhotoDiaryResult.vue";
import DailyExerciseLoading from "@/views/senior/DailyExerciseLoading.vue";
import DailyExercise from "@/views/senior/DailyExercise.vue";
import DailyExerciseResult from "@/views/senior/DailyExerciseResult.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
  },
  {
    path: "/family/report",
    name: "BogoItdaMonth",
    component: BogoItdaMonth
  },
  {
    path: "/family/report/daily",
    name: "BogoItdaDaily",
    component: BogoItdaDaily
  },
  {
    path: "/family/quiz",
    name: "QuizItdaList",
    component: QuizItdaList
  },
  {
    path: "/family/quiz/make",
    name: "QuizItdaMakeQuestion",
    component: QuizItdaMakeQuestion
  },
  {
    path: "/family/quiz/id",
    name: "QuizItdaQuestion",
    component: QuizItdaQuestion
  },
  {
    path: '/senior/main',
    name: 'SeniorMain',
    component: SeniorMain
  },
  {
    path: '/senior/login',
    name: 'SeniorLogin',
    component: SeniorLogin
  },
  {
    path: '/senior/photodiary/loading',
    name: 'PhotoDiaryLoading',
    component: PhotoDiaryLoading
  },
  {
    path: '/senior/photodiary',
    name: 'PhotoDiary',
    component: PhotoDiary
  },
  {
    path: '/senior/photodiary/result',
    name: 'PhotoDiaryResult',
    component: PhotoDiaryResult
  },
  {
    path: '/senior/exercise/loading',
    name: 'DailyExerciseLoading',
    component: DailyExerciseLoading
  },
  {
    path: '/senior/exercise',
    name: 'DailyExercise',
    component: DailyExercise
  },
  {
    path: '/senior/exercise/result',
    name: 'DailyExerciseResult',
    component: DailyExerciseResult
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior() { 
    return { x: 0, y: 0 } 
  },
  routes
})

export default router
