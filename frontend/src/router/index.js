import Vue from 'vue'
import VueRouter from 'vue-router'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import Main from '../views/family/Main.vue'
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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
