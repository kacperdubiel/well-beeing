<template>
    <div class="section-bg">
        <div v-if="!['ROLE_TRAINER', 'ROLE_DOCTOR', 'ROLE_DIETICIAN'].every(elem => this.$store.getters.getRoles.includes(elem))">
            <div class="row">
                <h4 class="pt-3">
                    Złóż prośbę o weryfikację konta specjalisty
                </h4>
            </div>

            <div class="row">
                <h5 class="pt-4 step">
                    1. Wybierz rolę, o którą się ubiegasz
                </h5>
            </div>

            <div class="row justify-content-center">
                <div class="col-lg-3 col-md-12 my-1 mt-lg-0" v-if="!this.$store.getters.getRoles.includes('ROLE_DIETICIAN')">
                    <button
                        class="btn-panel-"
                        :class="{ 'dietician' : this.roleRequest.role === 'ROLE_DIETICIAN'}"
                        @click="selectRole('ROLE_DIETICIAN'); clearStatus()">
                        DIETETYK
                    </button>
                </div>
                <div class="col-lg-3 col-md-12 my-1 mt-lg-0" v-if="!this.$store.getters.getRoles.includes('ROLE_TRAINER')">
                    <button
                        class="btn-panel-"
                        :class="{ 'trainer' : this.roleRequest.role === 'ROLE_TRAINER'}"
                        @click="selectRole('ROLE_TRAINER'); clearStatus()">
                        TRENER
                    </button>
                </div>
                <div class="col-lg-3 col-md-12 my-1 mt-lg-0" v-if="!this.$store.getters.getRoles.includes('ROLE_DOCTOR')" >
                    <button
                        class="btn-panel-"
                        :class="{ 'doctor' : this.roleRequest.role === 'ROLE_DOCTOR'}"
                        @click="selectRole('ROLE_DOCTOR'); clearStatus()">
                        LEKARZ
                    </button>
                </div>
            </div>

            <div class="row mt-3">
                <h5 class="t-2 step">
                    2. Dołącz dowód swoich kompetencji (.pdf)
                </h5>
            </div>

            <div class="row">
                <div class="col-9 col-lg-7 offset-1">
                    <input
                        class="form-control"
                        type="file"
                        ref="myfile"
                        id="formFile"
                        accept="application/pdf"
                        @focus="clearStatus"
                    >
                </div>
            </div>

            <div class="row mt-3" v-if="roleRequest.role === 'ROLE_DOCTOR'">
                <h5 class="t-2 step">
                    3. Wybierz specjalizację
                </h5>
            </div>
            <div class="row" v-if="roleRequest.role === 'ROLE_DOCTOR'">
                <div class="col-9 col-lg-7 offset-1">
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="specialization.id"
                    >
                        <option v-for="spec in doctorSpecializations" :key="spec.name" :value="spec.id">{{ spec.name }}</option>
                    </select>
                </div>
            </div>

            <div v-if="errorRequest" class="row mt-3 text-start ms-4">
                <p class="has-error m-0">
                    Proszę uzupełnić wszystkie dane!
                </p>
            </div>

            <div class="row mt-5 pb-3">
                <div class="col-4 offset-7">
                    <button class="btn-panel-social" @click="submitRoleRequest">Wyślij prośbę</button>
                </div>

            </div>
        </div>

        <div class="row justify-content-center pt-3" v-if="roleRequests.length !== 0">
            <div class="col-11">
                <role-requests-table :role-requests-source="roleRequests" :key="roleRequests" @cancel:roleRequest="cancelRoleRequest"/>
            </div>
        </div>

    </div>
</template>

<script>
import RoleRequestsTable from "@/components/social/role-requests/RoleRequestsTable";
export default {
    name: "RoleRequestView",
    components: {
        RoleRequestsTable
    },
    data () {
        return {
            roleRequest: {
                role: "",
                documentImgPath: ""
            },
            roleRequests: [],
            submittingRequest: false,
            errorRequest: false,
            successRequest: false,
            requestId: 0,
            doctorSpecializations: [],
            specialization: {
                id: ""
            }
        }
    },
    methods: {
        selectRole(role) {
            this.roleRequest.role = role
        },
        submitRoleRequest () {
            this.submittingRequest = true
            this.clearStatus()
            console.log(this.$refs.myfile.files[0])
            if (this.$refs.myfile.files.length > 0) {
                this.roleRequest.documentImgPath = this.$refs.myfile.files[0].name
            }
            if (this.invalidRole || this.invalidFile) {
                this.errorRequest = true
                // console.log("wielbłąd")
                return
            }
            //clearinputs
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            this.axios.post(url, this.roleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.requestId = response.data['roleReqId']
                this.$func_global.importData(this.$refs.myfile, this.$store.getters.getToken, 'roleRequest', this.requestId)
                this.clearInputs()
                this.getMyRoleRequests()
            }).catch(error => {
                console.log(error.response)
            });

            this.submittingRequest = false
        },



        clearStatus() {
            this.submittingRequest = false
            this.errorRequest = false
            this.successRequest = false
        },
        clearInputs () {
            this.roleRequest.role = ""
            document.getElementById("formFile").value = ""
        },
        getMyRoleRequests() {
            const url = `${this.apiURL}role-request/my`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.roleRequests = response.data
            })
        },
        cancelRoleRequest(id) {
            const url = `${this.apiURL}role-request/${id}/cancel`
            const token = this.$store.getters.getToken;
            console.log('tu jestem')
            this.axios.patch(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.getMyRoleRequests()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        getUserInfo () {
            const url = `${this.apiURL}profile/my`
            const token = this.$store.getters.getToken;

            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                let roles = []
                response.data['roles'].forEach((e) => {
                    roles.push(e['role'])
                })
                this.$store.commit('setRoles', roles);
                console.log('role', this.$store.getters.getRoles)
            }).catch(error => {
                console.log(error.response);
            });
        },
        getDoctorSpecializations() {
            const url = `${this.apiURL}doctor-specializations`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.doctorSpecializations = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        }
    },
    computed: {
        invalidRole() {
            return !(this.roleRequest.role === 'ROLE_DOCTOR' || this.roleRequest.role === 'ROLE_DIETICIAN' || this.roleRequest.role === 'ROLE_TRAINER')
        },
        invalidFile() {
            return this.roleRequest.documentImgPath === ""
        }
    },
    mounted() {
        this.getMyRoleRequests()
        this.getUserInfo()
        this.getDoctorSpecializations()
    }
}
</script>

<style scoped>
.doctor {
    background-color: var(--TELEMEDIC);
}

.dietician {
    background-color: var(--DIET);
}

.trainer {
    background-color: var(--SPORT);
}

.btn-panel- {
    width: 120px;
}

.step {
    text-align: left;
    margin-left: 5%;
}


</style>