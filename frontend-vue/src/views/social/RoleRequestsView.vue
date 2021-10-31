<template>
    <div class="section-bg">
        <h4>
            Złóż prośbę o weryfikację konta specjalisty
        </h4>
        <p>
            1. Kim jesteś
        </p>
        <div class="row ms-auto">
            <div class="col-4" v-if="!this.$store.getters.getRoles.includes('ROLE_DIETICIAN')">
                <button class="btn-panel-" :class="{ 'dietician' : this.roleRequest.role === 'ROLE_DIETICIAN'}" @click="selectRole('ROLE_DIETICIAN')">
                    DIETETYK
                </button>
            </div>
            <div class="col-4" v-if="!this.$store.getters.getRoles.includes('ROLE_TRAINER')">
                <button class="btn-panel-" :class="{ 'trainer' : this.roleRequest.role === 'ROLE_TRAINER'}" @click="selectRole('ROLE_TRAINER')">
                    TRENER
                </button>
            </div>
            <div class="col-4" v-if="!this.$store.getters.getRoles.includes('ROLE_DOCTOR')" >
                <button class="btn-panel-" :class="{ 'doctor' : this.roleRequest.role === 'ROLE_DOCTOR'}" @click="selectRole('ROLE_DOCTOR')">
                    LEKARZ
                </button>
            </div>
        </div>
        <p>
            2. Dołącz dowód swoich kompetencjiaaaa
        </p>
        <div class="mb-3">
            <label for="formFile" class="form-label">Default file input example</label>
            <input class="form-control" type="file" ref="myfile" id="formFile" accept="application/pdf">
            <button @click="submitRoleRequest">Wyślij prośbę</button>
            <button @click="downloadFile">Pobierz</button>
        </div>

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
            requestId:0
        }
    },
    methods: {
        selectRole(role) {
            this.roleRequest.role = role
        },
        submitRoleRequest () {
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            this.axios.post(url, this.roleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.requestId = response.data['roleReqId']
                this.importData()
            }).catch(error => {
                console.log(error.response.status)
            });
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
</style>