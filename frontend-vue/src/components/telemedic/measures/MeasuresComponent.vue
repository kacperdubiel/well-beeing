<template>
    <div>
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-10 col-md-7">
                    <select class="form-select" v-model="selectedMeasureType">
                        <option v-for="measureType in measureTypes" :value="measureType" :key="measureType.id">
                            {{ measureType.name }}
                        </option>
                    </select>
                </div>
                <div v-if="isModificationAllowed" class="col-2  pt-1">
                    <font-awesome-icon :icon="['fa', 'plus-circle']" size="2x" class="telemedic-icon"
                                       data-bs-toggle="modal" data-bs-target="#addMeasureModal"/>
                </div>
            </div>

            <!-- Modal - AddMeasure -->
            <div v-if="isModificationAllowed" class="modal fade" id="addMeasureModal" tabindex="-1" aria-labelledby="addMeasureModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="addMeasureModalLabel"> Podaj wynik pomiaru </h5>
                            <button id="close-add-modal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureValue"
                                            class="form-control"
                                            :class="{ 'has-error': this.measureValueError}"
                                            min="0" max="1000"
                                            step="0.5"
                                        />
                                        <span v-if="selectedMeasureType" class="input-group-text">
                                            {{ selectedMeasureType.unit }}
                                        </span>
                                    </div>
                                    <div v-if="measureValueError" class="text-danger align-left">
                                        Wartość musi być pomiędzy {{ selectedMeasureType.minValue }}
                                        a {{ selectedMeasureType.maxValue }} {{ selectedMeasureType.unit }}.
                                    </div>
                                    <div class="row mt-3">
                                        <date-picker v-model="measureDate" mode="dateTime" is24hr>
                                            <template v-slot="{ inputValue, inputEvents }">
                                                <div class="input-group">
                                                    <input
                                                        class="form-control"
                                                        :value="inputValue"
                                                        v-on="inputEvents"
                                                    />
                                                    <span class="input-group-text">
                                                        <font-awesome-icon :icon="['far', 'calendar-check']" />
                                                    </span>
                                                </div>
                                            </template>
                                        </date-picker>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="clearInputs">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" @click="addMeasure">Dodaj</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - EditMeasure -->
            <div v-if="isModificationAllowed" class="modal fade" id="editMeasureModal" tabindex="-1" aria-labelledby="editMeasureModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="editMeasureModalLabel"> Edytuj wynik pomiaru </h5>
                            <button id="close-edit-modal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid" id="modal-container">
                                <div class="col-11 mx-auto">
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureValue"
                                            class="form-control"
                                            :class="{ 'has-error': this.measureValueError}"
                                            min="0" max="1000"
                                            step="0.5"
                                        />
                                        <span v-if="selectedMeasureType" class="input-group-text">
                                            {{ selectedMeasureType.unit }}
                                        </span>
                                    </div>
                                    <div v-if="measureValueError" class="text-danger align-left">
                                        Wartość musi być pomiędzy {{ selectedMeasureType.minValue }}
                                        a {{ selectedMeasureType.maxValue }} {{ selectedMeasureType.unit }}.
                                    </div>
                                    <div class="row mt-3">
                                        <date-picker v-model="measureDate" mode="dateTime" is24hr>
                                            <template v-slot="{ inputValue, inputEvents }">
                                                <div class="input-group">
                                                    <input
                                                        class="form-control"
                                                        :value="inputValue"
                                                        v-on="inputEvents"
                                                    />
                                                    <span class="input-group-text">
                                                        <font-awesome-icon :icon="['far', 'calendar-check']" />
                                                    </span>
                                                </div>
                                            </template>
                                        </date-picker>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="clearInputs">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" @click="editMeasure">Zmień</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - DeleteMeasure -->
            <div v-if="isModificationAllowed" class="modal fade" id="deleteMeasureModal" tabindex="-1" aria-labelledby="deleteMeasureModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="deleteMeasureModalLabel"> Usuń pomiar </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        Czy na pewno chcesz usunąć ten pomiar?
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="deleteMeasure">Usuń</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <table class="table measures-table">
                        <thead>
                        <tr>
                            <th scope="col" class="w-15">Data</th>
                            <th scope="col" class="w-15">Godzina</th>
                            <th scope="col">Wartość</th>
                            <th scope="col" v-if="this.isModificationAllowed"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr v-for="measure in measures" v-bind:key="measure.id">
                                <td>{{ this.$func_global.formatDate(measure.measureDate) }}</td>
                                <td>{{ this.$func_global.formatTime(measure.measureDate) }}</td>
                                <td>{{ measure.value }} {{ measure.measureType.unit }}</td>
                                <td v-if="this.isModificationAllowed" class="align-right">
                                    <button class="btn-white m-r-5 btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#editMeasureModal"
                                            @click="selectMeasure(measure)">
                                        <font-awesome-icon :icon="['fa', 'pen']" />
                                    </button>
                                    <button class="btn-white btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#deleteMeasureModal"
                                            @click="selectMeasure(measure)">
                                        <font-awesome-icon :icon="['fa', 'trash']" />
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div v-if="measures && measures.length === 0" class="row mb-2">
                        Brak wpisów.
                    </div>
                </div>
            </div>

            <div v-if="this.navigation.totalPages > 0" class="row w-100 mt-3">
                <nav>
                    <ul class="pagination justify-content-center my-auto">
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst()}">
                            <a class="page-link" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'fast-backward']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst()}">
                            <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'chevron-left']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'active' : navigation.currentPage === page}"
                            v-for="page in navigation.pagesNavbar" :key="page"
                        >
                            <a class="page-link" @click="goToPage(page)" >
                                {{page+1}}
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast()}">
                            <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                <font-awesome-icon :icon="['fa', 'chevron-right']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast()}">
                            <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                                <font-awesome-icon :icon="['fa', 'fast-forward']" />
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</template>

<script>
import { DatePicker } from 'v-calendar';

export default {
    name: 'MeasuresComponent',
    components: {
        DatePicker,
    },
    props: {
        userId: String
    },
    data() {
        return {
            measureTypes: {},
            selectedMeasureType: null,

            navigation: {},
            measures: {},
            measureDate: new Date(),
            measureValue: null,
            selectedMeasure: {},
            isModificationAllowed: false,

            measureValueError: false,
        }
    },
    watch: {
        selectedMeasureType: function() {
            this.getMeasures();
        },
        userId: function() {
            if(this.selectedMeasureType) {
                this.getMeasures();
            }
        }
    },
    methods: {
        getMeasureTypes() {
            this.axios.get(`${this.apiURL}measure-types`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.measureTypes = response.data;
                    if(response.data.length > 0){
                        this.selectedMeasureType = response.data[0];
                        this.setModificationAllowed();
                    }
                })
                .catch(e => {
                    console.log(e);
                })
        },
        setModificationAllowed(){
            if(this.userId === this.$store.getters.getProfileId){
                this.isModificationAllowed = true;
            }
        },
        getMeasures() {
            if(this.userId && this.userId.length > 0){
                this.axios.get(`${this.apiURL}measures/user/${this.userId}/type/${this.selectedMeasureType.id}`
                    + `?page=${this.navigation.toGoPage ?? 0}`, {
                    headers: {
                        Authorization: 'Bearer ' + this.$store.getters.getToken
                    }
                })
                    .then(response => {
                        this.navigation = response.data;
                        this.getPagesNavbar();
                        this.navigation.toGoPage = this.navigation.currentPage;
                        this.measures = response.data.objects;
                    })
                    .catch(e => {
                        console.log(e);
                    })
            }
        },
        getPagesNavbar(){
            this.navigation.pagesNavbar = []
            if (this.navigation.currentPage > 1)
                this.navigation.pagesNavbar.push(this.navigation.currentPage-2);
            if (this.navigation.currentPage !== 0)
                this.navigation.pagesNavbar.push(this.navigation.currentPage-1);
            for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                this.navigation.pagesNavbar.push(i);
                if (i === this.navigation.currentPage + 2)
                    break;
            }
        },
        goToPage(pageNo) {
            this.navigation.toGoPage = pageNo;
            this.getMeasures();
        },
        isPageFirst(){
            return this.navigation.currentPage === 0;
        },
        isPageLast(){
            return this.navigation.currentPage === this.navigation.totalPages - 1;
        },
        selectMeasure(measure){
            this.selectedMeasure = measure;
            this.measureValue = measure.value;
            this.measureDate = measure.measureDate;
        },
        addMeasure() {
            const data = {
                "value": this.measureValue,
                "measureType": {
                    "id": this.selectedMeasureType.id,
                },
                "measureDate": this.measureDate,
            }

            this.axios({
                method: 'post',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measures`,
                data: data
            })
                .then(() => {
                    this.getMeasures();
                    this.clearInputs();
                    this.measureValueError = false;
                    this.closeAddModal();
                }).catch(e => {
                    if(e.response.status === 400){
                        this.measureValueError = true;
                    } else {
                        console.log(e);
                    }
                })
        },
        editMeasure() {
            const data = {
                "id": this.selectedMeasure.id,
                "value": this.measureValue,
                "measureDate": this.measureDate,
            }

            this.axios({
                method: 'put',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measures`,
                data: data
            })
                .then(() => {
                    this.getMeasures();
                    this.clearInputs();
                    this.measureValueError = false;
                    this.closeEditModal();
                }).catch(e => {
                if(e.response.status === 400){
                    this.measureValueError = true;
                } else {
                    console.log(e);
                }
                })
        },
        deleteMeasure(){
            this.axios({
                method: 'delete',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measures/${this.selectedMeasure.id}`,
            })
                .then(() => {
                    this.getMeasures();
                    this.clearInputs();
                }).catch(e => {
                    console.log(e);
                })
        },
        closeAddModal() {
            document.getElementById('close-add-modal').click();
        },
        closeEditModal() {
            document.getElementById('close-edit-modal').click();
        },
        clearInputs(){
            this.measureDate = new Date();
            this.measureValue = null;
            this.measureValueError = false;
        }
    },
    created(){
        this.getMeasureTypes();
    },
}
</script>

<style scoped>
    .m-r-5 {
        margin-right: 5px
    }
    .w-15 {
        width: 15%;
    }

    .telemedic-icon {
        color: white;
        cursor: pointer;
    }

    .telemedic-icon:hover {
        color: var(--TELEMEDIC);
    }

    .btn-hover:hover {
        color: var(--GREY1);
    }

    .modal-header, .modal-body {
        color: black;
    }

    .modal-dialog-centered {
        min-height: calc(60% - 3.5rem);
    }

    .measures-table {
        color: white;
        margin-top: 20px;
    }

    .measures-table tbody tr:hover {
        background-color: var(--TELEMEDIC);
    }

</style>