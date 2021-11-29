<template>
    <div>
        <div class="row">
            <div class="col-6 ">
                <p class="form-label text-start">Tydzień</p>
                <select
                    v-model="generatorData.beginningDate"
                    class=" p-2 float-start"
                    style="border-radius: 5px"
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
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                >
                    <option disabled value="">Wybierz proporcje</option>
                    <option v-for="strategy in strategies" :key="strategy"
                            :value="strategy">{{ this.$func_global.mapWorkoutStrategy(strategy) }}
                    </option>
                </select>
            </div>
        </div>
        <div class="row">
            <p class="form-label text-start mt-3">Wprowadź liczbę treningów każdego dnia</p>
            <div v-for="day in this.$func_global.days" :key="day" class="col-1">
                {{ day.short }}
                <input v-model="generatorData.trainingsPerDay[day.num !== 0 ? day.num-1 : 6]"
                       class="trainings-number w-100 p-0" max="5"
                       min="0" type="number"/>
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
                    class=" p-2 float-start"
                    style="border-radius: 5px"
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
                    class=" p-2 float-start"
                    style="border-radius: 5px"
                    type="number"
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
                    class="d-block"
                />
            </div>
            <div class="row">
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
                textValue: 0,
                goalTargetDate: null
            },
            myActivityGoals: [],
            activeActivityGoal: null
        }
    },
    methods: {
        async generatePlan() {
            const url = `${this.apiURL}sport/training-plan/generate-plan`
            const token = this.$store.getters.getToken;
            this.generatorData.activityGoalId = this.activeActivityGoal.activityGoalId
            const data = this.generatorData
            await this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.$emit('set:generated', response.data)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async createActivityGoal() {
            const url = `${this.apiURL}sport/activity-goal`
            const token = this.$store.getters.getToken;
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

</style>
