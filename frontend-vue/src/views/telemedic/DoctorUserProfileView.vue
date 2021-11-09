<template>
    <div class="section-bg telemedic-section">
        <header>
            <div class="row mb-4">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div class="col">
                    <h3 v-if="user" class="align-left">
                        {{ user.firstName }} {{ user.lastName }}
                    </h3>
                </div>
            </div>
        </header>
        <section>
            <measures-component :user-id="this.userId"/>
        </section>
    </div>
</template>

<script>
import MeasuresComponent from "@/components/telemedic/MeasuresComponent";

export default {
    name: 'DoctorUserProfileView',
    components: {
        MeasuresComponent
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
                    Authorization: 'Bearer ' + this.$store.getters.getToken
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