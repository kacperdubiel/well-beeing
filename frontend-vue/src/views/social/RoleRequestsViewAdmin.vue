<template>
    <div class="row justify-content-center">
        <div class="col-10">
            <div>
                <div class="row mb-4">
                    <div class="col-md-2 col-sm-6 text-start">
                        <label for="status-select" class="form-label">Status:</label>
                        <select
                            v-model="filters.statusFilter"
                            class="p-2 w-100"
                            id="status-select"
                            @change="getRoleRequests()"
                        >
                            <option v-for="status in filters.allStatusFilters" :key="status.label" :value="status.value">{{ status.label }}</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-6 text-start">
                        <label for="role-select" class="form-label">Rola:</label>
                        <select
                            v-model="filters.roleFilter"
                            class="p-2 w-100"
                            id="role-select"
                            @change="getRoleRequests()"
                        >
                            <option v-for="role in filters.allRoleFilters" :key="role.label" :value="role.value">{{ role.label }}</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-6 text-start ms-auto">
                        <label for="page-size-select" class="form-label">Rozmiar strony:</label>
                        <select
                            v-model="userNavigation.pageSize"
                            class="p-2 w-100"
                            id="page-size-select"
                            @change="getRoleRequests()"
                        >
                            <option v-for="size in userNavigation.pageSizeOptions" :key="size" :value="size">{{ size }}</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-6 text-start">
                        <label for="sorting-select" class="form-label">Sortowanie:</label>
                        <select
                            v-model="filters.sortBy"
                            class="p-2 w-100"
                            id="sorting-select"
                            @change="getRoleRequests()"
                        >
                            <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{ sort.label }}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <role-requests-table :role-requests-source="roleRequests" :key="roleRequests"/>
            </div>
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
            roleRequests: [],
            filters: {
                allRoleFilters: [
                    {label:'-', value:''},
                    {label:'Dietetyk', value:'ROLE_DIETICIAN'},
                    {label:'Lekarz', value:'ROLE_DOCTOR'},
                    {label:'Trener', value:'ROLE_TRAINER'}
                ],
                roleFilter: '',
                allStatusFilters: [
                    {label:'-', value:''},
                    {label:'Zaakceptowane', value:'ACCEPTED'},
                    {label:'Odrzucone', value:'REJECTED'},
                    {label:'Oczekujące', value:'PENDING'},
                    {label:'Anulowane', value:'CANCELLED'}
                ],
                statusFilter: '',
                sortByOptions: [
                    {label:'Od najnowszych', value:'addedDate,desc'},
                    {label:'Od najstarszych', value:'addedDate,asc'}],
                sortBy: 'addedDate,desc'
            },
            navigation: {
                totalElements: 0,
                totalPages: 0,
                isFirst: false,
                isLast: false,
                isEmpty: false,
                currentPage: 0,
                pageSize: 20
            },
            userNavigation: {
                goToPage: 0,
                pageSizeOptions: [10, 20, 50],
                pageSize: 20
            }
        }
    },
    methods: {
        getRoleRequests() {
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            const myParams = {
                page: this.userNavigation.goToPage,
                size: this.userNavigation.pageSize,
                sort: this.filters.sortBy,
                status: this.filters.statusFilter,
                role: this.filters.roleFilter
            }
            this.axios.get(url,{params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data['content'])
                this.roleRequests = response.data['content']
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