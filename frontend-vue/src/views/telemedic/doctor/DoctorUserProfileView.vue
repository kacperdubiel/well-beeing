<template>
    <div>
        <div>
            <div class="d-flex flex-row mb-4 align-items-center align-left">
                <div class="mx-3">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="goBack"/>
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
        </div>
        <div>
            <doctor-user-profile-navbar/>
        </div>
        <div>
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";
import DoctorUserProfileNavbar from "@/components/telemedic/navbars/DoctorUserProfileNavbar";

export default {
    name: 'DoctorUserProfileView',
    components: {
        UserAvatarComponent,
        DoctorUserProfileNavbar
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
        goBack(){
            this.$router.push({ name: 'DoctorUsersView' });
        }
    },
    created(){
        this.getUserProfile();
    },
}
</script>

<style scoped>

</style>