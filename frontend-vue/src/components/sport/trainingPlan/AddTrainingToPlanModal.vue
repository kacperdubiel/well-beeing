<template>
    <div id="addTrainingToPlanModal" aria-hidden="true" aria-labelledby="addTrainingToPlanModalLabel"
         class="modal fade "
         tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="addTrainingToPlanModalLabel" class="modal-title ms-2">Dodawanie treningu do planu</h5>
                    <button ref="closeModal" aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"
                            @click="clearStatus()"></button>
                </div>
                <div class="modal-body">
                    <div id="modal-container" class="container-fluid">
                        <div class="row mt-3 mx-1">

                            <div class="col-8 align-content-start text-start">
                                <p class="form-label">Data treningu :
                                    {{
                                        this.$store.getters.getTrainingPositionDate != null ? this.$func_global.dateDayMonth(this.$store.getters.getTrainingPositionDate) : ''
                                    }}</p>

                            </div>
                        </div>
                        <div class="row mt-3 mx-1 justify-content-start">
                            <div class="col-3 d-flex justify-content-start align-content-center">
                                <span class="form-label text-start float-start">Pora dnia:</span>
                            </div>
                            <div class="col-9 justify-content-start text-start float-start">
                                <select
                                    v-model="timeOfDay"
                                    :class="{ 'has-error': submittingAddTrainingToPlan && invalidTimeOfDay}"
                                    class="register-input p-2 input-select"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                >
                                    <option disabled value="">Wybierz porę dnia</option>
                                    <option v-for="op in ['MORNING', 'NOON', 'AFTERNOON', 'EVENING', 'NIGHT']"
                                            :key="op" :value="op">{{ this.$func_global.mapTimeOfDay(op) }}
                                    </option>
                                </select>
                                <div v-if="invalidTimeOfDay && submittingAddTrainingToPlan" class="row text-end">
                                    <p class="has-error m-0">
                                        Wybierz porę dnia!
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3 mx-1">
                            <div class="col-12 mx-auto form-group ">
                                <div class="row justify-content-evenly mt-3">
                                    <div class="col-xl-4 col-lg-10 col-sm-10 col-10 align-self-center">
                                        <input
                                            id="search-input"
                                            v-model="filters.nameSearch"
                                            class="w-100 shadow"
                                            placeholder="Wyszukaj..."
                                            type="text"
                                            v-on:keyup.enter="getTrainingsWithFilters(true)"
                                        />
                                    </div>
                                    <div class="col-xl-1 col-lg-2 col-sm-2 col-2 align-self-center">
                <span class="float-start button-icon" @click="getTrainingsWithFilters(true)">
                    <font-awesome-icon :icon="['fa', 'search']" class="icon"/>
                </span>
                                    </div>
                                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                                        <p class="m-0 px-1 text-start">Sortowanie</p>
                                        <select
                                            v-model="filters.sortBy"
                                            class=" p-2 w-100"
                                            style="border-radius: 5px"
                                            @change="getTrainingsWithFilters(true)"
                                        >
                                            <option disabled value="">Wybierz sortowanie</option>
                                            <option v-for="sort in filters.sortByOptions" :key="sort.label"
                                                    :value="sort.value">{{
                                                    sort.label
                                                }}
                                            </option>
                                        </select>
                                    </div>
                                    <div class="col-xl-2 col-lg-4 col-md-4 col-sm-12 filter-control align-self-center">
                                        <p class="m-0 px-1 text-start">Na stronie</p>
                                        <select
                                            v-model="userNavigation.pageSize"
                                            class=" p-2 w-100"
                                            style="border-radius: 5px"
                                            @change="getTrainingsWithFilters(true)"
                                        >
                                            <option disabled value="">Rozmiar strony</option>
                                            <option v-for="size in userNavigation.pageSizeOptions" :key="size"
                                                    :value="size">{{ size }}
                                            </option>
                                        </select>
                                    </div>
                                    <div class="col-xl-2 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                                        <p class="m-0 px-1 text-start">Poziom</p>
                                        <select
                                            v-model="filters.difficultyFilter"
                                            class=" p-2 w-100"
                                            style="border-radius: 5px"
                                            @change="getTrainingsWithFilters(true)"
                                        >
                                            <option disabled value="">Poziom</option>
                                            <option v-for="diff in filters.allDifficultyFilters" :key="diff.label"
                                                    :value="diff.value">
                                                {{ diff.label }}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-3 px-3 mt-3 mw-100">
                                    <div class="col-md-12 search-info">
                                        <div class="container d-inline-flex px-1 py-1 align-text-center">
                                            <span id="search-results"
                                                  class="align-text-bottom me-2">Nałożone filtry: </span>
                                            <div v-if="filters.lastNameSearch !== ''"
                                                 class="form-label label-node p-2 mx-1 my-1">
                                                <span class="fst-italic my-auto">Nazwa: "{{
                                                        filters.lastNameSearch
                                                    }}"</span>
                                                <button class="btn btn-sm btn-outline-4 size" type="button"
                                                        @click="removeFilters('name')">
                                                    <font-awesome-icon :icon="['fa', 'times']"/>
                                                </button>
                                            </div>
                                            <div v-if="filters.difficultyFilter !== ''"
                                                 class="form-label label-node p-2 mx-1 my-1">
                                                <span class="fst-italic my-auto">Poziom: "{{
                                                        this.$func_global.mapTrainingDifficulty(filters.difficultyFilter)
                                                    }}"</span>
                                                <button class="btn btn-sm btn-outline-4 size" type="button"
                                                        @click="removeFilters('type')">
                                                    <font-awesome-icon :icon="['fa', 'times']"/>
                                                </button>
                                            </div>
                                            <button
                                                v-if="(filters.lastNameSearch !== '' || filters.difficultyFilter !== '')"
                                                class="btn btn-sm btn-outline-4 my-auto" type="button"
                                                @click="removeFilters()">
                                                <font-awesome-icon :icon="['fa', 'times']" class="btn-outline-danger"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mb-3 px-3 mt-3 mx-auto ">

                                </div>

                                <div class="row my-2 align-items-center justify-content-start d-flex">
                                    <nav aria-label="..."
                                         class="col-xl-8 col-lg-4 col-md-8 col-8 offset-xl-2 offset-lg-2 offset-md-0 offset-0 ">
                                        <ul class="pagination justify-content-center my-auto">
                                            <li class="page-item sport-page"
                                                v-bind:class="{'disabled' : navigation.isFirst}">
                                                <a aria-disabled="true" class="page-link" tabindex="-1"
                                                   @click="goToPage(0)">
                                                    <font-awesome-icon :icon="['fa', 'fast-backward']"/>
                                                </a>
                                            </li>
                                            <li class="page-item sport-page"
                                                v-bind:class="{'disabled' : navigation.isFirst}">
                                                <a aria-disabled="true" class="page-link" tabindex="-1"
                                                   @click="goToPage(navigation.currentPage-1)">
                                                    <font-awesome-icon :icon="['fa', 'chevron-left']"/>
                                                </a>
                                            </li>
                                            <li v-for="page in userNavigation.pagesNavbar" :key="page"
                                                class="page-item sport-page"
                                                v-bind:class="{'active' : navigation.currentPage === page}"><a
                                                class="page-link"
                                                @click="goToPage(page)">{{ page + 1 }}</a>
                                            </li>
                                            <li class="page-item sport-page"
                                                v-bind:class="{'disabled' : navigation.isLast}">
                                                <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                                    <font-awesome-icon :icon="['fa', 'chevron-right']"/>
                                                </a>
                                            </li>
                                            <li class="page-item sport-page"
                                                v-bind:class="{'disabled' : navigation.isLast}">
                                                <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                                                    <font-awesome-icon :icon="['fa', 'fast-forward']"/>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                    <span class="col-xl-1 col-2 float-end px-2 justify-content-end"
                                          v-bind:class="{'active-view': !this.isListView}" @click="setListView(false)">
                <font-awesome-icon :icon="['fa', 'th']" class="icon"/>
            </span>
                                    <span class="col-xl-1 col-2 float-end px-0"
                                          v-bind:class="{'active-view': this.isListView}"
                                          @click="setListView(true)">
                <font-awesome-icon :icon="['fa', 'list-ul']" class="icon"/>
            </span>
                                </div>
                            </div>
                        </div>
                        <trainings-grid-component v-if="!isListView" :in-modal="true" :mode="'toPlan'"
                                                  :trainings-source="trainingsSource"/>
                        <trainings-list-component v-if="isListView" :in-modal="true" :mode="'toPlan'"
                                                  :trainings-source="trainingsSource"/>
                        <div v-if="errorAddTrainingToPlan && !invalidTimeOfDay" class="row text-end">
                            <p class="has-error m-0">
                                Wybierz trening!
                            </p>
                        </div>
                        <div class="col-4">
                            <button class="register-btn btn-panel-sport p-2" @click="addToTrainingPlan">Dodaj</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingsGridComponent from "@/components/sport/training/TrainingsGridComponent";
import TrainingsListComponent from "@/components/sport/training/TrainingsListComponent";
import moment from "moment";

export default {
    name: "AddTrainingToPlanModal",
    components: {TrainingsListComponent, TrainingsGridComponent},
    data() {
        return {
            trainingId: -1,
            timeOfDay: '',
            date: '',
            submittingAddTrainingToPlan: false,
            successAddTrainingToPlan: false,
            errorAddTrainingToPlan: false,
            isListView: true,
        }
    },
    props: {
        trainingsSource: Array,
        userNavigation: Object,
        navigation: Object,
        filters: Object,
        trainingPlanId: Number
    },
    methods: {
        moment: function () {
            return moment();
        },
        setDate(newDate) {
            this.date = newDate
        },
        setListView(value) {
            console.log(value)
            this.isListView = value
        },
        addToTrainingPlan() {
            this.trainingId = this.$store.getters.getPlanTrainingId
            this.date = this.$store.getters.getTrainingPositionDate
            //http://localhost:8090/sport/training-plan/9/add-position
            this.submittingAddTrainingToPlan = true
            this.clearStatus()
            if (this.invalidTimeOfDay || this.date === '' || this.date == null || this.trainingId < 0) {
                this.errorAddTrainingToPlan = true
                return
            }
            const data = {
                trainingId: this.trainingId,
                date: this.date,
                trainingPlanId: this.trainingPlanId,
                timeOfDay: this.timeOfDay
            }


            const url = `${this.apiURL}sport/training-plan/${data.trainingPlanId}/add-position`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successAddTrainingToPlan = true
                this.$emit('new:plan')
                this.$refs.closeModal.click()
                // this.$emit('get:exercises')
            }).catch(error => {
                alert(error)
            });
            this.submittingAddTrainingToPlan = false
            document.getElementsByClassName('')
        },
        clearStatus() {
            this.errorAddTrainingToPlan = false
            this.successAddTrainingToPlan = false
            this.nameTaken = false
        },
        goToPage(pageNo) {
            this.$emit('goto:page', pageNo)
        },
        async removeFilters(filter) {
            this.$emit('remove:filters', filter)
        },
        getTrainingsWithFilters(resetGoTo) {
            this.$emit('get:trainings', resetGoTo)
        }
    },
    mounted() {
        this.setDate(this.$store.getters.getTrainingPositionDate)
        this.$store.commit('setPlanTrainingId', -1)
    },
    computed: {
        invalidTimeOfDay() {
            return this.timeOfDay == null || this.timeOfDay === '';
        }
    }
}
Date.prototype.addDays = function (days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}
</script>

<style scoped>
.grey-btn {
    background: var(--GREY2);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    /*font-weight: bold;*/
}

.modal-title {
    color: var(--SPORT);
}

.modal-body {
    background-color: var(--GREY2);
}

.form-label {
    color: white;
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}

.register-btn {
    color: white;
    font-weight: bold;
}


.register-btn {
    color: white;
    font-weight: bold;
}

.active-view {
    color: var(--SPORT);
}

.input-select {
    border-radius: 5px;
    font-size: small;
}

.button-icon {
    cursor: pointer;
    color: var(--SPORT);
    background-color: transparent;
}

.filter-control {
    font-size: small;
}

.label-node {
    border-radius: 10px;
    color: var(--bs-white);
    background-color: var(--SPORT);
    font-size: x-small;
    font-weight: normal;
}
</style>
