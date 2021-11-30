<template>
    <div>
        <div class="m-3 mx-4 header">
            <span>Aktualny plan treningowy </span>
            <span class="week">({{
                    this.$func_global.getWeekRangeFromMonday(moment().clone().isoWeekday(1).toDate())
                }})</span>
            <p v-if="activePlan.trainingPlanId == null" class="week text-center mt-2">Nie masz planu na ten tydzień
                :(</p>
        </div>
        <div v-if="activePlan.trainingPlanId != null" class="row justify-content-start m-3 mx-4">
            <div class="col-3">
                <div class="progress-text">
                    {{ this.activePlanProgress.trainingsCompleted }}/{{ this.activePlanProgress.trainingsTotal }}
                    treningi
                </div>
                <div class="progress">
                    <div
                        :style="{width: this.activePlanProgress.trainingsCompleted/this.activePlanProgress.trainingsTotal*100 + '%'}"
                        aria-valuemax="100"
                        aria-valuemin="0"
                        aria-valuenow="{{this.activePlanProgress.trainingsCompleted/this.activePlanProgress.trainingsTotal*100}}"
                        class="progress-bar progress-bar-striped progress-bar-animated"
                        role="progressbar">
                        {{
                            Math.floor(this.activePlanProgress.trainingsCompleted / this.activePlanProgress.trainingsTotal * 100)
                        }}%
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="progress-text">
                    {{ this.activePlanProgress.caloriesBurned }}/{{ this.activePlanProgress.caloriesTotal }} kcal
                </div>
                <div class="progress">
                    <div
                        :style="{width: this.activePlanProgress.caloriesBurned/this.activePlanProgress.caloriesTotal*100 + '%'}"
                        aria-valuemax="100"
                        aria-valuemin="0"
                        aria-valuenow="{{this.activePlanProgress.caloriesBurned/this.activePlanProgress.caloriesTotal*100}}"
                        class="progress-bar progress-bar-striped progress-bar-animated"
                        role="progressbar">
                        {{
                            Math.floor(this.activePlanProgress.caloriesBurned / this.activePlanProgress.caloriesTotal * 100)
                        }}%
                    </div>
                </div>
            </div>
            <div class="col-xxl-2 col-xl-3 col-md-4 d-flex ms-auto">
                <button class="btn-panel-sport mx-2 ms-auto" @click="downloadPlan(this.activePlan.trainingPlanId)">
                    <font-awesome-icon :icon="['fa', 'download']"/>
                </button>
            </div>
        </div>
        <!--Active plan-->
        <TrainingPlanWeek v-if="activePlan.trainingPlanId != null" :days="this.$func_global.days"
                          :plan="activePlan" :plan-type="'active'"
                          :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(activePlan.beginningDate))"
                          @update:items="updateItems"
                          @update:active="getMyTrainingPlans" @set:training="setTraining"/>

        <div v-if="myTrainingPlans != null && myTrainingPlans.length > 0" class="m-3 mx-4 header">
            <span>Twoje pozostałe plany</span>
        </div>
        <training-plans-table v-if="myTrainingPlans != null && myTrainingPlans.length > 0"
                              :is-active="activePlan.trainingPlanId != null"
                              :training-plans-source="myTrainingPlans.filter(p => p.planStatus === 'PLANNED')"
                              @update:items="updateItems"
                              @set:active="setNewActive" @download:plan="downloadPlan"
                              @update:plan="getMyTrainingPlans"/>
        <div class="m-3 mx-4 header">
            <span>Tworzenie nowego planu </span>
        </div>
        <div class="m-3 mx-4 row-fluid justify-content-center">
            <span class="mx-3">
                <button class="new-plan-option-btn dark-grey-btn" type="button"
                        v-bind:class="{'active-create': createAuto}"
                        @click="setNewPlanCreationMethod(true)">Automatyczne</button>
            </span>
            <span class="mx-3">
                <button class="new-plan-option-btn dark-grey-btn" type="button"
                        v-bind:class="{'active-create': createManual}"
                        @click="setNewPlanCreationMethod(false); getScratchPlan();">Ręczne</button>
            </span>
        </div>
        <div v-if="createAuto">
            <training-plan-generator @set:generated="setGeneratedPlan"/>
            <div v-if="generatedPlan">
                <div class="m-3 mx-4 header">
                    <span>Wygenerowany plan </span>
                </div>
                <TrainingPlanWeek v-if="generatedPlan.trainingPlanId != null" :days="this.$func_global.days"
                                  :plan="generatedPlan"
                                  :plan-type="'details'"
                                  :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(generatedPlan.beginningDate))"
                                  @update:items="updateItems" @set:training="setTraining"/>

            </div>
        </div>
        <div v-if="createManual">
            <div class="row my-3 mx-3">
                <div class="col-6 ">
                    <div class="row">
                        <div class="col-6 ">
                            <p class="form-label text-start">Tydzień</p>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-7 justify-content-start">
                            <select
                                v-model="newPlan.beginningDate"
                                class=" p-2 float-start"
                                style="border-radius: 5px"
                                @change="updateBeginningDate()"
                            >
                                <option disabled value="">Wybierz tydzień</option>
                                <option v-for="range in this.$func_global.generateNWeeks(7)" :key="range.weekNo"
                                        :value="range.beginningDate">{{ range.range }}
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <!--New plan-->
            <TrainingPlanWeek v-if="newCreatedPlan.trainingPlanId != null" :days="this.$func_global.days"
                              :plan="newCreatedPlan"
                              :plan-type="'create'"
                              :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(newCreatedPlan.beginningDate))"
                              @update:items="updateItems" @set:training="setTraining"/>

            <div class="row mt-3 mx-4">
                <div class="col-12">
                    <button class="btn-panel-sport " type="button" @click="savePlanStatus('PLANNED')">Zapisz</button>
                </div>
            </div>
        </div>
        <div class="row mx-auto mt-3">
            <div v-if="savedNewPlan" class="col-11 mx-auto submit-correct">
                <p>
                    Dodano nowy plan!
                </p>
            </div>
        </div>
        <TrainingDetails :training="infoTraining"/>
        <AddTrainingToPlanModal v-if="newCreatedPlan != null && newCreatedPlan.trainingPlanId != null"
                                :filters="filters" :navigation="navigation"
                                :training-plan-id="newCreatedPlan.trainingPlanId" :trainings-source="trainings"
                                :user-navigation="userNavigation" @goto:page="goToPage" @remove:filters="removeFilters"
                                @get:trainings="getTrainingsWithFilters"
                                @new:plan="refreshNewPlan"/>

    </div>
</template>

<script>
import moment from "moment";
import TrainingPlanWeek from "@/components/sport/trainingPlan/TrainingPlanWeek";
import TrainingDetails from "@/components/sport/training/TrainingDetails";
import AddTrainingToPlanModal from "@/components/sport/trainingPlan/AddTrainingToPlanModal";
import TrainingPlansTable from "@/components/sport/trainingPlan/TrainingPlansTable";
import TrainingPlanGenerator from "@/components/sport/trainingPlan/TrainingPlanGenerator";

export default {
    name: "TrainingPlansView",
    components: {TrainingPlanGenerator, TrainingPlansTable, AddTrainingToPlanModal, TrainingDetails, TrainingPlanWeek,},
    data() {
        return {
            beginningDate: new Date(),
            week: 43,
            year: 2021,
            myTrainingPlans: [],
            trainingPlan: {
                "trainingPlanId": 27,
                "year": 2021,
                "week": 33,
                "details": "Do details",
                "planStatus": "SCRATCH",
                beginningDate: new Date()
            },
            trainingPosition: {
                "trainingPositionId": 54,
                "training": Object,
                "trainingDate": "2021-10-28T22:25:50.339+00:00",
                "trainingStatus": "SCRATCH",
                "timeOfDay": null
            },
            activePlan:
                {
                    trainingPositions: []
                },
            activePlanProgress: {
                progressPercent: 79,
                caloriesBurned: 0,
                caloriesTotal: 0,
                trainingsCompleted: 0,
                trainingsTotal: 0,
            },
            savedNewPlan: false,
            createAuto: false,
            createManual: false,
            newPlan: {
                trainingPlanId: -1,
                week: 0,
                details: "",
                planStatus: "",
                trainingPositions: []
            },
            newCreatedPlan: {
                trainingPositions: []
            },
            trainings: [],
            infoTraining: {},
            generatedPlan: null,

            filters: {
                allDifficultyFilters: [
                    {label: '-', value: ''},
                    {label: 'Łatwe', value: 'EASY'},
                    {label: 'Średnie', value: 'MEDIUM'},
                    {label: 'Trudne', value: 'HARD'}
                ],
                difficultyFilter: '',
                nameSearch: '',
                lastNameSearch: '',
                exerciseNameSearch: '',
                lastExerciseNameSearch: '',
                sortByOptions: [
                    {label: 'Nazwa A-Z', value: 'name,asc'},
                    {label: 'Nazwa Z-A', value: 'name,desc'},
                    {label: 'Trudność rosnąco', value: 'trainingDifficulty,asc'},
                    {label: 'Trudność malejąco', value: 'trainingDifficulty,desc'}],
                sortBy: 'name,asc'
            },
            navigation: {
                totalElements: 0,
                totalPages: 0,
                isFirst: false,
                isLast: false,
                isEmpty: false,
                currentPage: 0,
                pageSize: 20
            },
            userNavigation: {
                goToPage: 0,
                pageSizeOptions: [1, 3, 5, 10, 20, 50],
                pageSize: 20,
                pagesNavbar: []
            }
        }
    },
    methods: {
        moment: function () {
            return moment();
        },
        async setNewActive(plan) {
            const url = `${this.apiURL}sport/training-plan/${plan.trainingPlanId}`
            const token = this.$store.getters.getToken;
            let data = {
                planStatus: 'STARTED'
            }
            await this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                // Dodać zmianę dat w pozycjach treningowych
                this.setActivePlan()
                this.getMyTrainingPlans()
            }).catch(error => {
                console.log(error.response);
            });
        },
        updateItems() {
            {
                setTimeout(() => {
                    const sliders = document.querySelectorAll('.items');
                    let isDown = false;
                    let startX;
                    let scrollLeft;
                    console.log(sliders)
                    if (sliders != null) {
                        sliders.forEach(slider => {
                            slider.addEventListener('mousedown', (e) => {
                                isDown = true;
                                slider.classList.add('active');
                                startX = e.pageX - slider.offsetLeft;
                                scrollLeft = slider.scrollLeft;
                            });
                            slider.addEventListener('mouseleave', () => {
                                isDown = false;
                                slider.classList.remove('active');
                            });
                            slider.addEventListener('mouseup', () => {
                                isDown = false;
                                slider.classList.remove('active');
                            });
                            slider.addEventListener('mousemove', (e) => {
                                if (!isDown) return;
                                e.preventDefault();
                                const x = e.pageX - slider.offsetLeft;
                                const walk = (x - startX); //scroll-fast
                                slider.scrollLeft = scrollLeft - walk;
                            });
                        })

                    } else
                        console.log('EMPTY');
                }, 500)

            }
        },
        async downloadPlan(planId) {
            const url = `${this.apiURL}sport/training-plan/export/${planId}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {
                headers: {Authorization: `Bearer ${token}`, 'Accept': 'application/pdf'},
                responseType: 'arraybuffer'
            }).then((response) => {
                console.log(response.data)
                const blob = new Blob([response.data], {type: 'application/pdf'})
                const objectUrl = window.URL.createObjectURL(blob)
                window.open(objectUrl)
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        async updateBeginningDate() {
            console.log('New week: ', this.newPlan.beginningDate)
            const url = `${this.apiURL}sport/training-plan/${this.newCreatedPlan.trainingPlanId}`
            const token = this.$store.getters.getToken;
            let data = {
                beginningDate: moment(new Date(this.newPlan.beginningDate)).format('DD.MM.YYYY')
            }
            await this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                console.log('New post week: ', response.data)
                // Dodać zmianę dat w pozycjach treningowych
                this.refreshNewPlan()
            }).catch(error => {
                console.log(error.response);
            });
        },
        async savePlanStatus(status) {
            const url = `${this.apiURL}sport/training-plan/${this.newCreatedPlan.trainingPlanId}`
            const token = this.$store.getters.getToken;
            let data = {
                planStatus: status
            }
            await this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                // Dodać zmianę dat w pozycjach treningowych
                this.refreshNewPlan()
                this.getMyTrainingPlans()
                this.savedNewPlan = true
            }).catch(error => {
                console.log(error.response);
            });
        },
        setTraining(training) {
            this.infoTraining = training
        },
        calculateProgress() {
            let caloriesTotal = 0;
            let caloriesCompleted = 0;
            let trainingsCompleted = 0;
            this.activePlan.trainingPositions.forEach(pos => {
                caloriesTotal += pos.training.caloriesBurned
                if (pos.trainingStatus === 'COMPLETED') {
                    caloriesCompleted += pos.training.caloriesBurned
                    trainingsCompleted += 1
                }
            })
            let trainingsTotal = this.activePlan.trainingPositions.length
            this.activePlanProgress.caloriesBurned = caloriesCompleted
            this.activePlanProgress.caloriesTotal = caloriesTotal
            this.activePlanProgress.trainingsCompleted = trainingsCompleted
            this.activePlanProgress.trainingsTotal = trainingsTotal
        },
        generateNWeeks(n) {
            let week = new Date().getWeek()
            let currentMondayDate = moment().clone().isoWeekday(1).toDate()
            console.log('Current monday', currentMondayDate)
            let weekArray = []
            for (let i = 0; i < n; i++) {
                weekArray.push({
                    weekNo: week + i,
                    beginningDate: currentMondayDate.addDays(i * 7),
                    range: this.getWeekRangeFromMonday(currentMondayDate.addDays(i * 7))
                })
                console.log('Week from ', currentMondayDate.addDays(i * 7), ' range: ', this.getWeekRangeFromMonday(currentMondayDate.addDays(i * 7)))
            }
            return weekArray;
        },
        async getMyTrainingPlans() {
            const url = `${this.apiURL}sport/training-plan/my`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.myTrainingPlans = response.data
                this.setActivePlan()
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getTrainingPlan(planId) {
            const url = `${this.apiURL}sport/training-plan/${planId}`
            const token = this.$store.getters.getToken;
            return await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                return response.data
            }).catch(error => {
                console.log(error.response);
            });
        },
        getNextMonday() {
            let d = new Date();
            d.setDate(d.getDate() + (((1 + 7 - d.getDay()) % 7) || 7));
            return d
        },
        async refreshNewPlan() {
            const url = `${this.apiURL}sport/training-plan/${this.newPlan.trainingPlanId}`
            const token = this.$store.getters.getToken;
            console.log('here')
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                // this.newPlan.trainingPlanId = response.data.trainingPlanId
                this.newCreatedPlan = response.data
                this.newCreatedPlan.beginningDate = moment(this.newCreatedPlan.beginningDate).toDate();
                console.log('Nowa data z momenta', this.newCreatedPlan.beginningDate)
                this.newPlan.beginningDate = this.newCreatedPlan.beginningDate
                this.getMyTrainingPlans()
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getScratchPlan() {
            await this.getMyTrainingPlans()
            let scratchPlan = this.myTrainingPlans.find(plan => plan.planStatus === 'SCRATCH')
            if (scratchPlan == null) {
                await this.createNewPlan()
            } else {
                this.newCreatedPlan = scratchPlan
                this.newCreatedPlan.beginningDate = moment(this.newCreatedPlan.beginningDate).toDate();
                console.log('Data scratch', this.newCreatedPlan.beginningDate)
                this.newPlan.week = moment(new Date(this.newCreatedPlan.beginningDate)).toDate().getWeek()
                this.newPlan.beginningDate = this.newCreatedPlan.beginningDate
                this.newPlan.trainingPlanId = this.newCreatedPlan.trainingPlanId
            }
        },
        async createNewPlan() {
            const url = `${this.apiURL}sport/training-plan`
            const token = this.$store.getters.getToken;
            console.log('here')
            const data = {
                trainingPlan: {
                    beginningDate: this.newPlan.beginningDate,
                    // details: this.newPlan.details,
                    details: "Nowy plan"
                }
            }
            await this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.newPlan.trainingPlanId = response.data.trainingPlanId
                this.newCreatedPlan = response.data
            }).catch(error => {
                console.log(error.response);
            });
        },
        setNewPlanCreationMethod(isAuto) {
            this.createAuto = isAuto;
            this.createManual = !isAuto;
            this.savedNewPlan = false
            this.updateItems()
        },
        setActivePlan() {
            let tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds

            let plan = this.myTrainingPlans.find(plan => plan.planStatus === 'STARTED' && new Date(new Date(plan.beginningDate) - tzoffset).toISOString().slice(0, 10) === moment().clone().isoWeekday(1).toDate().toISOString().slice(0, 10));
            console.log('Active', plan)
            if (plan != null) {
                this.activePlan = plan;
                this.calculateProgress()
            }
        },
        getWeekRangeFromMonday(mondayDate) {
            // console.log('Monday', mondayDate)
            let from = mondayDate.getDate().toString().padStart(2, '0') + '.' + eval(mondayDate.getMonth() + 1).toString().padStart(2, '0');
            mondayDate.setDate(mondayDate.getDate() + 6);
            let to = mondayDate.getDate().toString().padStart(2, '0') + '.' + eval(mondayDate.getMonth() + 1).toString().padStart(2, '0');
            return from + " - " + to
        },
        getDatesArrayFromMonday(d1) {
            // console.log('Array monday:', d1)
            let weekDays = []
            for (let i = 0; i < 7; i++) {
                weekDays.push({
                    day: this.$func_global.days[i],
                    date: d1.addDays(i)
                })
            }
            return weekDays;
        },
        matchesDayOfTheWeek(position, day) {
            return new Date(position.trainingDate).getDay() === day;
        },
        async getTrainingsWithFilters(resetGoToPage) {
            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            if (resetGoToPage)
                this.userNavigation.goToPage = 0
            const myParams = {
                page: this.userNavigation.goToPage,
                size: this.userNavigation.pageSize,
                sort: this.filters.sortBy,
                name: this.filters.nameSearch,
                exerciseName: this.filters.exerciseNameSearch,
                trainingDifficulty: this.filters.difficultyFilter
            }
            await this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                this.trainings = response.data['content']
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                this.filters.lastNameSearch = myParams.name
                this.filters.lastExerciseNameSearch = myParams.exerciseName
                this.userNavigation.pagesNavbar = []
                if (this.navigation.currentPage > 1)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 2)
                if (this.navigation.currentPage !== 0)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.userNavigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 2)
                        break;
                }
                // this.userNavigation.pagesNavbar.push(this.navigation.currentPage+1)
                // if (this.navigation.currentPage === 0)
                //     this.userNavigation.pagesNavbar.push(this.navigation.currentPage+2)
                console.log(this.trainings)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getTrainings() {
            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.trainings = response.data['content']
                console.log(this.trainings)
            }).catch(error => {
                console.log(error.response);
            });
        },
        setGeneratedPlan(planId) {
            this.getTrainingPlan(planId).then((result) => {
                this.generatedPlan = result
            })
            this.updateItems()
            this.getMyTrainingPlans()
        },
        goToPage(pageNo) {
            this.userNavigation.goToPage = pageNo;
            this.getTrainingsWithFilters(false)
        },
        async removeFilters(filter) {
            switch (filter) {
                case 'name':
                    this.filters.nameSearch = '';
                    this.filters.lastNameSearch = '';
                    break;
                case 'difficulty':
                    this.filters.difficultyFilter = '';
                    break;
                default: {
                    this.filters.nameSearch = '';
                    this.filters.difficultyFilter = '';
                    this.filters.lastNameSearch = '';
                    this.filters.exerciseNameSearch = ''
                    this.filters.lastExerciseNameSearch = '';
                    break;
                }
            }

            await this.getTrainingsWithFilters(true)
        },
    },
    mounted() {
        this.week = new Date().getWeek()
        this.getMyTrainingPlans()
        this.getTrainingsWithFilters(true)
    },
    watch: {
        activePlan: function () {
            this.updateItems()
        }
    }

}
Date.prototype.addDays = function (days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}
Date.prototype.getWeek = function () {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    // Thursday in current week decides the year.
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    // January 4 is always in week 1.
    var week1 = new Date(date.getFullYear(), 0, 4);
    // Adjust to Thursday in week 1 and count number of weeks from date to week1.
    return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000 - 3 + (week1.getDay() + 6) % 7) / 7);
}

</script>

<style scoped>

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}

.week {
    font-size: 20px;
}

.row-fluid {
    white-space: nowrap;
}

.row-fluid .col-4 {
    display: inline-block;
}

.col-4 {
    white-space: normal;
}

.day {
    background-color: var(--GREY3);
    border-radius: 5px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

p.week {
    color: var(--SPORT);
}

.dark-grey-btn {
    background: var(--GREY3);
}

.new-plan-option-btn {
    color: white;
    border-radius: 5px;
    border: none;
    padding: 0.25rem;
    font-size: 1.25rem;
    font-weight: bold;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    width: 180px;
}

.active-create {
    border: solid;
    border-color: var(--SPORT);
    border-radius: 5px;
    border-width: 2px;
}

.btn-panel-sport {
    width: 50%;
}

.progress .progress-bar {
    background-color: var(--SPORT);
}

.progress {
    height: 30%;
    font-size: 12px;
    font-weight: bold;
}

.submit-correct {
    color: var(--TELEMEDIC);
}
</style>
