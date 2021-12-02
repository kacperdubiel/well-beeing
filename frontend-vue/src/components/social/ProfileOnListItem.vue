<template>
    <div class="d-flex flex-row w-100">
        <div class="d-flex flex-column text-start">
            <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
            <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">

        </div>

        <div class="d-flex flex-column align-self-center w-100">
            <div class="text-start d-flex ms-3">
                <h5 class="m-0" @click="redirectToProfile(this.profileSource.id)">
                    {{this.profileSource.firstName}} {{this.profileSource.lastName}}
                </h5>
                <button class="btn-white ms-auto mx-1" @click="getConversation(this.profileSource.id)" v-if="!isProfileMine">
                    <font-awesome-icon :icon="['fa', 'comments']" size="1x"/>
                </button>
            </div>

        </div>

    </div>
</template>

<script>
export default {
    name: "ProfileOnListItem",
    data() {
        return {
            profilePictureSrc: "",
        }
    },
    props: {
        profileSource: Object
    },
    methods: {
        downloadProfilePicture () {
            if(this.profileSource.profileImgPath != null) {
                const url = `${this.apiURL}profile/export/${this.profileSource.id}`
                const token = this.$store.getters.getToken;
                this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
            }
        },
        redirectToProfile(id) {
            let modalToClose = document.getElementById('likes-modal-close')
            if(modalToClose)
                modalToClose.click();
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        },
        getConversation(profileId) {
            let modalToClose = document.getElementById('likes-modal-close')

            const url = `${this.apiURL}conversations/profile/${profileId}/type/WITH_USER`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then(response => {
                this.openConversation(response.data.id);
                if(modalToClose)
                    modalToClose.click();
            })
                .catch(e => {
                    console.log(e);
                    this.addConversation(profileId);
                    if(modalToClose)
                        modalToClose.click();
                })
        },
        addConversation(otherUserId) {
            const url = `${this.apiURL}conversations`
            const token = this.$store.getters.getToken;

            const data = {
                "connectionType": "WITH_USER",
                "firstProfile": {
                    "id": this.userId,
                },
                "secondProfile": {
                    "id": otherUserId,
                },
            }
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.openConversation(response.data.id);
            }).catch(error => {
                console.log(error)
            });

        },
        openConversation(conversationId) {
            this.$router.push({ name: 'UserUserConversationView', params: { conversationId: conversationId } });
        },
    },
    mounted() {
        this.downloadProfilePicture()
    },
    computed: {
        isProfileMine() {
            return this.profileSource.id === this.$store.getters.getProfileId
        }
    }
}
</script>

<style scoped>
h5:hover {
    text-decoration: underline;
}
</style>