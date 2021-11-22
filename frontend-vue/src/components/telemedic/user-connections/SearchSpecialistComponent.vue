<template>
    <div class="container-fluid">
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false">
            <div class="row">
                <div class="col-1">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div class="col align-left">
                    <h3>
                        Wyszukaj specjalistę
                    </h3>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col input-group">
                    <input v-model="searchValue" @change="getSpecialists" type="text" class="form-control" placeholder="Wyszukaj...">
                </div>
            </div>
            <div v-if="connectionType === 'WITH_DOCTOR'" class="row mt-3">
                <div class="col-10 col-md-7">
                    <select class="form-select" v-model="selectedDoctorSpecialization">
                        <option v-for="doctorSpec in doctorSpecializations" :value="doctorSpec" :key="doctorSpec.id">
                            {{ doctorSpec.name }}
                        </option>
                    </select>
                </div>
            </div>

            <!-- Modal - ConnectWithSpecialist -->
            <div class="modal fade" id="connectWithSpecialistModal" tabindex="-1" aria-labelledby="connectWithSpecialistModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="connectWithSpecialistModalLabel">
                                <span>Zapisz się do specjalisty</span>
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span v-if="!connectionDone">
                                            Czy na pewno chcesz zapisać się do tego specjalisty?
                                        </span>
                                        <span v-else>{{ this.connectionResultMsg }}</span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button v-if="!connectionDone" class="btn-panel-telemedic p-2"
                                                    :class="{
                                                    'btn-panel-telemedic':connectionType === 'WITH_DOCTOR',
                                                    'btn-panel-sport':connectionType === 'WITH_TRAINER',
                                                    'btn-panel-diet':connectionType === 'WITH_DIETICIAN'}"
                                                    data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button v-if="!connectionDone" class="btn-panel-telemedic p-2"
                                                    :class="{
                                                    'btn-panel-telemedic':connectionType === 'WITH_DOCTOR',
                                                    'btn-panel-sport':connectionType === 'WITH_TRAINER',
                                                    'btn-panel-diet':connectionType === 'WITH_DIETICIAN'}"
                                                    @click="connectWithSpecialist">
                                                <span>Zapisz</span>
                                            </button>
                                            <button v-else class="btn-panel-telemedic p-2"
                                                    :class="{
                                                    'btn-panel-telemedic':connectionType === 'WITH_DOCTOR',
                                                    'btn-panel-sport':connectionType === 'WITH_TRAINER',
                                                    'btn-panel-diet':connectionType === 'WITH_DIETICIAN'}"
                                                    data-bs-dismiss="modal">
                                                <span>Zamknij</span>
                                            </button>
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
                    <table class="table specialists-table" :class="{'specialists-table':connectionType === 'WITH_DOCTOR',
                                                'trainers-table':connectionType === 'WITH_TRAINER',
                                                'dieticians-table':connectionType === 'WITH_DIETICIAN'}">
                        <thead>
                            <tr>
                                <th scope="col">Imię i nazwisko</th>
                                <th v-if="connectionType === 'WITH_DOCTOR'" scope="col" class="w-15">Specjalizacja</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="specialist in specialists" v-bind:key="specialist.id">
                                <td>
                                    <user-avatar-component :profileId="specialist.id"
                                                           :height="40" :width="40"
                                    />
                                    <span class="mx-2">
                                        {{ specialist.firstName }} {{ specialist.lastName }}
                                    </span>
                                </td>

                                <td v-if="connectionType === 'WITH_DOCTOR'">
                                    <ul v-for="spec in specialist.doctorProfile.specializations"
                                        v-bind:key="spec.id"
                                        class="specialization-list"
                                    >
                                        <li>{{ spec.name }}</li>
                                    </ul>
                                </td>
                                <td class="align-right">
                                    <button class="btn-white m-r-5 btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#connectWithSpecialistModal"
                                            @click="selectSpecialist(specialist)">
                                        Zapisz się
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
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
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";

export default {
    name: 'SearchSpecialistComponent',
    components: {
        UserAvatarComponent
    },
    props: {
        connectionType: String
    },
    data() {
        return {
            componentError: null,
            doctorSpecializations: {},
            selectedDoctorSpecialization: null,

            navigation: {},
            specialists: {},
            selectedSpecialist: {},
            searchValue: "",
            connectionDone: false,
            connectionResultMsg: ""
        }
    },
    watch: {
        selectedDoctorSpecialization: function() {
            this.getSpecialists();
        },
    },
    methods: {
        isConnectionTypeCorrect(){
            if(this.connectionType === "WITH_DOCTOR" || this.connectionType === "WITH_DIETICIAN" ||
                this.connectionType === "WITH_TRAINER")
            {
                this.componentError = false;
                this.getDoctorSpecializations();
            } else {
                this.componentError = true;
            }
        },
        getDoctorSpecializations() {
            this.axios.get(`${this.apiURL}doctor-specializations`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.doctorSpecializations = response.data;
                    if(response.data.length > 0){
                        this.selectedDoctorSpecialization = response.data[0];
                        this.getSpecialists();
                    }
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getSpecialists() {
            let endpoint = "";
            if(this.connectionType === "WITH_DOCTOR"){
                endpoint = `profile/doctors/doctor-specializations/${this.selectedDoctorSpecialization.id}`;
            } else if(this.connectionType === "WITH_DIETICIAN"){
                endpoint = "profile/dieticians";
            } else if(this.connectionType === "WITH_TRAINER"){
                endpoint = "profile/trainers";
            }
            if(this.connectionType === "WITH_TRAINER") {
                this.axios.get(`${this.apiURL}${endpoint}?firstName=${this.searchValue}&lastName=${this.searchValue}&role=ROLE_TRAINER`, {
                    headers: {
                        Authorization: 'Bearer ' + this.$store.getters.getToken
                    }
                })
                    .then(response => {
                        this.specialistsPage = response.data;
                        this.specialists = response.data["content"];
                    })
                    .catch(e => {
                        console.log(e);
                    })
                return
            }
            this.axios.get(`${this.apiURL}${endpoint}?like=${this.searchValue}`
                            + `&page=${this.navigation.toGoPage ?? 0}`, {
                            headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.navigation = response.data;
                    this.getPagesNavbar();
                    this.navigation.toGoPage = this.navigation.currentPage;
                    this.specialists = response.data.objects;
                })
                .catch(e => {
                    console.log(e);
                })
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
            this.getSpecialists();
        },
        isPageFirst(){
            return this.navigation.currentPage === 0;
        },
        isPageLast(){
            return this.navigation.currentPage === this.navigation.totalPages - 1;
        },
        selectSpecialist(specialist){
            this.connectionDone = false;
            this.connectionResultMsg = "";
            this.selectedSpecialist = specialist;
        },
        connectWithSpecialist(){
            const data = {
                "connectionType": this.connectionType,
                "connectedWith": {
                    "id": this.selectedSpecialist.id,
                },
            }

            this.axios({
                method: 'post',
                url: `${this.apiURL}profile-connections`,
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                data: data
            })
                .then(() => {
                    this.connectionResultMsg = "Zapisano. Poczekaj na akceptację przez specjalistę.";
                    this.connectionDone = true;
                }).catch(() => {
                    this.connectionResultMsg = "Nie udało się zapisać. Prawdopodobnie zrobiłeś to już wczesniej.";
                    this.connectionDone = true;
                })
        },
    },
    created(){
        this.isConnectionTypeCorrect();
    },
}
</script>

<style scoped>

.m-r-5 {
    margin-right: 5px
}

.btn-panel-telemedic{
    font-size: medium;
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

.specialists-table {
    color: white;
    margin-top: 20px;
    text-align: left;
}

.specialists-table tbody tr:hover {
    background-color: var(--TELEMEDIC);
}

.trainers-table tbody tr:hover {
    background-color: var(--SPORT);
}

.dieticians-table tbody tr:hover {
    background-color: var(--DIET);
}

.specialization-list {
    list-style: none outside none;
    padding: 0;
    margin: 0;
}

</style>