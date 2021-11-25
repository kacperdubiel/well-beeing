<template>
    <div>
        <div class="row p-2 pt-3">
            <training-plan-request-list :trainingPlanRequestsSource="trainingPlanRequests"
                                        @set:status="setRequestStatus"
                                        @set:request="setSelectedRequest"/>
        </div>

    </div>
</template>

<script>
import TrainingPlanRequestList from "@/components/sport/request/TrainingPlanRequestList";
// import TrainingPlanRequestForm from "@/components/sport/request/TrainingPlanRequestForm";

export default {
    name: "TrainingPlanRequest",
    components: {TrainingPlanRequestList},
    data() {
        return {
            trainingPlanRequests: [],
            selectedRequest: null,
            filters: {
                allStatusFilters: [
                    {label: '-', value: ''},
                    {label: 'Złożone', value: 'SUBMITTED'},
                    {label: 'Wyświetlone', value: 'SEEN'},
                    {label: 'Zaakceptowane', value: 'ACCEPTED'},
                    {label: 'Odrzucone', value: 'DENIED'},
                    {label: 'Zrealizowane', value: 'COMPLETED'},
                    {label: 'Anulowane', value: 'CANCELLED'}
                ],
                statusFilter: '',
                nameSearch: '',
                lastNameSearch: '',
                exerciseNameSearch: '',
                lastExerciseNameSearch: '',
                sortByOptions: [
                    {label: 'Od najnowszych', value: 'requestDate,asc'},
                    {label: 'Od najstarszych', value: 'requestDate,desc'}],
                sortBy: 'requestDate,desc'
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
                pageSizeOptions: [1, 3, 5, 10, 20, 50],
                pageSize: 20,
                pagesNavbar: []
            }
        }
    },
    methods: {
        async getRequestsWithFilters(resetGoToPage) {
            const url = `${this.apiURL}sport/training-plan/get-trainer-requests`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            if (resetGoToPage)
                this.userNavigation.goToPage = 0
            const myParams = {
                trainer: this.$store.getters.getProfileId,
                page: this.userNavigation.goToPage,
                size: this.userNavigation.pageSize,
                sort: this.filters.sortBy,
                requestStatus: this.filters.statusFilter
            }
            await this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                this.trainingPlanRequests = response.data['content']
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']
                this.navigation.pageSize = response.data['size']
                this.userNavigation.pagesNavbar = []
                if (this.navigation.currentPage > 1)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 2)
                if (this.navigation.currentPage !== 0)
                    this.userNavigation.pagesNavbar.push(this.navigation.currentPage - 1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.userNavigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 2)
                        break;
                }
            }).catch(error => {
                console.log(error.response);
            });
        },
        openRequestDetails(conversationId) {
            this.$router.push({name: 'UserTrainerConversationView', params: {conversationId: conversationId}});
        },
        setSelectedRequest(request) {
            this.selectedRequest = request
            this.$store.commit('setSelectedRequest', request)
            console.log('request: ', request)
            this.$router.push({
                name: 'TrainingPlanRequestForm',
                params: {requestId: request.id}
            });

        },
        async setRequestStatus(requestId, newStatus) {
            const url = `${this.apiURL}sport/training-plan/request`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            const body = {
                requestId: requestId,
                newStatus: newStatus
            }
            await this.axios.patch(url, body, {
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                console.log(response)
                this.getRequestsWithFilters()
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
    mounted() {
        this.getRequestsWithFilters(true)
    }
}
</script>

<style scoped>

</style>
