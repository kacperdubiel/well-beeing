<template>
    <div>
        <div class="row">
            <div class="col-md-2 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.sortBy"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getRoleRequests()"
                >
                    <option disabled value="">Wybierz sortowanie</option>
                    <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{ sort.label }}</option>
                </select>
            </div>
            <div class="col-md-2 col-sm-12  filter-control align-self-center">
                <select
                    v-model="userNavigation.pageSize"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getRoleRequests()"
                >
                    <option disabled value="">Rozmiar strony</option>
                    <option v-for="size in userNavigation.pageSizeOptions" :key="size" :value="size">{{ size }}</option>
                </select>
            </div>
            <div class="col-md-2 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.statusFilter"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getRoleRequests()"
                >
                    <option disabled value="">Wybierz status</option>
                    <option v-for="status in filters.allStatusFilters" :key="status.label" :value="status.value">{{ status.label }}</option>
                </select>
            </div>
            <div class="col-md-2 col-sm-12 align-self-center filter-control">
                <select
                    v-model="filters.roleFilter"
                    class=" p-2"
                    style="border-radius: 5px"
                    @change="getRoleRequests()"
                >
                    <option disabled value="">Wybierz rolę</option>
                    <option v-for="role in filters.allRoleFilters" :key="role.label" :value="role.value">{{ role.label }}</option>
                </select>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-11">
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