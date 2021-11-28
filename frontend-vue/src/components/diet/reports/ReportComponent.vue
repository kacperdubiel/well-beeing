<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <div class="accordion" id="accordion-products">
                <div v-if="this.report.productDetailsList != null && this.report.productDetailsList.length == 0" class="alert alert-danger" role="alert">
                    Brak produktów w raporcie. 
                </div>
                <div v-else v-for="detail in this.report.productDetailsList" :key="detail.id" class="accordion-item">
                    <h2 class="accordion-header" :id="'product-heading-' + detail.id">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#collapse-product' + detail.id" aria-expanded="true" aria-controls="collapse-product">
                            <div style="width: 100%;" class="row">
                                <b class="col-lg-7 p-0 m-0">{{detail.product.name}}</b>
                                <p class="col-lg-3">{{detail.amountOfProduct}} {{this.$func_global.mapMeasure(detail.measureType)}}</p>
                                <p class="col-lg-2">{{detail.consumingTime.substring(11, 16)}}</p>
                            </div>
                        </button>
                    </h2>
                    <div :id="'collapse-product' + detail.id" class="accordion-collapse collapse" :aria-labelledby="'product-heading' + detail.id" data-bs-parent="#accordion-products">
                        <table style="text-align: center;" class="table table-hover table-report-info p-2">
                            <tbody>
                                <tr>
                                    <th>Kalorie</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCalories)}} kcal</td>
                                </tr>
                                <tr>
                                    <th class="col-lg-6">Białka</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedProteins*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Węglowodany</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCarbohydrates*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Cukry</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedSugar*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedFats*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze nasycone</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedSaturatedFats*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Cholesterol</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCholesterol*1000)/1000}} g</td>
                                </tr>
                                <tr>
                                    <th>Błonnik</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedFiber*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Sól</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedSalt*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Kofeina</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCaffeine*1000)/1000}} g</td>
                                </tr>
                                <tr>
                                    <th>Indeks glikemiczny</th>
                                    <td>{{this.$func_global.mapGlycemicIndex(detail.product.glycemicIndexLevel)}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">DANIA</h6>
            </div>
            <div class="accordion" id="accordion-dishes">
                <div v-if="this.report.dishDetailsList != null && this.report.dishDetailsList.length == 0" class="alert alert-danger" role="alert">
                    Brak dań w raporcie. 
                </div>
                <div v-else v-for="dishDetail in this.report.dishDetailsList" :key="dishDetail.id" class="accordion-item">
                    <h2 class="accordion-header" :id="'dish-heading-' + dishDetail.id">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#collapse-dish' + dishDetail.id" aria-expanded="true" aria-controls="collapse-dish">
                            <div style="width: 100%;" class="row">
                                <b class="col-lg-7 p-0 m-0">{{dishDetail.dish.name}}</b>
                                <p class="col-lg-3">{{dishDetail.portions}} porcji</p>
                                <p class="col-lg-2">{{dishDetail.consumingTime.substring(11, 16)}}</p>
                            </div>
                        </button>
                    </h2>
                    <div :id="'collapse-dish' + dishDetail.id" class="accordion-collapse collapse" :aria-labelledby="'dish-heading' + dishDetail.id" data-bs-parent="#accordion-dishes">
                        <table style="text-align: center;" class="table table-hover table-report-info p-2">
                            <tbody>
                                <tr>
                                    <th>Kalorie</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCalories)}} kcal</td>
                                </tr>
                                <tr>
                                    <th class="col-lg-6">Białka</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedProteins*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Węglowodany</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCarbohydrates*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Cukry</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedSugar*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedFats*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze nasycone</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedSaturatedFats*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Cholesterol</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCholesterol*1000)/1000}} g</td>
                                </tr>
                                <tr>
                                    <th>Błonnik</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedFiber*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Sól</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedSalt*100)/100}} g</td>
                                </tr>
                                <tr>
                                    <th>Kofeina</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCaffeine * 1000)/1000}} g</td>
                                </tr>
                                <tr>
                                    <th>Indeks glikemiczny</th>
                                    <td>{{this.$func_global.mapGlycemicIndex(dishDetail.dish.glycemicIndexLevel)}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <hr v-if="calculationsLoaded && this.calculations != ''" class="hr-dish title-line"/>
        <div v-if="calculationsLoaded && this.calculations != ''" class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">KALKULACJE</h6>
        </div>
        <div v-if="calculationsLoaded && this.calculations != ''" style="width: 100%;">
            <div class="m-2">
                <p style="color: black; text-align: start;">Kalorie {{ Math.round(report.derivedNutritionalValues.derivedCalories*100)/100 }} / {{Math.round(this.calculations.suggestedCalories)}} kcal</p>
                <progress-bar-component 
                :measure="'kcal'" 
                :actualValue="Math.round(report.derivedNutritionalValues.derivedCalories*100)/100" 
                :expectedValue="Math.round(this.calculations.suggestedCalories)"/>
            </div>
            <div class="m-2">
                <p style="color: black; text-align: start;">Węglowodany {{ Math.round(report.derivedNutritionalValues.derivedCarbohydrates*100)/100 }} / {{Math.round(this.calculations.suggestedCarbohydrates)}} g</p>
                <progress-bar-component 
                :measure="'g'" 
                :actualValue="Math.round(report.derivedNutritionalValues.derivedCarbohydrates*100)/100" 
                :expectedValue="Math.round(this.calculations.suggestedCarbohydrates)"/>
            </div>
            <div class="m-2">
                <p style="color: black; text-align: start;">Tłuszcze {{ Math.round(report.derivedNutritionalValues.derivedFats*100)/100 }} / {{Math.round(this.calculations.suggestedFats)}} g</p>
                <progress-bar-component 
                :measure="'g'" 
                :actualValue="Math.round(report.derivedNutritionalValues.derivedFats * 100)/100" 
                :expectedValue="Math.round(this.calculations.suggestedFats)"/>
            </div>
            <div class="m-2">
                <p style="color: black; text-align: start;">Białka {{ Math.round(report.derivedNutritionalValues.derivedProteins*100)/100 }} / {{Math.round(this.calculations.suggestedProteins)}} g</p>
                <progress-bar-component 
                :measure="'g'" 
                :actualValue="Math.round(report.derivedNutritionalValues.derivedProteins * 100)/100" 
                :expectedValue="Math.round(this.calculations.suggestedProteins)"/>
            </div>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">PODSUMOWANIE</h6>
        </div>
        <div style="align-items: center; display: flex; width:100%;">
            <table class="table table-hover">
                <tbody>
                    <tr>
                        <th>Kalorie</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCalories*100)/100}} kcal</td>
                    </tr>
                    <tr>
                        <th class="col-lg-6">Białka</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedProteins*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Węglowodany</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCarbohydrates*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Cukry</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedSugar*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Tłuszcze</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedFats*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Tłuszcze nasycone</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedSaturatedFats*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Cholesterol</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCholesterol*1000)/1000}} g</td>
                    </tr>
                    <tr>
                        <th>Błonnik</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedFiber*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Sól</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedSalt*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Kofeina</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCaffeine*1000)/1000}} g</td>
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
                        <th class="col-sm-6" scope="col">Nazwa</th>
                        <th class="col-sm-6" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.vitaminsMicroGram" :key="key">
                            <th class="col-sm-2" scope="row">{{this.$func_global.mapVitamin(key)}}</th>
                            <td class="col-sm-2">{{Math.round(value*100) / 100}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="Object.keys(this.reportDetails.mineralsMilliGram).length != 0">
                <h6 class="title">MINERAŁY</h6>
                <table class="table table-hover table-borderless">
                    <tr>
                        <th class="col-sm-6" scope="col">Nazwa</th>
                        <th class="col-sm-6" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.mineralsMilliGram" :key="key">
                            <th class="col-sm-3" scope="row">{{this.$func_global.mapMineral(key)}}</th>
                            <td class="col-sm-7">{{Math.round(value*100) / 100}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="Object.keys(this.reportDetails.mineralsMilliGram).length != 0">
                <h6 class="title">MAKROELEMENTY</h6>
                <table class="table table-hover table-borderless">
                    <tr>
                        <th class="col-sm-6" scope="col">Nazwa</th>
                        <th class="col-sm-6" scope="col">Ilość</th>
                    </tr>
                    <tbody>
                        <tr v-for="(value, key) in this.reportDetails.macroDetailsGram" :key="key">
                            <th class="col-sm-3" scope="row">{{this.$func_global.mapMacro(key)}}</th>
                            <td class="col-sm-7">{{Math.round(value*100) / 100}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import ProgressBarComponent from '@/components/diet/ProgressBarComponent.vue'
export default {
    name: "ReportComponent",
    props: {
        report: {
            type: Object
        },
        fromDietician: {
            type: Boolean
        },
        userId: {
            type: String
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
    components: {
        ProgressBarComponent
    },
    data(){
        return{
            dataLoaded: false,
            reportDetails: Object,
            calculations: null,
            calculationsLoaded: false
        }
    },
    mounted(){
        this.getProfileCalculations()
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
        getOwnProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc', {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.calculations = response.data
                this.calculationsLoaded = true;
            })
            .catch(e => alert(e))
        },
        getOtherProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc/profile/' + this.userId, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.calculations = response.data
                this.calculationsLoaded = true;
            })
            .catch(e => alert(e))
        },
        getProfileCalculations(){
            if(this.fromDietician && this.userId != null){
                this.getOtherProfileCalculations()
            }
            else
                this.getOwnProfileCalculations()
        }
    }
}
</script>

<style scoped>
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 100%;
        margin-top: 20px;          
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

    .accordion-button.collapsed {
        background: white;
        color: black;
        border-color: black;
    }

    .accordion-button {
        background: var(--DIET);
        color: white;
        border-color: black;
    }

    .accordion-button:focus {
        box-shadow: none;
    }

</style>