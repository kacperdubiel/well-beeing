<template>
    <div>
        <div class="carousel-item-item">
            <p>{{this.meal}}</p>
            <p>{{this.mapDishName(position)}}</p> 
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
        }
    },
    methods: {
        mapDishName(position){
            if(position === null)
                return 'BRAK'
            else return position.dish.name
        }
    }
}
</script>

<style scoped>

.carousel-item-item{
        height: 90px;
        padding: 5px;
        width: 70%;
        background-color: var(--GREY3);
        justify-content: center;
        border-radius: 10px;
        align-self: center;
        align-content: center;
    }

</style>