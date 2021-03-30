import Vue from 'vue'
import VueRouter from 'vue-router'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import Main from '../views/family/Main.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
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
