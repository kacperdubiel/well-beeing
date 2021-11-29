<template>
    <div class="tab-content" style="flex-direction: column; display: flex;">
        <div class="row mb-4 ms-3 mt-2">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div class="col-10">
                <h3 v-if="user" class="align-left">
                    {{ user.firstName }} {{ user.lastName }}
                </h3>
            </div>
        </div>
        <profile-card-component :dieticianView="true" :userId="this.userId"/>
        <h4 style="text-align: start;" class="container mt-4"> Raporty </h4>
        <dietician-profile-reports-component :fromDietician="true" class="mt-3" :userId="this.userId" />
        <h4 style="text-align: start;" class="container mt-4">Główny plan dietetyczny</h4>
        <nutrition-plan-component :userId="this.userId" :fromProfile="true" :editOff="true" v-if="userNutritionPlan != null" :nutritionPlanId="userNutritionPlan.id"/>
        <div style="align-self: center;" v-else class="alert alert-danger mt-3 mx-3" role="alert">
                Użytkownik nie ma ustawionego głównego planu. 
        </div>
    </div>
</template>

<script> 
import DieticianProfileReportsComponent from "@/components/diet/reports/DieticianProfileReportsComponent";
import NutritionPlanComponent from '@/components/diet/plans/NutritionPlanComponent'
import ProfileCardComponent from '@/components/diet/profile-card/ProfileCardComponent.vue';
export default {
    name: "DieticianUserProfileView",
    components: {
        DieticianProfileReportsComponent,
        ProfileCardComponent,
        NutritionPlanComponent,
    },
    data() {
        return {
            userId: this.$route.params.userId,
            user: null,
            userNutritionPlan: null
        }
    },
    methods: {
        getUserProfile(){
            this.axios.get(`${this.apiURL}profile/${this.userId}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.user = response.data;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getUserMainNutritionPlan(){
            this.axios.get(`${this.apiURL}nutrition-plan/profile/${this.userId}/main`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
                })
                .then(response => {
                    this.userNutritionPlan = response.data;
                    console.log("main")
                    console.log(this.userNutritionPlan)
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    created(){
        this.getUserProfile();
        this.getUserMainNutritionPlan();
    },
}
</script>

<style scoped>

</style>