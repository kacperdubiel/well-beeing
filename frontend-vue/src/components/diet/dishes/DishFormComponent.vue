<template>
    <div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ZDJĘCIE</h6>
        </div>
        <div>
            <!--<div>
                <img style="width: 100px; height: 100px" :src="this.imgPath">
            </div>
            <div style="flex-direction: column; display: flex;"> 
                <input type="file" class="form-control-file" accept="image/png, image/jpeg" @change="this.onFileChange">
                <button style="width: 150px;" class="btn-card-panel-diet" @click="this.clearPhoto">usuń zdjęcie</button>
            </div>-->
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">OPIS</h6>
        </div>
        <div>
            <textarea id="dish-description" class="form-control" rows="3" maxlength="255" v-model="this.dishDescription"></textarea>
        </div>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">PRZEPIS</h6>
        </div>
        <div>
            <textarea id="dish-recipe" class="form-control" rows="3" maxlength="255" v-model="this.dishRecipe"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">NAZWA</h6>
        </div>
        <div>
            <textarea id="dish-name" class="form-control" rows="1" maxlength="255" v-model="this.dishName"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ETYKIETY</h6>
        </div>
        <div style="width: 95%;">
            <v-select
                style="margin-bottom: 10px;"
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.chosenNutritionLabels"
                :options="nutritionLabels"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">TYP POSIŁKU</h6>
        </div>
        <div style="width: 95%;">
            <v-select
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.chosenMealTypes"
                :options="this.dishTypes"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">SKŁADNIKI NA JEDNĄ PORCJĘ</h6>
        </div>
        <div class="row">
            <div class="col-lg-7">
                <v-select @keypress="this.getProductsToSelect" v-model="this.actualSelectedProduct" :options="this.productsToSelect" label="name" />
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
                <button @click="this.addProductDetail" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
            </div>
        </div>
        <div v-if="this.productDetailAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
            Dodałeś już ten produkt!
            <button @click="this.productDetailAlert = false" type="button" class="btn-close" aria-label="Close"></button>
        </div>
        <div style="text-align: left;" v-if="this.addedProductDetails.length > 0">
            <table class="table table-borderless table-hover">
                <thead>
                    <tr>
                        <th class="col-lg-9" scope="col">Produkt</th>
                        <th class="col-lg-2" scope="col">Ilość</th>
                        <th class="col-lg-1" scope="col"> </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="detail in this.addedProductDetails" :key="detail.product.id">
                        <th scope="row">{{detail.product.name}}</th>
                        <td>{{detail.amountOfProduct}} {{this.$func_global.mapMeasure(detail.measureType)}}</td>
                        <td><button @click="this.deleteProductDetail(detail.product.id)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div style="justify-content: flex-end; display: flex;">
            <button v-if="this.action==='UPDATE'" class="btn-card-panel-diet" @click="this.updateDish">Aktualizuj</button>
            <button v-if="this.action==='ADD'" class="btn-card-panel-diet" @click="this.addDish">Dodaj</button>
        </div>
        <div v-if="this.successForm" style="margin: 5px;" class="alert alert-success" role="alert">
            Zaktualizowano dane
        </div>
        <div v-if="this.errorForm" style="margin: 5px;" class="alert alert-danger" role="alert">
            Upewnij się, że poprawnie uzupełniłeś oznaczone gwiazdką dane!
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    props: {
        dish: {
            type: Object
        },
        action: {
            type: String
        },
        modalClosed: {
            type: Boolean
        },
        modalOpened: {
            type: Boolean
        }
    },
    name: "DishFormComponent",
    mounted(){
        this.getNutritionLabels()
        this.getProductsToSelect()
    },
    watch: {
        modalOpened: function () {
            console.log("opened")
            if(this.action === 'UPDATE'){
                this.fillInputsOnUpdate()
            }
        },
        modalClosed: function () {
            this.clearStatus()
            this.clearInputs()
        }
    },
    data(){
        return{
            nutritionLabels: [],
            dishTypes: [
                {
                    id: 'BREAKFAST',
                    name: this.$func_global.mapMeal('BREAKFAST')
                },
                {
                    id: 'LUNCH',
                    name: this.$func_global.mapMeal('LUNCH')
                },
                {
                    id: 'DINNER',
                    name: this.$func_global.mapMeal('DINNER')
                },
                {
                    id: 'SNACK',
                    name: this.$func_global.mapMeal('SNACK')
                },
                {
                    id: 'SUPPER',
                    name: this.$func_global.mapMeal('SUPPER')
                },
            ],
            actualSelectedProduct: '',
            actualSelectedProductAmount: 0,
            actualSelectedProductMeasure: '',

            chosenNutritionLabels: [],
            chosenMealTypes: [],

            productsToSelect: [],
            addedProductDetails: [],
            dishDescription: '',
            dishName: '',
            dishRecipe: '',
            imgPath: '',
            postFile: '',

            productDetailAlert: false,
            successForm: false,
            errorForm: false,
        }
    },
    methods: {
        getNutritionLabels(){
            axios.get('http://localhost:8090/nutrition-label', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.nutritionLabels = data.data
            }).catch(e => alert(e))
        },
        clearStatus(){
            this.productDetailAlert= false
            this.successForm= false
            this.errorForm= false
        },
        clearProductsStatus(){
            this.actualSelectedProduct = ''
            this.actualSelectedProductAmount = 0
            this.actualSelectedProductMeasure = ''
        },
        fillInputsOnUpdate(){
            this.actualSelectedProduct = '',
            this.actualSelectedProductAmount = 0,
            this.actualSelectedProductMeasure = '',

            this.chosenNutritionLabels = [],
            this.dish.allowedForNutritionLabels.forEach((elem) => this.chosenNutritionLabels.push(elem.id))

            this.chosenMealTypes= [],
            this.dish.dishMealTypes.forEach((elem) => this.chosenMealTypes.push(elem.mealType))

            this.addedProductDetails = [],
            this.dish.dishProductDetails.forEach((elem) => this.addedProductDetails.push(
                {
                    amountOfProduct: elem.amountOfProduct,
                    measureType: elem.measureType,
                    product: {
                        id: elem.product.id,
                        name: elem.product.name
                    }
                }
            ))

            this.dishDescription = this.dish.description,
            this.dishName = this.dish.name,
            this.dishRecipe = this.dish.recipe,
            this.imgPath = '',
            this.postFile = '',

            this.productDetailAlert = false
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
        addProductDetail(){         
            let detail = {
                amountOfProduct: this.actualSelectedProductAmount,
                measureType: this.actualSelectedProductMeasure,
                product: {
                    id: this.actualSelectedProduct.id,
                    name: this.actualSelectedProduct.name
                }
            }
            if(this.addedProductDetails.filter((det) => det.product.id === detail.product.id).length > 0){
                this.productDetailAlert = true;
                return;
            }    
            this.addedProductDetails.push(detail)
            this.clearProductsStatus()
        },
        deleteProductDetail(id){
            this.addedProductDetails = this.addedProductDetails.filter((det) => det.product.id != id)
        },
        onFileChange(e) {
            this.postFile = e.target.files[0];
            this.imgPath = URL.createObjectURL(this.postFile);
        },
        clearPhoto() {
            this.postFile = null
            this.imgPath = ''
        },
        prepareMealObjectsArray(){
            let result = []
            this.chosenMealTypes.forEach((mealType) => result.push({mealType: mealType}))
            return result
        },
        prepareNutritionObjectsArray(){
            let result = []
            this.chosenNutritionLabels.forEach((nutritionLabel) => result.push({id: nutritionLabel}))
            return result;

        },
        prepareProductsDetailArray(){
            let result = []
            this.addedProductDetails.forEach((detail) => result.push({amountOfProduct: detail.amountOfProduct,
            measureType: detail.measureType, product: {id: detail.product.id}}))
            return result;

        },
        prepareData(id){
            let result = {
                    id: id,
                    name: this.dishName,
                    description: this.dishDescription,
                    recipe: this.dishRecipe,
                    imgDishPath: this.imgPath,
                    dishProductDetails: this.prepareProductsDetailArray(),
                    dishMealTypes: this.prepareMealObjectsArray(),
                    allowedForNutritionLabels: this.prepareNutritionObjectsArray(),
                }
            return result;
        },
        updateDish(){
            axios({
                method: 'put',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/dish/" + this.dish.id + '/dietician',
                data: this.prepareData(this.dish.id)
            })
            .then((response) => {console.log(response);this.$emit("dish:updated");this.successForm=true})
            .catch(e => {this.errorForm = true;console.log(e);})
        },
        addDish(){
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/dish/dietician",
                data: this.prepareData(null)
            })
            .then((response) => {console.log(response); this.$emit("dish:updated"); this.successForm=true})
            .catch(e => {this.errorForm = true;console.log(e);})
        },
        clearInputs(){
            this.actualSelectedProduct = ''
            this.actualSelectedProductAmount = 0
            this.actualSelectedProductMeasure = ''
            this.chosenNutritionLabels = []
            this.chosenMealTypes= []
            this.addedProductDetails = []
            this.dishDescription = ''
            this.dishName = ''
            this.dishRecipe = ''
            this.imgPath = ''
            this.postFile = ''
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
    .label-picker{
        margin-bottom: 10px;
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