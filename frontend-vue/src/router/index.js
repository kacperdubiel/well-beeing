import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
//import DietView from "@/views/diet/DietView"

import DietViewMain from "@/views/diet/DietViewMain"
import DietCardView from "@/views/diet/DietCardView"
import DietReportsView from "@/views/diet/DietReportsView"
import DietDishBrowserView from "@/views/diet/DietDishBrowserView"
import DietCalculationsView from "@/views/diet/DietCalculationsView"
import DietSpecialistsView from "@/views/diet/DietSpecialistsView"
import DietSpecialistsSearchView from "@/views/diet/DietSpecialistsSearchView"
import DieticianViewMain from "@/views/diet/DieticianViewMain"
import UserDieticianAllConversationsView from "@/views/diet/UserDieticianAllConversationsView";
import UserDieticianConversationView from "@/views/diet/UserDieticianConversationView";

import DieticianUserAllConversationsView from "@/views/diet/DieticianUserAllConversationsView";
import DieticianUserConversationView from "@/views/diet/DieticianUserConversationView";
import DieticianDishesView from "@/views/diet/DieticianDishesView";
import DieticianUsersView from "@/views/diet/DieticianUsersView";
import DieticianLabelsView from "@/views/diet/DieticianLabelsView";
import DieticianUserProfileView from "@/views/diet/DieticianUserProfileView";

import UserMeasuresView from "@/views/telemedic/UserMeasuresView";
import UserDoctorsView from "@/views/telemedic/UserDoctorsView";
import UserDoctorConversationsView from "@/views/telemedic/UserDoctorConversationsView";
import UserDoctorConversationView from "@/views/telemedic/UserDoctorConversationView";
import AnalysisView from "@/views/telemedic/AnalysisView";
import DoctorUsersView from "@/views/telemedic/DoctorUsersView";
import DoctorUserProfileView from "@/views/telemedic/DoctorUserProfileView";
import SearchDoctorsView from "@/views/telemedic/SearchDoctorsView";
import DoctorUserConversationsView from "@/views/telemedic/DoctorUserConversationsView";
import DoctorUserConversationView from "@/views/telemedic/DoctorUserConversationView";
import FeedView from "@/views/social/feed/FeedView";
import RoleRequestsView from "@/views/social/role-requests/RoleRequestsView";
import LoginAdminView from "@/views/social/admin/LoginAdminView";
import RoleRequestViewAdmin from "@/views/social/admin/RoleRequestsViewAdmin";
import DataImportsViewAdmin from "@/views/social/admin/DataImportsViewAdmin";
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
    name: 'FeedView',
    component: FeedView
  },
  {
    path: '/diet',
    redirect: {name: 'DietCardView'},
    name: 'DietViewMain',
    component: DietViewMain,
    children: [
      {
        path: 'card',
        name: 'DietCardView',
        component: DietCardView,
      },
      {
        path: 'calculations',
        name: 'DietCalculationsView',
        component: DietCalculationsView
      },
      {
        path: 'reports',
        name: 'DietReportsView',
        component: DietReportsView
      },
      {
        path: 'dishes',
        name: 'DietDishBrowserView',
        component: DietDishBrowserView
      },
      {
        path: 'dieticians',
        name: 'DietSpecialistsView',
        component: DietSpecialistsView
      },
      {
        path: 'dieticians/search',
        name: 'DietSpecialistsSearchView',
        component: DietSpecialistsSearchView
      },
      {
        path: 'conversations/:conversationId',
        name: 'UserDieticianConversationView',
        component: UserDieticianConversationView
      },
      {
        path: 'conversations',
        name: 'UserDieticianAllConversationsView',
        component: UserDieticianAllConversationsView
      },
    ]
  },
  {
    path: '/dietician',
    name: 'DieticianViewMain',
    redirect: {name: 'DieticianUsersView'},
    component: DieticianViewMain,
    children: [
      {
        path: 'conversations/:conversationId',
        name: 'DieticianUserConversationView',
        component: DieticianUserConversationView
      },
      {
        path: 'conversations',
        name: 'DieticianUserAllConversationsView',
        component: DieticianUserAllConversationsView
      },
      {
        path: 'users',
        name: 'DieticianUsersView',
        component: DieticianUsersView
      },
      {
        path: 'dishes',
        name: 'DieticianDishesView',
        component: DieticianDishesView
      },
      {
        path: 'users/:userId',
        name: 'DieticianUserProfileView',
        component: DieticianUserProfileView
      },
      {
        path: 'labels',
        name: 'DieticianLabelsView',
        component: DieticianLabelsView
      },
    ]
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
    path: '/telemedic/userdoctors/search',
    name: 'SearchDoctorsView',
    component: SearchDoctorsView
  },
  {
    path: '/telemedic/conversations',
    name: 'UserDoctorConversationsView',
    component: UserDoctorConversationsView
  },
  {
    path: '/telemedic/conversations/:conversationId',
    name: 'UserDoctorConversationView',
    component: UserDoctorConversationView
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
  {
    path: '/doctor/conversations',
    name: 'DoctorUserConversationsView',
    component: DoctorUserConversationsView
  },
  {
    path: '/doctor/conversations/:conversationId',
    name: 'DoctorUserConversationView',
    component: DoctorUserConversationView
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
    path: '/admin/imports',
    name: 'DataImportsViewAdmin',
    meta: { layout: "admin"},
    component: DataImportsViewAdmin
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
