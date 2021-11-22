<template>
    <div class="section-bg telemedic-section">
        <header>
            <div class="d-flex flex-row mb-4 align-items-center align-left">
                <div class="mx-3">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div v-if="user" class="d-flex flex-row align-items-center clickable" @click="openProfile(user.id)">
                    <div>
                        <user-avatar-component :profileId="user.id"
                                               :height="45" :width="45"
                        />
                    </div>
                    <div class="h4 ms-3">
                        {{ user.firstName }} {{ user.lastName }}
                    </div>
                </div>
            </div>
        </header>
        <section>
            <measures-component :user-id="this.userId"/>
        </section>
    </div>
</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";
import MeasuresComponent from "@/components/telemedic/MeasuresComponent";

export default {
    name: 'DoctorUserProfileView',
    components: {
        UserAvatarComponent,
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
        openProfile(profileId){
            this.$router.push({ name: 'ProfileView', params: { profileId: profileId } });
        },
    },
    created(){
        this.getUserProfile();
    },
}
</script>

<style scoped>

</style>