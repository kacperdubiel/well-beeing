<template>
    <div id="role-requests-table">
        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th>Data</th>
                    <th v-if="isAdmin">Imię i nazwisko</th>
                    <th>Rola</th>
                    <th>Dokument</th>
                    <th>Status</th>
                    <th>Specjalizacja</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="req in roleRequestsSource" :key="req.roleReqId">
                    <td>{{ this.$func_global.formatDate(req.addedDate) }}</td>
                    <td v-if="isAdmin">{{req.submitter.firstName}} {{req.submitter.lastName}}</td>
                    <td>{{ this.$func_global.mapRole(req.role) }}</td>
                    <td>
                        <button class="no-bg" @click="downloadFile(req.roleReqId)">
                            <font-awesome-icon :icon="['far', 'file-pdf']" size="2x" class="navbar-icon text-white" />
                        </button>
                    </td>
                    <td>{{ this.$func_global.mapRoleRequestStatus(req.status) }}</td>
                    <td>
                        <span v-if="req.role === 'ROLE_DOCTOR' && req.specialization !== null">{{req.specialization.name}}</span>
                        <span v-else>-</span>

                    </td>
                    <td class="text-end" v-if="!isAdmin">
                        <button class="btn-white mx-1" data-bs-toggle="modal" data-bs-target="#cancelRoleRequestModal" @click="handleGetCancel(req.roleReqId)" v-if="req.status === 'PENDING'">
                            Anuluj
                        </button>
                        <button class="btn-white mx-1" v-if="req.status === 'PENDING'" @click="handleGet(req)" data-bs-toggle="modal" data-bs-target="#roleRequestEditModal2">
                            <font-awesome-icon :icon="['fa', 'pen']" size="1x"/>
                        </button>
                        <button class="btn-white mx-1" v-if="req.status === 'REJECTED'" @click="handleGet(req)" data-bs-toggle="modal" data-bs-target="#roleRequestDetailsModal">
                            <font-awesome-icon :icon="['fa', 'question']" size="1x"/>
                        </button>
                    </td>
                    <td class="text-end" v-else>
                        <button class="btn-white mx-1" v-if="req.status === 'PENDING'" @click="handleGet(req)" data-bs-toggle="modal" data-bs-target="#roleRequestRejectModal">
                            <font-awesome-icon :icon="['fa', 'times']" size="1x" @click="handleGet(req)"/>
                        </button>
                        <button class="btn-white mx-1" v-if="req.status === 'PENDING'" @click="acceptRoleRequest(req)">
                            <font-awesome-icon :icon="['fa', 'check']" size="1x"/>
                        </button>
                    </td>

                </tr>
            </tbody>
        </table>
        <RoleRequestEdit :role-request-source="roleRequest" :refresh="openingModal" v-if="roleRequest && !isAdmin" @download:file="downloadFile"/>
        <RoleRequestDetails :role-request-source="roleRequest" v-if="roleRequest && !isAdmin"  @download:file="downloadFile"/>
        <RoleRequestReject :role-request-source="roleRequest" v-if="roleRequest && isAdmin" @reject:role-request="rejectRoleRequest"/>

        <!--        Modal - cancel role request-->
        <div class="modal fade" id="cancelRoleRequestModal" tabindex="-1" aria-labelledby="cancelRoleRequestModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="cancelRoleRequestModalLabel">
                            Anulowanie prośby o nadanie roli
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="col">
                                <div class="row">
                                    <span>
                                        Czy na pewno chcesz anulowac tę prośbę?
                                    </span>
                                </div>

                                <div class="row justify-content-end mt-3">
                                    <div class="col-3">
                                        <button class="btn-panel- p-2" data-bs-dismiss="modal">Powrót</button>
                                    </div>
                                    <div class="col-3">
                                        <button class="btn-panel-social p-2" data-bs-dismiss="modal" @click="handleCancel(idToCancel)">
                                            Potwierdź
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import RoleRequestDetails from "@/components/social/role-requests/RoleRequestDetails";
import RoleRequestEdit from "@/components/social/role-requests/RoleRequestEdit";
import RoleRequestReject from "@/components/social/role-requests/RoleRequestReject";
export default {
    name: "RoleRequestsTable",
    components: {
        RoleRequestDetails,
        RoleRequestEdit,
        RoleRequestReject
    },
    data () {
        return {
            roleRequest: Object,
            openingModal: 0,
            processedRoleRequest: Object,
            idToCancel: 0
        }
    },
    props: {
        roleRequestsSource: Array
    },
    methods: {
        downloadFile (reqId) {
            const url = `${this.apiURL}role-request/export/${reqId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPdfFile(url, token)
        },
        handleCancel (id) {
            this.$emit('cancel:roleRequest', id)
        },
        handleGetCancel (id) {
            this.idToCancel = id
        },
        handleGet(req) {
            this.roleRequest = req
            this.openingModal += 1
            console.log('Table req ', this.roleRequest)
            // this.$refs.editmodal.checkPossibleRoles()
        },
        acceptRoleRequest(req) {
            const url = `${this.apiURL}role-request/${req.roleReqId}/process`
            const token = this.$store.getters.getToken;
            this.processedRoleRequest = {
                "status": "ACCEPTED",
                "specialization": this.specialization
            }
            this.axios.patch(url, this.processedRoleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response)
                this.$parent.getRoleRequests()
            }).catch(error => {
                console.log(error.response)
            });
        },
        rejectRoleRequest(id, comment) {
            const url = `${this.apiURL}role-request/${id}/process`
            const token = this.$store.getters.getToken;
            this.processedRoleRequest = {
                "status": "REJECTED",
                "comment": comment
            }
            this.axios.patch(url, this.processedRoleRequest, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response)
                this.$parent.getRoleRequests()
            }).catch(error => {
                console.log(error.response)
            });
        },
    },
    computed: {
        isAdmin() {
            return this.$store.getters.getRoles.includes('ROLE_ADMIN')

        }
    }
}
</script>

<style scoped>
.modal-body {
    color: var(--GREY3);
    text-align: left;
}

.modal-header {
    color: var(--GREY3);
}

</style>