<template>
    <div id="role-requests-table">
        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Rola</th>
                    <th>Dokument</th>
                    <th>Status</th>

                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="req in roleRequestsSource" :key="req.roleReqId">
                    <td>{{ formatDate(req.addedDate) }}</td>
                    <td>{{ req.role }}</td>
                    <td>
                        <button class="no-bg" @click="downloadFile(req.roleReqId)">
                            <font-awesome-icon :icon="['far', 'file-pdf']" size="2x" class="navbar-icon text-white" />
                        </button>
                    </td>
                    <td>{{ req.status }}</td>
                    <td class="text-end">
                        <button class="btn-white mx-1" @click="handleCancel(req.roleReqId)" v-if="req.status === 'PENDING'">
                            Anuluj
                        </button>
                        <button class="btn-white mx-1" v-if="req.status === 'PENDING'">
                            <font-awesome-icon :icon="['fa', 'pen']" size="1x" class="navbar-icon" />
                        </button>
                        <button class="btn-white mx-1">
                            <font-awesome-icon :icon="['fa', 'info']" size="1x" class="navbar-icon" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import moment from 'moment'
export default {
    name: "RoleRequestsTable",
    props: {
        roleRequestsSource: Array
    },
    methods: {
        formatDate(date) {
            if (date) {
                return moment(String(date)).format('DD/MM/YYYY')
            }
        },
        downloadFile (reqId) {
            const url = `${this.apiURL}role-request/export/${reqId}`
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
        handleCancel (id) {
            if (confirm('Na pewno chcesz anulować prośbę?')) {
                this.$emit('cancel:roleRequest', id)
            } else {
                console.log('Anuluj')
            }
        }
    }
}
</script>

<style scoped>
.no-bg {
    background-color: transparent;
    border: none;
}

</style>