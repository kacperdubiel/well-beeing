<template>
    <div>
        <div id="infoTrainingModal" aria-hidden="true" aria-labelledby="infoTrainingModalLabel" class="modal fade"
             tabindex="-1">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div v-if="training != null" class="modal-content">
                    <div class="modal-header">
                        <h5 id="infoTrainingModalLabel" class="modal-title ms-2">Szczegóły treningu</h5>
                        <button
                            v-if="this.$store.getters.getRoles.includes('ROLE_TRAINER') && this.$store.getters.getProfileId === training.creatorId"
                            class="btn-white"
                            @click="editTraining()">
                            <font-awesome-icon :icon="['fa', 'pencil-alt']"/>
                        </button>
                        <button id="infoTrainingModalClose" ref="infoTrainingModalClose" aria-label="Close"
                                class="btn-close"
                                data-bs-dismiss="modal" type="button" @click="clearInputs(); clearStatus;"></button>
                    </div>
                    <div class="modal-body">
                        <div id="modal-container" class="container-fluid">
                            <div v-if="training != null" class="col-11 mx-auto form-group">
                                <div class="row">
                                    <div class="col-xl-5 col-md-12 px-0 ">
                                        <p class="form-label">Nazwa</p>
                                        <p v-if="!edit" class="info-value">{{ training.name }}</p>
                                        <input
                                            v-if="this.edit"
                                            id="name-form"
                                            v-model="editedTraining.name"
                                            :class="{ 'has-error': submittingEditTraining && invalidName || nameTaken}"
                                            class="register-input w-100 p-2 float-start"
                                            placeholder="Nazwa treningu"
                                            type="text"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                        <p v-if="nameTaken && edit" class="has-error m-0">
                                            Istnieje już trening o takiej nazwie!
                                        </p>
                                    </div>
                                    <div :class="{'ps-2' : edit}" class="col-xl-4 col-md-12 px-0">
                                        <p class="form-label">Trudność</p>
                                        <p v-if="!edit" class="info-value">
                                            {{
                                                this.$func_global.mapTrainingDifficulty(training.trainingDifficulty)
                                            }}</p>
                                        <select
                                            v-if="this.edit"
                                            v-model="editedTraining.trainingDifficulty"
                                            :class="{ 'has-error': submittingEditTraining && invalidDifficulty}"
                                            class="register-input p-2 float-start"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        >
                                            <option disabled value="">Wybierz trudność</option>
                                            <option v-for="difficulty in difficultyOptions" :key="difficulty"
                                                    :value="difficulty">
                                                {{ this.$func_global.mapTrainingDifficulty(difficulty) }}
                                            </option>

                                        </select>
                                    </div>
                                    <div v-if="!edit" class="col-xl-3 col-md-12 px-0">
                                        <p class="form-label">kcal</p>
                                        <p class="info-value">{{ training.caloriesBurned }}</p>
                                    </div>
                                </div>
                                <div v-if="!edit" class="row mt-3">
                                    <p class="form-label">Etykiety</p>
                                    <div v-if="training.labels != null && training.labels.length > 0"
                                         class="container labels-container px-1 py-1">
                                        <div v-for="label in training.labels"
                                             :key="label.sportLabelId"
                                             :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                             class="form-label label-node p-2 mx-1 my-1">{{ label.name }}
                                        </div>

                                    </div>
                                    <p v-else class="info-value">Brak etykiet</p>
                                </div>
                                <div v-if="!edit" class="row mt-3">
                                    <p class="form-label">Czas trwania</p>
                                    <p class="info-value">
                                        {{
                                            this.$func_global.getTimePrettyFromSeconds(training.totalTrainingTimeSeconds)
                                        }}</p>
                                </div>

                                <div class="row mt-3">
                                    <p class="form-label">Opis</p>
                                    <p v-if="!edit" class="info-value">{{ training.description }}</p>
                                    <textarea
                                        v-if="this.edit"
                                        v-model="editedTraining.description"
                                        :class="{ 'has-error': submittingEditTraining && invalidDescription}"
                                        class="register-input p-2"
                                        placeholder=" Opis"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <div class="row mt-3">
                                    <p class="form-label">Instrukcja</p>
                                    <p v-if="!edit" class="info-value">{{ training.instruction }}</p>
                                    <textarea
                                        v-if="this.edit"
                                        v-model="editedTraining.instruction"
                                        :class="{ 'has-error': submittingEditTraining && invalidInstruction}"
                                        class="register-input p-2"
                                        placeholder=" Instrukcja"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <!--EXERCISES IN TRAINING-->
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <table class=" table table-hover">
                                            <caption class="caption-top form-label">Cwiczenia w treningu</caption>
                                            <thead>
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Nazwa</th>
                                                <th scope="col">Typ</th>
                                                <th scope="col">kcal</th>
                                                <th scope="col">Powt</th>
                                                <th scope="col">Serie</th>
                                                <th scope="col">Czas</th>
                                                <th scope="col"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <template
                                                v-for="ex in (this.edit ? this.editedTraining.exerciseInTrainings : this.training.exerciseInTrainings)"
                                                :key="ex.exercise.exerciseId">
                                                <tr id="accordionExample" class="accordion accordion-toggle"
                                                    v-bind:class="{'opened': opened.includes(ex.exercise.exerciseId)}">
                                                    <td>{{ ex.exercise.exerciseId }}</td>
                                                    <td>{{ ex.exercise.name }}</td>
                                                    <td>{{
                                                            this.$func_global.mapExerciseType(ex.exercise.exerciseType)
                                                        }}
                                                    </td>
                                                    <td>{{ ex.caloriesBurned }}</td>
                                                    <td>{{ ex.repetitions }}</td>
                                                    <td>{{ ex.series }}</td>
                                                    <td>{{
                                                            this.$func_global.getTimePrettyFromSeconds(ex.time_seconds)
                                                        }}
                                                    </td>
                                                    <td>
                                                        <button class="btn-white mx-2"
                                                                @click="toggle(ex.exercise.exerciseId)">
                                                            <font-awesome-icon
                                                                :icon="opened.includes(ex.exercise.exerciseId) ? ['fa', 'chevron-up'] : ['fa', 'chevron-down']"/>
                                                        </button>
                                                        <button v-if="edit" class="btn-white mx-2"
                                                                @click="removeExercise(ex)">
                                                            <font-awesome-icon :icon="['fa', 'trash']"/>
                                                        </button>
                                                    </td>
                                                </tr>
                                                <tr v-if="opened.includes(ex.exercise.exerciseId)" :key="ex.name"
                                                    class="sub-table">
                                                    <td colspan="8">
                                                        <div v-if="ex.exercise != null" class="container-fluid">
                                                            <div class="col-11 mx-auto form-group">
                                                                <div class="row mt-3">
                                                                    <p class="form-label">Etykiety</p>
                                                                    <div
                                                                        v-if="ex.exercise.labels != null && ex.exercise.labels.length > 0"
                                                                        class="container labels-container px-1 py-1">
                                                                        <div v-for="label in ex.exercise.labels"
                                                                             :key="label.sportLabelId"
                                                                             :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                                                             class="form-label label-node p-2 mx-1 my-1">
                                                                            {{ label.name }}
                                                                        </div>

                                                                    </div>
                                                                    <p v-else class="info-value">Brak etykiet</p>

                                                                </div>
                                                                <div class="row mt-3">
                                                                    <p class="form-label">Wydatek energetyczny
                                                                        (intensywność)</p>
                                                                    <p class="info-value">{{ ex.exercise.met }} METs</p>
                                                                </div>

                                                                <div class="row mt-3">
                                                                    <p class="form-label">Opis</p>
                                                                    <p class="info-value">
                                                                        {{ ex.exercise.description }}</p>
                                                                </div>
                                                                <div class="row mt-3 ">
                                                                    <p class="form-label ">Instrukcja</p>
                                                                    <p class="info-value"
                                                                       v-html="ex.exercise.instruction"></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </template>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--                                EDYCJA ĆWICZEŃ-->
                                <div v-if="edit" class="row mt-3 section-bg">

                                    <div class="row mt-3 mx-3">
                                        <div class="col-12 form-label white">
                                            Cwiczenia do wyboru
                                        </div>
                                    </div>
                                    <div>
                                        <div class="row justify-content-evenly mt-3">
                                            <div class="col-xl-4 col-lg-10 col-sm-10 col-10 align-self-center">
                                                <input
                                                    id="search-input"
                                                    v-model="filters.nameSearch"
                                                    class="w-100 shadow"
                                                    placeholder="Wyszukaj..."
                                                    type="text"
                                                    v-on:keyup.enter="getExercisesWithFilters(true)"
                                                />
                                            </div>
                                            <div class="col-xl-1 col-lg-2 col-sm-2 col-2 align-self-center">
                <span class="float-start button-icon" @click="getExercisesWithFilters(true)">
                    <font-awesome-icon :icon="['fa', 'search']" class="icon"/>
                </span>
                                            </div>
                                            <div
                                                class="col-xl-3 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                                                <p class="m-0 px-1 text-start">Sortowanie</p>
                                                <select
                                                    v-model="filters.sortBy"
                                                    class=" p-2 w-100"
                                                    style="border-radius: 5px"
                                                    @change="getExercisesWithFilters(true)"
                                                >
                                                    <option disabled value="">Wybierz sortowanie</option>
                                                    <option v-for="sort in filters.sortByOptions" :key="sort.label"
                                                            :value="sort.value">{{
                                                            sort.label
                                                        }}
                                                    </option>
                                                </select>
                                            </div>
                                            <div
                                                class="col-xl-2 col-lg-4 col-md-4 col-sm-12  filter-control align-self-center">
                                                <p class="m-0 px-1 text-start">Na stronie</p>
                                                <select
                                                    v-model="userNavigation.pageSize"
                                                    class=" p-2 w-100"
                                                    style="border-radius: 5px"
                                                    @change="getExercisesWithFilters(true)"
                                                >
                                                    <option disabled value="">Rozmiar strony</option>
                                                    <option v-for="size in userNavigation.pageSizeOptions" :key="size"
                                                            :value="size">{{ size }}
                                                    </option>
                                                </select>
                                            </div>
                                            <div
                                                class="col-xl-2 col-lg-4 col-md-4 col-sm-12 align-self-center filter-control">
                                                <p class="m-0 px-1 text-start">Typ</p>
                                                <select
                                                    v-model="filters.typeFilter"
                                                    class=" p-2 w-100"
                                                    style="border-radius: 5px"
                                                    @change="getExercisesWithFilters(true)"
                                                >
                                                    <option disabled value="">Wybierz typ</option>
                                                    <option v-for="type in filters.allTypeFilters" :key="type.label"
                                                            :value="type.value">{{
                                                            type.label
                                                        }}
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row mb-3 px-3 mt-3 mw-100">
                                            <div class="col-md-12 search-info">
                                                <div class="container d-inline-flex px-1 py-1 align-text-center">
                                                    <span id="search-results" class="align-text-bottom me-2">Nałożone filtry: </span>
                                                    <div v-if="filters.lastNameSearch !== ''"
                                                         class="form-label label-node p-2 mx-1 my-1">
                                                        <span class="fst-italic">Nazwa: "{{
                                                                filters.lastNameSearch
                                                            }}"</span>
                                                        <button class="btn btn-sm btn-outline-4 size" type="button"
                                                                @click="removeFilters('name')">
                                                            <font-awesome-icon :icon="['fa', 'times']"/>
                                                        </button>
                                                    </div>
                                                    <div v-if="filters.typeFilter !== ''"
                                                         class="form-label label-node p-2 mx-1 my-1">
                                                        <span class="fst-italic">Typ: "{{
                                                                this.$func_global.mapExerciseType(filters.typeFilter)
                                                            }}"</span>
                                                        <button class="btn btn-sm btn-outline-4 size" type="button"
                                                                @click="removeFilters('type')">
                                                            <font-awesome-icon :icon="['fa', 'times']"/>
                                                        </button>
                                                    </div>
                                                    <button
                                                        v-if="(filters.lastNameSearch !== '' || filters.typeFilter !== '')"
                                                        class="btn btn-sm btn-outline-4 size" type="button"
                                                        @click="removeFilters()">
                                                        <font-awesome-icon :icon="['fa', 'times']"
                                                                           class="btn-outline-danger"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row my-2 align-items-center justify-content-end d-flex">
                                            <nav aria-label="..."
                                                 class="col-xl-8 col-lg-4 col-md-8 col-8 offset-xl-2 offset-lg-2 offset-md-0 offset-0 ">
                                                <ul class="pagination justify-content-center my-auto">
                                                    <li class="page-item sport-page"
                                                        v-bind:class="{'disabled' : navigation.isFirst}">
                                                        <a aria-disabled="true" class="page-link sport-page"
                                                           tabindex="-1" @click="goToPage(0)">
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
                                                        <a class="page-link"
                                                           @click="goToPage(navigation.currentPage+1)">
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
                                                  v-bind:class="{'active-view': !this.isListView}"
                                                  @click="setListView(false)">
                <font-awesome-icon :icon="['fa', 'th']" class="icon"/>
            </span>
                                            <span class="col-xl-1 col-2 float-end px-0"
                                                  v-bind:class="{'active-view': this.isListView}"
                                                  @click="setListView(true)">
                <font-awesome-icon :icon="['fa', 'list-ul']" class="icon"/>
            </span>
                                            <exercises-list-component v-if="isListView"
                                                                      :exercises-source="exercisesSource"
                                                                      :in-modal="true"
                                                                      :mode="'toTraining'"/>
                                            <exercises-grid-component v-if="!isListView"
                                                                      :exercises-source="exercisesSource"
                                                                      :in-modal="true"
                                                                      :mode="'toTraining'"/>

                                            <div class="row justify-content-center mt-3">
                                                <div class="col-4">
                                                    <button class="register-btn btn-panel-sport p-2 mb-3"
                                                            @click="setCurrent">Dodaj
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3 d-inline-flex align-content-center text-start  mb-3 white">
                                        <div class="col-3 align-content-end">
                                            <p class="form-label white">Nazwa</p>
                                            <p class="form-label text-start white">
                                                {{
                                                    currentExercise.exercise != null ? currentExercise.exercise.name : ''
                                                }}
                                            </p>
                                        </div>
                                        <div class="col-3 ">
                                            <label class="form-label white" for="reps-form">Powtórzenia</label>
                                            <input
                                                id="reps-form"
                                                v-model="currentExercise.repetitions"
                                                class="register-input col-10 p-1 justify-self-start"
                                                type="number"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-2">
                                            <label class="form-label white" for="series-form">Serie</label>
                                            <input
                                                id="series-form"
                                                v-model="currentExercise.series"
                                                class="register-input p-1 col-8 d-flex justify-self-start"
                                                type="number"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-3">
                                            <label class="form-label white" for="time-form">Czas
                                                [{{ currentExercise.timeUnits }}]</label>
                                            <input
                                                id="time-form"
                                                v-model="currentExercise.time"
                                                class="p-1 col-8 d-flex justify-self-start"
                                                type="number"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-1 d-flex">
                                            <button class="register-btn align-self-end btn-panel-sport h-auto p-2"
                                                    @click="addExerciseToTraining(currentExercise)">+
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div v-if="successAddExercise && submittingAddExercise" class="row text-end">
                                    <p class="text-success m-0">
                                        Nowe ćwiczenie zostało dodane do treningu!
                                    </p>
                                </div>
                                <div v-if="errorAddExercise && submittingAddExercise" class="row text-end">
                                    <p class="has-error m-0">
                                        Wypełnij wszystkie pola!
                                    </p>
                                </div>
                                <div v-if="errorEditTraining" class="row text-end">
                                    <p class="has-error m-0">
                                        Proszę uzupełnić wszystkie dane poprawnie!
                                    </p>
                                </div>
                                <div v-if="edit" class="row mt-3 justify-content-between">
                                    <div class="col-4 ">
                                        <button class="grey-btn h-100 text-white float-start"
                                                @click="cancelEditTraining()">
                                            Anuluj edycję
                                        </button>
                                    </div>
                                    <div class="col-4">
                                        <button class="btn-panel-sport" @click="submitEditTraining()">
                                            Zapisz zmiany
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div v-else>
                                NIE MA NIC
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";

export default {
    name: "TrainingDetails",
    components: {
        ExercisesListComponent,
        ExercisesGridComponent
    },
    data() {
        return {
            difficultyOptions: ['EASY', 'MEDIUM', 'HARD'],
            edit: false,
            values: [],
            name: "",
            type: "",
            trainingDifficulty: 'EASY',
            description: "",
            instruction: "",
            errorEditTraining: false,
            successEditTraining: false,
            errorAddExercise: false,
            successAddExercise: false,
            submittingAddExercise: false,
            nameTaken: false,
            submittingEditTraining: false,
            labelStyle: {
                backgroundColor: this.randomColor()
            },
            opened: [],
            editedTraining: Object,
            editedRemovedExercises: [],
            editedAddedExercises: [],
            exercisesSource: [],
            currentExercise: {
                exercise: null,
                repetitions: 20,
                time: 15,
                timeUnits: "min", //min, h, s
                series: 2,
            },
            createdTraining: {},
            showExercises: false,
            isListView: true,
            enableButtons: false,
            isCreated: false,
            submittingCreateTraining: false,
            filters: {
                allTypeFilters: [
                    {label: '-', value: ''},
                    {label: 'Kardio', value: 'CARDIO'},
                    {label: 'Siłowe', value: 'STRENGTH'},
                    {label: 'Inne', value: 'OTHER'}
                ],
                typeFilter: '',
                metMin: 0,
                metMax: 30,
                nameSearch: '',
                lastNameSearch: '',
                sortByOptions: [
                    {label: 'Kcal rosnąco', value: 'met,asc'},
                    {label: 'Kcal malejąco', value: 'met,desc'},
                    {label: 'Nazwa A-Z', value: 'name,asc'},
                    {label: 'Nazwa Z-A', value: 'name,desc'},
                    {label: 'Typ A-Z', value: 'exerciseType,asc'},
                    {label: 'Typ Z-A', value: 'exerciseType,desc'}],
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
    props: {
        trainingId: Number,
        training: Object
    },
    methods: {
        submitEditTraining() {
            this.submittingCreateTraining = true
            this.clearStatus()
            if (this.invalidName || this.invalidDifficultyLevel || this.invalidDescription || this.invalidInstruction) {
                this.errorCreateTraining = true
                return
            }
            const data = {
                "name": this.editedTraining.name,
                "trainingDifficulty": this.editedTraining.trainingDifficulty,
                "description": this.editedTraining.description,
                "instruction": this.editedTraining.instruction
            }


            const url = `${this.apiURL}sport/training/${this.editedTraining.trainingId}`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.editedRemovedExercises.forEach(e => {
                    this.removeExerciseFromTrainingById(this.editedTraining.trainingId, e.exercise.exerciseId)
                })
                this.editedAddedExercises.forEach(e => {
                    this.addExerciseToTrainingById(this.editedTraining.trainingId, e.exercise.exerciseId, e)
                })
                this.editedRemovedExercises = []
                this.editedAddedExercises = []
                this.createdTraining = response.data
                this.successCreateTraining = true
                this.isCreated = true
                this.clearInputs()
                setTimeout(() => this.$emit('get:trainings'), 1000);

                this.$refs.infoTrainingModalClose.click()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateTraining = false
            document.getElementsByClassName('')
        },
        setCurrent() {
            this.currentExercise.exercise = this.exercisesSource.find(ex => ex.exerciseId === this.$store.getters.getExerciseToTrainingId)
        },
        resetAddExercise() {
            this.submittingAddExercise = false
            this.errorAddExercise = false
            this.successAddExercise = false
        },
        addExerciseToTraining(exercise) {
            this.resetAddExercise()
            this.submittingAddExercise = true
            if (this.invalidSeries || this.invalidTime || this.invalidRepetitions) {
                this.errorAddExercise = true
                return
            }
            if (exercise.exercise == null) {
                this.errorAddExercise = true
                return
            }
            const newExercise = Object.assign({}, exercise)
            newExercise.time_seconds = exercise.time * 60
            newExercise.caloriesBurned = Math.floor((newExercise.time_seconds * exercise.series) / 60 * (exercise.exercise.met * 3.5 * 80 / 200))
            this.editedTraining.exerciseInTrainings.push(newExercise)
            this.editedAddedExercises.push(newExercise)
            this.successAddExercise = true
        },
        removeExercise(exercise) {
            const index = this.editedTraining.exerciseInTrainings.findIndex(ex => ex === exercise)
            if (index > -1) {
                this.editedTraining.exerciseInTrainings.splice(index, 1)
                this.editedRemovedExercises.push(exercise)
            }
        },
        addExerciseToTrainingById(trainingId, exerciseId, exercise) {
            const url = `${this.apiURL}sport/training/${trainingId}/add-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            let time_seconds = 0;
            switch (exercise.timeUnits) {
                case "s":
                    time_seconds = exercise.time;
                    break;
                case "min":
                    time_seconds = exercise.time * 60;
                    break;
                case "h":
                    time_seconds = exercise.time * 3600;
                    break;
            }
            const data = {
                "reps": exercise.repetitions,
                "time_seconds": time_seconds,
                "series": exercise.series
            }
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                // this.refreshCreatedTraining()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
        },
        removeExerciseFromTrainingById(trainingId, exerciseId) {
            const url = `${this.apiURL}sport/training/${trainingId}/remove-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            const data = {}
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                // this.refreshCreatedTraining()
                // this.successCreateTraining = true
                // this.clearInputs()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
        },
        cancelEditTraining() {
            this.editedTraining = {}
            this.edit = false
        },
        setListView(value) {
            this.isListView = value
        },
        editTraining() {
            this.editedTraining = Object.assign({}, this.training)
            this.editedTraining.exerciseInTrainings = Object.assign([], this.training.exerciseInTrainings)
            this.getExercisesWithFilters(true)
            this.edit = true
        },
        toggle(id) {
            const index = this.opened.indexOf(id);
            if (index > -1) {
                this.opened.splice(index, 1)
            } else {
                this.opened.push(id)
            }
        },
        async getTraining() {
            const url = `${this.apiURL}sport/training/${this.$store.getters.getTrainingId}`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.training = response.data
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getExercises() {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercisesSource = response.data['content']
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getExercisesWithFilters(resetGoToPage) {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
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
                this.exercisesSource = response.data['content']
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
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 2)
                if (this.navigation.currentPage !== 0)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.userNavigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 2)
                        break;
                }
            }).catch(error => {
                console.log(error.response);
            });
        },
        goToPage(pageNo) {
            this.userNavigation.goToPage = pageNo;
            this.getExercisesWithFilters(false)
        },
        async removeFilters(filter) {
            switch (filter) {
                case 'name':
                    this.filters.nameSearch = '';
                    this.filters.lastNameSearch = '';
                    break;
                case 'type':
                    this.filters.typeFilter = '';
                    break;
                default: {
                    this.filters.nameSearch = ''
                    this.filters.typeFilter = ''
                    this.filters.lastNameSearch = '';
                    break;
                }
            }

            await this.getExercisesWithFilters(true)
        },
        clearInputs() {
            this.name = ""
            this.type = ""
            this.trainingDifficulty = 'EASY'
            this.description = ""
            this.instruction = ""
            this.values = []
            this.edit = false
        },
        clearStatus() {
            this.errorEditTraining = false
            this.successEditTraining = false
            this.submittingAddExercise = false
            this.nameTaken = false
        },
        randomColor(seed) {
            let availableColors = ['#C33149', '#FEA12A', '#08415C', '#0E9594', '#8FB339', '#90E39A', '#96E6B3', '#5386E4', '#585123', '#802392']
            return availableColors[seed % availableColors.length];
            // return `rgb(${r()}, ${r()}, ${r()})`;
        }
    },
    computed: {
        invalidName() {
            return this.editedTraining.name === ''
        },
        invalidDifficultyLevel() {
            return this.editedTraining.trainingDifficulty === '';
        },
        invalidDescription() {
            return this.editedTraining.description === ''
        },
        invalidInstruction() {
            return this.editedTraining.instruction === ''
        },
        invalidTime() {
            return this.currentExercise.time === '' || this.currentExercise.time === '' || this.currentExercise.time < 1
        },
        invalidSeries() {
            return this.currentExercise.series === '' || this.currentExercise.series === '' || this.currentExercise.series < 1
        },
        invalidRepetitions() {
            return this.currentExercise.repetitions == null || this.currentExercise.repetitions === '' || this.currentExercise.repetitions < 1
        }
    }
}
</script>

<style scoped>
table {
    table-layout: fixed;
    width: 100%;
}

.opened {
    background-color: rgba(0, 0, 0, 0.075);
}

.sub-table {
    background-color: rgba(0, 0, 0, 0.075);
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

.form-label {
    color: var(--GREY3);
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}

.form-label.white {
    color: white;
}

.info-value {
    color: var(--GREY3);
    text-align: left;
    padding-left: 0;
}

.register-btn {
    color: white;
    font-weight: bold;
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
    font-size: x-small;
    font-weight: normal;
}

.grey-btn {
    background: var(--GREY2);
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

.label-node {
    border-radius: 10px;
    color: var(--bs-white);
    background-color: var(--SPORT);
    font-size: x-small;
    font-weight: normal;
}
</style>
