<template>
    <div class="tab-content column-view">
        <nutrition-plan-component @positions:updated="onPositionsUpdated" :nutritionPlanId="this.actualPlanId"/>
        <user-nutrition-plans-list-component :updateAfterChoice="this.actualPlanId" :updateAfterMark="this.updateAfterMark" :mainFound="false" @plan:marked="onPlanMarked" @main:found="onMainFound" :dieticianView="false" :suggested="false" @load:plan="changePlan"/>
        <user-nutrition-plans-list-component :updateAfterChoice="this.actualPlanId" :updateAfterMark="this.updateAfterMark" :mainFound="this.mainFound" @plan:marked="onPlanMarked" :dieticianView="false" :suggested="true" @load:plan="changePlan"/>
    </div>
</template>

<script>
import NutritionPlanComponent from "@/components/diet/plans/NutritionPlanComponent.vue"
import UserNutritionPlansListComponent from "@/components/diet/plans/UserNutritionPlansListComponent"
export default {
    name: "UserNutritionPlansView",
    data(){
        return{
            actualPlanId: '',
            updatedNutritionPlan: {id: null},
            mainFound: false,
            updateAfterMark: 0
        }
    },
    components: {
        NutritionPlanComponent,
        UserNutritionPlansListComponent
    },
    methods: {
        onPlanMarked(){
            this.updateAfterMark = this.updateAfterMark+1
        },
        onMainFound(value){
            this.mainFound = value
        },
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