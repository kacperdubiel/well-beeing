// import Vue from 'vue'
// import App from './App.vue'
//
// Vue.config.productionTip = false
//
// new Vue({
//   render: h => h(App),
// }).$mount('#app')

import {createApp} from "vue";
import App from '@/App';
import store from '@/store';
import router from './router'

const app = createApp(App).use(router);

app.use(store)

app.mount('#app');

