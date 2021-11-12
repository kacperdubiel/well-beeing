<template>
    <div class="container-fluid">
        <div style="justify-content: flex-end; display: flex; flex-direction: row;">
            <button @click="this.formAction='ADD'" class="add-button" data-bs-toggle="modal" data-bs-target="#dishAddModal"> <font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
        </div>
        <table style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-1" scope="col">Danie</th>
                    <th class="col-sm-1" scope="col">Status</th>
                    <th class="col-sm-1" scope="col">Data utworzenia</th>
                    <th class="col-sm-1" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dish in this.dishesToShow" :key="dish.id">
                    <td>{{dish.name}}</td>
                    <td>{{this.$func_global.mapPublished(dish.draft)}}</td>
                    <td>{{dish.createdDate}}</td>
                    <td style="text-align: end;">
                        <button @click="this.setActualDish(dish)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#dishInfoModal"><font-awesome-icon :icon="['fa', 'info']"/></button>
                        <button @click="this.setActualDish(dish)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#dishAddModal"><font-awesome-icon :icon="['fa', 'edit']"/></button>
                        <button @click="this.setActualDish(dish)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#dishDeleteModal"><font-awesome-icon :icon="['fa', 'trash']"/></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="dishAddModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="dishAddModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="dishAddModalLabel">Modyfikuj swoje dania</h5>
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <dish-form-component :modalClosed="this.modalClosed" :modalOpened="this.modalOpened" @dish:updated="this.onDishesUpdated" :action="this.formAction" :dish="this.modalDish"></dish-form-component>
                    </div>
                </div>
            </div>
        </div>
        <div id="dishInfoModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="dishInfoModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="reportModalLabel">{{this.modalDish.name}}</h5>
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <dish-component :dish="this.modalDish"></dish-component>
                    </div>
                </div>
            </div>
        </div>
        <div id="dishDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="dishAddModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="dishDeleteModalLabel">Usuń danie</h5>
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p style="color: black;">Czy na pewno chcesz usunąć?</p>
                        <div>
                            <button @click="this.deleteDish" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                            <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import DishComponent from '../DishComponent.vue'
import DishFormComponent from './DishFormComponent.vue'
export default {
    name: "DieticianDishesComponent",
    components: {
        DishComponent,
        DishFormComponent
    },
    data(){
        return {
            dishesToShow: [],
            modalDish: {
                derivedNutritionalValues: {
                    derivedProteins: 0,
                    derivedFats: 0,
                    derivedCarbohydrates: 0,
                    derivedCalories: 0
                }
            },
            formAction: 'UPDATE',
            modalClosed: false,
            modalOpened: false
        }
    },
    mounted(){
        this.getDishesToShow()
    },
    methods: {
        onDishesUpdated(){
            this.getDishesToShow()
            console.log("ZAKTUALIZOWAŁEM")
            this.$emit("dishes:updated")
        },
        getDishesToShow(){   
            axios.get('http://localhost:8090/dish/dietician', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
            })
            .then(data => {
                this.dishesToShow = data.data
            }).catch(e => alert(e))  
        },
        deleteDish(){
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/dish/"+ this.modalDish.id + "/dietician",
            })
            .then((response) => {this.getDishesToShow(); console.log(response); this.onDishesUpdated(); this.modalClosed = !this.modalClosed})
            .catch(error => {console.log(error)})
        },
        setActualDish(dish){
            this.modalDish = dish
            this.formAction = 'UPDATE'
            this.modalOpened = !this.modalOpened
        }
    }
}
</script>

<style scoped>

.add-button{
    color: var(--DIET);
    background-color: transparent;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    padding: 0px;
    margin: 0;
    border: none;
    font-size: 2rem;
    font-weight: bold;
}

</style>