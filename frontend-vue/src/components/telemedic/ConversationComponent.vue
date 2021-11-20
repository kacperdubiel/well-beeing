<template>
    <div class="container-fluid">
        <header>
            <div class="row mb-3 pb-3 bottom-border">
                <div class="col-1">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" size="2x" class="clickable" @click="$router.go(-1)"/>
                </div>
                <div class="col">
                    <h3 v-if="conversation" class="align-left">
                        <span v-if="conversation.firstProfile.id !== userId">
                            {{ conversation.firstProfile.firstName }} {{ conversation.firstProfile.lastName }}
                        </span>
                        <span v-else>
                            {{ conversation.secondProfile.firstName }} {{ conversation.secondProfile.lastName }}
                        </span>
                    </h3>
                </div>
            </div>
        </header>
        <section v-if="conversation">
            <messages-component :conversation="conversation" :user-id="userId"/>
        </section>
    </div>
</template>

<script>
import MessagesComponent from "@/components/telemedic/MessagesComponent";

export default {
    name: 'ConversationComponent',
    components: {
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