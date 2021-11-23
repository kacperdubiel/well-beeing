<template>
    <div>
        <div class="carousel-item-item">
            <div class="mt-1">{{this.meal}}</div>
            <button @click="addPosition" v-if="this.position == null && isForm" class="add-button" data-bs-toggle="modal" data-bs-target="#addPositionModal"> <font-awesome-icon :icon="['fa', 'plus-circle']"/> </button>
            <p v-if="!isForm">{{this.mapDishName(position)}}</p>
            <div v-if="this.position != null && isForm">
                <p>{{this.mapDishName(position)}}
                    <button @click="deletePosition" class="add-button"> <font-awesome-icon :icon="['fa', 'trash']"/> </button>
                </p>
            </div>
        </div>
    </div>
</template>

<script>
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
    watch: {
        componentState: function () {
            this.isForm = this.componentState
        }
    },
    data(){
        return{
            isForm: false,
        }
    },
    methods: {
        addPosition(){
            this.$emit("add:position", this.day, this.meal)
        },
        deletePosition(){
            this.$emit("delete:position", this.position.id)
        },
        mapDishName(position){
            if(position === null)
                return 'BRAK'
            else return position.dish.name
        },
    }
}
</script>

<style scoped>

    .carousel-item-item{
        height: 90px;
        padding: 2px;
        width: 70%;
        background-color: var(--GREY3);
        justify-content: center;
        border-radius: 10px;
        align-self: center;
        align-content: center;
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

</style>