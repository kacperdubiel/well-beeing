<template>
    <div class="section-bg">
        <div class="row mx-4 py-4">
            <profile-info :profile-source="profile" v-if="profile"/>
        </div>

    </div>
</template>

<script>
import ProfileInfo from "@/components/social/profile/ProfileInfo";
export default {
    name: "ProfileView",
    components: {
        ProfileInfo
    },
    data () {
        return {
            profile: null
        }
    },
    methods: {
        getMyProfile() {
            const url = `${this.apiURL}profile/my`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.profile = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        getProfileById(id) {
            const url = `${this.apiURL}profile/${id}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.profile = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        }
    },
    mounted() {
        if (this.$route.params.profileId) {
            console.log('id')
            this.getProfileById()
        }
        else {
            console.log('mój')
            this.getMyProfile()
        }
    }
}
</script>

<style scoped>

</style>