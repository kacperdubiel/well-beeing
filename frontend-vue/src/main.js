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

//sidebar
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { faUser } from '@fortawesome/free-solid-svg-icons'
import { faUserFriends } from '@fortawesome/free-solid-svg-icons'
import { faUsers } from '@fortawesome/free-solid-svg-icons'
import { faComments } from '@fortawesome/free-solid-svg-icons'
import { faUtensils } from '@fortawesome/free-solid-svg-icons'
import { faDumbbell } from '@fortawesome/free-solid-svg-icons'
import { faBriefcaseMedical } from '@fortawesome/free-solid-svg-icons'
import { faInfo } from '@fortawesome/free-solid-svg-icons'

library.add(faUserSecret)
library.add(faUser)
library.add(faUserFriends)
library.add(faUsers)
library.add(faComments)
library.add(faUtensils)
library.add(faDumbbell)
library.add(faBriefcaseMedical)
library.add(faInfo)

//navbar
import { faBell } from '@fortawesome/free-regular-svg-icons'
import { faUserCircle } from '@fortawesome/free-regular-svg-icons'
import { func_global} from './global-func.js'

library.add(faBell)
library.add(faUserCircle)

const app = createApp(App).use(router);
app.config.globalProperties.$func_global = func_global

app.use(store)
app.use(VueAxios, axios)
app.component('font-awesome-icon', FontAwesomeIcon)
app.component('default-layout', Default)
app.component('clean-layout', Clean)
app.mount('#app');

