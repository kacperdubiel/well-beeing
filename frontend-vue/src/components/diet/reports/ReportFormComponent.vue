<template>
    <div class="modal-diet-content">
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">PRODUKTY</h6>
            </div>
            <div>
                <div class="row py-1">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="product-report-input">Produkt</label>
                    </div>
                    <div class="col-lg-8">
                        <v-select @click="this.clearProductStatus" id="product-report-input" style="margin 0px; padding: 0px;" v-model="this.actualSelectedProduct" :options="this.productsToSelect" :reduce="name => name.id" label="name" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="product-amount-report-input">Ilość</label>
                    </div>
                    <div class="col-lg-8">
                        <input
                            @focus="this.clearProductStatus"
                            style="width: 100%;"
                            id="product-amount-report-input"
                            type="number"
                            class="form-control px-0"
                            min=0
                            v-model="this.actualSelectedProductAmount"
                        />
                    </div>
                </div>
                <div class="row py-1">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="product-measure-input">Miara</label>
                    </div>
                    <div class="col-lg-8">
                        <select @focus="this.clearProductStatus" id="product-measure-input" v-model="this.actualSelectedProductMeasure" class="form-select" aria-label="Product measure">
                            <option value="GRAM">{{this.$func_global.mapMeasure("GRAM")}}</option>
                            <option value="MILLI_GRAM">{{this.$func_global.mapMeasure("MILLI_GRAM")}}</option>
                            <option value="MICRO_GRAM">{{this.$func_global.mapMeasure("MICRO_GRAM")}}</option>
                            <option value="KILO_GRAM">{{this.$func_global.mapMeasure("KILO_GRAM")}}</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="product-report-time">Godzina</label>
                    </div>
                    <div class="col-lg-8">
                        <input @focus="this.clearProductStatus" v-model="this.actualSelectedProductConsumingTime" class="form-control" id="product-report-time" type="time"/>
                    </div>
                </div>
                <div class="col-lg-4">
                    <button style="width: 200px;" @click="this.addProductToReport" class="btn-card-panel-diet">Dodaj produkt</button>
                </div>
            </div>
            <div v-if="this.productFormError" style="margin: 5px;" class="alert alert-danger" role="alert">
                Wprowadź wszystkie dane!
            </div>
            <hr/>
            <div v-if="showEmptyProductsAlert" class="alert alert-danger" role="alert">
                    Brak produktów w raporcie. 
            </div>
            <div v-else>
                <table style="color: var(--GREY2)" class="table table-borderless table-hover">
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
                            <td>{{this.$func_global.mapMeasure(detail.measureType)}}</td>
                            <td>{{detail.consumingTime.substring(11, 16)}}</td>
                            <td><button @click="this.deleteProductFromReport(detail.id)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        <div style="width: 100%; text-align: left;">
            <hr style="margin-top: 50px;" class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">DANIA</h6>
            </div>
            <div>
                <div class="row py-1">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="report-dish-select">Danie</label>
                    </div>
                    <div class="col-lg-8">
                        <v-select @click="this.clearDishStatus" id="report-dish-select" style="margin 0px; padding: 0px;" v-model="this.actualSelectedDish" :options="this.dishesToSelect" :reduce="name => name.id" label="name" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="report-dish-portions-input">Liczba porcji</label>
                    </div>
                    <div class="col-lg-8">
                        <input
                            id="report-dish-portions-input"
                            type="number"
                            min=1
                            class="form-control px-0"
                            @focus="this.clearDishStatus"
                            v-model="this.actualSelectedDishPortions"
                        />
                    </div>
                </div>
                <div class="row py-1">
                    <div class="col-lg-4">
                        <label class="form-report-label" for="report-dish-time">Godzina</label>
                    </div>
                    <div class="col-lg-8">
                        <input @focus="this.clearDishStatus"  class="form-control px-0" v-model="this.actualSelectedDishConsumingTime" id="report-dish-time" type="time"/>
                    </div>
                </div>
                <div class="col-lg-4">
                    <button style="width: 200px;" @click="this.addDishToReport" class="btn-card-panel-diet">Dodaj danie</button>
                </div>
            </div>
            <div v-if="this.dishFormError" style="margin: 5px;" class="alert alert-danger" role="alert">
                Wprowadź wszystkie dane!
            </div>
            <hr/>
            <div v-if="showEmptyDishesAlert" class="alert alert-danger" role="alert">
                    Brak dań w raporcie. 
            </div>
            <div v-else>
                <table style="color: var(--GREY2)" class="table table-borderless table-hover">
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
            actualSelectedProductAmount: '',
            actualSelectedProductMeasure: '',
            actualSelectedProductConsumingTime: '',

            dishesToSelect: [],
            actualSelectedDish: '',
            actualSelectedDishPortions: '',
            actualSelectedDishConsumingTime: '',

            productFormError: false,
            dishFormError: false,
            showEmptyProductsAlert: false,
            showEmptyDishesAlert: false
        }
    },
    computed: {
        ivalidProductForm(){
            return this.actualSelectedProduct === '' || this.actualSelectedProduct == null ||
                   this.actualSelectedProductAmount === '' || this.actualSelectedProductAmount <= 0 ||
                   this.actualSelectedProductMeasure === '' ||
                   this.actualSelectedProductConsumingTime === ''

        },
        invalidDishForm(){
            return this.actualSelectedDish === '' || this.actualSelectedDish == null ||
                   this.actualSelectedDishPortions === '' || this.actualSelectedDishPortions <= 0 ||
                   this.actualSelectedDishConsumingTime === ''
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
        clearDishInputs(){
            this.actualSelectedDish= ''
            this.actualSelectedDishPortions=''
            this.actualSelectedDishConsumingTime= ''
        },
        clearProductInputs(){
            this.actualSelectedProduct= ''
            this.actualSelectedProductAmount= ''
            this.actualSelectedProductMeasure= ''
            this.actualSelectedProductConsumingTime= ''
        },
        clearProductStatus(){
            this.productFormError = false;
        },
        clearDishStatus(){
            this.dishFormError = false;
        },
        getProductsToSelect(){
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
           if(this.ivalidProductForm){
               this.productFormError = true
               return
           }
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
            .then((response) => {this.getCurrentReport(); console.log(response); this.clearProductInputs(); this.$emit('updated:report', response.data);})
            .catch(e => {console.log(e);})
        },
        addDishToReport(){
           if(this.invalidDishForm){
               this.dishFormError = true
               return
           }
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
            .then((response) => {this.getCurrentReport(); console.log(response); this.clearDishInputs(); this.$emit('updated:report', response.data);})
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
            .then((response) => {
                console.log(response);
                this.actualReport = response.data
                this.showEmptyProductsAlert = true ? this.actualReport.productDetailsList.length == 0 : false
                console.log(this.showEmptyProductsAlert)
                this.showEmptyDishesAlert = true ? this.actualReport.dishDetailsList.length == 0 : false
                console.log(this.showEmptyDishesAlert)
                })
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
    .form-report-label{
        color: var(--GREY3)
    }
</style>