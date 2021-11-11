<template>
    <div class="container-fluid">
        <div  style="width: 100%;">
            <input
                style="border-radius: 10px; padding: 5px"
                type="text"
                placeholder="Wyszukaj..."
                class="w-100 shadow"
                v-model="this.actualNameLike"
            />
        </div>
        <hr style="margin-top: 15px;" class="title-line"/>
        <div style="align-items: flex-start; display: flex">
            <h6 class="title">WYBIERZ ETYKIETY</h6>
        </div>
        <div style="width: 100%;" class="label-picker-container">
            <v-select
                class="style-chooser-diet px-0 py-2 label-picker"
                id="labelPicker"
                multiple v-model="chosenNutritionLabels"
                :options="nutritionLabels"
                :reduce="name => name.id"
                label="name"/>
        </div>
        <div style="width: 100%;" v-for="dish in this.dishesToShow" :key="dish.id">
            <dish-browser-element-component @change:dishModal="changeModalDish" :actualDish="dish"></dish-browser-element-component>
        </div>
        <div v-if="this.showMore" style="justify-content: flex-end; flex-direction: row; display: flex;">
            <button @click="loadNextPage" class="btn-card-panel-diet">Załaduj więcej</button>
        </div>
        <div id="dishBrowserModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="dishModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 style="color: black;" class="modal-title" id="dishModalLabel">{{this.modalDish.name}}</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <dish-component :dish="this.modalDish"></dish-component>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import DishBrowserElementComponent from './DishBrowserElementComponent.vue'
import DishComponent from './DishComponent.vue'
export default {
    name: "DishBrowserComponent",
    components: {
        DishBrowserElementComponent,
        DishComponent
    },
    data(){
        return{
            page: 0,
            numberOfElements: 3,
            nutritionLabels: [],
            chosenNutritionLabels: [],
            dishesToShow: [],
            actualNameLike: '',
            allItems: 0,
            modalDish: {
                derivedNutritionalValues: {
                    derivedProteins: 0,
                    derivedFats: 0,
                    derivedCarbohydrates: 0,
                    derivedCalories: 0
                }
            },
        }
    },
    watch: {
        actualNameLike: function () {
            this.allItems=0
            this.dishesToShow = []
            this.getDishes()
            this.page = 0;
        },
        chosenNutritionLabels: function () {
            this.allItems=0
            this.dishesToShow = []
            this.getDishes()
            this.page = 0;
        }
    },
    computed: {
        showMore(){
            return this.allItems > this.dishesToShow.length
        }
    },
    mounted(){
        this.getNutritionLabels()
        this.getDishes(this.page)
    },
    methods: {
        getNutritionLabels(){
            axios.get('http://localhost:8090/nutrition-label', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.nutritionLabels = data.data
            }).catch(e => alert(e))
        },
        getDishes(page){
            let params = {
                page: page,
                size: this.numberOfElements,
                nameLike: this.actualNameLike
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')},
                params: params,
                url: "http://localhost:8090/dish/labeled",
                data: this.chosenNutritionLabels
            })
            .then((response) => {this.allItems = response.data.totalItems; console.log(response); response.data.objects.forEach((elem) => this.dishesToShow.push(elem))})
            .catch(e => {this.errorForm = true;console.log(e);})
        },
        loadNextPage(){
            this.page = this.page + 1
            this.getDishes(this.page)
        },
        changeModalDish(dish){
            this.modalDish = dish
            console.log(this.modalDish)
            document.get
        }
    }
}
</script>

<style scoped>
.label-picker-container {
    width: 100%;
}

.label-picker {
    width: 100%;
    border-color: whitesmoke;
}

#labelPicker .v-select .dropdown-toggle {
   border-color: whitesmoke;
}

.title-line {
    margin-bottom: 2px;
    
}

.title {
    color: var(--GREY1);
    margin-top: 1px;
    margin-bottom: 5px;
    font-size: 10pt;
}


</style>