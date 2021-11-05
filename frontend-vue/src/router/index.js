import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Feed from "@/views/social/Feed";
import RoleRequestsView from "@/views/social/RoleRequestsView";
import LoginAdminView from "@/views/social/LoginAdminView";
import RoleRequestViewAdmin from "@/views/social/RoleRequestsViewAdmin";

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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
