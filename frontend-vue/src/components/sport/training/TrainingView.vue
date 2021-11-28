<template>
    <div>
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end justify-content-end">Dodaj trening</span>
            <span class="col-2 float-end button-icon">
                <font-awesome-icon :icon="['fa', 'plus-circle']" class="icon  mx-4" data-bs-toggle="modal"
                                   href="#addTrainingModal"/>
            </span>
        </div>
        <div class="row mt-3">
            <div class="col header">
                Baza treningów
            </div>
        </div>
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
                    <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{
                            sort.label
                        }}
                    </option>
                </select>
            </div>
            <div class="col-xl-2 col-lg-4 col-md-4 col-sm-12 filter-control align-self-center">
                <p class="m-0 px-1 text-start">Rozmiar strony</p>
                <select
                    v-model="userNavigation.pageSize"
                    class=" p-2 w-100"
                    style="border-radius: 5px"
                    @change="getTrainingsWithFilters(true)"
                >
                    <option disabled value="">Rozmiar strony</option>
                    <option v-for="size in userNavigation.pageSizeOptions" :key="size" :value="size">{{ size }}</option>
                </select>
            </div>
            <div class="col-xl-2 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                <p class="m-0 px-1 text-start">Poziom trudności</p>
                <select
                    v-model="filters.difficultyFilter"
                    class=" p-2 w-100"
                    style="border-radius: 5px"
                    @change="getTrainingsWithFilters(true)"
                >
                    <option disabled value="">Poziom</option>
                    <option v-for="diff in filters.allDifficultyFilters" :key="diff.label" :value="diff.value">
                        {{ diff.label }}
                    </option>
                </select>
            </div>
        </div>
        <div class="row mb-3 px-3 mt-3 mw-100">
            <div class="col-md-12 search-info">
                <div class="container d-inline-flex px-1 py-1 align-text-center">
                    <span id="search-results" class="align-text-bottom me-2">Nałożone filtry: </span>
                    <div v-if="filters.lastNameSearch !== ''" class="form-label label-node p-2 mx-1 my-1">
                        <span class="fst-italic my-auto">Nazwa: "{{ filters.lastNameSearch }}"</span>
                        <button class="btn btn-sm btn-outline-4 size" type="button" @click="removeFilters('name')">
                            <font-awesome-icon :icon="['fa', 'times']"/>
                        </button>
                    </div>
                    <div v-if="filters.difficultyFilter !== ''" class="form-label label-node p-2 mx-1 my-1">
                        <span class="fst-italic my-auto">Poziom: "{{ filters.difficultyFilter }}"</span>
                        <button class="btn btn-sm btn-outline-4 size" type="button" @click="removeFilters('type')">
                            <font-awesome-icon :icon="['fa', 'times']"/>
                        </button>
                    </div>
                    <button v-if="(filters.lastNameSearch !== '' || filters.difficultyFilter !== '')"
                            class="btn btn-sm btn-outline-4 my-auto" type="button" @click="removeFilters()">
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
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a aria-disabled="true" class="page-link" tabindex="-1" @click="goToPage(0)">
                            <font-awesome-icon :icon="['fa', 'fast-backward']"/>
                        </a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a aria-disabled="true" class="page-link" tabindex="-1"
                           @click="goToPage(navigation.currentPage-1)">
                            <font-awesome-icon :icon="['fa', 'chevron-left']"/>
                        </a>
                    </li>
                    <li v-for="page in userNavigation.pagesNavbar" :key="page"
                        class="page-item sport-page" v-bind:class="{'active' : navigation.currentPage === page}"><a
                        class="page-link"
                        @click="goToPage(page)">{{ page + 1 }}</a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isLast}">
                        <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                            <font-awesome-icon :icon="['fa', 'chevron-right']"/>
                        </a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isLast}">
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
            <span class="col-xl-1 col-2 float-end px-0" v-bind:class="{'active-view': this.isListView}"
                  @click="setListView(true)">
                <font-awesome-icon :icon="['fa', 'list-ul']" class="icon"/>
            </span>
        </div>
        <trainings-list-component v-if="isListView" :trainings-source="trainings" @get:trainings="getTrainings"
                                  @delete:training="deleteTraining"/>
        <trainings-grid-component v-if="!isListView" :trainings-source="trainings" @get:trainings="getTrainings"
                                  @delete:training="deleteTraining"/>
        <!--Modal-->
        <training-form :exercises-source="exercises" :labels-source="labels" @get:exercises="getExercises"
                       @get:trainings="getTrainings"/>
    </div>
</template>

<script>
import TrainingForm from "@/components/sport/training/TrainingForm";
import TrainingsGridComponent from "@/components/sport/training/TrainingsGridComponent";
import TrainingsListComponent from "@/components/sport/training/TrainingsListComponent";

export default {
    name: "TrainingView",
    components: {
        TrainingForm,
        TrainingsGridComponent,
        TrainingsListComponent,
    },
    data() {
        return {
            trainings: [],
            exercises: [],
            labels: [],
            isListView: true,
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
        async getExercises() {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercises = response.data['content']
                console.log(this.exercises)
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
        async getLabels() {
            const url = `${this.apiURL}sport/exercise/labels`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.labels = response.data
                console.log(this.labels)
            }).catch(error => {
                console.log(error.response);
            });
        },
        deleteTraining(trainingId) {
            const url = `${this.apiURL}sport/training/${trainingId}`
            const token = this.$store.getters.getToken;
            this.axios.delete(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.getTrainingsWithFilters(true)
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        setListView(value) {
            console.log(value)
            this.isListView = value
        }
    },
    mounted() {
        this.getTrainingsWithFilters(true);
        // this.getTrainings();
        this.getExercises();
        this.getLabels();
    }

}
</script>

<style scoped>
.icon {
    font-size: 30px;
}

.sport-tabs .active {
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}

.sport-tabs {
    border-bottom-color: var(--SPORT);
}

.active-view {
    color: var(--SPORT);
}

.modal:nth-of-type(even) {
    z-index: 1062 !important;
}

.modal-backdrop.show:nth-of-type(even) {
    z-index: 1061 !important;
}

.label-node {
    border-radius: 10px;
    color: var(--bs-white);
    background-color: var(--SPORT);
    font-size: x-small;
    font-weight: normal;
}

span {
    display: inline-flex;
    align-items: center;
}

.button-icon {
    cursor: pointer;
    color: var(--SPORT);
    background-color: transparent;
}

.filter-control {
    font-size: small;
}

.page-item:disabled {
    background-color: #D83D68;
}

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}
</style>
