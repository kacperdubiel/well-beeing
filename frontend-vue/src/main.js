import {createApp} from "vue";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import App from '@/App';
import store from '@/store';
import router from './router'
import axios from "axios";
import VueAxios from "vue-axios";
import vSelect from 'vue-select'
import { func_global} from './global-func.js'

import Default from "@/layouts/Default";
import Clean from "@/layouts/Clean";
import Admin from "@/layouts/Admin";

// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

//sidebar
import {
    faDownload,
    faInfo,
    faListUl,
    faPencilAlt,
    faPlusCircle, faSearch,
    faTh,
    faTrash,
    faUserSecret
} from '@fortawesome/free-solid-svg-icons'
import { faUser } from '@fortawesome/free-solid-svg-icons'
import { faUserFriends } from '@fortawesome/free-solid-svg-icons'
import { faUsers } from '@fortawesome/free-solid-svg-icons'
import { faComments } from '@fortawesome/free-solid-svg-icons'
import { faUtensils } from '@fortawesome/free-solid-svg-icons'
import { faDumbbell } from '@fortawesome/free-solid-svg-icons'
import { faBriefcaseMedical } from '@fortawesome/free-solid-svg-icons'
import { faEdit } from '@fortawesome/free-solid-svg-icons'
import 'vue-select/dist/vue-select.css';

library.add(faUserSecret)
library.add(faUser)
library.add(faUserFriends)
library.add(faUsers)
library.add(faComments)
library.add(faUtensils)
library.add(faDumbbell)
library.add(faBriefcaseMedical)
library.add(faInfo)
library.add(faTrash)
library.add(faEdit)
library.add(faPlusCircle)


//navbar
import { faBell } from '@fortawesome/free-regular-svg-icons'
import { faUserCircle } from '@fortawesome/free-regular-svg-icons'
library.add(faBell)
library.add(faUserCircle)

import {faChevronUp} from "@fortawesome/free-solid-svg-icons/faChevronUp"
import {faChevronDown} from "@fortawesome/free-solid-svg-icons/faChevronDown"
import {faChevronRight} from "@fortawesome/free-solid-svg-icons/faChevronRight"
import {faChevronLeft} from "@fortawesome/free-solid-svg-icons/faChevronLeft"
import {faFastBackward} from "@fortawesome/free-solid-svg-icons/faFastBackward"
import {faFastForward} from "@fortawesome/free-solid-svg-icons/faFastForward"

//sport
library.add(faPencilAlt)
library.add(faInfo)
library.add(faTrash)
library.add(faPlusCircle)
library.add(faTh)
library.add(faListUl)
library.add(faDownload)
library.add(faSearch)
library.add(faChevronUp)
library.add(faChevronDown)
library.add(faChevronLeft)
library.add(faChevronRight)
library.add(faFastBackward)
library.add(faFastForward)
//role request
import { faFilePdf } from '@fortawesome/free-regular-svg-icons'
import { faPen } from '@fortawesome/free-solid-svg-icons'
import { faQuestion } from '@fortawesome/free-solid-svg-icons'
import { faTimes } from '@fortawesome/free-solid-svg-icons'
import { faCheck } from '@fortawesome/free-solid-svg-icons'
library.add(faFilePdf)
library.add(faInfo)
library.add(faPen)
library.add(faQuestion)
library.add(faTimes)
library.add(faCheck)

//profile
import { faEllipsisH } from '@fortawesome/free-solid-svg-icons'
library.add(faEllipsisH)

//telemedic
import { faCalendarCheck } from '@fortawesome/free-regular-svg-icons'
library.add(faCalendarCheck)

//post
import { faHeart } from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart } from '@fortawesome/free-regular-svg-icons'
import { faCommentAlt } from '@fortawesome/free-regular-svg-icons'
import { faShareSquare } from '@fortawesome/free-regular-svg-icons'
library.add(faHeart)
library.add(farHeart)
library.add(faCommentAlt)
library.add(faShareSquare)

const app = createApp(App).use(router);

app.config.globalProperties.apiURL = 'http://localhost:8090/'
app.config.globalProperties.$func_global = func_global

app.use(store)
app.use(VueAxios, axios)
app.component('v-select', vSelect)
app.component('font-awesome-icon', FontAwesomeIcon)
app.component('default-layout', Default)
app.component('clean-layout', Clean)
app.component('admin-layout', Admin)

app.mount('#app');

