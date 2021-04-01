import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/family/Main.vue'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import SeniorLogin from "@/views/senior/SeniorLogin.vue";
import PhotoDiaryLoading from "@/views/senior/PhotoDiaryLoading.vue";
import PhotoDiary from "@/views/senior/PhotoDiary.vue";
import PhotoDiaryResult from "@/views/senior/PhotoDiaryResult.vue";
import BogoItdaMonth from '../views/family/BogoItdaMonth.vue'
import BogoItdaDaily from '../views/family/BogoItdaDaily.vue'

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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
