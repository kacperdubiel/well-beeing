<template>
    <div class="container-fluid">
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end justify-content-end">Dodaj</span>
            <span class="col-2 float-end button-icon">
                <font-awesome-icon :icon="['fa', 'plus-circle']" class="icon  mx-4" data-bs-target="#addExerciseModal"
                                   data-bs-toggle="modal" @click="addNewReportForToday"/>
            </span>
        </div>
        <div class="row">
            <div class="col-lg-6" style="align-items: flex-start; display: flex;">
                <input id="month-picker" v-model="this.actualMonth" min="2021-01" name="start"
                       type="month" @change="this.getReportsToShow">
            </div>
            <div class="col-lg-6" style="justify-content: flex-end; display: flex; flex-direction: row;">
                <div v-if="this.showConflictError" class="alert alert-danger alert-dismissible fade show" role="alert">
                    Raport na dzisiaj już istnieje!
                    <button aria-label="Close" class="btn-close" type="button" @click="clearError"></button>
                </div>
            </div>
        </div>
        <table class="table" style="color: white; text-align: start;">
            <thead>
            <tr>
                <th class="col-sm-3" scope="col">Data</th>
                <th class="col-sm-3" scope="col">Spalone kalorie</th>
                <th class="col-sm-2" scope="col">Czas</th>
                <th class="col-sm-3" scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="report in reportsToShow" :key="report.id">
                <td>{{ report.reportDate }}</td>
                <td>{{ Math.round(report.caloriesBurned) }} kcal</td>
                <td>{{ this.$func_global.getTimePrettyFromSeconds(report.totalTimeSeconds) }}</td>
                <td style="text-align: end;">
                    <button class="btn-white mx-2" data-bs-target="#sportReportModal" data-bs-toggle="modal"
                            @click="this.setActualReport(report)">
                        <font-awesome-icon :icon="['fa', 'info']"/>
                    </button>
                    <button class="btn-white" data-bs-target="#sportReportFormModal" data-bs-toggle="modal"
                            @click="this.setActualReport(report)">
                        <font-awesome-icon :icon="['fa', 'edit']"/>
                    </button>
                    <button class="btn-white mx-2" data-bs-target="#sportReportDeleteModal" data-bs-toggle="modal"
                            @click="this.setActualReport(report)">
                        <font-awesome-icon :icon="['fa', 'trash']"/>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        <div id="sportReportModal" aria-hidden="false" aria-labelledby="sportReportModalLabel" class="modal fade"
             data-bs-backdrop="static"
             data-bs-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="sportReportModalLabel" class="modal-title" style="color: black;">Raport
                            {{ this.actualModalReport.reportDate }}</h5>
                        <button aria-label="Close" class="btn-close" data-bs-dismiss="modal"
                                type="button" @click="this.closeInfoModal = !this.closeInfoModal"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <sport-report-component :close="this.closeInfoModal"
                                                    :report="this.actualModalReport"></sport-report-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="sportReportDeleteModal" aria-hidden="false" aria-labelledby="sportReportDeleteModalLabel"
             class="modal fade"
             data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                    </div>
                    <div class="modal-body">
                        <p style="color: black;">Czy na pewno chcesz usunąć?</p>
                        <div>
                            <button class="btn btn-success" data-bs-dismiss="modal" style="margin: 2px;"
                                    type="button" @click="this.deleteReport">TAK
                            </button>
                            <button class="btn btn-danger" data-bs-dismiss="modal" style="margin: 2px;" type="button">
                                NIE
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="sportReportFormModal" aria-hidden="false" aria-labelledby="sportReportFormModalLabel"
             class="modal fade"
             data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="sportReportFormModalLabel" class="modal-title" style="color: black;">Edytuj raport</h5>
                        <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <sport-report-form-component :report="this.actualModalReport"
                                                         @updated:report="this.updateReportsAfterUpdate"></sport-report-form-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import SportReportComponent from "@/components/sport/reports/SportReportComponent";
import SportReportFormComponent from "@/components/sport/reports/SportReportFormComponent";

export default {
    name: "SportReportsComponent",
    components: {
        SportReportComponent,
        SportReportFormComponent
    },
    data() {
        return {
            reportsToShow: [],
            showConflictError: false,
            actualMonth: '',
            actualModalReport: {},
            closeInfoModal: false
        }
    },
    mounted() {
        this.actualMonth = this.getActualMonth()
        this.getReportsToShow()
    },
    methods: {
        async addNewReportForToday() {
            const url = `${this.apiURL}sport/report`
            const token = this.$store.getters.getToken;
            await this.axios.post(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.getReportsToShow();
                console.log(response)
            })
                .catch(error => {
                        console.log(error)
                        if (error.response.status === 409) {
                            this.showConflictError = true
                        }
                    }
                )
        },
        getReportsToShow() {
            const url = `${this.apiURL}sport/report`
            const token = this.$store.getters.getToken;
            var yearMonth = this.actualMonth.split("-")
            var year = parseInt(yearMonth[0], 10)
            var month = parseInt(yearMonth[1], 10)
            let params = {
                month: month,
                year: year,
            }
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                },
                params: params
            })
                .then(data => {
                    this.reportsToShow = data.data;
                }).catch(e => alert(e))
        },
        clearError() {
            this.showConflictError = false;
        },
        getActualMonth() {
            const date = new Date()
            const month = ("0" + (date.getMonth() + 1)).slice(-2)
            const year = date.getFullYear()
            return `${year}-${month}`;
        },
        setActualReport(report) {
            this.actualModalReport = report
        },
        deleteReport() {
            const url = `${this.apiURL}sport/report/${this.actualModalReport.id}`
            const token = this.$store.getters.getToken;
            axios.delete(url, {headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {
                    this.getReportsToShow();
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
                })
        },
        updateReportsAfterUpdate(response) {
            console.log(response)
            this.getReportsToShow()
        }
    }
}
</script>

<style scoped>

.button-icon {
    cursor: pointer;
}

.icon {
    font-size: 30px;
}

span {
    display: inline-flex;
    align-items: center;
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: var(--SPORT);
    -webkit-box-shadow: inset 0 0 6px rgba(90, 90, 90, 0.7);
}

</style>
