<template>
    <div class="section-bg telemedic-section">
        <header>

        </header>
        <section>
            <div class="row mb-4">
                <div class="col-2">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" />
                </div>
                <div class="col">
                    <h3 v-if="user" class="user-name">
                        {{ user.firstName }} {{ user.lastName }}
                    </h3>
                </div>
            </div>

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
            this.axios.get(`http://localhost:8090/profile/${this.userId}`, {
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
.telemedic-section {
    padding: 20px;
}

.user-name {
    text-align: left;
}
</style>