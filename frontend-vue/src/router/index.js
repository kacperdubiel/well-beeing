import {createRouter, createWebHashHistory} from 'vue-router'
import Home from '../views/Home.vue'
//import DietView from "@/views/diet/DietView"
import UserTelemedicView from "@/views/telemedic/user/UserTelemedicView";
import UserMeasuresView from "@/views/telemedic/user/UserMeasuresView";
import UserDoctorsView from "@/views/telemedic/user/UserDoctorsView";
import UserDoctorConversationsView from "@/views/telemedic/user/UserDoctorConversationsView";
import UserDoctorConversationView from "@/views/telemedic/user/UserDoctorConversationView";
import AnalysisView from "@/views/telemedic/user/AnalysisView";
import DoctorTelemedicView from "@/views/telemedic/doctor/DoctorTelemedicView";
import DoctorUsersView from "@/views/telemedic/doctor/DoctorUsersView";
import DoctorUserProfileView from "@/views/telemedic/doctor/DoctorUserProfileView";
import DoctorUserProfileMeasuresView from "@/views/telemedic/doctor/DoctorUserProfileMeasuresView";
import DoctorUserProfileAnalysisView from "@/views/telemedic/doctor/DoctorUserProfileAnalysisView";
import SearchDoctorsView from "@/views/telemedic/user/SearchDoctorsView";
import DoctorUserConversationsView from "@/views/telemedic/doctor/DoctorUserConversationsView";
import DoctorUserConversationView from "@/views/telemedic/doctor/DoctorUserConversationView";
import DoctorMeasureTypesView from "@/views/telemedic/doctor/DoctorMeasureTypesView";
import DietViewMain from "@/views/diet/DietViewMain"
import DietCardView from "@/views/diet/basic-user-panel/DietCardView"
import DietReportsView from "@/views/diet/basic-user-panel/DietReportsView"
import DietDishBrowserView from "@/views/diet/basic-user-panel/DietDishBrowserView"
import DietCalculationsView from "@/views/diet/basic-user-panel/DietCalculationsView"
import DietSpecialistsView from "@/views/diet/basic-user-panel/DietSpecialistsView"
import DietSpecialistsSearchView from "@/views/diet/basic-user-panel/DietSpecialistsSearchView"
import DieticianViewMain from "@/views/diet/DieticianViewMain"
import UserDieticianAllConversationsView from "@/views/diet/basic-user-panel/UserDieticianAllConversationsView";
import UserDieticianConversationView from "@/views/diet/basic-user-panel/UserDieticianConversationView";
import DieticianUserAllConversationsView from "@/views/diet/dietician-panel/DieticianUserAllConversationsView";
import DieticianUserConversationView from "@/views/diet/dietician-panel/DieticianUserConversationView";
import DieticianDishesView from "@/views/diet/dietician-panel/DieticianDishesView";
import DieticianUsersView from "@/views/diet/dietician-panel/DieticianUsersView";
import DieticianLabelsView from "@/views/diet/dietician-panel/DieticianLabelsView";
import DieticianUserProfileView from "@/views/diet/dietician-panel/DieticianUserProfileView";
import DieticianNutritionPlansView from "@/views/diet/dietician-panel/DieticianNutritionPlansView";
import UserNutritionPlansView from "@/views/diet/basic-user-panel/UserNutritionPlansView";
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
import SportReportsComponent from "@/components/sport/SportReportsComponent";
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
import TrainingPlanRequestForm from "@/components/sport/request/TrainingPlanRequestForm";
import SearchProfilesView from "@/views/social/SearchProfilesView";
import FriendsView from "@/views/social/friends/FriendsView";
import MyFriendsView from "@/views/social/friends/MyFriendsView";
import FriendsInvitationsReceivedView from "@/views/social/friends/FriendsInvitationsReceivedView";
import FriendsInvitationsSentView from "@/views/social/friends/FriendsInvitationsSentView";
import UserUserConversationView from "@/views/social/messages/UserUserConversationView";
import UserUserConversationsView from "@/views/social/messages/UserUserConversationsView";
import FollowView from "@/views/social/follow/FollowView";
import MyFollowingsView from "@/views/social/follow/MyFollowingsView";
import MyFollowersView from "@/views/social/follow/MyFollowersView";
import SportDataImportsViewAdmin from "@/views/social/admin/SportDataImportsViewAdmin";
import SportStatsView from "@/views/sport/SportStatsView";

const routes = [
    {
        path: '/',
        name: 'Home',
        meta: {layout: "clean"},
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
        path: '/search',
        name: 'SearchProfilesView',
        component: SearchProfilesView
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
            {
                path: 'plans',
                name: 'UserNutritionPlansView',
                component: UserNutritionPlansView
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
            {
                path: 'plans',
                name: 'DieticianNutritionPlansView',
                component: DieticianNutritionPlansView
            },
        ]
    },
    {
        path: '/telemedic',
        redirect: {name: 'UserMeasuresView'},
        name: 'UserTelemedicView',
        component: UserTelemedicView,
        children: [
            {
                path: 'measures',
                name: 'UserMeasuresView',
                component: UserMeasuresView,
            },
            {
                path: 'userdoctors',
                name: 'UserDoctorsView',
                component: UserDoctorsView
            },
            {
                path: 'userdoctors/search',
                name: 'SearchDoctorsView',
                component: SearchDoctorsView
            },
            {
                path: 'conversations/:conversationId',
                name: 'UserDoctorConversationView',
                component: UserDoctorConversationView
            },
            {
                path: 'conversations',
                name: 'UserDoctorConversationsView',
                component: UserDoctorConversationsView
            },
            {
                path: 'analysis',
                name: 'AnalysisView',
                component: AnalysisView
            },
        ]
    },
    {
        path: '/doctor',
        redirect: {name: 'DoctorUsersView'},
        name: 'DoctorTelemedicView',
        component: DoctorTelemedicView,
        children: [
            {
                path: 'users',
                name: 'DoctorUsersView',
                component: DoctorUsersView
            },
            {
                path: 'users/:userId',
                redirect: {name: 'DoctorUserProfileMeasuresView'},
                name: 'DoctorUserProfileView',
                component: DoctorUserProfileView,
                children: [
                    {
                        path: 'measures',
                        name: 'DoctorUserProfileMeasuresView',
                        component: DoctorUserProfileMeasuresView
                    },
                    {
                        path: 'analysis',
                        name: 'DoctorUserProfileAnalysisView',
                        component: DoctorUserProfileAnalysisView
                    },
                ]
            },
            {
                path: 'conversations',
                name: 'DoctorUserConversationsView',
                component: DoctorUserConversationsView
            },
            {
                path: 'conversations/:conversationId',
                name: 'DoctorUserConversationView',
                component: DoctorUserConversationView
            },
            {
                path: 'measure-types',
                name: 'DoctorMeasureTypesView',
                component: DoctorMeasureTypesView
            },
        ]
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
                path: 'stats',
                name: 'SportStatsView',
                component: SportStatsView
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
        redirect: {name: 'TrainerUsersView'},
        name: 'SportTrainerView',
        component: SportTrainerView,
        children: [
            {
                path: '',
                redirect: 'users', // default child path
            },
            {
                path: 'users',
                name: 'TrainerUsersView',
                component: TrainerUsersView
            },
            {
                path: 'users/:userId',
                name: 'TrainerUserProfileView',
                component: TrainerUserProfileView,
                props: true
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
            },
            {
                path: '/requests/:requestId',
                name: 'TrainingPlanRequestForm',
                component: TrainingPlanRequestForm,
                props: true
            },
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
        meta: {layout: "clean"},
        component: LoginAdminView
    },
    {
        path: '/admin/role-requests',
        name: 'RoleRequestsViewAdmin',
        meta: {layout: "admin"},
        component: RoleRequestViewAdmin
    },
    {
        path: '/admin/imports',
        name: 'DataImportsViewAdmin',
        meta: {layout: "admin"},
        component: DataImportsViewAdmin
    },
    {
        path: '/admin/sport-imports',
        name: 'SportDataImportsViewAdmin',
        meta: {layout: "admin"},
        component: SportDataImportsViewAdmin
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
    {
        path: '/conversations/:conversationId',
        name: 'UserUserConversationView',
        component: UserUserConversationView
    },
    {
        path: '/conversations',
        name: 'UserUserConversationsView',
        component: UserUserConversationsView
    },
    {
        path: '/friends',
        redirect: {name: 'MyFriendsView'},
        name: 'FriendsView',
        component: FriendsView,
        children: [
            {
                path: 'my',
                name: 'MyFriendsView',
                component: MyFriendsView
            },
            {
                path: 'invitations/sent',
                name: 'FriendsInvitationsSentView',
                component: FriendsInvitationsSentView
            },
            {
                path: 'invitations/received',
                name: 'FriendsInvitationsReceivedView',
                component: FriendsInvitationsReceivedView
            },
        ]
    },
    {
        path: '/follows',
        redirect: {name: 'MyFollowingsView'},
        name: 'FollowView',
        component: FollowView,
        children: [
            {
                path: 'followings',
                name: 'MyFollowingsView',
                component: MyFollowingsView
            },
            {
                path: 'followers',
                name: 'MyFollowersView',
                component: MyFollowersView
            },
        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
