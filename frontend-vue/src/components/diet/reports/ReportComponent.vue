<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <table class="table table-hover table-borderless">
                <thead>
                    <tr>
                        <th class="col-sm-5" scope="col">Produkt</th>
                        <th class="col-sm-1" scope="col">Ilość</th>
                        <th class="col-sm-1" scope="col">Kcal</th>
                        <th class="col-sm-1" scope="col">B</th>
                        <th class="col-sm-1" scope="col">W</th>
                        <th class="col-sm-1" scope="col">T</th>
                        <th class="col-sm-1" scope="col">Czas</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="detail in this.report.productDetailsList" :key="detail.id">
                        <th scope="row">{{detail.product.name}}</th>
                        <td>{{detail.amountOfProduct}} {{this.$func_global.mapMeasure(detail.measureType)}}</td>
                        <td>{{Math.round(detail.derivedNutritionalValues.derivedCalories)}}</td>
                        <td>{{Math.round(detail.derivedNutritionalValues.derivedProteins)}} g</td>
                        <td>{{Math.round(detail.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                        <td>{{Math.round(detail.derivedNutritionalValues.derivedFats)}} g</td>
                        <td>{{detail.consumingTime.substring(11, 16)}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">DANIA</h6>
            </div>
            <table class="table table-hover table-borderless">
                <thead>
                    <tr>
                        <th class="col-sm-5" scope="col">Danie</th>
                        <th class="col-sm-1" scope="col">Porcje</th>
                        <th class="col-sm-1" scope="col">Kcal</th>
                        <th class="col-sm-1" scope="col">B</th>
                        <th class="col-sm-1" scope="col">W</th>
                        <th class="col-sm-1" scope="col">T</th>
                        <th class="col-sm-1" scope="col">Czas</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="detailDish in this.report.dishDetailsList" :key="detailDish.id">
                        <th scope="row">{{detailDish.dish.name}}</th>
                        <td>{{detailDish.portions}}</td>
                        <td>{{Math.round(detailDish.derivedNutritionalValues.derivedCalories)}}</td>
                        <td>{{Math.round(detailDish.derivedNutritionalValues.derivedProteins)}} g</td>
                        <td>{{Math.round(detailDish.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                        <td>{{Math.round(detailDish.derivedNutritionalValues.derivedFats)}} g</td>
                        <td>{{detailDish.consumingTime.substring(11, 16)}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PODSUMOWANIE</h6>
        </div>
        <div style="align-items: center; display: flex; width:100%;">
            <table class="table table-report-info">
                <tbody>
                    <tr>
                        <th class="col-lg-6">Białka</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedProteins)}} g</td>
                    </tr>
                    <tr>
                        <th>Węglowodany</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                    </tr>
                    <tr>
                        <th>Tłuszcze</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedFats)}} g</td>
                    </tr>
                    <tr>
                        <th>Kalorie</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCalories)}} kcal</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-if="!this.dataLoaded" style="justify-content: flex-end; display: flex;width: 100%; text-align: left;">
            <button class="btn-card-panel-diet" @click="this.getReportDetails">Zobacz szczegóły</button>
        </div>
        <div v-if="this.dataLoaded" style="width: 100%; text-align: left;">
            <br/>
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">SZCZEGÓŁY</h6>
            </div>
            <br/>
            <div v-if="Object.keys(this.reportDetails.vitaminsMicroGram).length != 0">
                <h6 class="title">WITAMINY</h6>
                <table class="table table-hover table-borderless">
                    <tr>
                        <th class="col-sm-3" scope="col">Nazwa</th>
                        <th class="col-sm-7" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.vitaminsMicroGram" :key="key">
                            <th class="col-sm-2" scope="row">{{key}}</th>
                            <td class="col-sm-2">{{value}} mcg</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="Object.keys(this.reportDetails.mineralsMilliGram).length != 0">
                <h6 class="title">MINERAŁY</h6>
                <table class="table table-hover table-borderless">
                    <tr>
                        <th class="col-sm-3" scope="col">Nazwa</th>
                        <th class="col-sm-7" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.mineralsMilliGram" :key="key">
                            <th class="col-sm-3" scope="row">{{key}}</th>
                            <td class="col-sm-9">{{value}} mg</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="Object.keys(this.reportDetails.mineralsMilliGram).length != 0">
                <h6 class="title">MAKROELEMENTY</h6>
                <table class="table table-hover table-borderless">
                    <tr>
                        <th class="col-sm-3" scope="col">Nazwa</th>
                        <th class="col-sm-7" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.macroDetailsGram" :key="key">
                            <th class="col-sm-5" scope="row">{{key}}</th>
                            <td class="col-sm-7">{{value}} g</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "ReportComponent",
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
            axios.get('http://localhost:8090/report/' + this.report.id + '/details', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
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
    .hr-dish {
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
        border-width: 1px;
        border-color: var(--DIET);
        margin-top: 10px;
    }

    .bdr{
        border-radius: 10px;
        overflow: hidden;
    }
</style>