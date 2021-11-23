<template>
    <div class="row justify-content-center">
        <div class="col-10">

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


            <div class="row">
                <div class="col text-center">
                    <nav aria-label="..." >
                        <ul class="pagination justify-content-center my-auto">
                            <li class="page-item admin-page" v-bind:class="{'disabled' : navigation.isFirst}">
                                <a class="page-link" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                                    <font-awesome-icon :icon="['fa', 'fast-backward']" />
                                </a>
                            </li>
                            <li class="page-item admin-page" v-bind:class="{'disabled' : navigation.isFirst}">
                                <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                                    <font-awesome-icon :icon="['fa', 'chevron-left']" />
                                </a>
                            </li>
                            <li
                                class="page-item admin-page"
                                v-bind:class="{'active' : navigation.currentPage === page}"
                                v-for="page in userNavigation.pagesNavbar"
                                :key="page">
                                <a
                                    class="page-link"
                                    @click="goToPage(page)"
                                >
                                    {{page+1}}
                                </a>
                            </li>
                            <li class="page-item admin-page" v-bind:class="{'disabled' : navigation.isLast}">
                                <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                                    <font-awesome-icon :icon="['fa', 'chevron-right']" />
                                </a>
                            </li>
                            <li class="page-item admin-page" v-bind:class="{'disabled' : navigation.isLast}">
                                <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                                    <font-awesome-icon :icon="['fa', 'fast-forward']" />
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>



            <div class="row justify-content-center mt-3">
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
                pageSizeOptions: [5, 10, 20, 50],
                pageSize: 20,
                pagesNavbar: []
            }
        }
    },
    methods: {
        goToPage(pageNo) {
            this.userNavigation.goToPage = pageNo;
            this.getRoleRequests(false)
        },
        getRoleRequests(resetGoToPage) {
            const url = `${this.apiURL}role-request`
            const token = this.$store.getters.getToken;
            if (resetGoToPage)
                this.userNavigation.goToPage = 0
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
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                this.userNavigation.pagesNavbar = []
                if (this.navigation.currentPage !== 0)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage-1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.userNavigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 3)
                        break;
                }
            })
        },
    },
    mounted() {
        this.getRoleRequests(true)
    }
}
</script>

<style scoped>

</style>