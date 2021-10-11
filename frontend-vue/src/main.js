import {createApp} from "vue";
import App from '@/App';
import store from '@/store';
import router from './router'
import axios from "axios";
import VueAxios from "vue-axios";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'

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

