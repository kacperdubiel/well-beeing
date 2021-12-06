<template>
    <div class="tab-content column-view">
        <div style="align-self: center;" v-if="!this.dataLoaded" class="spinner-border mt-3" role="status">
            <span class="sr-only">Loading...</span>
        </div>
        <div :hidden="!this.dataLoaded" class="container-fluid">
            <nutrition-plan-component :userId="null" @positions:updated="onPositionsUpdated" :nutritionPlanId="this.actualPlanId"/>
            <user-nutrition-plans-list-component @plans:fetched="plansFetched" :updateAfterChoice="this.actualPlanId" :updateAfterMark="this.updateAfterMark" :mainFound="false" @plan:marked="onPlanMarked" @main:found="onMainFound" :dieticianView="false" :suggested="false" @load:plan="changePlan"/>
            <user-nutrition-plans-list-component @plans:fetched="plansFetched" :updateAfterChoice="this.actualPlanId" :updateAfterMark="this.updateAfterMark" :mainFound="this.mainFound" @plan:marked="onPlanMarked" :dieticianView="false" :suggested="true" @load:plan="changePlan"/>
        </div>
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
            updateAfterMark: 0,
            dataLoaded: false,
            updatesNumber: 0
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
        },
        plansFetched(){
            this.updatesNumber = this.updatesNumber+1
            console.log(this.updatesNumber)
            if(this.updatesNumber >= 2){
                this.dataLoaded = true
            }
                
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