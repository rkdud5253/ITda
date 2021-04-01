import Vue from 'vue'
import VueRouter from 'vue-router'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import Main from '@/views/family/Main.vue'
import BogoItdaMonth from '@/views/family/BogoItdaMonth.vue'
import BogoItdaDaily from '@/views/family/BogoItdaDaily.vue'
import QuizItdaList from '@/views/family/QuizItdaList.vue'
import QuizItdaMakeQuestion from '@/views/family/QuizItdaMakeQuestion.vue'
import QuizItdaQuestion from '@/views/family/QuizItdaQuestion.vue'

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
