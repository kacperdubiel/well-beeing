<template>
    <div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">NAZWA*</h6>
        </div>
        <div>
            <textarea @focus="this.clearFormAndNameStatus" id="label-name" class="form-control" rows="1" maxlength="255" v-model="this.name"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">OPIS*</h6>
        </div>
        <div>
            <textarea @focus="this.errorForm = this.invalidDescription ? false : this.errorForm" id="label-description" class="form-control" rows="3" maxlength="255" v-model="this.description"></textarea>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ZALECANA DLA CHORÓB / ALERGII / STANÓW FIZYCZNYCH*</h6>
        </div>
        <div>
            <v-select
                @click="this.errorForm=this.invalidAilments ? false : errorForm"
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.selectedAilments"
                :options="this.ailmentsToSelect"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title" style="align-items: flex-start; display: flex">
            <h6 class="title">ZALECANA DLA DIET*</h6>
        </div>
        <div>
            <v-select
                @click="this.errorForm=this.invalidDiets ? false : errorForm"
                class="px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="this.selectedDiets"
                :options="this.dietsToSelect"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <div style="justify-content: flex-end; display: flex;">
            <button v-if="this.action==='UPDATE'" class="btn-card-panel-diet" @click="this.updateLabel">Aktualizuj etykietę</button>
            <button v-if="this.action==='ADD'" class="btn-card-panel-diet" @click="this.addLabel">Zapisz etykietę</button>
        </div>
        <div v-if="this.successForm" style="margin: 5px;" class="alert alert-success" role="alert">
            Zaktualizowano dane.
        </div>
        <div v-if="this.errorForm" style="margin: 5px;" class="alert alert-danger" role="alert">
            Brak wymaganych informacji, upewnij się, że wprowadziłeś wszystkie dane oznaczone gwiazdką. 
        </div>
        <div v-if="this.errorFormName" style="margin: 5px;" class="alert alert-danger" role="alert">
            Istnieje już etykieta o takiej nazwie.
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    props: {
        label: {
            type: Object
        },
        action: {
            type: String
        },
        modalClosed: {
            type: Boolean
        },
        modalOpened: {
            type: Boolean
        }
    },
    name: "DishFormComponent",
    mounted(){
        this.getDietsToSelect()
        this.getAilmentsToSelect()
    },
    computed: {
        invalidForm(){
            return this.invalidName ||
                   this.invalidDescription ||
                   this.invalidAilments ||
                   this.invalidDiets 
        },
        invalidName(){
            return this.name === ''
        },
        invalidDescription(){
            return this.description === ''
        },
        invalidAilments(){
            return this.selectedAilments.length === 0
        },
        invalidDiets(){
            return this.selectedDiets.length === 0
        }
    },
    watch: {
        modalOpened: function () {
            if(this.action === 'UPDATE'){
                this.fillInputsOnUpdate()
            }
        },
        modalClosed: function () {
            this.clearStatus()
            this.clearInputs()
        }
    },
    data(){
        return{
            ailmentsToSelect: [],
            dietsToSelect: [],
            selectedAilments: [],
            selectedDiets: [],
            name: '',
            description: '',

            errorForm: false,
            errorFormName: false,
            successForm: false,
        }
    },
    methods: {
        fillInputsOnUpdate(){
            this.selectedAilments = []
            this.label.labeledAilments.forEach(elem => this.selectedAilments.push(elem.id))
            this.selectedDiets = []
            this.label.labeledDiets.forEach(elem => this.selectedDiets.push(elem.id))
            this.name = this.label.name
            this.description = this.label.description
        },

        //GET DATA FOR LABELS

        getDietsToSelect(){
            axios.get('http://localhost:8090/diet', {
            headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.dietsToSelect = data.data
            }).catch(e => alert(e))
        },
        getAilmentsToSelect(){
            axios.get('http://localhost:8090/ailment', {
            headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.ailmentsToSelect = data.data
            }).catch(e => alert(e))
        },

        //PREPARING LABEL DATA TO REQUEST
        prepareDietsArray(){
            let dietObjectsArray = []
            this.selectedDiets.forEach(elem => dietObjectsArray.push({"id": elem}))
            return dietObjectsArray
        },
        prepareAilmentsArray(){
            let ailmentsObjectsArray = []
            this.selectedAilments.forEach(elem => ailmentsObjectsArray.push({"id": elem}))
            return ailmentsObjectsArray
        },
        prepareData(id){
            let result = {
                nutritionLabelId: id,
                name: this.name,
                description: this.description,
                labeledAilments: this.prepareAilmentsArray(),
                labeledDiets: this.prepareDietsArray(),
                active: true,
                creator: {
                    id: localStorage.getItem('userId')
                }
            }
            return result
        },

        //SENDING LABEL DATA

        updateLabel(){
            if(this.invalidForm){
                this.errorForm = true;
                return
            }
            axios({
                method: 'put',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/nutrition-label/" + this.label.nutritionLabelId,
                data: this.prepareData(this.label.nutritionLabelId)
            })
            .then((response) => {
                this.successForm = true
                this.$emit('label:updated')
                console.log(response);
            })
            .catch(e => {this.errorForm = true;
                    if(e.response.status === 409)
                        this.errorFormName = true
                        this.errorForm = false;
                    console.log(e);})
        },
        
        addLabel(){
            if(this.invalidForm){
                this.errorForm = true;
                return
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/nutrition-label",
                data: this.prepareData(null)
            })
            .then((response) => {
                this.successForm = true
                console.log(response);
                this.$emit('label:updated')
            })
            .catch(e => {this.errorForm = true;
                    if(e.response.status === 409)
                        this.errorFormName = true
                        this.errorForm = false;
                    console.log(e);})
        },

        //CLEARS

        clearInputs(){
            this.selectedAilments = []
            this.selectedDiets = []
            this.name = ''
            this.description = ''
        },
        clearStatus(){
            this.errorForm =  false
            this.errorFormName = false
            this.successForm = false
        },
        clearFormAndNameStatus(){
            if(this.invalidName)
                this.errorForm = false;
            this.errorFormName = false;
        }
    }
}
</script>

<style scoped>
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 100%;
        margin-top: 20px;          
        background-color:var(--GREY1);
        height: 1px;
    }
    .title-line {
        margin-bottom: 4px
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 10px;
        font-size: 10pt;
    }
    .label-picker{
        margin-bottom: 0px;
        width: 100%;
    }
</style>