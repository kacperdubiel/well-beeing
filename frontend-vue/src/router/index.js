import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Feed from "@/views/social/Feed";
import SocialView from "@/views/social/SocialView";
import SportView from "@/views/sport/SportView";
import TrainingView from "@/components/sport/training/TrainingView";
import ExerciseView from "@/components/sport/exercise/ExerciseView";
import TrainingPlansView from "@/components/sport/TrainingPlansView";

const routes = [
  {
    path: '/',
    name: 'Home',
    meta: { layout: "clean"},
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/feed',
    name: 'Feed',
    component: Feed
  },
  {
    path: '/social',
    name: 'SovialView',
    component: SocialView
  },
  {
    path: '/sport',
    redirect: {name: 'TrainingPlansView'},
    name: 'SportView',
    component: SportView,
    children: [
      {
        path: 'training',
        name: 'TrainingView',
        component: TrainingView,
      },
      {
        path: 'exercise',
        name: 'ExerciseView',
        component: ExerciseView
      },
      {
        path: 'training-plans',
        name: 'TrainingPlansView',
        component: TrainingPlansView
      }
    ]
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
