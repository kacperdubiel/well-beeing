import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            // SOCIAL
            token: "",
            // PHYSICAL ACTIVITY
            exerciseId: 0,
            trainingToPlan: {
                trainingPlanId: -1,
                trainingId: -1,
                date: null
            }
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
        },
        setTrainingPlanId (state, value) {
            state.trainingToPlan.trainingPlanId = value;
        },
        setTrainingId (state, value) {
            state.trainingToPlan.trainingId = value;
        },
        setTrainingPositionDate (state, value) {
            state.trainingToPlan.date = value;
        }
    },
    getters: {
        getToken (state) {
            return state.token;
        },
        getExerciseId (state) {
            return state.exerciseId;
        },
        getTrainingPlanId (state) {
            return state.trainingToPlan.trainingPlanId;
        },
        getTrainingId (state) {
            return state.trainingToPlan.trainingId;
        },
        getTrainingPositionDate (state) {
            return state.trainingToPlan.date;
        }
    }
});
