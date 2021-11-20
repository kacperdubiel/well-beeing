<template>
    <div class="container-fluid">
        <div style="width=100%">
            <div style="justify-content: flex-end; display: flex; flex-direction: row;">
                <button class="add-button" @click="this.formAction='ADD'" data-bs-toggle="modal" data-bs-target="#labelFormModal"> <font-awesome-icon :icon="['fa', 'plus-circle']"/> </button>
            </div>
        </div>
        <div style="width=100%" class="row">
            <div style="justify-content: flex-start; display: flex; flex-direction: row;">
                <p style="color: var(--GREY1)">Liczba pozycji: {{this.navigation.totalElements}}</p>
            </div>
        </div>
        <table style="color: white; text-align: start;" class="table">
            <thead>
                <tr>
                    <th class="col-sm-4" scope="col">Nazwa</th>
                    <th class="col-sm-2" scope="col">Wykluczone dolegliwości</th>
                    <th class="col-sm-2" scope="col">Wykluczone diety</th>
                    <th class="col-sm-4" scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="label in labelsToShow" :key="label.nutritionLabelId">
                    <td>{{label.name}}</td>
                    <td>{{label.labeledAilments.length}}</td>
                    <td>{{label.labeledDiets.length}}</td>
                    <td style="text-align: end;">
                        <button @click="this.setActualLabel(label)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#labelModal"><font-awesome-icon :icon="['fa', 'info']"/></button>
                        <button @click="this.setActualLabel(label)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#labelFormModal"><font-awesome-icon :icon="['fa', 'edit']"/></button>
                        <button @click="this.setActualLabel(label)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#labelDeleteModal"><font-awesome-icon :icon="['fa', 'trash']"/></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="row m-3 align-items-center justify-content-start d-flex">
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
        <div id="labelModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="labelModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="labelModalLabel">{{this.actualModalLabel.name}}</h5>
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div style="width: 100%;">
                            <label-component :label="this.actualModalLabel"></label-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="labelDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade"  tabindex="-1" aria-labelledby="labelDeleteModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p style="color: black;">Czy na pewno chcesz usunąć?</p>
                        <div>
                            <button @click="this.deleteLabel" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                            <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="labelFormModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="labelFormModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 v-if="this.formAction==='UPDATE'" style="color: black;" class="modal-title" id="labelFormModalLabel">{{this.actualModalLabel.name}}</h5>
                        <h5 v-if="this.formAction==='ADD'" style="color: black;" class="modal-title" id="labelFormModalLabel">Dodaj etykietę</h5>
                        <button @click="this.modalClosed = !this.modalClosed" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <label-form-component @label:updated="this.getLabelsToShow" :modalClosed="this.modalClosed" :modalOpened="this.modalOpened" :action="this.formAction" :label="this.actualModalLabel"></label-form-component>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import LabelFormComponent from './LabelFormComponent.vue'
import LabelComponent from './LabelComponent.vue'
export default {
    name: "DieticianLabelsComponent",
    components: {
        LabelFormComponent,
        LabelComponent
    },
    data(){
        return {
            labelsToShow: [],
            actualModalLabel: {   
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
        this.getLabelsToShow()
    },
    methods: {
        goToPage(pageNo) {
            this.navigation.currentPage = pageNo
            this.getLabelsToShow()
        },
        getLabelsToShow(){
            let params = {
                page: this.navigation.currentPage,
                size: this.navigation.pageSize
            }
            axios.get('http://localhost:8090/nutrition-label/my', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
                params: params
            })
            .then(response => {
                    this.labelsToShow = response.data.objects;
                    this.navigation.totalElements = response.data.totalItems
                    this.navigation.totalPages = response.data.totalPages
                    this.navigation.isFirst = (this.navigation.currentPage === 0)
                    if(this.navigation.totalPages === 0 && this.navigation.currentPage === 0){
                        this.navigation.isLast = true;
                    }
                    else{
                        this.navigation.isLast = (this.navigation.currentPage === this.navigation.totalPages-1)
                    }
                    this.navigation.isEmpty = (this.labelsToShow.length === 0)
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
        setActualLabel(label){
            this.actualModalLabel = label
            this.formAction = 'UPDATE'
            this.modalOpened = !this.modalOpened
        },
        deleteLabel(){
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/nutrition-label/"+ this.actualModalLabel.nutritionLabelId,
            })
            .then((response) => {
                //if(this.labelsToShow.length === 1 && this.navigation.currentPage === this.navigation.totalPages-1){
                    //this.navigation.currentPage = this.navigtaion.currentPage - 1
                //}
                this.getLabelsToShow();
                console.log(response);
                })
            .catch(error => {console.log(error)})
        },
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