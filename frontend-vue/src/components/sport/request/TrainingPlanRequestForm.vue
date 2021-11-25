<template>
    <div v-if="requestSource != null" id="modal-container" class="container-fluid">
        <header>
            <div class="row mb-4 mt-3">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" class="clickable" size="2x"
                                       @click="$router.go(-1)"/>
                </div>
            </div>
        </header>
        <div class="row col-11 mx-auto justify-content-between text-start">
            <div class="col-4 ps-0">
                <p class="form-label">Podopieczny</p>
                <p class="ps-0">{{ requestSource.submitter.firstName }} {{
                        requestSource.submitter.lastName
                    }}</p>
            </div>
            <div class="col-4">
                <p class="form-label">Data złożenia prośby</p>
                <p>{{ this.$func_global.formatDateTime(requestSource.requestDate) }}</p>
            </div>
            <div class="col-4">
                <p class="form-label">Status</p>
                <p>{{ this.$func_global.mapPlanRequestStatus(requestSource.requestStatus) }}</p>
            </div>
        </div>
        <div v-if="requestSource.beginningDate != null" class="row col-11 mx-auto text-start">
            <p class="form-label">Tydzień treningu</p>
            <p class="ps-0">{{
                    this.$func_global.getWeekRangeFromMonday(new Date(requestSource.beginningDate))
                }}</p>
        </div>
        <div class="row col-11 mx-auto text-start">
            <p class="form-label">Wiadomość od podopiecznego</p>
            <p class="ps-0">{{ requestSource.message }}</p>
        </div>
        <div class="row col-11 justify-content-end">
            <div class="col-2 text-end p-0">
                <button
                    v-if="!['DENIED','CANCELLED', 'COMPLETED'].includes(requestSource.requestStatus)"
                    class="register-btn btn-panel-sport p-2" @click="setStatus(requestSource.id, 'DENIED')">Odrzuć
                </button>
            </div>
            <div class="col-2 text-end p-0">
                <button v-if="['SEEN','SUBMITTED'].includes(requestSource.requestStatus)"
                        class="register-btn btn-panel-sport p-2" @click="setStatus(requestSource.id, 'ACCEPTED')">
                    Zaakceptuj
                </button>
            </div>
        </div>
        <div v-if="requestSource.requestStatus === 'ACCEPTED'" class="row col-12 mt-3">
            <trainer-user-profile-view :beginning-date="requestSource.beginningDate"
                                       :create-plan="true"
                                       :request-id=requestId
                                       :userId="requestSource.submitter.id"/>
        </div>
    </div>
</template>

<script>
import TrainerUserProfileView from "@/views/sport/trainers/TrainerUserProfileView";
import moment from "moment";

export default {
    name: "TrainingPlanRequestForm",
    components: {TrainerUserProfileView},
    data() {
        return {
            requestSource: null
        }
    },
    props: {
        requestId: String,
    },
    methods: {
        moment: function () {
            return moment();
        },
        async setStatus(requestId, newStatus) {
            const url = `${this.apiURL}sport/training-plan/request`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            const body = {
                requestId: requestId,
                newStatus: newStatus
            }
            await this.axios.patch(url, body, {
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                console.log(response)
                this.getRequestsWithFilters()
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
    watch: {
        requestId: function () {
            this.requestSource = this.$store.getters.getSelectedRequest;
        }
    },
    mounted() {
        this.requestSource = this.$store.getters.getSelectedRequest;
    }
}
</script>

<style scoped>
.form-label {
    color: white;
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}

p {
    color: white;
}

.modal-title {
    color: var(--SPORT);
}
</style>
