<template>
    <div class="modal fade" id="roleRequestEditModal2" tabindex="-1" aria-labelledby="roleRequestEditModal2Label" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="roleRequestEditModal2Label">Edycja prośby</h4>
                    <button type="button" class="btn-close" id="modal-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col fw-bolder">
                                Data:
                            </div>
                            <div class="col fw-bolder">
                                Rola:
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                {{ formatDate(this.roleRequestSource.addedDate )}}
                            </div>
                            <div class="col">
                                <select class="form-select" aria-label="Default select example" v-model="editedRoleRequest.role">
                                    <option v-for="role in this.possibleRoles" :value="role" :key="role">
                                        {{ role }}
                                    </option>

                                </select>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col fw-bolder">
                                Dokument:
                            </div>
                            <div class="col fw-bolder">
                                Status:
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div v-if="!editFile">
                                    <button class="no-bg" @click="handleDownloadFile(this.roleRequestSource.roleReqId)">
                                        <font-awesome-icon id="document-icon" :icon="['far', 'file-pdf']" size="2x" class="navbar-icon" />
                                    </button>
                                    <button class="btn-white" @click="deleteFile">
                                        Usuń plik
                                    </button>
                                </div>

                                <input v-else
                                    class="form-control"
                                    type="file"
                                    ref="editfile"
                                    id="formFile"
                                    accept="application/pdf"
                                    @focus="clearStatus"
                                >
                            </div>
                            <div class="col">
                                {{ this.roleRequestSource.status }}
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-12 fw-bolder">
                                Komentarz:
                            </div>
                            <div class="col-12">
                                {{ this.roleRequestSource.comment === "" ? "-" : this.roleRequestSource.comment }}
                            </div>
                            <div class="col-12">
                                <button class="btn-panel-social" @click="updateRoleRequest">Uaktualnij prośbę</button>
                            </div>
                        </div>
                        <div v-if="errorRequest" class="row mt-3 text-start ms-4">
                            <p class="has-error m-0">
                                Proszę uzupełnić wszystkie dane!
                            </p>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import moment from 'moment'
export default {
    name: "RoleRequestEdit",
    data () {
        return {
            possibleRoles: [],
            editedRoleRequest: {
                role: "",
                documentImgPath: ""
            },
            editFile: false,

            updatingRequest: false,
            errorRequest: false,
            requestId: 0
        }
    },
    props: {
        roleRequestSource: Object,
        refresh: Number
    },
    methods: {
        formatDate(date) {
            if (date) {
                return moment(String(date)).format('DD/MM/YYYY')
            }
        },
        handleDownloadFile(id) {
            this.$emit('download:file', id)
        },
        checkPossibleRoles() {
            const roles = ["ROLE_DIETICIAN", "ROLE_TRAINER", "ROLE_DOCTOR"]

            this.$store.getters.getRoles.forEach((elem) => {
                if (roles.includes(elem)) {
                    const indexToDelete = roles.indexOf(elem)
                    roles.splice(indexToDelete, 1)
                }
            })
            this.possibleRoles = roles

            this.editedRoleRequest.role = this.roleRequestSource.role
            this.editedRoleRequest.documentImgPath = this.roleRequestSource.documentImgPath
            this.editFile = false
        },
        deleteFile() {
            this.editFile = true
        },
        updateRoleRequest() {
            this.updatingRequest = true
            this.clearStatus()
            console.log(this.$refs.editfile.files[0])
            if (this.$refs.editfile.files.length > 0) {
                this.editedRoleRequest.documentImgPath = this.$refs.editfile.files[0].name
            }
            if (this.invalidRole || this.invalidFile) {
                this.errorRequest = true
                // console.log("wielbłąd")
                return
            }
            //clearinputs
            const url = `${this.apiURL}role-request/${this.roleRequestSource.roleReqId}`
            const token = this.$store.getters.getToken;
            this.axios.put(url, this.editedRoleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response)
                this.$func_global.importData(this.roleRequestSource.roleReqId, this.$refs.editfile, this.$store.getters.getToken)
                // this.clearInputs()
                this.$parent.$parent.getMyRoleRequests()
                document.getElementById('modal-close').click();
            }).catch(error => {
                console.log(error.response)
            });

            this.submittingRequest = false
        },
        clearStatus() {
            this.updatingRequest = false
            this.errorRequest = false
        },

    },
    watch: {
        roleRequestSource: function() {
            this.checkPossibleRoles()
        },
        refresh: function () {
            this.checkPossibleRoles()
        }
    },
    computed: {
        invalidRole() {
            return !(this.editedRoleRequest.role === 'ROLE_DOCTOR' || this.editedRoleRequest.role === 'ROLE_DIETICIAN' || this.editedRoleRequest.role === 'ROLE_TRAINER')
        },
        invalidFile() {
            return this.editedRoleRequest.documentImgPath === ""
        }
    },
    created() {
        this.checkPossibleRoles()
    },
    mounted() {
        this.editFile = false
    }
}
</script>

<style scoped>
#document-icon {
    color: var(--GREY3)
}
.modal-body {
    color: var(--GREY3);
    text-align: left;
}

.modal-header {
    color: var(--GREY3);
}
</style>