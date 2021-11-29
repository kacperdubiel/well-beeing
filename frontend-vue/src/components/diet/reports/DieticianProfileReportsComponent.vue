<template>
    <div class="container-fluid">
        <div class="row">
            <div style="align-items: flex-start; display: flex;" class="col-lg-5">
                <input v-model="this.actualMonth" @change="this.getReportsToShow" class= "form-control" id="month-picker" type="month" name="start" min="2021-01">
            </div>
        </div>
        <table v-if="this.reportsToShow.length != 0" style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-3" scope="col">Data</th>
                    <th class="col-sm-3" scope="col">Kalorie</th>
                    <th class="col-sm-1" scope="col">B</th>
                    <th class="col-sm-1" scope="col">W</th>
                    <th class="col-sm-1" scope="col">T</th>
                    <th class="col-sm-3" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="report in reportsToShow" :key="report.id">
                    <td>{{report.reportDate}}</td>
                    <td>{{Math.round(report.derivedNutritionalValues.derivedCalories)}}kcal</td>
                    <td>{{Math.round(report.derivedNutritionalValues.derivedProteins)}}g</td>
                    <td>{{Math.round(report.derivedNutritionalValues.derivedCarbohydrates)}}g</td>
                    <td>{{Math.round(report.derivedNutritionalValues.derivedFats)}}g</td>
                    <td style="text-align: end;">
                        <button @click="this.setActualReport(report)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#reportModal"><font-awesome-icon :icon="['fa', 'info']"/></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <p class="mt-4" v-else>Brak raportów</p>
        <div id="reportModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="reportModalLabel">Raport {{this.actualModalReport.reportDate}}</h5>
                        <button @click="this.closeInfoModal = !this.closeInfoModal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <report-component :userId="this.userId" :fromDietician="true" :close="this.closeInfoModal" :report="this.actualModalReport"></report-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import ReportComponent from './ReportComponent.vue'
import ReportFormComponent from './ReportFormComponent.vue'
export default {
    name: "DieticianProfileReportsComponent",
    props: {
        userId: {
            type: String
        }
    },
    components: {
        ReportComponent,
        ReportFormComponent
    },
    data(){
        return {
            reportsToShow: [],
            actualMonth: '',
            actualModalReport: {
                derivedNutritionalValues: {
                    derivedProteins: 0,
                    derivedFats: 0,
                    derivedCarbohydrates: 0,
                    derivedCalories: 0
                }
            },
        }
    },
    mounted(){
        this.actualMonth = this.getActualMonth()
        this.getReportsToShow()
    },
    methods: {
        getReportsToShow(){
            var yearMonth = this.actualMonth.split("-")
            var year = parseInt(yearMonth[0],10)
            var month = parseInt(yearMonth[1],10)
            let params = {
                month: month,
                year: year,
            }
            axios.get('http://localhost:8090/report/profile/' + this.userId, {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
                params: params 
            })
            .then(data => {
                    this.reportsToShow = data.data;
            }).catch(e => alert(e))
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
    }
}
</script>

<style scoped>

.add-button{
    color: var(--DIET);
    background-color: transparent;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    padding: 0px;
    margin: 0;
    border: none;
    font-size: 2rem;
    font-weight: bold;
}

</style>