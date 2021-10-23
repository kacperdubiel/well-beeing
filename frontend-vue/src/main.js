import {createApp} from "vue";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import App from '@/App';
import store from '@/store';
import router from './router'
import axios from "axios";
import VueAxios from "vue-axios";


import Default from "@/layouts/Default";
import Clean from "@/layouts/Clean";

// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import { faUserSecret } from '@fortawesome/free-solid-svg-icons'

library.add(faUserSecret)

const app = createApp(App).use(router);

app.use(store)
app.use(VueAxios, axios)
app.component('font-awesome-icon', FontAwesomeIcon)
app.component('default-layout', Default)
app.component('clean-layout', Clean)

app.mount('#app');

