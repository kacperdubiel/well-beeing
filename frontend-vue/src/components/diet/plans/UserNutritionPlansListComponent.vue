<template>
    <div style="text-align: start;" class="container-fluid">
        <div class="title-container">
            <h5 class="mt-5 mb-1 title">{{this.getTitleText()}}</h5>
            <button v-if="!this.suggested" data-bs-toggle="modal" data-bs-target="#planAddModal" class="mt-5 mb-1 add-button"> <font-awesome-icon :icon="['fa', 'plus-circle']"/> </button>
        </div>
        <table v-if="this.userNutritionPlans.length !== 0" style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-4" scope="col">Nazwa</th>
                    <th class="col-sm-4" scope="col">Data utworzenia</th>
                    <th v-if="this.dieticianView" class="col-sm-2" scope="col">Udostępnione dla</th>
                    <th v-else class="col-sm-2" scope="col">
                        <p style="margin:0px;" v-if="this.suggested">Autor</p>
                    </th>
                    <th class="col-sm-2" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr class="tr-row" v-for="plan in this.userNutritionPlans" :key="plan.id">
                    <td @click="this.setActualNutritionPlan(plan)" style="cursor: pointer;" class="col-sm-4" v-bind:class="{'main' : plan.id === this.actualPlan.id}">{{plan.name}}
                        <font-awesome-icon class="mx-3" v-if="plan.main && !dieticianView" :icon="['fa', 'home']"/>
                    </td>
                    <td class="col-sm-4" v-bind:class="{'main' : plan.id === this.actualPlan.id}">{{this.$func_global.formatDateTime(plan.generationDate)}}</td>
                    <td v-if="this.dieticianView" class="col-sm-2">
                        <p v-if="plan.ownerProfile !== null">
                            {{plan.ownerProfile.firstName}} {{plan.ownerProfile.lastName}}
                            <button @click="this.setPlanToChangeOwner(plan)"  data-bs-toggle="modal" :data-bs-target="'#planDeleteOwnerModal_' + this.suggested"><font-awesome-icon  :icon="['fa', 'times']"/></button>
                        </p>
                        <p v-else>
                            <button @click="this.setPlanToChangeOwner(plan)"  data-bs-toggle="modal" :data-bs-target="'#planAddOwnerModal_' + this.suggested"><font-awesome-icon  :icon="['fa', 'plus-circle']"/></button>
                        </p>
                    </td>
                    <td v-else class="col-sm-2">
                        <p style="margin:0px;" v-if="this.suggested">{{plan.creatorProfile.firstName}} {{plan.creatorProfile.lastName}}</p>
                    </td>
                    <td class="col-sm-2" style="text-align: end;">
                        <button v-if="!dieticianView && !plan.main" @click="this.markNutritionPlanAsMain(plan)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'home']"/></button>
                        <button v-if="!dieticianView && plan.main" @click="this.markNutritionPlanAsMain(plan)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'times']"/></button>
                        <button @click="this.setPlanToDelete(plan)" class="btn-icon-panel-diet" data-bs-toggle="modal" :data-bs-target="'#planDeleteModal_' + this.suggested"><font-awesome-icon :icon="['fa', 'trash']"/></button>          
                    </td>
                </tr>
            </tbody>
        </table>
        <p v-else v-bind:class="{'mt-3' : this.suggested}">Brak planów.</p>
        <div :id="'planDeleteModal_' + this.suggested" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planModalLabel">Usuń plan</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div v-if="this.planToDelete.main" class="alert alert-danger" role="alert">
                                Nie usuwaj swojego głównego planu! :)
                        </div>
                        <div v-else>
                            <p style="color: black;">Czy na pewno chcesz usunąć plan?</p>
                            <div style="flex-direction: row; justify-content: center; display: flex;">
                                <button @click="this.deleteNutritionPlan" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                                <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                            </div>
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
                                <textarea @focus="clearStatus" id="plan-name" class="form-control mb-3" rows="1" maxlength="40" v-model="this.planName"></textarea>
                            </div>
                            <div style="width: 100%; justify-content: flex-end; display: flex;">
                                <button @click="addEmptyNutritionPlan" style="margin: 2px; slign-self: end;" type="button" class="btn-card-panel-diet">Dodaj plan</button>
                            </div>
                            <div v-if="this.addPlanSuccess" class="alert alert-success mt-4" role="alert">
                                Dodano pusty plan do twojego profilu, edytuj go dodając pozycje.  
                            </div>
                            <div v-if="this.addPlanError" class="alert alert-danger mt-4" role="alert">
                                Błąd połączenia, nie udało się dodać planu!
                            </div>
                            <div v-if="this.addPlanNameError" class="alert alert-danger mt-4" role="alert">
                                Wprowadź nazwę dla planu. 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div :id="'planAddOwnerModal_' + this.suggested" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planAddOwnerModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planAddOwnerModalLabel">Dodaj właściciela planu</h5>
                        <button id="close-add-owner-modal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <user-connections-to-me-component :fromNutritionPlans="true" connection-type="WITH_DIETICIAN" v-on:open-profile="addNutritionPlanOwner"/>
                    </div>
                </div>
            </div>
        </div>
        <div :id="'planDeleteOwnerModal_' + this.suggested" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planDeleteOwnerModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planDeleteOwnerModalLabel">Usuń właściciela</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <p style="color: black;">Czy na pewno usunąć właściciela planu?</p>
                            <div style="flex-direction: row; justify-content: center; display: flex;">
                                <button @click="this.deleteNutritionPlanOwner" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                                <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
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
import UserConnectionsToMeComponent from '@/components/telemedic/user-connections/UserConnectionsToMeComponent'
export default {
    name: "UserNutritionPlanListComponent",
    components: {
        UserConnectionsToMeComponent
    },
    props: {
        suggested: {
            type: Boolean
        },
        dieticianView: {
            type: Boolean
        },
        mainFound: {
            type: Boolean
        },
        updateAfterMark: {
            type: Number
        },
        updateAfterChoice: {
            type: String
        }
    },
    watch: {
        updateAfterMark: function(){
            console.log("updateAfterMark")
            this.getNutritionPlans()
        },
        updateAfterChoice: function(){
            if(this.updateAfterChoice !== this.actualPlan.id && this.actualPlan.id != null){
                this.actualPlan = {
                    id: null
                }         
            }
        },
    },
    data(){
        return {
            userNutritionPlans: [],
            actualPlan: {
                id: null
            },
            planLoaded : false,
            planToDelete: {
                id: null
            },
            planToChangeOwner: {
                id: null
            },
            planName: '',
            addPlanSuccess: false,
            addPlanError: false,
            addPlanNameError: false,
        }
    },
    mounted(){
        this.getNutritionPlans()
    },
    methods: {
        getNutritionPlans(){
            if(!this.dieticianView)
                this.getUserNutritionPlans()
            else{
                this.getDieticianNutritionPlans()
            }
        },
        addEmptyNutritionPlan(){
            if(!this.dieticianView){
                this.addUserEmptyNutritionPlan()
            }
            else{
                this.addDieticianEmptyNutritionPlan()
            }
        },
        getTitleText(){
            if(this.suggested){
                return 'PLANY UDOSTĘPNIONE CI PRZEZ DIETETYKÓW'
            }
            else{
                return 'TWOJE PLANY'
            }
        },
        addUserEmptyNutritionPlan(){
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
                    this.getNutritionPlans()
                    this.setActualNutritionPlan(response.data)
                }
            )
            .catch(e => {
                console.log(e)
                this.addPlanError = true;
            })
        },
        addDieticianEmptyNutritionPlan(){
            if(this.planName === ''){
                this.addPlanNameError = true
                return;
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan/dietician`,
                data: {
                    name: this.planName
                }
            })
            .then(response => {
                    console.log(response)
                    this.addPlanSuccess = true;
                    this.getNutritionPlans()
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
        setPlanToChangeOwner(plan){
            this.planToChangeOwner = plan
        },
        deleteNutritionPlan(){
            let planUrl;
            if(this.suggested)
                planUrl = `nutrition-plan/${this.planToDelete.id}/owned`
            else
                planUrl = `nutrition-plan/${this.planToDelete.id}/created`
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}${planUrl}`,
            })
            .then(response => {
                    console.log(response)
                    this.getNutritionPlans()
                }
            )
            .catch(e => console.log(e))
        },
        deleteNutritionPlanOwner(){
            let planUrl = `nutrition-plan/${this.planToChangeOwner.id}/owner`;
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}${planUrl}`,
            })
            .then(response => {
                    console.log(response)
                    this.getNutritionPlans()
                }
            )
            .catch(e => console.log(e))
        },
        addNutritionPlanOwner(ownerId){
            let planUrl = `nutrition-plan/${this.planToChangeOwner.id}/owner`;
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}${planUrl}`,
                data: {
                    nutritionPlanOwnerId: ownerId
                }
            })
            .then(response => {
                    console.log(response)
                    this.getNutritionPlans()
                    document.getElementById('close-add-owner-modal').click();
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
                    console.log("response from")
                    console.log(response)
                    this.$emit('plan:marked')
                    //this.getNutritionPlans()
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
                this.onNutritionPlansUpdated(response.data)
            })
            .catch(e => {
                console.log(e);
            })
        },
        getDieticianNutritionPlans(){
            let planUrl = 'nutrition-plan/created/dietician'
            
            this.axios.get(`${this.apiURL}${planUrl}`,
                {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                console.log(response)
                this.onNutritionPlansUpdated(response.data)
            })
            .catch(e => {
                console.log(e);
            })
        },
        onNutritionPlansUpdated(plans){
            this.userNutritionPlans = plans
                if(!this.planLoaded){
                    if(this.userNutritionPlans.length === 0){
                        if(!this.suggested || (this.suggested && !this.mainFound)){
                            this.$emit('load:plan', null)  
                        }
                        this.$emit("main:found", false) 
                    }            
                    else{
                        let mains;
                        if(!this.dieticianView)
                            mains = this.userNutritionPlans.filter(elem=> elem.main)
                        else
                            mains = this.userNutritionPlans

                        if(mains.length !== 0 && !this.mainFound){
                            if(!this.suggested)
                                this.$emit("main:found", true)
                            this.setActualNutritionPlan(mains[0])
                        }
                    }
                    this.planLoaded = true
                }
        },
        setActualNutritionPlan(plan){
            this.actualPlan = plan
            this.$emit('load:plan', plan.id)
        },
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

    .tr-row:hover{
        color: var(--DIET);
        font-weight: bold;
    }


</style>