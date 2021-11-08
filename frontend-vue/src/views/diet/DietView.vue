<template>  
    <div class="section-bg">
        <tab-wrapper-component>
            <tab-component title="Karta">
                <profile-card-component @card:updated="onCardUpdated"></profile-card-component>
            </tab-component>
            <tab-component title="Kalkulacje">
                <calculations-component :key="calcChanger"></calculations-component>
            </tab-component>
            <tab-component title="Raporty">
                <profile-reports-component :key="dishesChanger"></profile-reports-component>
            </tab-component>
            <tab-component title="Dania">
                <dish-browser-component :key="dishesChanger"></dish-browser-component>
            </tab-component>
            <tab-component title="Dietetycy">
                Dietetycyyy
            </tab-component>
            <tab-component title="Obsługa dań">
                <dietician-dishes-component @dishes:updated="onDishesUpdated"></dietician-dishes-component>
            </tab-component>
        </tab-wrapper-component>
    </div>
</template>

<script>
import TabComponent from '../../components/diet/TabComponent.vue'
import TabWrapperComponent from '../../components/diet/TabWrapperComponent.vue'
import ProfileCardComponent from '../../components/diet/ProfileCardComponent.vue'
import CalculationsComponent from '../../components/diet/CalculationsComponent.vue'
import DishBrowserComponent from '../../components/diet/DishBrowserComponent.vue'
import ProfileReportsComponent from '../../components/diet/reports/ProfileReportsComponent.vue'
import DieticianDishesComponent from '../../components/diet/dishes/DieticianDishesComponent.vue'
export default {
    name: "DietView",
    data(){
        return {
            calcChanger: false,
            dishesChanger: false
        }
    },
    components: {
        TabComponent,
        TabWrapperComponent,
        ProfileCardComponent,
        CalculationsComponent,
        DishBrowserComponent,
        ProfileReportsComponent,
        DieticianDishesComponent
    },
    mounted(){
        this.redirectIfLogout()
    },
    methods: {
        onDishesUpdated(){
            console.log("JEST NOWY DISZ !!!!!")
            this.dishesChanger = !this.dishesChanger
        },
        redirectIfLogout(){
            if(!localStorage.getItem('token')){
                this.$router.push(this.$route.query.redirect || '/')
                return true;
            }
            return false;
        },
        onCardUpdated(){
            this.calcChanger = !this.calcChanger
        }
    }
}
</script>

<style scoped>

</style>