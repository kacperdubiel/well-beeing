import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Feed from "@/views/social/Feed";
import SocialView from "@/views/social/SocialView";
import UserMeasuresView from "@/views/telemedic/UserMeasuresView";
import UserDoctorsView from "@/views/telemedic/UserDoctorsView";
import UserCommunicatorView from "@/views/telemedic/UserCommunicatorView";
import AnalysisView from "@/views/telemedic/AnalysisView";
import DoctorUsersView from "@/views/telemedic/DoctorUsersView";
import DoctorUserProfileView from "@/views/telemedic/DoctorUserProfileView";

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
    path: '/telemedic/measures',
    name: 'UserMeasuresView',
    component: UserMeasuresView
  },
  {
    path: '/telemedic/userdoctors',
    name: 'UserDoctorsView',
    component: UserDoctorsView
  },
  {
    path: '/telemedic/usercommunicator',
    name: 'UserCommunicatorView',
    component: UserCommunicatorView
  },
  {
    path: '/telemedic/analysis',
    name: 'AnalysisView',
    component: AnalysisView
  },
  {
    path: '/doctor/users',
    name: 'DoctorUsersView',
    component: DoctorUsersView
  },
  {
    path: '/doctor/users/:userId',
    name: 'DoctorUserProfileView',
    component: DoctorUserProfileView
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
