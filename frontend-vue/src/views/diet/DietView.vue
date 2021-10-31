<template>  
    <div class="section-bg">
        <tab-wrapper-component>
            <tab-component title="Karta">
                <profile-card-component></profile-card-component>
            </tab-component>
            <tab-component title="Kalkulacje">Hejo content 2</tab-component>
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
export default {
    name: "DietView",
    components: {
        TabComponent,
        TabWrapperComponent,
        ProfileCardComponent
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