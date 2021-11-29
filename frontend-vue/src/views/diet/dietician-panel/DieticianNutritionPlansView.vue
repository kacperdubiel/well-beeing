<template>
    <div class="tab-content column-view">
        <nutrition-plan-component @positions:updated="onPositionsUpdated" :nutritionPlanId="this.actualPlanId"/>
        <user-nutrition-plans-list-component :dieticianView="true" :suggested="false" @load:plan="changePlan"/>
    </div>
</template>

<script>
import NutritionPlanComponent from "@/components/diet/plans/NutritionPlanComponent.vue"
import UserNutritionPlansListComponent from "@/components/diet/plans/UserNutritionPlansListComponent"
export default {
    name: "DieticianNutritionPlansView",
    data(){
        return{
            actualPlanId: '',
            updatedNutritionPlan: {id: null}
        }
    },
    components: {
        NutritionPlanComponent,
        UserNutritionPlansListComponent
    },
    methods: {
        changePlan(planId){
            this.actualPlanId = planId
        },
        onPositionsUpdated(nutritionPlan){
            this.updatedNutritionPlan = nutritionPlan
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