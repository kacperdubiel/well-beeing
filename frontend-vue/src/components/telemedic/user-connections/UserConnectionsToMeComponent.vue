<template>
    <div class="container-fluid">
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false">
            <div v-if="connectionType !== 'WITH_USER' || !this.fromNutritionPlans" class="row justify-content-between">
                <div v-if="connectionType !== 'WITH_USER'"  class="col-10 col-md-7">
                    <select class="form-select" v-model="selectedAcceptState">
                        <option :value="true">
                            {{connectionType === 'WITH_TRAINER' ? 'Moi podopieczni' : 'Moi pacjenci'}}
                        </option>
                        <option :value="false">
                            Oczekujące zgłoszenia
                        </option>
                    </select>
                </div>
            </div>

            <!-- Modal - DeleteUserConnection -->
            <div v-if="!this.fromNutritionPlans" class="modal fade" id="deleteUserConnectionModal" tabindex="-1" aria-labelledby="deleteUserConnectionModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="deleteUserConnectionModalLabel">
                                Usuń połączenie
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span v-if="connectionType !== 'WITH_USER'">
                                            Czy na pewno chcesz usunąć pacjenta?
                                        </span>
                                        <span v-else>
                                            Czy na pewno chcesz usunąć zaproszenie do znajomych?
                                        </span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel- p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button :class="{
                                                        'btn-panel-telemedic': connectionType === 'WITH_DOCTOR',
                                                        'btn-panel-sport': connectionType === 'WITH_TRAINER',
                                                        'btn-panel-diet': connectionType === 'WITH_DIETICIAN',
                                                        'btn-panel-social': connectionType === 'WITH_USER'}"
                                                    class="btn-panel-telemedic p-2"
                                                    data-bs-dismiss="modal"
                                                    @click="deleteUserConnection">
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

            <!-- Modal - AcceptUserConnection -->
            <div v-if="!this.fromNutritionPlans" class="modal fade" id="acceptUserConnectionModal" tabindex="-1" aria-labelledby="acceptUserConnectionModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="acceptUserConnectionModalLabel">
                                Zaakceptuj połączenie
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        <span v-if="connectionType !== 'WITH_USER'">
                                            Czy na pewno chcesz zaakceptować pacjenta?
                                        </span>
                                        <span v-else>
                                            Czy na pewno chcesz zaakceptować zaproszenie do znajomych?
                                        </span>
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel- p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button :class="{
                                                        'btn-panel-telemedic': connectionType === 'WITH_DOCTOR',
                                                        'btn-panel-sport': connectionType === 'WITH_TRAINER',
                                                        'btn-panel-diet': connectionType === 'WITH_DIETICIAN',
                                                        'btn-panel-social': connectionType === 'WITH_USER'}"
                                                    class="btn-panel-telemedic p-2"
                                                    data-bs-dismiss="modal"
                                                    @click="acceptUserConnection">
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
                    <table
                        v-if="userConnections && userConnections.length > 0"
                        class="table connections-table" :class="{
                        'connections-table-telemedic': connectionType === 'WITH_DOCTOR',
                        'connections-table-sport': connectionType === 'WITH_TRAINER',
                        'connections-table-diet': connectionType === 'WITH_DIETICIAN',
                        'connections-table-social': connectionType === 'WITH_USER',
                        'connections-table-from-nutrition-plans' : fromNutritionPlans}">

                        <thead>
                            <tr>
                                <th scope="col">Imię i nazwisko</th>
                                <th v-if="!this.fromNutritionPlans" scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="connection in userConnections" v-bind:key="connection.id">
                                <td class="clickable" @click="$emit('open-profile', connection.profile.id, this.selectedAcceptState)">
                                    <user-avatar-component :profileId="connection.profile.id"
                                                           :isActive="this.selectedAcceptState && this.$func_global.getIsActive5minutes(connection.profile.lastRequestTime)"
                                                           :height="40" :width="40"
                                    />
                                    <span class="mx-2">
                                        {{ connection.profile.firstName }} {{ connection.profile.lastName }}
                                    </span>
                                </td>

<!--                                <td class="align-right align-middle">-->
                                <td v-if="!this.fromNutritionPlans" class="align-right align-middle">
                                    <button v-if="selectedAcceptState && connectionType === 'WITH_DOCTOR'" class="btn-white m-r-5 btn-hover"
                                            @click="$emit('open-profile', connection.profile.id, this.selectedAcceptState)">
                                        <font-awesome-icon :icon="['fa', 'chart-bar']" />
                                    </button>
                                    <button v-if="selectedAcceptState" class="btn-white m-r-5 btn-hover"
                                            @click="getConversation(connection.profile.id)">
                                        <font-awesome-icon :icon="['fa', 'comments']" />
                                    </button>
                                    <button v-if="!selectedAcceptState" class="btn-white m-r-5 btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#acceptUserConnectionModal"
                                            @click="selectUserConnection(connection)">
                                        <font-awesome-icon :icon="['fa', 'check']" />
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
                    <div v-else-if="userConnections && userConnections.length === 0" class="container mt-5">
                        <span v-if="this.connectionType === 'WITH_USER'">Nie posiadasz żadnych zaproszeń oczekujących na potwierdzenie.</span>
                        <span v-else>Brak wpisów.</span>
                    </div>
                </div>
            </div>

            <div v-if="this.navigation.totalPages > 1" class="row w-100 mt-3">
                <nav>
                    <ul class="pagination justify-content-center my-auto">
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN',
                        'social-page': connectionType === 'WITH_USER'}">
                            <a class="page-link" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'fast-backward']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageFirst(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN',
                        'social-page': connectionType === 'WITH_USER'}">
                            <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                                <font-awesome-icon :icon="['fa', 'chevron-left']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'active' : navigation.currentPage === page,
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN',
                        'social-page': connectionType === 'WITH_USER'}"
                            v-for="page in navigation.pagesNavbar" :key="page"
                        >
                            <a class="page-link" @click="goToPage(page)" >
                                {{page+1}}
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN',
                        'social-page': connectionType === 'WITH_USER'}">
                            <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                <font-awesome-icon :icon="['fa', 'chevron-right']" />
                            </a>
                        </li>
                        <li class="page-item telemedic-page" v-bind:class="{'disabled' : isPageLast(),
                        'sport-page' : connectionType === 'WITH_TRAINER',
                        'telemedic-page' : connectionType === 'WITH_DOCTOR',
                        'diet-page' : connectionType === 'WITH_DIETICIAN',
                        'social-page': connectionType === 'WITH_USER'}">
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
    name: 'UserConnectionsToMeComponent',
    components: {
        UserAvatarComponent
    },
    props: {
        connectionType: String,
        fromNutritionPlans: Boolean
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
            if(this.connectionType === "WITH_USER" || this.connectionType === "WITH_DOCTOR" ||
                this.connectionType === "WITH_DIETICIAN" || this.connectionType === "WITH_TRAINER")
            {
                this.componentError = false;
                this.getUserConnections();
            } else {
                this.componentError = true;
            }
        },
        getUserConnections() {
            this.axios.get(`${this.apiURL}profile-connections/to-me/type/${this.connectionType}/`
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
                    if(this.userConnections.length <= 1 && this.navigation.currentPage > 0){
                        this.goToPage(this.navigation.currentPage-1);
                    } else {
                        this.getUserConnections();
                    }
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        acceptUserConnection(){
            if(this.selectedUserConnection){
                this.axios({
                    method: 'put',
                    url: `${this.apiURL}profile-connections/${this.selectedUserConnection.id}/mark-as-accepted`,
                    headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                }).then(() => {
                    if(this.userConnections.length <= 1 && this.navigation.currentPage > 0){
                        this.goToPage(this.navigation.currentPage-1);
                    } else {
                        this.getUserConnections();
                    }
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        getConversation(profileId){
            this.axios.get(`${this.apiURL}conversations/profile/${profileId}/type/${this.connectionType}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.openConversation(response.data.id)
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
                    "id": otherUserId,
                },
                "secondProfile": {
                    "id": this.userId,
                },
            }

            this.axios({
                method: 'post',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}conversations`,
                data: data
            })
                .then(response => {
                    this.openConversation(response.data.id)
                })
                .catch(e => {
                    console.log(e);
                })
        },
        openConversation(conversationId){
            this.$emit('open-conversation', conversationId)
        }
    },
    created(){
        this.isConnectionTypeCorrect();
        if (this.connectionType === 'WITH_USER')
            this.selectedAcceptState = false
    },
}
</script>

<style scoped>

button[class^="btn-panel-"] {
    font-size: medium;
}

.m-r-5 {
    margin-right: 5px
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

.user-name-link span {
    cursor: pointer;
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

.connections-table-social tbody tr:hover {
    background-color: var(--DARK-YELLOW);
}

.connections-table-from-nutrition-plans {
    color: black;
}

</style>