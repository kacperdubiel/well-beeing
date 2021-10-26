import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            // SOCIAL
            token: "",
            firstName: "",
            lastName: ""
            // PHYSICAL ACTIVITY

            // NUTRITION

            // TELEMEDICINE
        }
    },
    mutations: {
        setToken (state, value) {
            state.token = value;
        },
        setFirstName (state, value) {
            state.firstName = value;
        },
        setLastName (state, value) {
            state.lastName = value;
        }
    },
    getters: {
        getToken (state) {
            return state.token;
        },
        getFirstName (state) {
            return state.firstName;
        },
        getLastName (state) {
            return state.lastName;
        }

    }
});
