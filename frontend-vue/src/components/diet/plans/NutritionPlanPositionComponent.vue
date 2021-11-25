<template>
    <div class="container-fluid">
        <div @click="openModal" style="pointer: cursor;" class="carousel-item-item">
            <div style="width: 100%; height: 92%; justify-content: center; display: flex;" class="row">
                <div class="photo-container col-lg-3">
                    <img v-if="this.dishPhotoSrc != ''" class="mt-1 ms-0" :src="this.dishPhotoSrc">
                </div>
                <div class="info-container col-lg-9">
                    <div style="align-self: end; color: var(--GREY1);" class="mt-1">
                        <p class="m-0" style="color: var(--GREY1);"> {{this.$func_global.mapMeal(this.meal)}}<span v-if="position == null"> / BRAK INFORMACJI </span></p>
                    </div>
                    <button style="align-self: end;" @click="addPosition" v-if="this.position == null && isForm" class="add-button m-2" data-bs-toggle="modal" data-bs-target="#addPositionModal"> <font-awesome-icon :icon="['fa', 'plus-circle']"/> </button>
                    <hr v-if="position!=null" class="hr-dish"/>
                    <p class="m-0" v-if="!isForm">{{this.mapDishName(position)}}</p>
                    <p class="m-0" style="color: var(--GREY1);" v-if="!isForm && this.position != null">{{position.dish.derivedNutritionalValues.derivedCalories}} kcal / porcja</p>
                    <div style="width: 100%;" v-if="this.position != null && isForm">
                        <div style="text-align: start;" class="row">
                            <div class="col-lg-9">
                                <p>{{this.mapDishName(position)}}</p>
                            </div>
                            <div style="justify-content: flex-end; display: flex;" class="col-lg-3">
                                <button @click="deletePosition" class="add-button m-2"> <font-awesome-icon :icon="['fa', 'trash']"/> </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import DishComponent from '@/components/diet/DishComponent.vue'
export default {
    name: "NutritionPlanPositionComponent",
    props: {
        position: {
            type: Object
        },
        meal: {
            type: String
        },
        day: {
            type: String
        },
        componentState: {
            type: Boolean
        },
        planId: {
            type: String
        }
    },
    components: {
        DishComponent
    },
    watch: {
        componentState: function () {
            this.isForm = this.componentState
        },
        position: function () {
            if(this.position == null){
                this.dishPhotoSrc = ''
                this.modalPosition = this.position
            }
            else{
                this.downloadPhoto()
            }          
        }
    },
    data(){
        return{
            isForm: false,
            dishPhotoSrc: '',
            modalPosition: this.position
        }
    },
    mounted(){
        this.downloadPhoto()
    },
    methods: {
        openModal(){
            if(this.position != null){
                this.$emit('open:position', this.position.dish)
            }
        },
        addPosition(){
            this.$emit("add:position", this.day, this.meal)
        },
        deletePosition(){
            this.$emit("delete:position", this.position.id)
        },
        mapDishName(position){
            if(position === null)
                return ''
            else return position.dish.name
        },
        mapDishDescription(position){
            if(position === null)
                return ''
            else return position.dish.description
        },
        downloadPhoto(){
            if(this.position != null){
                if (this.position.dish.imgDishPath != null && this.position.dish.imgDishPath != '') {
                    const url = `${this.apiURL}dish/${this.position.dish.id}/photo`
                    const token = this.$store.getters.getToken
                    this.$func_global.downloadPhoto(url, token).then(result => this.dishPhotoSrc = result)
                }
            }
        },
    }
}
</script>

<style scoped>

    .carousel-item-item{
        height: 120px;
        width: 70%;
        background-color: var(--GREY3);
        justify-content: center;
        border-radius: 10px;
        align-self: center;
        align-items: center;
        align-content: center;
        display: flex;
    }
    .carousel-item-item:hover{
        border-color: var(--GREY1);
        border-style: solid;
        border-width: 1px;
    }
    .add-button{
        color: var(--GREY1);
        background-color: transparent;
        width: 50px;
        height: 50px;
        border: none;
        font-size: 30px;
        font-weight: bold;
    }
    img {
        object-fit:cover;
        max-width:100px;
        max-height:100px;
        width: 100%;
        height: 100%;
        border-radius: 10px;
        display: flex;
    }
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 100%;
        margin-top: 0px;
        margin-bottom: 8px;           
        background-color:var(--GREY1);
        height: 2px;
    }
    .photo-container {
        height:100%;
        border-radius:10px;
        justify-content: center;
        display: flex;
    }

    .info-container {
        justify-content: start;
        display: flex;
        flex-direction: column;
        align-items: start;
    }

</style>