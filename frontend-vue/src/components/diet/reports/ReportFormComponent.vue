<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <v-select style="margin 0px; padding: 0px;" @keypress="this.getProductsToSelect" v-model="this.actualSelectedProduct" :options="this.productsToSelect" :reduce="name => name.id" label="name" />
                </div>
                <div class="col-lg-1">
                    <input
                        style="width: 60px;"
                        id="productReportInput"
                        type="number"
                        min=0
                        v-model="this.actualSelectedProductAmount"
                    />
                </div>
                <div class="col-lg-2">
                    <select id="measureProductInput" v-model="this.actualSelectedProductMeasure" class="form-select" aria-label="Product measure">
                        <option value="GRAM">{{this.$func_global.mapMeasure("GRAM")}}</option>
                        <option value="MILLI_GRAM">{{this.$func_global.mapMeasure("MILLI_GRAM")}}</option>
                        <option value="MICRO_GRAM">{{this.$func_global.mapMeasure("MICRO_GRAM")}}</option>
                        <option value="KILO_GRAM">{{this.$func_global.mapMeasure("KILO_GRAM")}}</option>
                    </select>
                </div>
                <div class="col-lg-2">
                    <input v-model="this.actualSelectedProductConsumingTime" id="productTime" type="time"/>
                </div>
                <div class="col-lg-2">
                    <button @click="this.addProductToReport" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
                </div>
            </div>
            <hr/>
            <div>
                <table class="table table-borderless table-hover">
                    <thead>
                        <tr>
                            <th class="col-lg-6" scope="col">Produkt</th>
                            <th class="col-lg-1" scope="col">Ilość</th>
                            <th class="col-lg-2" scope="col">Miara</th>
                            <th class="col-lg-2" scope="col">Godzina</th>
                            <th class="col-lg-1" scope="col"> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="detail in this.actualReport.productDetailsList" :key="detail.id">
                            <th scope="row">{{detail.product.name}}</th>
                            <td>{{detail.amountOfProduct}}</td>
                            <td>{{detail.measureType}}</td>
                            <td>{{detail.consumingTime.substring(11, 16)}}</td>
                            <td><button @click="this.deleteProductFromReport(detail.id)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">DANIA</h6>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <v-select @keypress="this.getDishesToSelect" v-model="this.actualSelectedDish" :options="this.dishesToSelect" :reduce="name => name.id" label="name" />
                </div>
                <div class="col-lg-1">
                    <input
                        style="width: 60px;"
                        id="dishReportInput"
                        type="number"
                        min=1
                        v-model="this.actualSelectedDishPortions"
                    />
                </div>
                <div class="col-lg-2">
                </div>
                <div class="col-lg-2">
                    <input v-model="this.actualSelectedDishConsumingTime" id="productTime" type="time"/>
                </div>
                <div class="col-lg-2">
                    <button @click="this.addDishToReport" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
                </div>
            </div>
            <hr/>
            <div>
                <table class="table table-borderless table-hover">
                    <thead>
                        <tr>
                            <th class="col-lg-6" scope="col">Danie</th>
                            <th class="col-lg-1" scope="col">Porcje</th>
                            <th class="col-lg-2" scope="col"></th>
                            <th class="col-lg-2" scope="col">Godzina</th>
                            <th class="col-lg-1" scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="detailDish in this.actualReport.dishDetailsList" :key="detailDish.id">
                            <th scope="row">{{detailDish.dish.name}}</th>
                            <td>{{detailDish.portions}}</td>
                            <td></td>
                            <td>{{detailDish.consumingTime.substring(11, 16)}}</td>
                            <td><button @click="this.deleteDishFromReport(detailDish.id)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "ReportFormComponent",
    props: {
        report: {
            type: Object
        }
    },
    data(){
        return{
            actualReport: Object,

            productsToSelect: [],
            actualSelectedProduct: '',
            actualSelectedProductAmount: 100,
            actualSelectedProductMeasure: '',
            actualSelectedProductConsumingTime: '12:00',

            dishesToSelect: [],
            actualSelectedDish: '',
            actualSelectedDishPortions: 1,
            actualSelectedDishConsumingTime: '12:00',
        }
    },
    watch: {
        report: function () {
            this.getCurrentReport()
        }
    },
    mounted() {
        this.getProductsToSelect()
        this.getDishesToSelect()
    },
    methods: {
        getProductsToSelect(){
            console.log("SPR")
            let params = {
                page: 0,
                size: 100,
                nameLike: this.actualSelectedProduct
            }
            axios.get('http://localhost:8090/product/search', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
                params: params
            })
            .then(data => {
                    this.productsToSelect = data.data.objects
            }).catch(e => alert(e))
        },
        getDishesToSelect(){
            let params = {
                page: 0,
                size: 100,
                nameLike: this.actualSelectedDish
            }  
            axios.get('http://localhost:8090/dish/search', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
                params: params
            })
            .then(data => {
                    console.log("POBIERAM DANIA DO SELECTA")
                    console.log(data.data)
                    this.dishesToSelect = data.data.objects
            }).catch(e => alert(e))
        },
        addProductToReport(){
           axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/report/" + this.report.id + "/product",
                data: [{
                    product: {
                        id: this.actualSelectedProduct
                    },
                    measureType: this.actualSelectedProductMeasure,
                    amountOfProduct: this.actualSelectedProductAmount,
                    consumingTime: this.makeConsumingTimestamp(this.actualSelectedProductConsumingTime)
                }]
            })
            .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
            .catch(e => {console.log(e);})
        },
        addDishToReport(){
           axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/report/" + this.report.id + "/dish",
                data: [{
                    dish: {
                        id: this.actualSelectedDish
                    },
                    portions: this.actualSelectedDishPortions,
                    consumingTime: this.makeConsumingTimestamp(this.actualSelectedDishConsumingTime)
                }]
            })
            .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
            .catch(e => {console.log(e);})
        },
        deleteProductFromReport(id){
           axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/report/" + this.report.id + "/product",
                data: [id]
            })
            .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data); })
            .catch(e => {console.log(e);})
        },
        deleteDishFromReport(id){
           axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/report/" + this.report.id + "/dish",
                data: [id]
            })
            .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data); })
            .catch(e => {console.log(e);})
        },
        getCurrentReport(){
            axios({
                method: 'get',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/report/" + this.report.id,
            })
            .then((response) => {console.log(response); this.actualReport = response.data})
            .catch(e => {console.log(e);})
        },
        makeConsumingTimestamp(consumingTime){
            let ts = this.report.reportDate + 'T' + consumingTime + ':00'
            return ts;
        }
    }
}
</script>

<style scoped>
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 95%;
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
        border-color: var(--DIET);
        margin-top: 10px;
    }

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