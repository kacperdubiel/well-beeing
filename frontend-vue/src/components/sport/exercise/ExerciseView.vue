<template>
    <div>
        <div class="add-exercise row my-2 align-items-center add">
            <span class="h3 col-8 offset-2 text-end justify-content-end">Dodaj</span>
            <span class="col-2 float-end button-icon">
                <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
            </span>
        </div>
        <div class="row justify-content-evenly">
            <div class="col-xl-5 col-lg-10 col-sm-10 col-10 align-self-center">
                <input
                    type="text"
                    v-model="filters.nameSearch"
                    v-on:keyup.enter="getExercisesWithFilters(true)"
                    placeholder="Wyszukaj..."
                    id="search-input"
                    class="w-100 shadow"
                />
            </div>
            <div class="col-xl-1 col-lg-2 col-sm-2 col-2 align-self-center">
                <span class="float-start button-icon" @click="getExercisesWithFilters(true)">
                    <font-awesome-icon class="icon" :icon="['fa', 'search']" />
                </span>
            </div>
            <div class="pt-2 col-xl-2 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.sortBy"
                    class=" p-2 w-100"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters(true)"
                >
                    <option disabled value="">Wybierz sortowanie</option>
                    <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{ sort.label }}</option>
                </select>
            </div>
            <div class="pt-2 col-xl-2 col-lg-4 col-md-4 col-sm-12  filter-control align-self-center">
                <select
                    v-model="userNavigation.pageSize"
                    class=" p-2 w-100"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters(true)"
                >
                    <option disabled value="">Rozmiar strony</option>
                    <option v-for="size in userNavigation.pageSizeOptions" :key="size" :value="size">{{ size }}</option>
                </select>
            </div>
            <div class="pt-2 col-xl-2 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.typeFilter"
                    class=" p-2 w-100"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters(true)"
                >
                    <option disabled value="">Wybierz typ</option>
                    <option v-for="type in filters.allTypeFilters" :key="type.label" :value="type.value">{{ type.label }}</option>
                </select>
            </div>
        </div>
        <div class="row mb-3 px-3 mt-3 mw-100">
            <div class="col-md-6 search-info">
                <div class="container d-inline-flex px-1 py-1 align-text-center" >
                    <span id="search-results" class="align-text-bottom me-2">Nałożone filtry: </span>
                    <div class="form-label label-node p-2 mx-1 my-1" v-if="filters.lastNameSearch !== ''">
                        <span class="fst-italic">Nazwa: "{{filters.lastNameSearch}}"</span>
                        <button class="btn btn-sm btn-outline-4 size" type="button" @click="removeFilters('name')">X</button>
                    </div>
                    <div class="form-label label-node p-2 mx-1 my-1" v-if="filters.typeFilter !== ''">
                        <span class="fst-italic">Typ: "{{filters.typeFilter}}"</span>
                        <button class="btn btn-sm btn-outline-4 size" type="button" @click="removeFilters('type')">X</button>
                    </div>
                    <button v-if="(filters.lastNameSearch !== '' || filters.typeFilter !== '')" class="btn btn-sm btn-outline-4 size" type="button" @click="removeFilters()">X</button>
                </div>
            </div>
        </div>
        <div class="row my-2 align-items-center justify-content-end d-flex">
            <nav aria-label="..." class="col-xl-8 col-lg-4 col-md-8 col-8 offset-xl-2 offset-lg-2 offset-md-0 offset-0 " >
                <ul class="pagination justify-content-center my-auto">
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a class="page-link sport-page" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                            <font-awesome-icon :icon="['fa', 'fast-backward']" />
                        </a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                            <font-awesome-icon :icon="['fa', 'chevron-left']" />
                        </a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'active' : navigation.currentPage === page}" v-for="page in userNavigation.pagesNavbar" :key="page"><a class="page-link" @click="goToPage(page)" >{{page+1}}</a></li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isLast}">
                        <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                            <font-awesome-icon :icon="['fa', 'chevron-right']" />
                        </a>
                    </li>
                    <li class="page-item sport-page" v-bind:class="{'disabled' : navigation.isLast}">
                        <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                            <font-awesome-icon :icon="['fa', 'fast-forward']" />
                        </a>
                    </li>
                </ul>
            </nav>
            <span class="col-xl-1 col-2 float-end px-2 justify-content-end" v-bind:class="{'active-view': !this.isListView}" @click="setListView(false)">
                <font-awesome-icon  class="icon" :icon="['fa', 'th']" />
            </span>
            <span class="col-xl-1 col-2 float-end px-0" v-bind:class="{'active-view': this.isListView}" @click="setListView(true)">
                <font-awesome-icon  class="icon" :icon="['fa', 'list-ul']" />
            </span>
        </div>
        <ExercisesListComponent @submit:editExercise="updateExercise" v-if="isListView" :exercises-source="exercises"/>
        <ExercisesGridComponent v-if="!isListView" :exercises-source="exercises"/>
        <!--Modal-->
        <ExerciseForm :labels-source="labels" @get:exercises="getExercises"/>
    </div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";
import ExerciseForm from "@/components/sport/exercise/ExerciseForm";
export default {
    name: "ExerciseView",
    components: {
        ExerciseForm,
        ExercisesGridComponent,
        ExercisesListComponent,
    },
    data () {
        return {
            exercises: [],
            labels: [],
            isListView: true,
            pageable: null,
            filters: {
                allTypeFilters: [
                    {label:'-', value:''},
                    {label:'Kardio', value:'CARDIO'},
                    {label:'Siłowe', value:'STRENGTH'},
                    {label:'Inne', value:'OTHER'}
                ],
                typeFilter: '',
                metMin: 0,
                metMax: 30,
                nameSearch: '',
                lastNameSearch: '',
                sortByOptions: [
                    {label:'Kcal rosnąco', value:'met,asc'},
                    {label:'Kcal malejąco', value:'met,desc'},
                    {label:'Nazwa A-Z', value:'name,asc'},
                    {label:'Nazwa Z-A', value:'name,desc'},
                    {label:'Typ A-Z', value:'exerciseType,asc'},
                    {label:'Typ Z-A', value:'exerciseType,desc'}],
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
                pageSizeOptions: [3, 5, 10, 20, 50],
                pageSize: 20,
                pagesNavBar: []
            }
        }
    },
    methods: {
        goToPage(pageNo) {
            this.userNavigation.goToPage = pageNo;
            this.getExercisesWithFilters(false)
        },
        async removeFilters(filter) {
            switch (filter) {
                case 'name': this.filters.nameSearch = ''; this.filters.lastNameSearch = ''; break;
                case 'type': this.filters.typeFilter = ''; break;
                default: {
                    this.filters.nameSearch = ''
                    this.filters.typeFilter = ''
                    this.filters.lastNameSearch = '';
                    break;
                }
            }

            await this.getExercisesWithFilters(true)
        },
        async getExercisesWithFilters (resetGoToPage) {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            if (resetGoToPage)
                this.userNavigation.goToPage = 0
            const myParams = {
                page: this.userNavigation.goToPage,
                size: this.userNavigation.pageSize,
                sort: this.filters.sortBy,
                name: this.filters.nameSearch,
                exerciseType: this.filters.typeFilter
            }
            await this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                this.exercises = response.data['content']
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                this.filters.lastNameSearch = myParams.name
                this.userNavigation.pagesNavbar = []
                if (this.navigation.currentPage > 1)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage-2)
                if (this.navigation.currentPage !== 0)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage-1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.userNavigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 2)
                        break;
                }
                console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getExercises () {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercises = response.data['content']
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['isFirst']
                this.navigation.isLast = response.data['isLast']
                this.navigation.isEmpty = response.data['isEmpty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getLabels () {
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
        setListView(value) {
            console.log(value)
            this.isListView = value
        },
        async updateExercise(updatedExercise, values) {
            updatedExercise.labels = values
            const url = `${this.apiURL}sport/exercise/${updatedExercise.exerciseId}`
            const token = this.$store.getters.getToken;
            const data = updatedExercise
            console.log('token ', token);
            await this.axios.patch(url, data,{headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.getExercisesWithFilters()
                // console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
    mounted() {
        this.getExercisesWithFilters();
        this.getLabels();
    }

}
</script>

<style scoped>
.icon {
    font-size: 30px;
}
.sport-tabs .active{
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}
.sport-tabs {
    border-bottom-color: var(--SPORT);
}
.active-view {
    color: var(--SPORT);
}
.button-icon {
    cursor: pointer;
    color: var(--SPORT);
    background-color: transparent;
}

.filter-control {
    font-size: small;
}
.labels-container {
     border: solid;
     border-color: var(--GREY1);
     border-radius: 5px;
     border-width: 2px;
     display: flex;
     flex-wrap: wrap;
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
</style>
