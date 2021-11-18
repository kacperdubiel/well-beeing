<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-pos title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <table class="table table-hover table-borderless">
                <thead>
                    <tr>
                        <th class="col-sm-5" scope="col">Cwiczenie</th>
                        <th class="col-sm-1" scope="col">Czas trwania</th>
                        <th class="col-sm-1" scope="col">Spalone kcal</th>
                        <th class="col-sm-1" scope="col">Czas</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="exercise in this.report.exerciseList" :key="exercise.id">
                        <th scope="row">{{exercise.exercise.name}}</th>
                        <td>{{this.$func_global.getTimePrettyFromSeconds(exercise.seconds)}}</td>
                        <td>{{Math.round(exercise.caloriesBurned)}}</td>
                        <td>{{exercise.exercisingTime.substring(11, 16)}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div style="width: 100%; text-align: left;">
            <hr class="hr-pos title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title text-capitalize">Treningi</h6>
            </div>
            <table class="table table-hover table-borderless">
                <thead>
                    <tr>
                        <th class="col-sm-5" scope="col">Trening</th>
                        <th class="col-sm-1" scope="col">Czas trwania</th>
                        <th class="col-sm-1" scope="col">Spalone kcal</th>
                        <th class="col-sm-1" scope="col">Czas</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="training in this.report.trainingList" :key="training.id">
                        <th scope="row">{{training.training.name}}</th>
                        <td>{{this.$func_global.getTimePrettyFromSeconds(training.seconds)}}</td>
                        <td>{{Math.round(training.caloriesBurned)}}</td>
                        <td>{{training.trainingTime.substring(11, 16)}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <hr class="hr-pos title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title text-capitalize">Podsumowanie</h6>
        </div>
        <div style="align-items: center; display: flex; width:100%;">
            <table class="table table-report-info">
                <tbody>
                    <tr>
                        <th class="col-lg-6">Łączny czas aktywności</th>
                        <td>{{this.$func_global.getTimePrettyFromSeconds(report.totalTimeSeconds)}}</td>
                    </tr>
                    <tr>
                        <th>Łącznie spalonych kalorii</th>
                        <td>{{Math.round(report.caloriesBurned)}} kcal</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "SportReportComponent",
    props: {
        report: {
            type: Object
        },
        close: Boolean
    },
    watch: {
        close: function () {
            setTimeout(() => {
                this.dataLoaded = false
            }, 500)
        }
    },
    data(){
        return{
            dataLoaded: false,
            reportDetails: Object
        }
    },
    methods: {
        getReportDetails(){
            const url = `${this.apiURL}sport/report/${this.report.id}/details`
            const token = this.$store.getters.getToken;
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then(data => {
                    this.reportDetails = data.data; this.dataLoaded = true
            }).catch(e => alert(e))
        },
    }
}
</script>

<style scoped>
    .hr-pos {
        border: 0;
        clear:both;
        display:block;
        width: 100%;
        margin-top: 10px;
        background-color:var(--GREY1);
        height: 1px;
    }
    .title-line {
        margin-bottom: 0px
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 5px;
        font-size: 10pt;
    }
    .table-report-info {
        color: var(--GREY2);
        border-style: solid;
        border-color: var(--SPORT);
        margin-top: 10px;
    }

    .bdr{
        border-radius: 10px;
        overflow: hidden;
    }
</style>
