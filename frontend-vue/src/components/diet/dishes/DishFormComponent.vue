<template>
    <div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ZDJĘCIE*</h6>
        </div>
        <div>
            <div style="margin-bottom: 10px;">
                <img v-if="this.newDishPhotoSrc != ''" style="width: 100px; height: 100px" :src="this.newDishPhotoSrc">
            </div>
            <div style="flex-direction: column; align-items: center; display: flex;">
                <label for="dish-photo-input" style="width:150px;" class="custom-file-upload">Dodaj zdjęcie</label>
                <input @click="this.clearFormAndPhotoStatus" type="file" id="dish-photo-input" ref="newDishPicture" class="form-control-file btn-card-panel-diet " accept="image/png, image/jpeg" @change="this.onFileChange">
                <button v-if="this.newDishPhotoSrc != ''" style="width: 150px;" class="custom-file-upload" @click="this.clearPhoto">Usuń zdjęcie</button>
            </div>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">NAZWA*</h6>
        </div>
        <div>
            <textarea @focus="this.clearFormAndNameStatus" id="dish-name" class="form-control" rows="1" maxlength="255" v-model="this.dishName"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">OPIS*</h6>
        </div>
        <div>
            <textarea @focus="this.errorForm = this.invalidDescription ? false : this.errorForm" id="dish-description" class="form-control" rows="3" maxlength="255" v-model="this.dishDescription"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">PRZEPIS*</h6>
        </div>
        <div>
            <textarea @focus="this.errorForm=this.invalidRecipe ? false : this.errorForm" id="dish-recipe" class="form-control" rows="12" maxlength="1000" v-model="this.dishRecipe"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ETYKIETY*</h6>
        </div>
        <div>
            <v-select
                @click="this.errorForm=this.invalidLabels ? false : errorForm"
                style="margin-bottom: 10px;"
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.chosenNutritionLabels"
                :options="nutritionLabels"
                :reduce="name => name.nutritionLabelId"
                label="name"/>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">TYP POSIŁKU*</h6>
        </div>
        <div>
            <v-select
                @click="this.errorForm=this.invalidMealTypes ? false : errorForm"
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.chosenMealTypes"
                :options="this.dishTypes"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">INDEKS GLIKEMICZNY*</h6>
        </div>
        <div>
            <select @click="this.errorForm=this.invalidGlycemicIndex ? false : errorForm" id="glycemicIndexInput" v-model="this.actualSelectedGlycemicIndex" class="form-select" aria-label="Glycemic index">
                        <option value="VERY_LOW">{{this.$func_global.mapGlycemicIndex("VERY_LOW")}}</option>
                        <option value="LOW">{{this.$func_global.mapGlycemicIndex("LOW")}}</option>
                        <option value="MEDIUM">{{this.$func_global.mapGlycemicIndex("MEDIUM")}}</option>
                        <option value="HIGH">{{this.$func_global.mapGlycemicIndex("HIGH")}}</option>
                        <option value="VERY_HIGH">{{this.$func_global.mapGlycemicIndex("VERY_HIGH")}}</option>
            </select>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">SKŁADNIKI NA JEDNĄ PORCJĘ*</h6>
        </div>
        <div>
            <div style="text-align: start;" class="row m-1">
                <div class="col-lg-5">
                    <label style="color: var(--GREY3);" for="select-product-dish">Produkt</label>
                </div>
                <div class="col-lg-7">
                    <v-select style="width: 100%;" id="select-product-dish" @keypress="this.getProductsToSelect" v-model="this.actualSelectedProduct" :options="this.productsToSelect" label="name" />
                </div>
            </div>
            <div style="text-align: start;" class="row m-1">
                <div class="col-lg-5">
                    <label style="color: var(--GREY3);" for="productReportInput">Ilość</label>
                </div>
                <div class="col-lg-7">
                    <input
                        style="width: 100%;"
                        id="productReportInput"
                        type="number"
                        class="form-control"
                        min=0
                        v-model="this.actualSelectedProductAmount"
                    />
                </div>
            </div>
            <div style="text-align: start;" class="row m-1">
                <div class="col-lg-5">
                    <label style="color: var(--GREY3);" for="measureProductInput">Miara</label>
                </div>
                <div class="col-lg-7">
                    <select style="width: 100%" id="measureProductInput" v-model="this.actualSelectedProductMeasure" class="form-select" aria-label="Product measure">
                        <option value="GRAM">{{this.$func_global.mapMeasure("GRAM")}}</option>
                        <option value="MILLI_GRAM">{{this.$func_global.mapMeasure("MILLI_GRAM")}}</option>
                        <option value="MICRO_GRAM">{{this.$func_global.mapMeasure("MICRO_GRAM")}}</option>
                        <option value="KILO_GRAM">{{this.$func_global.mapMeasure("KILO_GRAM")}}</option>
                    </select>
                </div>
            </div>
            <div class="row m-1">
                <!--<button @click="this.addProductDetail" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>-->
                <button @click="this.addProductDetail" class="btn-card-panel-diet col-lg-3">Dodaj składnik</button>
            </div>
        </div>
        <div v-if="this.productDetailAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
            Dodałeś już ten produkt!
            <button @click="this.productDetailAlert = false" type="button" class="btn-close" aria-label="Close"></button>
        </div>
        <hr class="hr-dish title-line"/>
        <div style="text-align: left;" v-if="this.addedProductDetails.length > 0">
            <table style="color: var(--GREY2)" class="table table-borderless table-hover">
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
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">PUBLIKACJA*</h6>
        </div>
        <div style="align-items: flex-start; display: flex;">
            <label style="color: var(--GREY3);" class="form-check-label px-3" for="publishInput">Publikacja (danie widoczne dla użytkowników) </label>
            <div style="margin-bottom: 10px;">
                <input
                    id="publishInput"
                    type="checkbox"
                    v-model="this.toPublish"
                />
            </div>
        </div>
        <div style="justify-content: flex-end; display: flex;">
            <button v-if="this.action==='UPDATE'" class="btn-card-panel-diet" @click="this.updateDish">Aktualizuj danie</button>
            <button v-if="this.action==='ADD'" class="btn-card-panel-diet" @click="this.addDish">Zapisz danie</button>
        </div>
        <div v-if="this.successForm" style="margin: 5px;" class="alert alert-success" role="alert">
            Zaktualizowano dane.
        </div>
        <div v-if="this.errorForm" style="margin: 5px;" class="alert alert-danger" role="alert">
            Brak wymaganych informacji, upewnij się, że wprowadziłeś wszystkie dane oznaczone gwiazdką. 
        </div>
        <div v-if="this.errorFormPhoto" style="margin: 5px;" class="alert alert-danger" role="alert">
            Nie można przesłać zdjęcia, spróbuj ponownie.
        </div>
        <div v-if="this.errorFormName" style="margin: 5px;" class="alert alert-danger" role="alert">
            Istnieje już danie o takiej nazwie.
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
    computed: {
        invalidForm(){
            return this.invalidPhoto ||
                   this.invalidDetails ||
                   this.invalidDescription ||
                   this.invalidRecipe ||
                   this.invalidName ||
                   this.invalidLabels ||
                   this.invalidMealTypes ||
                   this.invalidGlycemicIndex
        },
        invalidPhoto(){
            return this.newDishPhotoSrc === '' || this.newDishPhotoSrc == null
        },
        invalidDetails(){
            return this.addedProductDetails.length == 0
        },
        invalidDescription(){
            return this.dishDescription === ''
        },
        invalidRecipe(){
            return this.dishRecipe === ''
        },
        invalidName(){
            return this.dishName === ''
        },
        invalidLabels(){
            return this.chosenNutritionLabels.length == 0
        },
        invalidMealTypes(){
            return this.chosenMealTypes.length == 0
        },
        invalidGlycemicIndex(){
            return this.actualSelectedGlycemicIndex === '' || this.actualSelectedGlycemicIndex == null
        }
    },
    watch: {
        modalOpened: function () {
            console.log("opened")
            if(this.action === 'UPDATE'){
                this.fillInputsOnUpdate()
                this.fillPhotoOnUpdate()
            }
        },
        modalClosed: function () {
            this.clearStatus()
            this.clearInputs()
            this.clearPhoto()
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
            actualSelectedProductAmount: '',
            actualSelectedProductMeasure: '',
            actualSelectedGlycemicIndex: '',

            chosenNutritionLabels: [],
            chosenMealTypes: [],

            productsToSelect: [],
            addedProductDetails: [],
            dishDescription: '',
            dishName: '',
            dishRecipe: '',
            toPublish: false,

            productDetailAlert: false,
            successForm: false,
            errorForm: false,
            errorFormPhoto: false,
            errorFormName: false,

            newDishPhotoSrc: '',
            newPhoto: false,
        }
    },
    methods: {
        fillInputsOnUpdate(){
            this.actualSelectedProduct = '',
            this.actualSelectedProductAmount = '',
            this.actualSelectedProductMeasure = '',

            this.actualSelectedGlycemicIndex = this.dish.glycemicIndexLevel,
            this.chosenNutritionLabels = [],
            this.dish.allowedForNutritionLabels.forEach((elem) => this.chosenNutritionLabels.push(elem.nutritionLabelId))

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
            this.toPublish = !this.dish.draft,

            this.newDishPhotoSrc = ''
            this.newPhoto = false;

            this.productDetailAlert = false
        },
        fillPhotoOnUpdate(){
            if (this.dish.imgDishPath != null && this.dish.imgDishPath != '') {
                const url = `${this.apiURL}dish/${this.dish.id}/photo`
                const token = localStorage.getItem('token')
                this.$func_global.downloadPhoto(url, token).then(result => this.newDishPhotoSrc = result)
            }
        },
        onFileChange(e) {
            this.newDishPhotoSrc = URL.createObjectURL(e.target.files[0]);
            this.newPhoto = true;
        },

        //GET DATA FOR DISHES

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

        //ADDING DISH PRODUCT DETAILS IN FORM

        addProductDetail(){
            if(this.invalidDetails){
                this.errorForm = false
            }
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

        //PREPARING DISH DATA DO REQUEST

        prepareMealObjectsArray(){
            let result = []
            this.chosenMealTypes.forEach((mealType) => result.push({mealType: mealType}))
            return result
        },
        prepareNutritionObjectsArray(){
            let result = []
            this.chosenNutritionLabels.forEach((nutritionLabel) => result.push({nutritionLabelId: nutritionLabel}))
            return result;

        },
        prepareProductsDetailArray(){
            let result = []
            this.addedProductDetails.forEach((detail) => result.push({amountOfProduct: detail.amountOfProduct,
            measureType: detail.measureType, product: {id: detail.product.id}}))
            return result;
        },
        prepareData(id, imgPath){
            console.log(this.actualSelectedGlycemicIndex)
            let result = {
                    id: id,
                    name: this.dishName,
                    description: this.dishDescription,
                    recipe: this.dishRecipe,
                    imgDishPath: imgPath,
                    active: true,
                    draft: !this.toPublish,
                    glycemicIndexLevel: this.actualSelectedGlycemicIndex,
                    dishProductDetails: this.prepareProductsDetailArray(),
                    dishMealTypes: this.prepareMealObjectsArray(),
                    allowedForNutritionLabels: this.prepareNutritionObjectsArray(),
                }
            return result;
        },

        //SENDING DISH DATA

        updateDish(){
            if(this.invalidForm){
                this.errorForm = true;
                return
            }
            axios({
                method: 'put',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/dish/" + this.dish.id + '/dietician',
                data: this.prepareData(this.dish.id, this.dish.imgDishPath)
            })
            .then((response) => {
                console.log(response);
                if(this.newPhoto){
                    this.$func_global.importDataFunc(this.$refs.newDishPicture, localStorage.getItem('token'), 'dishPicture', this.dish.id)
                    .then((response) => {
                        console.log(response);this.$emit("dish:updated"); this.successForm=true;
                    })
                    .catch(e => {console.log(e); this.errorFormPhoto = true})
                }
                else{
                    console.log(response);this.$emit("dish:updated");this.successForm=true;
                }
            })
            .catch(e => {this.errorForm = true;
                    if(e.status === '409')
                        this.errorFormName = true
                    console.log(e);}
                )
        },
        
        addDish(){
            if(this.invalidForm){
                this.errorForm = true;
                return
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/dish/dietician",
                data: this.prepareData(null, null)
            })
            .then((response) => {
                console.log(response);
                if(this.newPhoto){
                    this.$func_global.importDataFunc(this.$refs.newDishPicture, localStorage.getItem('token'), 'dishPicture', response.data.id)
                    .then((response) => {
                        console.log(response);this.$emit("dish:updated");this.successForm=true;
                    })
                    .catch(e => {console.log(e); this.errorFormPhoto = true})
                }
                else{
                    console.log(response);this.$emit("dish:updated");this.successForm=true;
                }
            })
            .catch(e => {this.errorForm = true;
                    if(e.response.status === 409)
                        this.errorFormName = true
                        this.errorForm = false;
                    console.log(e);}
                )
        },

        //CLEARS

        clearInputs(){
            this.actualSelectedProduct = ''
            this.actualSelectedProductAmount = ''
            this.actualSelectedProductMeasure = ''
            this.chosenNutritionLabels = []
            this.chosenMealTypes= []
            this.addedProductDetails = []
            this.dishDescription = ''
            this.dishName = ''
            this.dishRecipe = ''
            this.actualSelectedGlycemicIndex = ''
            this.actualSelectedProductMeasure = ''
            this.toPublish = false;
        },
        clearStatus(){
            this.productDetailAlert= false
            this.successForm= false
            this.errorForm= false
            this.errorFormPhoto = false
            this.errorFormName = false
        },
        clearProductsStatus(){
            this.actualSelectedProduct = ''
            this.actualSelectedProductAmount = ''
            this.actualSelectedProductMeasure = ''
        },
        clearPhoto() {
            this.newDishPhotoSrc = ''
            this.newPhoto = false;
            this.errorFormPhoto = false;
            this.errorForm = false;
        },
        clearFormAndPhotoStatus(){
            if(this.invalidPhoto)
                this.errorForm = false;
            this.errorFormPhoto = false;
        },
        clearFormAndNameStatus(){
            if(this.invalidName)
                this.errorForm = false;
            this.errorFormName = false;
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
        margin-bottom: 4px
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 10px;
        font-size: 10pt;
    }
    .label-picker{
        margin-bottom: 0px;
        width: 100%;
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
    input[type="file"] {
        display: none;
    }
    .custom-file-upload {
        border: 1px solid #ccc;
        display: inline-block;
        padding: 5px;
        cursor: pointer;
        color: white;
        background-color: var(--DIET);
        border-radius: 10px;
        margin: 2px;
    }
</style>