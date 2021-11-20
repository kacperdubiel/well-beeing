<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <div class="accordion" id="accordion-products">
                <div v-for="detail in this.report.productDetailsList" :key="detail.id" class="accordion-item">
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
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedProteins)}} g</td>
                                </tr>
                                <tr>
                                    <th>Węglowodany</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedFats)}} g</td>
                                </tr>
                                <tr>
                                    <th>Cholesterol</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCholesterol)}} g</td>
                                </tr>
                                <tr>
                                    <th>Błonnik</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedFiber)}} g</td>
                                </tr>
                                <tr>
                                    <th>Sól</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedSalt)}} g</td>
                                </tr>
                                <tr>
                                    <th>Kofeina</th>
                                    <td>{{Math.round(detail.derivedNutritionalValues.derivedCaffeine)}} g</td>
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
                <div v-for="dishDetail in this.report.dishDetailsList" :key="dishDetail.id" class="accordion-item">
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
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedProteins)}} g</td>
                                </tr>
                                <tr>
                                    <th>Węglowodany</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                                </tr>
                                <tr>
                                    <th>Tłuszcze</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedFats)}} g</td>
                                </tr>
                                <tr>
                                    <th>Cholesterol</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCholesterol)}} g</td>
                                </tr>
                                <tr>
                                    <th>Błonnik</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedFiber)}} g</td>
                                </tr>
                                <tr>
                                    <th>Sól</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedSalt)}} g</td>
                                </tr>
                                <tr>
                                    <th>Kofeina</th>
                                    <td>{{Math.round(dishDetail.derivedNutritionalValues.derivedCaffeine)}} g</td>
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
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PODSUMOWANIE</h6>
        </div>
        <div style="align-items: center; display: flex; width:100%;">
            <table class="table">
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
                    <tr>
                        <th>Cholesterol</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCholesterol)}} g</td>
                    </tr>
                    <tr>
                        <th>Błonnik</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedFiber)}} g</td>
                    </tr>
                    <tr>
                        <th>Sól</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedSalt)}} g</td>
                    </tr>
                    <tr>
                        <th>Kofeina</th>
                        <td>{{Math.round(report.derivedNutritionalValues.derivedCaffeine)}} g</td>
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