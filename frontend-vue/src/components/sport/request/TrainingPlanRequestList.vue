<template>
    <div>
        <div class="row justify-content-center">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Podopieczny</th>
                    <th scope="col">Data wysłania</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="req in trainingPlanRequestsSource" :key="req.id"
                    :class="{'not-seen':req.requestStatus === 'SUBMITTED'}">
                    <td>{{ req.id }}</td>
                    <td>{{ req.submitter.firstName }} {{ req.submitter.lastName }}</td>
                    <td>{{ this.$func_global.formatDateTime(req.requestDate) }}</td>
                    <td>{{ this.$func_global.mapPlanRequestStatus(req.requestStatus) }}</td>
                    <td class="text-end">
                        <button class="btn-white mx-2"
                                @click="openInfoModal(req); req.requestStatus === 'SUBMITTED' ? setStatus(req.id,'SEEN') : '' ">
                            <font-awesome-icon :icon="['fa', 'info']"/>
                        </button>
                        <button v-if="['SEEN','SUBMITTED'].includes(req.requestStatus)" class="btn-white mx-2">
                            <font-awesome-icon :icon="['fa', 'check']" @click="setStatus(req.id,'ACCEPTED')"/>
                        </button>
                        <button v-if="!['CANCELLED', 'DENIED', 'COMPLETED'].includes(req.requestStatus)"
                                class="btn-white mx-2"
                                @click="setStatus(req.id,'DENIED')">
                            <font-awesome-icon :icon="['fa', 'times']"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
export default {
    name: "TrainingPlanRequestList",
    data() {
        return {}
    },
    props: {
        trainingPlanRequestsSource: Array
    },
    methods: {
        openInfoModal(request) {
            this.$emit('set:request', request)
        },
        setStatus(requestId, status) {
            this.$emit('set:status', requestId, status)
        }
    }
}
</script>

<style scoped>
th, td {
    color: white;
}

table {
    --bs-table-hover-color: none;
    color: white;
    border-bottom: none;
}

.not-seen {
    font-weight: bold;
}
</style>
