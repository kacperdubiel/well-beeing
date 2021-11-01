<template>  
    <div class="section-bg">
        <tab-wrapper-component>
            <tab-component title="Karta">
                <profile-card-component></profile-card-component>
            </tab-component>
            <tab-component title="Kalkulacje">
                <calculations-component></calculations-component>
            </tab-component>
            <tab-component title="Raporty">Hejo content 3</tab-component>
            <tab-component title="Dania">Hejo content 4</tab-component>
            <tab-component title="Dietetycy">Hejo content 5</tab-component>
        </tab-wrapper-component>
    </div>
</template>

<script>
import TabComponent from '../../components/diet/TabComponent.vue'
import TabWrapperComponent from '../../components/diet/TabWrapperComponent.vue'
import ProfileCardComponent from '../../components/diet/ProfileCardComponent.vue'
import CalculationsComponent from '../../components/diet/CalculationsComponent.vue'
export default {
    name: "DietView",
    components: {
        TabComponent,
        TabWrapperComponent,
        ProfileCardComponent,
        CalculationsComponent
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
        }
    }
}
</script>

<style scoped>

</style>