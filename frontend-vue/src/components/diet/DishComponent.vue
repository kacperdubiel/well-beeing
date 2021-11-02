<template>
    <div class="modal-dish-content">
        <h3>{{dish.name}}</h3>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">OPIS</h6>
        </div>
        <p>{{dish.description}}</p>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">PRZEPIS</h6>
        </div>
        <p>{{dish.recipe}}</p>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">SKŁADNIKI NA PORCJĘ</h6>
        </div>
        <table style="text-align: start; color: var(--GREY2)" class="table table-borderless">
            <thead>
                <tr>
                    <th style="width: 50%">Produkt</th>
                    <th style="width: 50%">Ilość</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="productDetail in this.dish.dishProductDetails" :key="productDetail.id">
                    <th scope="row">{{productDetail.product.name}}</th>
                    <td>{{productDetail.amount}} {{this.$func_global.mapMeasure(productDetail.measureType)}}</td>
                </tr>
            </tbody>
        </table>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">KALORIE I MAKROELEMENTY</h6>
        </div>
        <table style="text-align: start; color: var(--GREY2)" class="table table-borderless">
            <thead>
                <tr>
                    <th style="width: 50%">Makro</th>
                    <th style="width: 50%">Ilość</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">Białka</th>
                    <td>{{this.dish.derivedProteins}} g</td>
                </tr>
                <tr>
                    <th scope="row">Węglowodany</th>
                    <td>{{this.dish.derivedCarbohydrates}} g</td>
                </tr>
                <tr>
                    <th scope="row">Tłuszcze</th>
                    <td>{{this.dish.derivedFats}} g</td>
                </tr>
            </tbody>
        </table>
        <p> Łącznie: <span> {{this.dish.derivedCalories}} </span> kcal </p>
    </div>
</template>

<script>
export default {
    name: "DishComponent",
    props: {
        dish:  {
            type: Object
        }
    }

}
</script>

<style scoped>
    .modal-dish-content {
        background-color: white;
        color: black;
        width: 100%;
        height: 100%;
        border-radius: 25px;
        display: flex;
        flex-direction: column;
        align-content: flex-start;
        align-items: flex-start;
        padding: 30px;
    }

    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 96%;
        margin-top: 3px;
        margin-bottom: 8px;           
        background-color:var(--GREY1);
        height: 2px;
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
</style>