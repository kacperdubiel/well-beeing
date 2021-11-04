<template>
    <div>
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end justify-content-end">Dodaj</span>
            <span class="col-2 float-end button-icon">
                <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
            </span>
        </div>
        <div class="row justify-content-start">
            <div class="col-md-3 col-sm-12 align-self-center">
                <input
                    type="text"
                    v-model="filters.nameSearch"
                    v-on:keyup.enter="getExercisesWithFilters()"
                    placeholder="Wyszukaj..."
                    id="search-input"
                    class="w-100 shadow"
                />
            </div>
            <div class="col-md-1 col-sm-12 align-self-center">
                <span class="float-start button-icon" @click="getExercisesWithFilters()">
                    <font-awesome-icon class="icon  mx-4" :icon="['fa', 'search']" />
                </span>
            </div>
            <div class="col-md-2 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.sortBy"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters()"
                >
                    <option disabled value="">Wybierz sortowanie</option>
                    <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{ sort.label }}</option>
                </select>
            </div>
            <div class="col-md-2 col-sm-12  filter-control align-self-center">
                <select
                    v-model="userNavigation.pageSize"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters()"
                >
                    <option disabled value="">Wybierz typ</option>
                    <option v-for="size in userNavigation.pageSizeOptions" :key="size" :value="size">{{ size }}</option>
                </select>
            </div>
            <div class="col-md-2 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.typeFilter"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getExercisesWithFilters()"
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
        <div class="row my-2 align-items-center justify-content-end">
            <span class="col-2 float-end" v-bind:class="{'active-view': !this.isListView}" @click="setListView(false)">
                <font-awesome-icon  class="icon" :icon="['fa', 'th']" />
            </span>
            <span class="col-2 float-end" v-bind:class="{'active-view': this.isListView}" @click="setListView(true)">
                <font-awesome-icon  class="icon" :icon="['fa', 'list-ul']" />
            </span>
        </div>
        <ExercisesListComponent v-if="isListView" :exercises-source="exercises"/>
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
                pageSizeOptions: [10, 20, 50],
                pageSize: 20
            }
        }
    },
    methods: {
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

            await this.getExercisesWithFilters()
        },
        async getExercisesWithFilters () {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
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
                this.navigation.isFirst = response.data['isFirst']
                this.navigation.isLast = response.data['isLast']
                this.navigation.isEmpty = response.data['isEmpty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                this.filters.lastNameSearch = myParams.name
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
        }
    },
    mounted() {
        this.getExercises();
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
