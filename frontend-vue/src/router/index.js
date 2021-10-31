import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Feed from "@/views/social/Feed";
import RoleRequestsView from "@/views/social/RoleRequestsView";

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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
