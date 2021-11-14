<template>
    <div>
        <header>
            <div class="d-flex flex-row mb-4 align-items-center align-left bottom-border pb-3">
                <div class="mx-3">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div v-if="conversation && conversation.firstProfile.id !== userId"
                     class="d-flex flex-row align-items-center clickable" @click="openProfile(conversation.firstProfile.id)">
                    <div>
                        <user-avatar-component :profileId="conversation.firstProfile.id"
                                               :height="45" :width="45"
                        />
                    </div>
                    <div class="h4 ms-3">
                        {{ conversation.firstProfile.firstName }} {{ conversation.firstProfile.lastName }}
                    </div>
                </div>
                <div v-else-if="conversation" class="d-flex flex-row align-items-center clickable" @click="openProfile(conversation.secondProfile.id)">
                    <div>
                        <user-avatar-component :profileId="conversation.secondProfile.id"
                                               :height="45" :width="45"
                        />
                    </div>
                    <div class="h4 ms-3">
                        {{ conversation.secondProfile.firstName }} {{ conversation.secondProfile.lastName }}
                    </div>
                </div>
            </div>
        </header>
        <section v-if="conversation">
            <messages-component :conversation="conversation" :user-id="userId"/>
        </section>
    </div>
</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";
import MessagesComponent from "@/components/telemedic/communicator/MessagesComponent";

export default {
    name: 'ConversationComponent',
    components: {
        UserAvatarComponent,
        MessagesComponent
    },
    props: {
        conversationId: String
    },
    data() {
        return {
            userId: "",
            conversation: null,
        }
    },
    methods: {
        getProfile(){
            this.axios.get(`${this.apiURL}profile/my`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.userId = response.data.id;
                    this.getConversation();
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getConversation(){
            this.axios.get(`${this.apiURL}conversations/${this.conversationId}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.conversation = response.data;
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
        this.getProfile();
    },
}
</script>

<style scoped>

.bottom-border {
    border-bottom: 1px solid #c0c0c4;
}

</style>