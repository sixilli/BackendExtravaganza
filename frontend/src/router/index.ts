import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  //{
    //path: '/tournaments',
    //name: 'Tournament',
    //component: Tournamanet
  //},
  //{
    //path: '/profile',
    //name: 'Profile',
    //component: Profile
  //},
  //{
    //path: '/news',
    //name: 'News',
    //component: News
  //},
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
