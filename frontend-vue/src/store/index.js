import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            // SOCIAL
            token: "",
            firstName: "",
            lastName: "",
            roles: []
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
        },
        addRole (state, value) {
            state.roles.push(value);
        },
        setRoles (state, value) {
            state.roles = value;
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
        },
        getRoles (state) {
            return state.roles;
        }

    }
});
