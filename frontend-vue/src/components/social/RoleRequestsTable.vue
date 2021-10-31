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
                        <font-awesome-icon :icon="['far', 'file-pdf']" size="2x" class="navbar-icon"/>
                    </td>
                    <td>{{ req.status }}</td>
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
    }
}
</script>

<style scoped>

</style>