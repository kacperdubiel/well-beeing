<template>  
    <div class="section-bg">
        <tab-wrapper-component>
            <tab-component title="Karta">
                <profile-card-component @card:updated="onCardUpdated"></profile-card-component>
            </tab-component>
            <tab-component title="Kalkulacje">
                <calculations-component :key="calcChanger"></calculations-component>
            </tab-component>
            <tab-component title="Raporty">Hejo content 3</tab-component>
            <tab-component title="Dania">
                <dish-browser-component></dish-browser-component>
            </tab-component>
            <tab-component title="Dietetycy">Hejo content 5</tab-component>
        </tab-wrapper-component>
    </div>
</template>

<script>
import TabComponent from '../../components/diet/TabComponent.vue'
import TabWrapperComponent from '../../components/diet/TabWrapperComponent.vue'
import ProfileCardComponent from '../../components/diet/ProfileCardComponent.vue'
import CalculationsComponent from '../../components/diet/CalculationsComponent.vue'
import DishBrowserComponent from '../../components/diet/DishBrowserComponent.vue'
export default {
    name: "DietView",
    data(){
        return {
            calcChanger: false
        }
    },
    components: {
        TabComponent,
        TabWrapperComponent,
        ProfileCardComponent,
        CalculationsComponent,
        DishBrowserComponent
    },
    mounted(){
        this.redirectIfLogout()
    },
    methods: {
        redirectIfLogout(){
            if(!localStorage.getItem('token')){
                this.$router.push(this.$route.query.redirect || '/')
                return true;
            }
            return false;
        },
        onCardUpdated(){
            console.log("NO WYEMITOWALEM XD")
            this.calcChanger = !this.calcChanger
        }
    }
}
</script>

<style scoped>

</style>