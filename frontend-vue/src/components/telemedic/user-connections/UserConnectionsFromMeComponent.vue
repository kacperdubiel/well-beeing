<template>
    <div class="m-3">
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false" class="container">
            <div class="row justify-content-between">
                <div class="col-10 col-md-7">
                    <select v-model="selectedAcceptState" class="form-select">
                        <option :value="true">
                            <span v-if="connectionType === 'WITH_USER'">Moi znajomi</span>
                            <span v-if="connectionType === 'WITH_DOCTOR'">Moi lekarze</span>
                            <span v-if="connectionType === 'WITH_DIETICIAN'">Moi dietetycy</span>
                            <span v-if="connectionType === 'WITH_TRAINER'">Moi trenerzy</span>
                        </option>
                        <option :value="false">
                            Wysłane prośby
                        </option>
                    </select>
                </div>
                <div v-if="connectionType !== 'WITH_USER'" class="col-5">
                    <button v-if="connectionType === 'WITH_DOCTOR'" class="btn-panel-telemedic"
                            @click="$emit('search-specialist')"
                    >
                        Wyszukaj lekarza
                    </button>
                    <button v-if="connectionType === 'WITH_DIETICIAN'" class="btn-panel-diet"
                            @click="$emit('search-specialist')"
                    >
                        Wyszukaj dietetyka
                    </button>
                    <button v-if="connectionType === 'WITH_TRAINER'" class="btn-panel-sport"
                            @click="$emit('search-specialist')"
                    >
                        Wyszukaj trenera
                    </button>
                </div>
            </div>

            <!-- Modal - DeleteUserConnection -->
            <div id="deleteUserConnectionModal" aria-hidden="true" aria-labelledby="deleteUserConnectionModalLabel"
                 class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="deleteUserConnectionModalLabel" class="modal-title ms-2">
                                <span v-if="connectionType === 'WITH_USER'">Usuń znajomego</span>
                                <span v-if="connectionType !== 'WITH_USER'">Wypisz się od specjalisty</span>
                            </h5>
                            <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span v-if="connectionType === 'WITH_USER'">Czy na pewno chcesz usunąć znajomego?</span>
                                        <span
                                            v-if="connectionType !== 'WITH_USER'">Czy na pewno chcesz się wypisać?</span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj
                                            </button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal"
                                                    @click="deleteUserConnection">
                                                <span v-if="connectionType === 'WITH_USER'">Usuń</span>
                                                <span v-if="connectionType !== 'WITH_USER'">Wypisz</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - SubmitTrainingPlanRequest -->
            <div v-if="connectionType === 'WITH_TRAINER' && trainer" id="createTrainingPlanRequestModal"
                 aria-hidden="true"
                 aria-labelledby="createTrainingPlanRequestModalLabel"
                 class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="createTrainingPlanRequestModalLabel" class="modal-title ms-4">
                                <span>Wyślij prośbę o utworzenie planu treningowego</span>
                            </h5>
                            <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto text-start">
                                    <div class="row">
                                        <p class="form-label">Trener - Adresat</p>
                                        <p class="ps-0">{{ trainer.firstName }} {{
                                                trainer.lastName
                                            }}</p>
                                    </div>
                                    <div class="row my-3 mx-3">
                                        <div class="col-6 ">
                                            <div class="row">
                                                <div class="col-6 ">
                                                    <p class="form-label text-start">Tydzień</p>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="col-7 justify-content-start">
                                                    <select
                                                        v-model="request.beginningDate"
                                                        class=" p-2 float-start"
                                                        style="border-radius: 5px"
                                                    >
                                                        <option disabled value="">Wybierz tydzień</option>
                                                        <option v-for="range in this.$func_global.generateNWeeks(7)"
                                                                :key="range.weekNo"
                                                                :value="range.beginningDate">{{ range.range }}
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <p class="form-label">Treść prośby</p>
                                        <textarea
                                            v-model="requestMessage"
                                            :class="{ 'has-error': submittingRequest && invalidMessage}"
                                            class="register-input p-2"
                                            placeholder="Wiadomość"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                    <div v-if="errorSubmitRequest" class="row text-end">
                                        <p class="has-error m-0">
                                            Proszę uzupełnić wszystkie dane poprawnie!
                                        </p>
                                    </div>
                                    <div v-if="successSubmitRequest" class="row text-end">
                                        <p class="submit-correct m-0">
                                            Wysłano prośbę!
                                        </p>
                                    </div>
                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-sport  p-2" data-bs-dismiss="modal">Anuluj
                                            </button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-sport p-2"
                                                    @click="sendTrainingPlanRequest">
                                                <span>Wyślij</span>
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
                    <table :class="{
                        'connections-table-telemedic': connectionType === 'WITH_DOCTOR',
                        'connections-table-sport': connectionType === 'WITH_TRAINER',
                        'connections-table-diet': connectionType === 'WITH_DIETICIAN'}" class="table connections-table">
                        <thead>
                        <tr>
                            <th scope="col">Imię i nazwisko</th>
                            <th v-if="connectionType === 'WITH_DOCTOR'" class="w-15" scope="col">Specjalizacja</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="connection in userConnections" v-bind:key="connection.id">
                            <td v-if="connection.profile.id !== userId"
                                class="clickable"
                                @click="$emit('open-profile', connection.profile.id, this.selectedAcceptState)"
                            >
                                <user-avatar-component :height="40"
                                                       :isActive="this.$func_global.getIsActive5minutes(connection.profile.lastRequestTime)"
                                                       :profileId="connection.profile.id" :width="40"
                                />
                                <span class="mx-2">
                                        {{ connection.profile.firstName }} {{ connection.profile.lastName }}
                                    </span>
                            </td>
                            <td v-else
                                class="clickable"
                                @click="$emit('open-profile', connection.connectedWith.id, this.selectedAcceptState)"
                            >
                                <user-avatar-component :height="40"
                                                       :isActive="this.$func_global.getIsActive5minutes(connection.connectedWith.lastRequestTime)"
                                                       :profileId="connection.connectedWith.id" :width="40"
                                />
                                <span class="mx-2">
                                        {{ connection.connectedWith.firstName }} {{ connection.connectedWith.lastName }}
                                    </span>
                            </td>

                            <td v-if="connectionType === 'WITH_DOCTOR'">
                                <ul v-for="spec in connection.connectedWith.doctorProfile.specializations"
                                    v-bind:key="spec.id"
                                    class="specialization-list"
                                >
                                    <li>{{ spec.name }}</li>
                                </ul>
                            </td>
                            <td class="align-right">
                                <button v-if="connectionType === 'WITH_TRAINER'" class="btn-white m-r-5 btn-hover"
                                        data-bs-target="#createTrainingPlanRequestModal" data-bs-toggle="modal"
                                        @click="selectTrainer(connection.connectedWith)">
                                    <font-awesome-icon :icon="['fa', 'comments']"/>
                                </button>
                                <button v-if="selectedAcceptState" class="btn-white m-r-5 btn-hover"
                                        @click="getConversation(getOtherUserId(connection))">
                                    <font-awesome-icon :icon="['fa', 'comments']"/>
                                </button>
                                <button class="btn-white btn-hover"
                                        data-bs-target="#deleteUserConnectionModal" data-bs-toggle="modal"
                                        @click="selectUserConnection(connection)">
                                    <font-awesome-icon :icon="['fa', 'trash']"/>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div v-if="userConnections && userConnections.length === 0" class="row mb-2">
                        Brak wpisów.
                    </div>
                </div>
            </div>

            <div v-if="this.navigation.totalPages > 0" class="row w-100 mt-3">
                <nav>
                    <ul class="pagination justify-content-center my-auto">
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a aria-disabled="true" class="page-link" tabindex="-1" @click="goToPage(0)">
                                <font-awesome-icon :icon="['fa', 'fast-backward']"/>
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a aria-disabled="true" class="page-link" tabindex="-1"
                               @click="goToPage(navigation.currentPage-1)">
                                <font-awesome-icon :icon="['fa', 'chevron-left']"/>
                            </a>
                        </li>
                        <li v-for="page in navigation.pagesNavbar" :key="page"
                            class="page-item telemedic-page" v-bind:class="{'active' : navigation.currentPage === page,
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}"
                        >
                            <a class="page-link" @click="goToPage(page)">
                                {{ page + 1 }}
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                <font-awesome-icon :icon="['fa', 'chevron-right']"/>
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                                <font-awesome-icon :icon="['fa', 'fast-forward']"/>
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
import moment from "moment";

export default {
    name: 'UserConnectionsFromMeComponent',
    components: {
        UserAvatarComponent
    },
    props: {
        connectionType: String
    },
    data() {
        return {
            componentError: null,
            selectedAcceptState: true,
            navigation: {},
            userConnections: {},
            userId: this.$store.getters.getProfileId,
            selectedUserConnection: {},
            trainer: {},
            submittingRequest: false,
            successSubmitRequest: false,
            errorSubmitRequest: false,
            requestMessage: '',
            request: {
                beginningDate: moment().clone().isoWeekday(1).startOf('day').toDate()
            }

        }
    },
    watch: {
        selectedAcceptState: function () {
            this.getUserConnections();
        },
    },
    methods: {
        isConnectionTypeCorrect() {
            if (this.connectionType === "WITH_USER" || this.connectionType === "WITH_DOCTOR" ||
                this.connectionType === "WITH_DIETICIAN" || this.connectionType === "WITH_TRAINER") {
                this.componentError = false;
                this.getUserConnections();
            } else {
                this.componentError = true;
            }
        },
        getUserConnections() {
            this.axios.get(`${this.apiURL}profile-connections/from-me/type/${this.connectionType}/`
                + `accepted/${this.selectedAcceptState}?page=${this.navigation.toGoPage ?? 0}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.navigation = response.data;
                    this.getPagesNavbar();
                    this.navigation.toGoPage = this.navigation.currentPage;
                    this.userConnections = response.data.objects;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getPagesNavbar() {
            this.navigation.pagesNavbar = []
            if (this.navigation.currentPage > 1)
                this.navigation.pagesNavbar.push(this.navigation.currentPage - 2);
            if (this.navigation.currentPage !== 0)
                this.navigation.pagesNavbar.push(this.navigation.currentPage - 1);
            for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                this.navigation.pagesNavbar.push(i);
                if (i === this.navigation.currentPage + 2)
                    break;
            }
        },
        goToPage(pageNo) {
            this.navigation.toGoPage = pageNo;
            this.getUserConnections();
        },
        isPageFirst() {
            return this.navigation.currentPage === 0;
        },
        isPageLast() {
            return this.navigation.currentPage === this.navigation.totalPages - 1;
        },
        selectUserConnection(connection) {
            this.selectedUserConnection = connection;
        },
        selectTrainer(trainer) {
            this.trainer = trainer;
        },
        deleteUserConnection() {
            if (this.selectedUserConnection) {
                this.axios({
                    method: 'delete',
                    url: `${this.apiURL}profile-connections/${this.selectedUserConnection.id}`,
                    headers: {Authorization: 'Bearer ' + this.$store.getters.getToken},
                }).then(() => {
                    this.getUserConnections();
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        getOtherUserId(connection) {
            return connection.profile.id !== this.userId ? connection.profile.id : connection.connectedWith.id;
        },
        getConversation(profileId) {
            this.axios.get(`${this.apiURL}conversations/profile/${profileId}/type/${this.connectionType}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.openConversation(response.data.id);
                })
                .catch(e => {
                    console.log(e);
                    this.addConversation(profileId);
                })
        },
        addConversation(otherUserId) {
            const data = {
                "connectionType": this.connectionType,
                "firstProfile": {
                    "id": this.userId,
                },
                "secondProfile": {
                    "id": otherUserId,
                },
            }

            this.axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken},
                url: `${this.apiURL}conversations`,
                data: data
            })
                .then(response => {
                    this.openConversation(response.data.id);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        openConversation(conversationId) {
            this.$emit('open-conversation', conversationId);
        },
        clearStatus() {
            this.errorSubmitRequest = false
            this.submittingRequest = false
        },
        sendTrainingPlanRequest() {
            this.submittingRequest = true
            this.clearStatus()
            if (this.invalidMessage) {
                this.errorSubmitRequest = true
                return
            }
            const data = {
                "message": this.requestMessage,
                "trainerId": this.trainer.id,
                "beginningDate": this.request.beginningDate
            }

            const url = `${this.apiURL}sport/training-plan/send-request`
            const token = this.$store.getters.getToken;
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successSubmitRequest = true
                this.clearInputs()
            }).catch(error => {
                console.log(error)
            });
            this.submittingRequest = false
        }
    },
    created() {
        this.isConnectionTypeCorrect();
    },
    computed: {
        invalidMessage() {
            return this.requestMessage === ''
        }
    }
}
</script>

<style scoped>

.m-r-5 {
    margin-right: 5px
}

.btn-panel-telemedic {
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

.connections-table {
    color: white;
    margin-top: 20px;
    text-align: left;
}

.connections-table tbody tr:hover {
    background-color: var(--TELEMEDIC);
}

.connections-table-sport tbody tr:hover {
    background-color: var(--SPORT);
}

.connections-table-diet tbody tr:hover {
    background-color: var(--DIET);
}

.specialization-list {
    list-style: none outside none;
    padding: 0;
    margin: 0;
}

a {
    color: white;
    text-decoration: none;
}

.form-label {
    color: var(--GREY3);
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}

.submit-correct {
    color: var(--TELEMEDIC);
    /*font-weight: bold;*/
}
</style>
