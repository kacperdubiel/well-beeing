<template>
    <div>
        <div class="row">
            <div class="col-6 ">
                <p class="form-label text-start">Tydzień</p>
                <select
                    v-model="generatorData.beginningDate"
                    :class="{ 'has-error': submittingGenerate && invalidGenerateWeek}"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                    @focus="clearStatusGenerator"
                    @keypress="clearStatusGenerator"
                >
                    <option disabled value="">Wybierz tydzień</option>
                    <option v-for="range in this.$func_global.generateNWeeks(7)" :key="range.weekNo"
                            :value="range.beginningDate">{{ range.range }}
                    </option>
                </select>
            </div>
            <div class="col-6">
                <p class="form-label text-start">Sposób realizacji celu (aktywność : dieta)</p>
                <select
                    v-model="generatorData.strategy"
                    :class="{ 'has-error': submittingGenerate && invalidWorkoutStrategy}"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                    @focus="clearStatusGenerator"
                    @keypress="clearStatusGenerator"
                >
                    <option disabled value="">Wybierz proporcje</option>
                    <option v-for="strategy in strategies" :key="strategy"
                            :value="strategy">{{ this.$func_global.mapWorkoutStrategy(strategy) }}
                    </option>
                </select>
            </div>
        </div>
        <div class="row">
            <p :class="{ 'has-error': submittingGenerate && invalidGenerateTrainingsPerDay}"
               class="form-label text-start mt-3">Wprowadź liczbę treningów
                każdego dnia</p>
            <div v-for="day in this.$func_global.days" :key="day" class="col-1">
                {{ day.short }}
                <input v-model="generatorData.trainingsPerDay[day.num !== 0 ? day.num-1 : 6]"
                       class="trainings-number w-100 p-0" max="5"
                       min="0" type="number"
                       @focus="clearStatusGenerator"
                       @keypress="clearStatusGenerator"/>
            </div>
        </div>
        <div class="row m-3 mx-4 header">
            <span>Podaj cel swojej aktywności fizycznej</span>
        </div>
        <div class="m-3 mx-4 row-fluid justify-content-center">
            <span class="mx-3">
                <button class="activity-goal-option-btn dark-grey-btn" type="button"
                        v-bind:class="{'active-activity-goal': chooseExistingActivityGoal}"
                        @click="setActionGoalModeCreate(false); getActivityGoals();">Wybiorę istniejący</button>
            </span>
            <span class="mx-3">
                <button class="activity-goal-option-btn dark-grey-btn" type="button"
                        v-bind:class="{'active-activity-goal': createNewActivityGoal}"
                        @click="setActionGoalModeCreate(true)">Tworzę nowy</button>
            </span>
        </div>
        <div v-if="createNewActivityGoal" class="row">
            <div class="col-auto ">
                <p class="form-label text-start">Cel aktywności</p>
                <select
                    v-model="activityGoal.goalType"
                    :class="{ 'has-error': submittingAddActivityGoal && invalidGoalType}"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                    @focus="clearStatus"
                >
                    <option disabled value="">Wybierz typ celu aktywności</option>
                    <option v-for="goal in activityGoalTypes" :key="goal"
                            :value="goal">{{ this.$func_global.mapActivityGoal(goal) }}
                    </option>
                </select>
            </div>
            <div class="col-auto ">
                <p class="form-label text-start">Wartość numeryczna</p>
                <input
                    v-model="activityGoal.numericValue"
                    :class="{ 'has-error': submittingAddActivityGoal && invalidNumericValue}"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                    type="number"
                    @focus="clearStatus"
                    @keypress="clearStatus"
                >
            </div>
            <div class="col-auto ">
                <p class="form-label text-start">Wartość tekstowa</p>
                <input
                    v-model="activityGoal.textValue"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                >
            </div>
            <div class="col-auto ">
                <label class="form-label" for="input-target">Do kiedy</label>
                <DatePicker
                    id="input-target"
                    v-model="activityGoal.goalTargetDate"
                    :class="{ 'has-error': submittingAddActivityGoal && invalidGoalTargetDate}"
                    class="d-block"
                    @focus="clearStatus"
                    @keypress="clearStatus"
                />
            </div>
            <div v-if="submittingAddActivityGoal && activityGoalError"
                 class="alert alert-danger m-3 alert-dismissible fade show"
                 role="alert">
                Wypełnij poprawnie wszystkie pola!
            </div>
            <div class="row mt-4">
                <div class="col-12">
                    <button class="btn-panel-sport " type="button" @click="createActivityGoal">Dodaj cel</button>
                </div>
            </div>
        </div>
        <div v-if="chooseExistingActivityGoal">
            <activity-goals-table :activity-goal-source="myActivityGoals" @choose:goal="setGoalAsActive"/>
        </div>
        <div class="row">
            <p class="form-label text-start mt-3">Wybrany cel aktywności</p>
            <div class="col-auto">
                <activity-goal-node v-if="activeActivityGoal" :activity-goal-source="activeActivityGoal"/>
            </div>
        </div>
        <div v-if="submittingGenerate && generateError"
             class="alert alert-danger m-3 alert-dismissible fade show"
             role="alert">
            Wypełnij poprawnie wszystkie pola!
        </div>
        <div v-if="activeActivityGoal" class="row mt-3 mx-4">
            <div class="col-12">
                <button class="btn-panel-sport " type="button" @click="generatePlan">Wygeneruj</button>
            </div>
        </div>
    </div>
</template>

<script>
import ActivityGoalsTable from "@/components/sport/activityGoal/ActivityGoalsTable";
import ActivityGoalNode from "@/components/sport/activityGoal/ActivityGoalNode";
import {DatePicker} from "v-calendar";

export default {
    name: "TrainingPlanGenerator",
    components: {ActivityGoalNode, ActivityGoalsTable, DatePicker},
    data() {
        return {
            generatorData: {
                trainingsPerDay: [0, 0, 0, 0, 0, 0, 0],
                beginningDate: null,
                activityGoalId: null,
                strategy: null,
            },
            strategies: ['ONLY_WORKOUT', 'MOSTLY_WORKOUT', 'EVEN', 'MOSTLY_DIET', 'ONLY_DIET'],
            createNewActivityGoal: false,
            chooseExistingActivityGoal: false,
            activityGoalTypes: ['LOSE_WEIGHT', 'GAIN_WEIGHT', 'MAINTAIN_WEIGHT', 'EXTEND_LUNGS_CAPACITY', 'BUILD_MUSCLE'],
            activityGoal: {
                goalType: null,
                numericValue: 0,
                textValue: '',
                goalTargetDate: null
            },
            myActivityGoals: [],
            activeActivityGoal: null,
            submittingAddActivityGoal: false,
            activityGoalError: false,
            submittingGenerate: false,
            generateSuccess: false,
            generateError: false
        }
    },
    methods: {
        clearStatus() {
            this.activityGoalError = false
            this.submittingAddActivityGoal = false
        },
        clearStatusGenerator() {
            this.generateSuccess = false
            this.submittingGenerate = false
            this.generateError = false
        },
        async generatePlan() {
            this.submittingGenerate = true
            const url = `${this.apiURL}sport/training-plan/generate-plan`
            const token = this.$store.getters.getToken;
            if (this.invalidGenerateWeek || this.invalidGenerateTrainingsPerDay || this.invalidWorkoutStrategy) {
                this.generateError = true
                return
            }
            this.generatorData.activityGoalId = this.activeActivityGoal.activityGoalId
            const data = this.generatorData
            await this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.$emit('set:generated', response.data)
                this.generateSuccess = true
            }).catch(error => {
                console.log(error.response);
            });
            this.submittingGenerate = false
        },
        async createActivityGoal() {
            this.submittingAddActivityGoal = true
            const url = `${this.apiURL}sport/activity-goal`
            const token = this.$store.getters.getToken;
            if (this.invalidGoalType || this.invalidGoalTargetDate || this.invalidNumericValue) {
                this.activityGoalError = true
                return
            }
            const data = {
                goalType: this.activityGoal.goalType,
                numericValue: this.activityGoal.numericValue,
                textValue: this.activityGoal.textValue,
                goalTargetDate: this.activityGoal.goalTargetDate
            }
            await this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.activeActivityGoal = response.data
                this.createNewActivityGoal = false
                this.getActivityGoals()
            }).catch(error => {
                console.log(error.response);
            });
            this.submittingAddActivityGoal = false
        },
        setActionGoalModeCreate(create) {
            this.createNewActivityGoal = create;
            this.chooseExistingActivityGoal = !create;
        },
        async getActivityGoals() {
            const url = `${this.apiURL}sport/activity-goal`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.myActivityGoals = response.data
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getActivityGoal(goalId) {
            const url = `${this.apiURL}sport/activity-goal/${goalId}`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.activeActivityGoal = response.data
            }).catch(error => {
                console.log(error.response);
            });
        },
        setGoalAsActive(activityGoal) {
            this.activeActivityGoal = activityGoal
        }
    },
    computed: {
        invalidGoalType() {
            return this.activityGoal.goalType == null
        },
        invalidNumericValue() {
            return this.activityGoal.numericValue == null || this.activityGoal.numericValue === '' || this.activityGoal.numericValue < 0
        },
        invalidGoalTargetDate() {
            return this.activityGoal.goalTargetDate == null || this.activityGoal.goalTargetDate === ''
        },
        invalidGenerateWeek() {
            return this.generatorData.beginningDate == null
        },
        invalidGenerateTrainingsPerDay() {
            return this.generatorData.trainingsPerDay.includes('') || this.generatorData.trainingsPerDay.filter(t => t < 0).length > 0
        },
        invalidWorkoutStrategy() {
            return this.generatorData.strategy == null
        }
    }
}
</script>

<style scoped>
.activity-goal-option-btn {
    color: white;
    border-radius: 5px;
    border: none;
    padding: 0.25rem;
    font-size: 1.25rem;
    font-weight: bold;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    width: 180px;
}

.active-activity-goal {
    border: solid;
    border-color: var(--SPORT);
    border-radius: 5px;
    border-width: 2px;
}

.btn-panel-sport {
    width: 50%;
}

.dark-grey-btn {
    background: var(--GREY3);
}

.trainings-number::-webkit-outer-spin-button,
.trainings-number::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

input.has-error, textarea.has-error, select.has-error, .d-block.has-error, div.has-error {
    border: 3px solid var(--INTENSE-PINK);
}
</style>
