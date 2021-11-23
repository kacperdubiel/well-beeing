<template>
    <div class="container-fluid">
        <div style="justify-content: flex-end; display: flex; flex-direction: row;">
            <button @click="this.formAction='ADD'" class="add-button" data-bs-toggle="modal" data-bs-target="#dishAddModal"> <font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
        </div>
        <div style="width=100%">
            <div style="justify-content: flex-start; display: flex; flex-direction: row;">
                <p style="color: var(--GREY1)">Liczba pozycji: {{this.navigation.totalElements}}</p>
            </div>
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
        <div class="row my-2 align-items-center justify-content-start d-flex">
            <nav aria-label="..." class="col-xl-8 col-lg-4 col-md-8 col-8 offset-xl-2 offset-lg-2 offset-md-0 offset-0 " >
                <ul class="pagination justify-content-center my-auto">
                    <li class="page-item diet-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a class="page-link" @click="goToPage(0)" tabindex="-1" aria-disabled="true">
                            <font-awesome-icon :icon="['fa', 'fast-backward']" />
                        </a>
                    </li>
                    <li class="page-item diet-page" v-bind:class="{'disabled' : navigation.isFirst}">
                        <a class="page-link" @click="goToPage(navigation.currentPage-1)" tabindex="-1" aria-disabled="true">
                            <font-awesome-icon :icon="['fa', 'chevron-left']" />
                        </a>
                    </li>
                    <li class="page-item diet-page" v-bind:class="{'active' : navigation.currentPage === page}" v-for="page in navigation.pagesNavbar" :key="page"><a class="page-link" @click="goToPage(page)" >{{page+1}}</a></li>
                    <li class="page-item diet-page" v-bind:class="{'disabled' : navigation.isLast}">
                        <a class="page-link" @click="goToPage(navigation.currentPage+1)">
                            <font-awesome-icon :icon="['fa', 'chevron-right']" />
                        </a>
                    </li>
                    <li class="page-item diet-page" v-bind:class="{'disabled' : navigation.isLast}">
                        <a class="page-link" @click="goToPage(navigation.totalPages-1)">
                            <font-awesome-icon :icon="['fa', 'fast-forward']" />
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
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
            modalOpened: false,
            navigation: {
                totalElements: 0,
                totalPages: 0,
                isFirst: false,
                isLast: false,
                isEmpty: false,
                currentPage: 0,
                pageSize: 10,
                pagesNavbar: []
            }
        }
    },
    mounted(){
        this.getDishesToShow()
    },
    methods: {
        goToPage(pageNo) {
            this.navigation.currentPage = pageNo
            this.getDishesToShow()
        },
        onDishesUpdated(){
            this.getDishesToShow()
            this.$emit("dishes:updated")
        },
        getDishesToShow(){
            let params = {
                page: this.navigation.currentPage,
                size: this.navigation.pageSize
            }
            axios.get('http://localhost:8090/dish/dietician', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token'),
                    params: params
                },
            })
            .then(response => {
                this.dishesToShow = response.data.objects
                this.navigation.totalElements = response.data.totalItems
                this.navigation.totalPages = response.data.totalPages
                this.navigation.isFirst = (this.navigation.currentPage === 0)
                if(this.navigation.totalPages === 0 && this.navigation.currentPage === 0){
                    this.navigation.isLast = true;
                }
                else{
                    this.navigation.isLast = (this.navigation.currentPage === this.navigation.totalPages-1)
                }
                this.navigation.isEmpty = (this.dishesToShow.length === 0)
                this.navigation.pagesNavbar = []
                if (this.navigation.currentPage > 1)
                    this.navigation.pagesNavbar.push(this.navigation.currentPage-2)
                if (this.navigation.currentPage !== 0)
                    this.navigation.pagesNavbar.push(this.navigation.currentPage-1)
                for (let i = this.navigation.currentPage; i < this.navigation.totalPages; i++) {
                    this.navigation.pagesNavbar.push(i)
                    if (i === this.navigation.currentPage + 2)
                        break;
                }           
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