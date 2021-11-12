<template>
    <div class="modal-diet-content">
        <div class="row" style="width: 100%;">
            <div class="col-lg-4" style="border-radius: 10px; height: 200px; width: 200px; margin: 4px;">
                <img style="border-radius: 10px;" class="w-100 p-1 m-1" :src="this.dishPhotoSrc">
            </div>
            <div style="display: flex; flex-direction:column; margin-start: 25px;" class="col-lg-8">
                <hr class="hr-dish title-line"/>
                <div class="title" style="align-items: flex-start; display: flex">
                    <h6 class="title">ETYKIETY</h6>
                </div>
                <div class="labels-container" v-if="dish.allowedForNutritionLabels != null && dish.allowedForNutritionLabels.length > 0">
                    <div class="label-node px-2 py-1 mx-1 my-1"
                        v-for="label in dish.allowedForNutritionLabels"
                        v-bind:style="{backgroundColor: this.colors[this.getRandomInt(0, 100) % this.colors.length]}"
                        :key="label.id">{{label.name}}
                    </div>
                </div>
                <hr class="hr-dish title-line"/>
                <div class="title" style="align-items: flex-start; display: flex">
                    <h6 class="title">TYP POSIŁKU</h6>
                </div>
                <div class="labels-container" v-if="dish.dishMealTypes != null && dish.dishMealTypes.length > 0">
                    <div class="label-node px-2 py-1 mx-1 my-1"
                        v-for="meal in dish.dishMealTypes"
                        v-bind:style="{backgroundColor: this.colors[this.getRandomInt(0, 10)]}"
                        :key="meal.id">{{this.$func_global.mapMeal(meal.mealType)}}
                    </div>
                </div>
            </div>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">OPIS</h6>
        </div>
        <div style="text-align: start;">
            <p>{{dish.description}}</p>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="justify-content: start; align-items: flex-start; display: flex">
            <h6 class="title">PRZEPIS</h6>
        </div>
        <div style="text-align: start;">
            <p>{{dish.recipe}}</p>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">SKŁADNIKI NA PORCJĘ</h6>
        </div>
        <table class="table table-borderless table-hover table-dish-info">
            <thead>
                <tr>
                    <th class="col-lg-9">Produkt</th>
                    <th class="col-lg-3">Ilość</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="productDetail in this.dish.dishProductDetails" :key="productDetail.id">
                    <th scope="row">{{productDetail.product.name}}</th>
                    <td>{{productDetail.amountOfProduct}} {{this.$func_global.mapMeasure(productDetail.measureType)}}</td>
                </tr>
            </tbody>
        </table>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">MAKROELEMENTY</h6>
        </div>
        <table class="table table-borderless table-hover table-dish-info">
            <thead>
                <tr>
                    <th class="col-lg-9">Makro</th>
                    <th class="col-lg-3">Ilość</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">Białka</th>
                    <td >{{Math.round(this.dish.derivedNutritionalValues.derivedProteins)}} g</td>
                </tr>
                <tr>
                    <th scope="row">Węglowodany</th>
                    <td>{{Math.round(this.dish.derivedNutritionalValues.derivedCarbohydrates)}} g</td>
                </tr>
                <tr>
                    <th scope="row">Tłuszcze</th>
                    <td>{{Math.round(this.dish.derivedNutritionalValues.derivedFats)}} g</td>
                </tr>
            </tbody>
        </table>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">INDEKS GLIKEMICZNY</h6>
        </div>
        <p>{{this.$func_global.mapGlycemicIndex(this.dish.glycemicIndexLevel)}}</p>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">KALORIE</h6>
        </div>
        <h4 style="color: var(--GREY2);"> Łącznie: {{this.dish.derivedNutritionalValues.derivedCalories}} kcal / porcja </h4>
    </div>
</template>

<script>
export default {
    name: "DishComponent",
    props: {
        dish:  {
            type: Object
        }
    },
    watch: {
        dish: function () {
            this.downloadPhoto()
        }
    },
    mounted(){
        this.downloadPhoto()
    },
    data(){
        return{
            colors: ['#C33149', '#FEA12A', '#08415C', '#0E9594', '#8FB339', '#90E39A', '#96E6B3', '#5386E4', '#585123', '#802392'],
            dishPhotoSrc: ''
        }
    },
    methods: {
        getRandomInt(min, max) {
            min = Math.ceil(min);
            max = Math.floor(max);
            return Math.round(Math.floor(Math.random() * (max - min)) + min);
        },
        downloadPhoto(){
            if (this.dish.imgDishPath != null && this.dish.imgDishPath != '') {
                const url = `${this.apiURL}dish/${this.dish.id}/photo`
                const token = localStorage.getItem('token')
                this.$func_global.downloadPhoto(url, token).then(result => this.dishPhotoSrc = result)
            }
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
        margin-top: 8px;          
        background-color:var(--GREY1);
        height: 1px;
    }
    .title-line {
        margin-bottom: 0px
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 10px;
        font-size: 10pt;
    }
    .labels-container {
        width: 95%;
        border-style: dotted;
        border-color: var(--GREY1);
        border-radius: 5px;
        border-width: 2px;
        display: flex;
        flex-wrap: wrap;
        padding: 5px;
    }
    .label-node {
        border-radius: 10px;
        color: white;
        font-size: x-small;
        font-weight: normal;
    }
    .table-dish-info {
        text-align: start;
        color: var(--GREY2);
        width: 100%;
        border-color: var(--DIET);
        border-style: solid;
        border-width: 1px
    }
    .bdr{
        border-radius: 10px;
        overflow: hidden;
    }
    
</style>