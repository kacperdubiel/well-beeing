import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            // SOCIAL
            token: "",
            // PHYSICAL ACTIVITY
            exerciseId: 0
            // NUTRITION

            // TELEMEDICINE
        }
    },
    mutations: {
        setToken (state, value) {
            state.token = value;
        },
        setExerciseId (state, value) {
            state.exerciseId = value;
        }
    },
    getters: {
        getToken (state) {
            return state.token;
        },
        getExerciseId (state) {
            return state.exerciseId;
        }
    }
});
