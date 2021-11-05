<template>
    <div class="row justify-content-center">
        <div class="col-11">
            <role-requests-table :role-requests-source="roleRequests" :key="roleRequests"/>
        </div>

    </div>
</template>

<script>
import RoleRequestsTable from "@/components/social/role-requests/RoleRequestsTable";
export default {
    name: "RoleRequestsViewAdmin",
    components: {
        RoleRequestsTable
    },
    data () {
        return {
            roleRequests: []
        }
    },
    methods: {
        getRoleRequests() {
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data['requests'])
                this.roleRequests = response.data['requests']
            })
        },
    },
    mounted() {
        this.getRoleRequests()
    }
}
</script>

<style scoped>

</style>