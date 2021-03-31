import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/family/Main.vue'
import SeniorMain from "@/views/senior/SeniorMain.vue";
import PhotoDiary from "@/views/senior/PhotoDiary.vue";

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
  {
    path: '/senior/photodiary',
    name: 'PhotoDiary',
    component: PhotoDiary
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
