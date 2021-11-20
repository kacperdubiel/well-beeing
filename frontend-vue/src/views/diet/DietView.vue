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
                <div style="flex-direction: row; display: flex; width: 100%">
                    <user-connections-from-me-component v-if="!this.searchSpecialist"
                                                connection-type="WITH_DIETICIAN"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="openSearchSpecialist">
                    </user-connections-from-me-component>
                    <search-specialist-component @comeback="this.searchSpecialist=false" :routerUsage="false" v-if="this.searchSpecialist" connection-type="WITH_DIETICIAN"/>
                </div>
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
import UserConnectionsFromMeComponent from "@/components/telemedic/UserConnectionsFromMeComponent.vue";
import SearchSpecialistComponent from "@/components/telemedic/SearchSpecialistComponent.vue";
export default {
    name: "DietView",
    data(){
        return {
            calcChanger: false,
            dishesChanger: false,
            searchSpecialist: false,
        }
    },
    components: {
        TabComponent,
        TabWrapperComponent,
        ProfileCardComponent,
        CalculationsComponent,
        DishBrowserComponent,
        ProfileReportsComponent,
        DieticianDishesComponent,
        UserConnectionsFromMeComponent,
        SearchSpecialistComponent
    },
    methods: {
        onDishesUpdated(){
            this.dishesChanger = !this.dishesChanger
        },
        onCardUpdated(){
            this.calcChanger = !this.calcChanger
        },
        openConversation(conversationId){
           // this.$router.push({ name: 'UserDoctorConversationView', params: { conversationId: conversationId } });
           console.log("hehe1")
        },
        openSearchSpecialist(){
           // this.$router.push({ name: 'SearchDoctorsView' });
           console.log("hehe2")
           this.searchSpecialist = true
        },
    }
}
</script>

<style scoped>

</style>