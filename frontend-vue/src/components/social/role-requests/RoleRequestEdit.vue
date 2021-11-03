<template>
    <div class="modal fade" id="roleRequestEditModal2" tabindex="-1" aria-labelledby="roleRequestEditModal2Label" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="roleRequestEditModal2Label">Edycja prośby</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                                <button class="no-bg" @click="handleDownloadFile(this.roleRequestSource.roleReqId)">
                                    <font-awesome-icon id="document-icon" :icon="['far', 'file-pdf']" size="2x" class="navbar-icon" />
                                </button>
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
            selected: "",
            editedRoleRequest: {
                role: "",
                documentImgPath: ""
            },
        }
    },
    props: {
        roleRequestSource: Object
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
            // console.log('roles: ', roles)
            //
            // console.log(this.possibleRoles)
            //
            // console.log('source role', this.roleRequestSource.role)
            // console.log('new role pre', this.editedRoleRequest.role)
            this.editedRoleRequest.role = this.roleRequestSource.role
            // console.log('new role post', this.editedRoleRequest.role)
        },

    },
    watch: {
        roleRequestSource: function() { // watch it
            this.checkPossibleRoles()
            // console.log('Prop changed: ', newVal, ' | was: ', oldVal)
        }
    },
    created() {
        this.checkPossibleRoles()
        // this.possibleRoles = ["ROLE_DIETICIAN", "ROLE_TRAINER", "ROLE_DOCTOR"]
        // this.editedRoleRequest= this.roleRequestSource
        // console.log('source', this.editedRoleRequest)

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