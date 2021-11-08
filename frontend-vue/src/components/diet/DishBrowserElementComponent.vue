<template>
    <div class="row dish-browser-element">
        <div style="border-radius: 10px;" class="col-lg-3">
            <p style="align-self: flex-start; display: flex;">{{actualDish.derivedNutritionalValues.derivedCalories}} kcal / porcja</p>
        </div>
        <div style="text-align: start; align-items: flex-start; display: flex; flex-direction: column;" class="col-lg-9">
            <h6 style="margin-top: 8px;">{{this.actualDish.name}}</h6>
            <hr class="hr-dish"/>
            <p style="color: var(--GREY1);">{{this.actualDish.description}}</p>
            <button @click="changeModalDish" style="align-self: flex-end; display: flex;" class="btn-card-panel-diet" data-bs-toggle="modal" data-bs-target="#dishBrowserModal">...</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "DishBrowserElementComponent",
    props: {
        actualDish: {
            type: Object
        }
    },
    data(){
        return{
            image: '../../src/assets/food.png'
        }
    },
    methods: {
        changeModalDish(){
            this.$emit('change:dishModal', this.actualDish)
        }
    }
}
</script>

<style scoped>

.dish-browser-element {
    background-color: var(--GREY3);
    min-height: 125px;
    widows: 100%;
    border-radius: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
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

</style>