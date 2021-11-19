import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import DietView from "@/views/diet/DietView"
import UserMeasuresView from "@/views/telemedic/user/UserMeasuresView";
import UserDoctorsView from "@/views/telemedic/user/UserDoctorsView";
import UserDoctorConversationsView from "@/views/telemedic/user/UserDoctorConversationsView";
import UserDoctorConversationView from "@/views/telemedic/user/UserDoctorConversationView";
import AnalysisView from "@/views/telemedic/user/AnalysisView";
import DoctorUsersView from "@/views/telemedic/doctor/DoctorUsersView";
import DoctorUserProfileView from "@/views/telemedic/doctor/DoctorUserProfileView";
import SearchDoctorsView from "@/views/telemedic/user/SearchDoctorsView";
import DoctorUserConversationsView from "@/views/telemedic/doctor/DoctorUserConversationsView";
import DoctorUserConversationView from "@/views/telemedic/doctor/DoctorUserConversationView";
import DoctorMeasureTypesView from "@/views/telemedic/doctor/DoctorMeasureTypesView";
import FeedView from "@/views/social/feed/FeedView";
import RoleRequestsView from "@/views/social/role-requests/RoleRequestsView";
import LoginAdminView from "@/views/social/admin/LoginAdminView";
import RoleRequestViewAdmin from "@/views/social/admin/RoleRequestsViewAdmin";
import SportView from "@/views/sport/SportView";
import TrainingView from "@/components/sport/training/TrainingView";
import ExerciseView from "@/components/sport/exercise/ExerciseView";
import TrainingPlansView from "@/components/sport/trainingPlan/TrainingPlansView";
import ProfileView from "@/views/social/profile/ProfileView";
import ProfileEdit from "@/views/social/profile/ProfileEdit";
import SportReportsComponent from "@/components/sport/SportReportsComponent";
import TrainersView from "@/views/sport/trainers/TrainersView";
import UserTrainerConversationView from "@/views/sport/trainers/UserTrainerConversationView";
import UserTrainerConversationsView from "@/views/sport/trainers/UserTrainerConversationsView";
import SearchTrainersView from "@/views/sport/trainers/SearchTrainersView";
import UserTrainersView from "@/views/sport/trainers/UserTrainersView";
import TrainerUsersView from "@/views/sport/trainers/TrainerUsersView";
import TrainerUserProfileView from "@/views/sport/trainers/TrainerUserProfileView";
import TrainerUserConversationsView from "@/views/sport/trainers/TrainerUserConversationsView";
import TrainerUserConversationView from "@/views/sport/trainers/TrainerUserConversationView";
import SportTrainerView from "@/views/sport/SportTrainerView";
import TrainingPlanRequest from "@/views/sport/TrainingPlanRequest";

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
    name: 'DietView',
    component: DietView
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
    path: '/doctor/measure-types',
    name: 'DoctorMeasureTypesView',
    component: DoctorMeasureTypesView
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
      },
      {
        path: 'reports',
        name: 'SportReportsComponent',
        component: SportReportsComponent
      },
      {
        path: 'user-trainers',
        name: 'UserTrainersView',
        component: UserTrainersView
      },
      {
        path: 'user-trainers/search',
        name: 'SearchTrainersView',
        component: SearchTrainersView
      },
      {
        path: 'conversations',
        name: 'UserTrainerConversationsView',
        component: UserTrainerConversationsView
      },
      {
        path: 'conversations/:conversationId',
        name: 'UserTrainerConversationView',
        component: UserTrainerConversationView
      },
    ]
  },
  {
    path: '/sport/trainer',
    redirect: {name: 'TrainingPlansView'},
    name: 'SportTrainerView',
    component: SportTrainerView,
    children: [
      {
        path: 'users',
        name: 'TrainerUsersView',
        component: TrainerUsersView
      },
      {
        path: 'users/:userId',
        name: 'TrainerUserProfileView',
        component: TrainerUserProfileView
      },
      {
        path: 'conversations',
        name: 'TrainerUserConversationsView',
        component: TrainerUserConversationsView
      },
      {
        path: 'conversations/:conversationId',
        name: 'TrainerUserConversationView',
        component: TrainerUserConversationView
      },
      {
        path: 'requests',
        name: 'TrainingPlanRequest',
        component: TrainingPlanRequest
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
