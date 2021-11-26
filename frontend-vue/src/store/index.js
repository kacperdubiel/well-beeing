import {createStore} from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
    state() {
        return {
            // SOCIAL
            profileId: "",
            email: "",
            profileImageSrc: "",
            token: "",
            firstName: "",
            lastName: "",
            roles: [],
            specializations: [],
            searchPhrase: "",
            searchProfileResult: null,
            // PHYSICAL ACTIVITY
            exerciseId: 0,
            trainingId: 0,
            trainingToPlan: {
                trainingPlanId: -1,
                trainingId: -1,
                date: null
            },
            exerciseToTraining: {
                trainingId: -1,
                exerciseId: -1,
                currentExercise: null,
                reps: null,
                time_seconds: null,
                series: null
            },
            selectedRequest: null
            // NUTRITION

            // TELEMEDICINE
        }
    },
    mutations: {
        setProfileId(state, value) {
            state.profileId = value;
        },
        setEmail(state, value) {
            state.email = value;
        },
        setProfileImageSrc(state, value) {
            state.profileImageSrc = value;
        },
        setToken(state, value) {
            state.token = value;
        },
        setFirstName(state, value) {
            state.firstName = value;
        },
        setLastName(state, value) {
            state.lastName = value;
        },
        addRole(state, value) {
            state.roles.push(value);
        },
        setRoles(state, value) {
            state.roles = value;
        },
        addSpecialization (state, value) {
            state.specializations.push(value);
        },
        setSpecialization (state, value) {
            state.specializations = value;
        },
        setSearchPhrase (state, value) {
            state.searchPhrase = value;
        },
        setSearchProfileResult (state, value) {
            state.searchProfileResult = value;
        },
        addSearchProfileResult (state, value) {
            state.searchProfileResult.content = state.searchProfileResult.content.concat(value);
        },
        // PHYSICAL ACTIVITY
        setExerciseId(state, value) {
            state.exerciseId = value;
        },
        setTrainingId(state, value) {
            state.trainingId = value;
        },
        setTrainingPlanId(state, value) {
            state.trainingToPlan.trainingPlanId = value;
        },
        setPlanTrainingId(state, value) {
            state.trainingToPlan.trainingId = value;
        },
        setTrainingPositionDate(state, value) {
            state.trainingToPlan.date = value;
        },
        setExerciseSeries(state, value) {
            state.exerciseToTraining.series = value;
        },
        setExerciseToTrainingId(state, value) {
            state.exerciseToTraining.exerciseId = value;
        },
        setExerciseTrainingId(state, value) {
            state.exerciseToTraining.trainingId = value;
        },
        setExerciseReps(state, value) {
            state.exerciseToTraining.reps = value;
        },
        setExerciseTimeSeconds(state, value) {
            state.exerciseToTraining.time_seconds = value;
        },
        setCurrentExercise(state, value) {
            state.exerciseToTraining.currentExercise = value;
        },
        setSelectedRequest(state, value) {
            state.selectedRequest = value;
        }
    },
    getters: {
        getProfileId(state) {
            return state.profileId;
        },
        getEmail(state) {
            return state.email;
        },
        getProfileImageSrc(state) {
            return state.profileImageSrc;
        },
        getToken(state) {
            return state.token;
        },
        getFirstName(state) {
            return state.firstName;
        },
        getLastName(state) {
            return state.lastName;
        },
        getRoles(state) {
            return state.roles;
        },
        getSpecializations (state) {
            return state.specializations;
        },
        getSearchPhrase (state) {
            return state.searchPhrase;
        },
        getSearchProfileResult (state) {
            return state.searchProfileResult;
        },
        // PHYSICAL ACTIVITY
        getExerciseId(state) {
            return state.exerciseId;
        },
        getTrainingId(state) {
            return state.trainingId;
        },
        getTrainingPlanId(state) {
            return state.trainingToPlan.trainingPlanId;
        },
        getPlanTrainingId(state) {
            return state.trainingToPlan.trainingId;
        },
        getTrainingPositionDate(state) {
            return state.trainingToPlan.date;
        },
        getExerciseSeries(state) {
            return state.exerciseToTraining.series;
        },
        getExerciseToTrainingId(state) {
            return state.exerciseToTraining.exerciseId;
        },
        getExerciseTrainingId(state) {
            return state.exerciseToTraining.trainingId;
        },
        getExerciseReps(state) {
            return state.exerciseToTraining.reps;
        },
        getExerciseTimeSeconds(state) {
            return state.exerciseToTraining.time_seconds;
        },
        getCurrentExercise(state) {
            return state.exerciseToTraining.currentExercise;
        },
        getSelectedRequest(state) {
            return state.selectedRequest;
        }
    },
    plugins: [createPersistedState()],
});
