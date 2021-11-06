<template>
    <div>
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false" class="container">
            <div class="row justify-content-between">
                <div class="col-10 col-md-7">
                    <select class="form-select" v-model="selectedAcceptState">
                        <option :value="true">
                            Moi pacjenci
                        </option>
                        <option :value="false">
                            Oczekujące zgłoszenia
                        </option>
                    </select>
                </div>
            </div>

            <!-- Modal - DeleteSpecialistConnection -->
            <div class="modal fade" id="deleteSpecialistConnectionModal" tabindex="-1" aria-labelledby="deleteSpecialistConnectionModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="deleteSpecialistConnectionModalLabel">
                                Usuń pacjenta
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span>Czy na pewno chcesz usunąć pacjenta?</span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="deleteSpecialistConnection">
                                                <span>Usuń</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - AcceptSpecialistConnection -->
            <div class="modal fade" id="acceptSpecialistConnectionModal" tabindex="-1" aria-labelledby="acceptSpecialistConnectionModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="acceptSpecialistConnectionModalLabel">
                                Zaakceptuj pacjenta
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span>Czy na pewno chcesz zaakceptować pacjenta?</span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="acceptSpecialistConnection">
                                                <span>Akceptuj</span>
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
                    <table class="table specialists-table">
                        <thead>
                            <tr>
                                <th scope="col">Imię i nazwisko</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="connection in specialistConnections" v-bind:key="connection.id">
                                <td v-if="selectedAcceptState" class="user-name-link">
                                    <router-link :to="{ name: 'DoctorUserProfileView', params: { userId: connection.profile.id } }">
                                        {{ connection.profile.firstName }} {{ connection.profile.lastName }}
                                    </router-link>
                                </td>
                                <td v-if="!selectedAcceptState">
                                    {{ connection.profile.firstName }} {{ connection.profile.lastName }}
                                </td>

                                <td class="align-right">
                                    <button v-if="selectedAcceptState" class="btn-white m-r-5 btn-hover">
                                        <font-awesome-icon :icon="['fa', 'comments']" />
                                    </button>
                                    <button v-if="!selectedAcceptState" class="btn-white m-r-5 btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#acceptSpecialistConnectionModal"
                                            @click="selectSpecialistConnection(connection)">
                                        <font-awesome-icon :icon="['fa', 'check']" />
                                    </button>
                                    <button class="btn-white btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#deleteSpecialistConnectionModal"
                                            @click="selectSpecialistConnection(connection)">
                                        <font-awesome-icon :icon="['fa', 'trash']" />
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'SpecialistConnectionsComponent',
    components: {

    },
    props: {
        connectionType: String
    },
    data() {
        return {
            componentError: null,
            selectedAcceptState: true,
            specialistConnectionsPage: {},
            specialistConnections: {},
            userId: "",
            selectedSpecialistConnection: {},
        }
    },
    watch: {
        selectedAcceptState: function (){
            this.getSpecialistConnections();
        },
    },
    methods: {
        isConnectionTypeCorrect(){
            if(this.connectionType === "WITH_DOCTOR" || this.connectionType === "WITH_DIETICIAN" ||
                this.connectionType === "WITH_TRAINER")
            {
                this.componentError = false;
                this.getProfile();
            } else {
                this.componentError = true;
            }
        },
        getProfile(){
            this.axios.get('http://localhost:8090/profile', {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.userId = response.data.id;
                    this.getSpecialistConnections();
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getSpecialistConnections() {
            this.axios.get(`http://localhost:8090/profile-connections/to-me/type/${this.connectionType}/accepted/${this.selectedAcceptState}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.specialistConnectionsPage = response.data;
                    this.specialistConnections = response.data.objects;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        selectSpecialistConnection(connection){
            this.selectedSpecialistConnection = connection;
        },
        deleteSpecialistConnection(){
            if(this.selectedSpecialistConnection){
                this.axios({
                    method: 'delete',
                    url: `http://localhost:8090/profile-connections/${this.selectedSpecialistConnection.id}`,
                    headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                }).then(() => {
                    this.getSpecialistConnections();
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        acceptSpecialistConnection(){
            if(this.selectedSpecialistConnection){
                this.axios({
                    method: 'put',
                    url: `http://localhost:8090/profile-connections/${this.selectedSpecialistConnection.id}/mark-as-accepted`,
                    headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                }).then(() => {
                    this.getSpecialistConnections();
                }).catch(e => {
                    console.log(e);
                })
            }
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

.user-name-link a {
    color: white;
    text-decoration: none;
}

.specialists-table tbody tr:hover {
    background-color: var(--TELEMEDIC);
}

</style>