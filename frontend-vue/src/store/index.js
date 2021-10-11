import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            // SOCIAL
            token: ""
            // PHYSICAL ACTIVITY

            // NUTRITION

            // TELEMEDICINE
        }
    },
    mutations: {
        setToken (state, value) {
            state.token = value;
        }
    },
    getters: {
        getToken (state) {
            return state.token;
        }
    }
});
