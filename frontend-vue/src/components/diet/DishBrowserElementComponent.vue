<template>
    <div class="row dish-browser-element" @click="changeModalDish" style="align-self: flex-end; display: flex;" data-bs-toggle="modal" data-bs-target="#dishBrowserModal">
        <div style="border-radius: 10px; align-self: center;" class="col-lg-3 p-1 ">
            <img style="border-radius: 10px;" class="w-100 p-1 m-1" :src="this.dishPhotoSrc">
            <!--<p style="align-self: flex-start; display: flex;">{{actualDish.derivedNutritionalValues.derivedCalories}} kcal / porcja</p>-->
        </div>
        <div style="text-align: start; align-items: flex-start; display: flex; flex-direction: column;" class="col-lg-9">
            <div style="flex-direction: row; display:flex; width: 100%">
                <h6 class="col-lg-8 px-1" style="margin-top: 8px;">{{this.actualDish.name}}</h6>
                <p class="col-lg-4" style="margin-top: 8px; color: var(--GREY1)">{{actualDish.derivedNutritionalValues.derivedCalories}} kcal / porcja</p>
            </div>
            <hr class="hr-dish"/>
            <p style="color: var(--GREY1);">{{this.actualDish.description}}</p>
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
            dishPhotoSrc: ''
        }
    },
    mounted(){
        this.downloadPhoto()
    },
    methods: {
        changeModalDish(){
            this.$emit('change:dishModal', this.actualDish)
        },
        downloadPhoto(){
            if (this.actualDish.imgDishPath != null && this.actualDish.imgDishPath != '') {
                const url = `${this.apiURL}dish/${this.actualDish.id}/photo`
                const token = localStorage.getItem('token')
                this.$func_global.downloadPhoto(url, token).then(result => this.dishPhotoSrc = result)
            }
        },
    }
}
</script>

<style scoped>

.dish-browser-element {
    background-color: var(--GREY3);
    min-height: 210px;
    widows: 100%;
    border-radius: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
    cursor: pointer;
}

.dish-browser-element:hover {
    border-style: solid;
    border-width: 1px;
    border-color: var(--GREY1)
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