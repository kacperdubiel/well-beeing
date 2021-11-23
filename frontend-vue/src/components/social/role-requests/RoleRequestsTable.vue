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
                    <td class="text-end" v-if="!isAdmin">
                        <button class="btn-white mx-1" @click="handleCancel(req.roleReqId)" v-if="req.status === 'PENDING'">
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
            processedRoleRequest: Object
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
            if (confirm('Na pewno chcesz anulować prośbę?')) {
                this.$emit('cancel:roleRequest', id)
            } else {
                console.log('Anuluj')
            }
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
                "status": "ACCEPTED"
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
        }
    },
    computed: {
        isAdmin() {
            return this.$store.getters.getRoles.includes('ROLE_ADMIN')

        }
    }
}
</script>

<style scoped>


</style>