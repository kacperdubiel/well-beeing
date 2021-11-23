<template>
    <div class="tab-content column-view">
        <nutrition-plan-component :nutritionPlan="this.actualPlan"/>
        <user-nutrition-plans-list-component :suggested="false" @load:plan="changePlan"/>
        <user-nutrition-plans-list-component :suggested="true" @load:plan="changePlan"/>
    </div>
</template>

<script>
import NutritionPlanComponent from "@/components/diet/plans/NutritionPlanComponent.vue"
import UserNutritionPlansListComponent from "@/components/diet/plans/UserNutritionPlansListComponent"
export default {
    name: "UserNutritionPlansView",
    data(){
        return{
            actualPlan: {
                nutritionPlanPositions: []
            },
        }
    },
    components: {
        NutritionPlanComponent,
        UserNutritionPlansListComponent
    },
    methods: {
        changePlan(plan){
            this.actualPlan = plan
        }
    },
}
</script>

<style scoped>

.column-view{
    flex-direction: column;
    display: flex;
}

</style>