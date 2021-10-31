<template>
    <div class="section-bg">

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

        <div class="row">
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

        <div v-if="errorRequest" class="row mt-3 text-start ms-4">
            <p class="has-error m-0">
                Proszę uzupełnić wszystkie dane!
            </p>
        </div>

        <div class="row mt-5">
            <div class="col-4 offset-7">
                <button class="btn-panel-social" @click="submitRoleRequest">Wyślij prośbę</button>
            </div>

        </div>

        <button @click="downloadFile">Pobierz</button>

    </div>
</template>

<script>
export default {
    name: "RoleRequestView",
    data () {
        return {
            roleRequest: {
                role: "",
                documentImgPath: "path"
            },
            requestId:0,
            submittingRequest: false,
            errorRequest: false,
            successRequest: false
        }
    },
    methods: {
        selectRole(role) {
            this.roleRequest.role = role
        },
        submitRoleRequest () {
            this.submittingRequest = true
            this.clearStatus()
            console.log(document.getElementById("formFile").value === "")
            if (this.invalidRole || this.invalidFile) {
                this.errorRequest = true
                console.log("wielbłąd")
                return
            }
            //clearinputs
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            this.axios.post(url, this.roleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.requestId = response.data['roleReqId']
                this.importData()
                this.clearInputs()
            }).catch(error => {
                console.log(error.response.status)
            });

            this.submittingRequest = false
        },
        downloadFile () {
            const url = `${this.apiURL}role-request/export/${this.requestId}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`, 'Accept': 'application/pdf'}, responseType: 'arraybuffer'}).then((response) => {
                console.log(response.data)
                const blob = new Blob([response.data], { type: 'application/pdf' })
                const objectUrl = window.URL.createObjectURL(blob)
                window.open(objectUrl)
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        importData() {
            let myfile = this.$refs.myfile;
            let files = myfile.files;
            let file = files[0];
            var formData = new FormData();
            formData.append("file", file);
            this.uploadFileRequest(formData)
        },
        uploadFileRequest (data) {
            const url = `${this.apiURL}role-request/import/${this.requestId}/`
            const token = this.$store.getters.getToken;
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data'}}).then((response) => {
                console.log(response.data)
            }).catch(error => {
                    console.log(error.response.status)
            });
        },
        clearStatus() {
            this.submittingRequest = false
            this.errorRequest = false
            this.successRequest = false
        },
        clearInputs () {
            this.roleRequest.role = ""
            document.getElementById("formFile").value = ""
        }
    },
    computed: {
        invalidRole() {
            return !(this.roleRequest.role === 'ROLE_DOCTOR' || this.roleRequest.role === 'ROLE_DIETICIAN' || this.roleRequest.role === 'ROLE_TRAINER')
        },
        invalidFile() {
            // return this.$refs.myfile.files[0] === null
            return document.getElementById("formFile").value === ""
        }
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