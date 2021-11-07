import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Feed from "@/views/social/Feed";
import RoleRequestsView from "@/views/social/RoleRequestsView";
import LoginAdminView from "@/views/social/LoginAdminView";
import RoleRequestViewAdmin from "@/views/social/RoleRequestsViewAdmin";
import SportView from "@/views/sport/SportView";
import TrainingView from "@/components/sport/training/TrainingView";
import ExerciseView from "@/components/sport/exercise/ExerciseView";
import TrainingPlansView from "@/components/sport/trainingPlan/TrainingPlansView";
import ProfileView from "@/views/social/profile/ProfileView";
import ProfileEdit from "@/views/social/profile/ProfileEdit";

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
  {
    path: '/role-requests',
    name: 'RoleRequestsView',
    component: RoleRequestsView
  },
  {
    path: '/admin',
    name: 'LoginAdminView',
    meta: { layout: "clean"},
    component: LoginAdminView
  },
  {
    path: '/admin/role-requests',
    name: 'RoleRequestsViewAdmin',
    meta: { layout: "admin"},
    component: RoleRequestViewAdmin
  },
  {
    path: '/profile/:profileId',
    name: 'ProfileView',
    component: ProfileView
  },
  {
    path: '/profile/my',
    name: 'MyProfileView',
    component: ProfileView,
  },
  {
    path: '/profile/edit',
    name: 'ProfileEdit',
    component: ProfileEdit
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
