<template>
    <div class="container-fluid">
        <table style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-1" scope="col">Nazwa</th>
                    <th class="col-sm-1" scope="col">Data utworzenia</th>
                    <th class="col-sm-1" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="plan in this.userNutritionPlans" :key="plan.id">
                    <td>{{plan.name}}</td>
                    <td>{{plan.generationDate}}</td>
                    <td style="text-align: end;">
                        <button @click="this.setActualNutritionPlan(plan)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'info']"/></button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: "UserNutritionPlanListComponent",
    data(){
        return {
            userNutritionPlans: []
        }
    },
    mounted(){
        this.getUserNutritionPlans()
    },
    methods: {
        getUserNutritionPlans(){
            this.axios.get(`${this.apiURL}nutrition-plan`,
                {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                console.log(response)
                this.userNutritionPlans = response.data
            })
            .catch(e => {
                console.log(e);
            })
        },
        setActualNutritionPlan(plan){
            this.$emit('load:plan', plan)
        }
    }
}
</script>

<style scoped>

</style>