<template>
    <div v-if="this.userNutritionPlans.length !== 0" class="container-fluid">
        <h5 class="mt-5 mb-1 title" v-if="suggested">PLANY UDOSTĘPNIANE CI PRZEZ DIETETYKÓW</h5>
        <div v-else class="title-container">
            <h5 class="mt-5 mb-1 title">TWOJE PLANY</h5>
            <button data-bs-toggle="modal" data-bs-target="#planAddModal" class="mt-5 mb-1 add-button"> <font-awesome-icon :icon="['fa', 'plus-circle']"/> </button>
        </div>
        <table style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-1" scope="col">Nazwa</th>
                    <th class="col-sm-1" scope="col">Data utworzenia</th>
                    <th class="col-sm-1" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="plan in this.userNutritionPlans" :key="plan.id" @click="this.setActualNutritionPlan(plan)" style="cursor: pointer;">
                    <td v-bind:class="{'main' : plan.id === this.actualPlan.id}">{{plan.name}} <font-awesome-icon class="mx-3" v-if="plan.main" :icon="['fa', 'home']"/></td>
                    <td v-bind:class="{'main' : plan.id === this.actualPlan.id}">{{plan.generationDate}}</td>
                    <td style="text-align: end;">
                        <!--<button @click="this.setActualNutritionPlan(plan)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'info']"/></button>-->
                        <button @click="this.markNutritionPlanAsMain(plan)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'home']"/></button>
                        <button @click="this.setPlanToDelete(plan)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#planDeleteModal"><font-awesome-icon :icon="['fa', 'trash']"/></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="planDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planModalLabel">Usuń plan</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div v-if="!this.planToDelete.main">
                            <p style="color: black;">Czy na pewno chcesz usunąć plan?</p>
                            <div v-if="!planToDelete.main">
                                <button @click="this.deleteNutritionPlan(this.planToDelete)" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                                <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                            </div>
                        </div>
                        <div v-else>
                            <p class="my-4" style="color: black;">Nie usuwaj swojego głównego planu, najpierw zmień go na inny!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="planAddModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planAddModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planAddModalLabel">Dodaj nowy plan</h5>
                        <button @click="clearStatusAndInput" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div style="text-align: start;">
                            <p style="color: black;">Podaj nazwę dla planu: </p>
                            <div>
                                <textarea @focus="clearStatus" id="plan-name" class="form-control mb-3" rows="1" maxlength="255" v-model="this.planName"></textarea>
                            </div>
                            <div style="width: 100%; justify-content: flex-end; display: flex;">
                                <button @click="addEmpyNutritionPlan" style="margin: 2px;" type="button" class="btn btn-success">Dodaj plan</button>
                            </div>
                            <div v-if="this.addPlanSuccess" class="alert alert-success" role="alert">
                                Dodano pusty plan do twojego profilu, edytuj go dodając pozycje.  
                            </div>
                            <div v-if="this.addPlanError" class="alert alert-danger" role="alert">
                                Błąd połączenia, nie udało się dodać planu!
                            </div>
                            <div v-if="this.addPlanNameError" class="alert alert-danger" role="alert">
                                Wprowadź nazwę dla planu. 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "UserNutritionPlanListComponent",
    props: {
        suggested: {
            type: Boolean
        }
    },
    data(){
        return {
            userNutritionPlans: [],
            planLoaded : false,
            planToDelete: {},
            planName: '',
            addPlanSuccess: false,
            addPlanError: false,
            addPlanNameError: false,
        }
    },
    mounted(){
        this.getUserNutritionPlans()
    },
    methods: {
        addEmpyNutritionPlan(){
            if(this.planName === ''){
                this.addPlanNameError = true
                return;
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan`,
                data: {
                    name: this.planName
                }
            })
            .then(response => {
                    console.log(response)
                    this.addPlanSuccess = true;
                    //this.getUserNutritionPlans()
                    this.setActualNutritionPlan(response.data)
                }
            )
            .catch(e => {
                console.log(e)
                this.addPlanError = true;
            })
        },
        clearStatus(){
            this.addPlanSuccess= false
            this.addPlanError= false
            this.addPlanNameError= false
        },
        clearStatusAndInput(){
            setTimeout(() => {
                this.planName = ''
                this.clearStatus()
            }, 500)
        },
        setPlanToDelete(plan){
            this.planToDelete = plan
        },
        deleteNutritionPlan(plan){
            console.log("DELETE")
            let planUrl;
            if(this.suggested)
                planUrl = `nutrition-plan/${plan.id}/owned`
            else
                planUrl = `nutrition-plan/${plan.id}/created`
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}${planUrl}`,
            })
            .then(response => {
                    console.log(response)
                    this.getUserNutritionPlans()
            }
            )
            .catch(e => console.log(e))
        },
        markNutritionPlanAsMain(plan){
            console.log(`mark! ${plan.id}`)
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan/${plan.id}/main`,
                data: null
            })
            .then(response => {
                    console.log("tututu")
                    console.log(response)
                    //this.getUserNutritionPlans()
                    this.setActualNutritionPlan(response.data)
                }
            )
            .catch(e => console.log(e))
        },
        getUserNutritionPlans(){
            let planUrl;
            if(this.suggested)
                planUrl = 'nutrition-plan/owned'
            else{
                planUrl = 'nutrition-plan/created'
            }
            
            this.axios.get(`${this.apiURL}${planUrl}`,
                {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                console.log(response)
                this.userNutritionPlans = response.data
                if(!this.planLoaded){
                    if(this.userNutritionPlans.length === 0 && !this.suggested)
                        this.$emit('load:plan', null)                
                    else{
                        let mains = this.userNutritionPlans.filter(elem=> elem.main)
                        if(mains.length !== 0){
                            this.setActualNutritionPlan(mains[0])
                        }
                    }
                    this.planLoaded = true
                }
            })
            .catch(e => {
                console.log(e);
            })
        },
        setActualNutritionPlan(plan){
            this.getUserNutritionPlans()
            this.actualPlan = plan
            this.$emit('load:plan', plan)
        }
    }
}
</script>

<style scoped>

    .main{
        color: var(--DIET);
        font-weight: bold;
    }
    .title {
        color: whitesmoke;
        text-align: left;
        display: flex;
        justify-content: flex-start;
        width: 100%;
        align-items: center;
    }

    .add-button{
        color: var(--DIET);
        background-color: transparent;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        border: none;
        font-size: 2rem;
        font-weight: bold;
    }

    .title-container{
        flex-direction: row;
        justify-content: space-between;
        display: flex;
    }

    tr:hover{
        color: var(--DIET);
        font-weight: bold;
    }


</style>