<template> 
    <div class="tab-content column-view">
        <div style="align-self: center;" v-if="!this.dataLoaded" class="spinner-border mt-3" role="status">
                <span class="sr-only">Loading...</span>
        </div>
        <div :hidden="!this.dataLoaded" class="container-fluid">
            <nutrition-plan-component @positions:updated="onPositionsUpdated" :fromDietician="true" :nutritionPlanId="this.actualPlanId"/>
            <user-nutrition-plans-list-component @plans:fetched="plansFetched" :dieticianView="true" :suggested="false" @load:plan="changePlan"/>
        </div>
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
        },
        plansFetched(){
            this.dataLoaded = true        
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