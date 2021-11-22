<template>
    <div class="telemedic-section">
        <header>
            <div class="row mb-4">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" class="clickable" size="2x"
                                       @click="$router.go(-1)"/>
                </div>
                <div class="col">
                    <h3 v-if="user" class="align-left">
                        {{ user.firstName }} {{ user.lastName }}
                    </h3>
                </div>
            </div>
        </header>
        <section v-if="user">
            <div id="accordion-user" class="accordion accordion-flush p-1">
                <div class="accordion-item">
                    <h2 id="heading-profile-card" class="accordion-header">
                        <button aria-controls="collapse-profile-card" aria-expanded="true"
                                class="accordion-button collapsed"
                                data-bs-target="#collapse-profile-card" data-bs-toggle="collapse"
                                type="button">
                            Podstawowe informacje
                        </button>
                    </h2>
                    <div id="collapse-profile-card" aria-labelledby="heading-profile-card"
                         class="accordion-collapse collapse">
                        <div class="accordion-body">
                            <table class="table text-white">
                                <thead>
                                <tr>
                                    <th>Waga</th>
                                    <th>Wzrost</th>
                                    <th>Wiek</th>
                                    <th>Płeć</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        <p v-if="user.profileCard.weight > 0" class="card-elem-container-row">
                                            {{ user.profileCard.weight }} kg </p>
                                        <p v-if="user.profileCard.weight <= 0" class="card-elem-container-row">Brak
                                            informacji</p>
                                    </td>
                                    <td>
                                        <p v-if="user.profileCard.height > 0" class="card-elem-container-row">
                                            {{ user.profileCard.height * 100 }} cm </p>
                                        <p v-if="user.profileCard.height <= 0" class="card-elem-container-row"> Brak
                                            informacji </p>
                                    </td>
                                    <td>
                                        <p v-if="user.profileCard.age > 0" class="card-elem-container-row">
                                            {{ user.profileCard.age }} lat </p>
                                        <p v-else class="card-elem-container-row">Brak informacji</p>
                                    </td>
                                    <td>
                                        <p class="card-elem-container-row">
                                            {{ this.$func_global.mapSex(user.profileCard.esex) }} </p>

                                    </td>
                                </tr>
                                </tbody>

                            </table>

                            <div class="row justify-content-between">
                                <div class="col-lg-6 text-start">
                                    <p class="bold">Dolegliwości i stany fizyczne:</p>
                                </div>
                                <div class="col-lg-3 ">

                                    <div v-for="ailment_elem in user.profileCard.ailments"
                                         :key="ailment_elem.id"
                                         class="row mt-1 d-flex">
                                        <div class="col-8 text-start">
                                            <span>{{ ailment_elem.name }}</span>
                                        </div>
                                        <div class="col-4 float-end">
                                            <button class="btn-icon-panel-sport" data-bs-target="#ailmentModal"
                                                    data-bs-toggle="modal" @click="showModal(ailment_elem)">
                                                <font-awesome-icon :icon="['fa', 'info']"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row text-start justify-content-between mt-4">
                                <div class="col-lg-7">
                                    <p class="bold">Poziom aktywności </p>
                                </div>
                                <div class="col-lg-3">
                                    <p class="card-elem-container-row">
                                        {{ this.$func_global.mapActivity(user.profileCard.activityLevel) }} </p>
                                </div>
                            </div>
                            <div class="row text-start justify-content-between">
                                <div class="col-lg-7">
                                    <p class="bold">Aktywność treningowa w tygodniu </p>
                                </div>
                                <div class="col-lg-3">
                                    <p class="card-elem-container-row">
                                        {{ user.profileCard.trainingActivityTimePerWeek }} minut </p>
                                </div>
                            </div>
                            <div class="row text-start justify-content-between">
                                <div class="col-lg-7">
                                    <p class="bold">Cel </p>
                                </div>
                                <div class="col-lg-3">
                                    <p class="card-elem-container-row">
                                        {{ this.$func_global.mapDietGoal(user.profileCard.dietGoal) }} </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 id="heading-active-plan" class="accordion-header">
                        <button aria-controls="collapse-active-plan" aria-expanded="true"
                                class="accordion-button collapsed"
                                data-bs-target="#collapse-active-plan" data-bs-toggle="collapse"
                                type="button" @click="updateItems">
                            Aktualny plan treningowy podopiecznego
                        </button>
                    </h2>
                    <div id="collapse-active-plan" aria-labelledby="heading-active-plan"
                         class="accordion-collapse collapse">
                        <div class="accordion-body">
                            <div class="m-3 mx-4 header">
                                <span>Tydzień  </span><span
                                class="week">({{
                                    this.$func_global.getWeekRangeFromMonday(moment().clone().isoWeekday(1).toDate())
                                }})</span>
                                <p v-if="userActiveTrainingPlan == null ||  userActiveTrainingPlan.trainingPlanId == null"
                                   class="week text-center mt-2">
                                    Podopieczny nie ma planu na ten tydzień</p>
                                <button
                                    v-if="userActiveTrainingPlan != null &&  userActiveTrainingPlan.trainingPlanId != null"
                                    class="btn-panel-sport float-end"
                                    @click="downloadPlan(this.userActiveTrainingPlan.trainingPlanId)">
                                    <font-awesome-icon :icon="['fa', 'download']"/>
                                </button>
                            </div>
                            <TrainingPlanWeek
                                v-if="userActiveTrainingPlan!= null && userActiveTrainingPlan.trainingPlanId != null"
                                :days="this.$func_global.days" :plan="userActiveTrainingPlan" :plan-type="'active'"
                                :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(userActiveTrainingPlan.beginningDate))"
                                @update:items="updateItems"
                                @update:active="getUserProfile" @set:training="setTraining"/>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <button class="btn-panel-sport" @click="getScratchPlan">Utwórz plan</button>
            </div>
        </section>
        <!-- Ailment modal -->
        <div id="ailmentModal" aria-hidden="false" aria-labelledby="ailmentModalLabel" class="modal fade" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-ailment-content">
                    <h3>{{ modalData.name }}</h3>
                    <p style="font-size: 10pt; color: grey;">{{ this.$func_global.mapAilmentType(modalData.type) }}</p>
                    <span style="text-align: left;">{{ modalData.description }}</span>
                </div>
            </div>
        </div>
        <!-- TRAINING PLAN -->
        <div v-if="newCreatedPlan">
            <div class="row my-3 mx-3">
                <div v-if="beginningDate == null" class="col-6 ">
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
                <!--                <div class="col-4">-->
                <!--                    <button type="button" class="new-plan-option-btn dark-grey-btn" @click="createNewPlan">Utwórz</button>-->
                <!--                </div>-->
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
                    Dodano nowy plan podopiecznemu!
                </p>
            </div>
        </div>

        <TrainingDetails :training="infoTraining"/>
        <AddTrainingToPlanModal v-if="newCreatedPlan != null && newCreatedPlan.trainingPlanId != null"
                                :training-plan-id="newCreatedPlan.trainingPlanId"
                                :trainings-source="trainings" class="popup-focus"
                                @new:plan="refreshNewPlan"/>
    </div>
</template>

<script>
// import MeasuresComponent from "@/components/telemedic/MeasuresComponent";

import TrainingPlanWeek from "@/components/sport/trainingPlan/TrainingPlanWeek";
import moment from "moment";
import TrainingDetails from "@/components/sport/training/TrainingDetails";
import AddTrainingToPlanModal from "@/components/sport/trainingPlan/AddTrainingToPlanModal";

export default {
    name: 'TrainerUserProfileView',
    components: {
        TrainingPlanWeek,
        TrainingDetails,
        AddTrainingToPlanModal
        // MeasuresComponent
    },
    data() {
        return {
            // userId: this.$route.params.userId,
            user: null,
            modalData: Object,
            userActiveTrainingPlan: null,
            infoTraining: null,
            newCreatedPlan: {
                trainingPositions: []
            },
            newPlan: {
                trainingPlanId: -1,
                week: 0,
                beginningDate: '',
                details: "",
                planStatus: "",
                trainingPositions: []
            },
            trainings: [],
            createManual: false,
            savedNewPlan: false
        }
    },
    props: {
        userId: Object,
        createPlan: Boolean,
        beginningDate: Date,
        requestId: String
    },
    methods: {
        moment: function () {
            return moment();
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
        setActivePlan() {
            let tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds

            let plan = this.user.trainingPlans.find(plan => plan.planStatus !== 'SCRATCH' && new Date(new Date(plan.beginningDate) - tzoffset).toISOString().slice(0, 10) === moment().clone().isoWeekday(1).toDate().toISOString().slice(0, 10));
            console.log('Active', plan)
            if (plan != null) {
                this.userActiveTrainingPlan = plan;
                this.calculateProgress()
            }
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
                this.savedNewPlan = true
            }).catch(error => {
                console.log(error.response);
            });
        },
        async refreshNewPlan() {
            const url = `${this.apiURL}sport/training-plan/${this.newPlan.trainingPlanId}`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                // this.newPlan.trainingPlanId = response.data.trainingPlanId
                this.newCreatedPlan = response.data
                console.log('pre', this.newCreatedPlan.beginningDate)
                this.newCreatedPlan.beginningDate = moment(this.newCreatedPlan.beginningDate).toDate();
                console.log('post', this.newCreatedPlan.beginningDate)

            }).catch(error => {
                console.log('Error response ', error.response);
            });
        },
        showModal(ailment) {
            this.modalData = ailment
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
        getUserProfile() {
            this.axios.get(`${this.apiURL}profile/${this.userId}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.user = response.data;
                    this.setActivePlan()
                })
                .catch(e => {
                    console.log(e);
                })
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
        async getScratchPlan() {

            let scratchPlan = this.user.trainingPlans.find(plan => plan.planStatus === 'SCRATCH' &&
                plan.requestId === parseInt(this.requestId))
            console.log('SCRATCH ', scratchPlan)
            if (scratchPlan == null) {
                await this.createNewPlan()
            } else {
                this.newCreatedPlan = scratchPlan
                console.log('Date scratch', new Date(this.newCreatedPlan.beginningDate))
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
                    details: "Nowy plan od trenera",
                },
                ownerId: this.user.id,
                requestId: this.requestId
            }
            await this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.newPlan.trainingPlanId = response.data.trainingPlanId
                this.newCreatedPlan = response.data
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

    },
    created() {
        this.getUserProfile();
        this.getTrainings();
        if (this.beginningDate != null)
            this.newPlan.beginningDate = this.beginningDate
    },
    watch: {
        userActiveTrainingPlan: function () {
            this.updateItems()
        },
        userId: function () {
            this.getUserProfile()
        }
    }
}
</script>

<style scoped>
.modal-ailment-content {
    background-color: white;
    color: black;
    width: 100%;
    height: 100%;
    border-radius: 25px;
    display: flex;
    flex-direction: column;
    align-content: flex-start;
    align-items: flex-start;
    padding: 30px;
}

.accordion-item, .accordion-button {
    background-color: var(--GREY3);
    color: white;
}

.accordion-body {
    background-color: var(--GREY3);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.accordion-header {

    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.accordion-button:not(.collapsed) {
    color: var(--DARK-YELLOW);
    font-weight: bolder;
}

.accordion-button:not(.collapsed)::after {
    background-image: url("data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23F9BA0F'><path fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/></svg>");
}

.accordion-button:focus {
    box-shadow: none;
}

.accordion-button::after {

    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23ffffff'%3e%3cpath fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/%3e%3c/svg%3e");
}

.bold {
    font-weight: bold;
}

.week {
    font-size: 20px;
}

p.week {
    color: var(--SPORT);
}

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}

.telemedic-section {
    background-color: var(--GREY2);
}

.popup-focus {
    z-index: 9995;
}
</style>
