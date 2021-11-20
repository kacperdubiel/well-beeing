<template>
    <div class="tab-content" style="flex-direction: column; display: flex;">
        <div class="row mb-4 ms-3 mt-2">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div class="col-10">
                <h3 v-if="user" class="align-left">
                    {{ user.firstName }} {{ user.lastName }}
                </h3>
            </div>
        </div>
        <dietician-profile-reports-component class="mt-3" :userId="this.userId" />
    </div>
</template>

<script>
import DieticianProfileReportsComponent from "@/components/diet/reports/DieticianProfileReportsComponent";
export default {
    name: "DieticianUserProfileView",
    components: {
        DieticianProfileReportsComponent
    },
    data() {
        return {
            userId: this.$route.params.userId,
            user: null,
        }
    },
    methods: {
        getUserProfile(){
            this.axios.get(`${this.apiURL}profile/${this.userId}`, {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')//this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.user = response.data;
                })
                .catch(e => {
                    console.log(e);
                })
        },
    },
    created(){
        this.getUserProfile();
    },
}
</script>

<style scoped>

</style>