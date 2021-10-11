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
import axios from "axios";
import VueAxios from "vue-axios";

// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import { faUserSecret } from '@fortawesome/free-solid-svg-icons'

library.add(faUserSecret)

const app = createApp(App).use(router);

app.use(store)
app.use(VueAxios, axios)
app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app');

