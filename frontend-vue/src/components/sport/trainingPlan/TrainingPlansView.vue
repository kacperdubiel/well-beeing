<template>
    <div>
        <div class="m-3 mx-4 header">
            <span >Aktualny plan treningowy </span>
            <span class="week">({{this.getDateRangeOfWeek(week)}})</span>
            <p class="week text-center mt-2" v-if="activePlan.trainingPlanId == null">Nie masz planu na ten tydzień :(</p>
        </div>
        <div class="container align-items-start" style="overflow: auto" v-if="activePlan.trainingPlanId != null">
            <div class="row-fluid d-flex align-items-start">
                <div v-for="day in days" :key="day.num" class="col-4 day mx-1  mb-3 p-3">
                    <TrainingPlanDay :day=day.name :positions="activePlan.trainingPositions.filter( pos => matchesDayOfTheWeek(pos, day.num))">

                    </TrainingPlanDay>
                </div>
            </div>
        </div>
        <div class="m-3 mx-4 header">
            <span >Tworzenie nowego planu </span>
        </div>
        <div class="m-3 mx-4 row-fluid justify-content-center">
            <span class="mx-3">
                <button type="button" class="new-plan-option-btn dark-grey-btn" @click="setNewPlanCreationMethod(true)" v-bind:class="{'active-create': createAuto}">Automatyczne</button>
            </span>
            <span class="mx-3">
                <button type="button" class="new-plan-option-btn dark-grey-btn" @click="setNewPlanCreationMethod(false)" v-bind:class="{'active-create': createManual}">Ręczne</button>
            </span>
        </div>
        <div v-if="createAuto">
            AUTO
        </div>
        <div v-if="createManual">
            <div class="row mt-3 mx-4">
                <div class="col-4">
                    <p class="form-label text-start">Tydzień</p>
                    <v-select
                        class="style-chooser pb-2"
                        v-model="newPlan.week"
                        :options=generateNWeeks(10)
                        :reduce="range => range.weekNo"
                        label="range"
                    />
                    <button type="button" class="new-plan-option-btn dark-grey-btn" @click="createNewPlan">Utwórz</button>

                    <p class="form-label">{{ newPlan.week }}</p>
                </div>
            </div>
            <!--Plan-->
            <div class="container align-items-start" style="overflow: auto" v-if="activePlan.trainingPlanId != null">
                <div class="row-fluid d-flex align-items-start">
                    <div v-for="day in days" :key="day.num" class="col-4 day mx-1  mb-3 p-3">
                        <TrainingPlanDay :create="true" :day=day.name :positions="newCreatedPlan.trainingPositions.filter( pos => matchesDayOfTheWeek(pos, day.num))">

                        </TrainingPlanDay>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingPlanDay from "@/components/sport/trainingPlan/TrainingPlanDayComponent";
export default {
    name: "TrainingPlansView",
    components: {TrainingPlanDay},
    data () {
        return {
            week: 43,
            year: 2021,
            days: [
                    {
                        num:1,
                        name: 'Poniedziałek'
                    },
                    {
                        num:2,
                        name: 'Wtorek'
                    },
                    {
                        num:3,
                        name: 'Środa'
                    },
                    {
                        num:4,
                        name: 'Czwartek'
                    },
                    {
                        num:5,
                        name: 'Piątek'
                    },
                    {
                        num:6,
                        name: 'Sobota'
                    },
                    {
                        num:0,
                        'name': 'Niedziela'
                    }
                ],
            myTrainingPlans: [],
            trainingPlan :{
                "trainingPlanId": 27,
                "year": 2021,
                "week": 33,
                "details": "Do details",
                "planStatus": "SCRATCH",
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
            createAuto: false,
            createManual: false,
            newPlan: {
                trainingPlanId: -1,
                week: 0,
                year: 2021,
                details:"",
                planStatus:"",
                trainingPositions: []
            },
            newCreatedPlan: {
                trainingPositions: []
            }
        }
    },
    methods: {
        generateNWeeks(n) {
            let weekArray = []
            for (let i = 0; i < n; i++) {
                weekArray.push({
                    weekNo: this.week+i,
                    range:this.getDateRangeOfWeek(this.week + i)})
            }
            return weekArray;
        },
        async getMyTrainingPlans () {
            const url = `${this.apiURL}sport/training-plan/my`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.myTrainingPlans = response.data
                this.setActivePlan()
            }).catch(error => {
                console.log(error.response);
            });
        },
        async createNewPlan () {
            const url = `${this.apiURL}sport/training-plan`
            const token = this.$store.getters.getToken;
            console.log('here')
            const data = {
                trainingPlan: {
                    week: this.newPlan.week,
                    year: new Date().getFullYear(),
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
        async addTrainingToPlan() {

        },
        setNewPlanCreationMethod (isAuto) {
            this.createAuto = isAuto;
            this.createManual = !isAuto;
        },
        setActivePlan() {
          let plan = this.myTrainingPlans.find(plan => plan.week === this.week);
          if (plan != null)
              this.activePlan = plan;
        },
        getDateRangeOfWeek(weekNo){
            var d1 = new Date();
            var numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
            d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
            var weekNoToday = d1.getWeek();
            var weeksInTheFuture = eval( weekNo - weekNoToday );
            d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
            var rangeIsFrom = d1.getDate().toString().padStart(2, '0') + '.' + eval(d1.getMonth()+1).toString().padStart(2, '0');
            d1.setDate(d1.getDate() + 6);
            var rangeIsTo = d1.getDate().toString().padStart(2, '0') + '.' + eval(d1.getMonth()+1).toString().padStart(2, '0');
            return rangeIsFrom + " - "+rangeIsTo;
        },
        matchesDayOfTheWeek(position, day) {
            return new Date(position.trainingDate).getDay() === day;
        }
    },
    mounted() {
        this.week = new Date().getWeek()
        this.getMyTrainingPlans()
    }
}
Date.prototype.getWeek = function() {
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
.row-fluid{
    white-space: nowrap;
}
.row-fluid .col-4{
    display: inline-block;
}
.col-4 { white-space: normal; }
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
</style>
