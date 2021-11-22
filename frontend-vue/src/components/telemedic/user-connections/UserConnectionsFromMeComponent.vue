<template>
    <div class="container-fluid m-3">
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false" class="container">
            <div class="row justify-content-between">
                <div class="col-10 col-md-7">
                    <select class="form-select" v-model="selectedAcceptState">
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
                    <button v-if="connectionType === 'WITH_DIETICIAN'" class="btn-card-panel-diet"
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
            <div class="modal fade" id="deleteUserConnectionModal" tabindex="-1" aria-labelledby="deleteUserConnectionModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="deleteUserConnectionModalLabel">
                                <span v-if="connectionType === 'WITH_USER'">Usuń znajomego</span>
                                <span v-if="connectionType !== 'WITH_USER'">Wypisz się od specjalisty</span>
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span v-if="connectionType === 'WITH_USER'">Czy na pewno chcesz usunąć znajomego?</span>
                                        <span v-if="connectionType !== 'WITH_USER'">Czy na pewno chcesz się wypisać?</span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="deleteUserConnection">
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

            <div class="row">
                <div class="col-12">
                    <table class="table connections-table" :class="{
                        'connections-table-telemedic': connectionType === 'WITH_DOCTOR',
                        'connections-table-sport': connectionType === 'WITH_TRAINER',
                        'connections-table-diet': connectionType === 'WITH_DIETICIAN'}">
                        <thead>
                            <tr>
                                <th scope="col">Imię i nazwisko</th>
                                <th v-if="connectionType === 'WITH_DOCTOR'" scope="col" class="w-15">Specjalizacja</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="connection in userConnections" v-bind:key="connection.id">
                                <td v-if="connection.profile.id !== userId"
                                    @click="$emit('open-profile', connection.profile.id, this.selectedAcceptState)"
                                    class="clickable"
                                >
                                    <user-avatar-component :profileId="connection.profile.id"
                                                           :isActive="this.$func_global.getIsActive5minutes(connection.profile.lastRequestTime)"
                                                           :height="40" :width="40"
                                    />
                                    <span class="mx-2">
                                        {{ connection.profile.firstName }} {{ connection.profile.lastName }}
                                    </span>
                                </td>
                                <td v-else
                                    @click="$emit('open-profile', connection.connectedWith.id, this.selectedAcceptState)"
                                    class="clickable"
                                >
                                    <user-avatar-component :profileId="connection.connectedWith.id"
                                                           :isActive="this.$func_global.getIsActive5minutes(connection.connectedWith.lastRequestTime)"
                                                           :height="40" :width="40"
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
                                    <button v-if="selectedAcceptState" class="btn-white m-r-5 btn-hover"
                                            @click="getConversation(getOtherUserId(connection))">
                                        <font-awesome-icon :icon="['fa', 'comments']" />
                                    </button>
                                    <button class="btn-white btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#deleteUserConnectionModal"
                                            @click="selectUserConnection(connection)">
                                        <font-awesome-icon :icon="['fa', 'trash']" />
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
                            <a class="page-link" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'fast-backward']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'chevron-left']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'active' : navigation.currentPage === page,
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}"
                            v-for="page in navigation.pagesNavbar" :key="page"
                        >
                            <a class="page-link" @click="goToPage(page)" >
                                {{page+1}}
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
                            <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                <font-awesome-icon :icon="['fa', 'chevron-right']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN'}">
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
        }
    },
    watch: {
        selectedAcceptState: function (){
            this.getUserConnections();
        },
    },
    methods: {
        isConnectionTypeCorrect(){
            if(this.connectionType === "WITH_USER"       || this.connectionType === "WITH_DOCTOR" ||
                this.connectionType === "WITH_DIETICIAN" || this.connectionType === "WITH_TRAINER")
            {
                this.componentError = false;
                this.getUserConnections();
            } else {
                this.componentError = true;
            }
        },
        getProfile(){
            this.axios.get(`${this.apiURL}profile/my`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.userId = response.data.id;
                    this.getUserConnections();
                })
                .catch(e => {
                    console.log(e);
                })
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
            this.getUserConnections();
        },
        isPageFirst(){
            return this.navigation.currentPage === 0;
        },
        isPageLast(){
            return this.navigation.currentPage === this.navigation.totalPages - 1;
        },
        selectUserConnection(connection){
            this.selectedUserConnection = connection;
        },
        deleteUserConnection(){
            if(this.selectedUserConnection){
                this.axios({
                    method: 'delete',
                    url: `${this.apiURL}profile-connections/${this.selectedUserConnection.id}`,
                    headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                }).then(() => {
                    this.getUserConnections();
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        getOtherUserId(connection){
            return connection.profile.id !== this.userId ? connection.profile.id : connection.connectedWith.id;
        },
        getConversation(profileId){
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
        addConversation(otherUserId){
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
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
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
        openConversation(conversationId){
            this.$emit('open-conversation', conversationId);
        }
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

</style>