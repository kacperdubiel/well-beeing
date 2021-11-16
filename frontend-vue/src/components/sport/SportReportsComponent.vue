<template>
    <div class="container-fluid">
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end justify-content-end">Dodaj</span>
            <span class="col-2 float-end button-icon">
                <font-awesome-icon @click="addNewReportForToday" class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
            </span>
        </div>
        <div class="row">
            <div style="align-items: flex-start; display: flex;" class="col-lg-6">
                <input v-model="this.actualMonth" @change="this.getReportsToShow" id="month-picker" type="month" name="start" min="2021-01">
            </div>
            <div class="col-lg-6" style="justify-content: flex-end; display: flex; flex-direction: row;">
                <div v-if="this.showConflictError" class="alert alert-danger alert-dismissible fade show" role="alert">
                    Raport na dzisiaj już istnieje!
                    <button @click="clearError" type="button" class="btn-close" aria-label="Close"></button>
                </div>
            </div>
        </div>
        <table style="color: white; text-align: start;" class="table">
            <thead>
            <tr>
                <th class="col-sm-3" scope="col">Data</th>
                <th class="col-sm-3" scope="col">Spalone kalorie</th>
                <th class="col-sm-1" scope="col">Czas</th>
                <th class="col-sm-3" scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="report in reportsToShow" :key="report.id">
                <td>{{report.reportDate}}</td>
                <td>2400 kcal</td>
                <td>180 min</td>
                <td style="text-align: end;">
                    <button @click="this.setActualReport(report)" class="btn-white mx-2" data-bs-toggle="modal" data-bs-target="#sportReportModal"><font-awesome-icon :icon="['fa', 'info']"/></button>
                    <button @click="this.setActualReport(report)" class="btn-white" data-bs-toggle="modal" data-bs-target="#sportReportFormModal"><font-awesome-icon :icon="['fa', 'edit']"/></button>
                    <button @click="this.setActualReport(report)" class="btn-white mx-2" data-bs-toggle="modal" data-bs-target="#sportReportDeleteModal"><font-awesome-icon :icon="['fa', 'trash']"/></button>
                </td>
            </tr>
            </tbody>
        </table>
        <div id="sportReportModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="sportReportModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="sportReportModalLabel">Raport {{this.actualModalReport.reportDate}}</h5>
                        <button @click="this.closeInfoModal = !this.closeInfoModal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <sport-report-component :close="this.closeInfoModal" :report="this.actualModalReport"></sport-report-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="sportReportDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade"  tabindex="-1" aria-labelledby="sportReportDeleteModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p style="color: black;">Czy na pewno chcesz usunąć?</p>
                        <div>
                            <button @click="this.deleteReport" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                            <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="sportReportFormModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="sportReportFormModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="sportReportFormModalLabel">Edytuj raport</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <sport-report-form-component @updated:report="this.updateReportsAfterUpdate" :report="this.actualModalReport"></sport-report-form-component>
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
    data(){
        return {
            reportsToShow: [],
            showConflictError: false,
            actualMonth: '',
            actualModalReport: {
                derivedNutritionalValues: {
                    derivedProteins: 0,
                    derivedFats: 0,
                    derivedCarbohydrates: 0,
                    derivedCalories: 0
                }
            },
            closeInfoModal: false
        }
    },
    mounted(){
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
        getReportsToShow(){
            const url = `${this.apiURL}sport/report`
            const token = this.$store.getters.getToken;
            var yearMonth = this.actualMonth.split("-")
            var year = parseInt(yearMonth[0],10)
            var month = parseInt(yearMonth[1],10)
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
                    console.log("TU")
                    console.log(data)
                    console.log(this.reportsToShow)
                }).catch(e => alert(e))
        },
        clearError(){
            this.showConflictError = false;
        },
        getActualMonth(){
            const date= new Date()
            const month=("0" + (date.getMonth() + 1)).slice(-2)
            const year=date.getFullYear()
            return `${year}-${month}`;
        },
        setActualReport(report){
            this.actualModalReport = report
        },
        deleteReport(){
            const url = `${this.apiURL}sport/report/${this.actualModalReport.id}`
            const token = this.$store.getters.getToken;
            axios.delete(url,{headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {this.getReportsToShow(); console.log(response)})
                .catch(error => {console.log(error)})
        },
        updateReportsAfterUpdate(response){
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

</style>
