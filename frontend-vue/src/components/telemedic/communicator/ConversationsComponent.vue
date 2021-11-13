<template>
    <div>
        <div v-if="componentError === true" class="container">
            Błąd ładowania.
        </div>
        <div v-if="componentError === false" class="container mt-4">
            <div v-if="conversations" class="row mb-2 bottom-border"></div>
            <div v-for="conversation in conversations" v-bind:key="conversation.id" class="row pb-2 mb-2 bottom-border clickable"
                @click="$emit('open-conversation', conversation.id)"
            >
                <div class="col-2 col-xl-1">
                    <user-avatar-component v-if="conversation.firstProfile.id !== userId"
                                                   :profileId="conversation.firstProfile.id"
                                                   :height="45" :width="45"
                    />
                    <user-avatar-component v-else :profileId="conversation.secondProfile.id"
                                                   :height="45" :width="45"
                    />
                </div>
                <div class="col-7 col-xl-8" v-bind:class="{ 'bold-text': isMsgRead(conversation) }">
                    <div class="align-left">
                        <span v-if="conversation.firstProfile.id !== userId">
                            {{ conversation.firstProfile.firstName }} {{ conversation.firstProfile.lastName }}
                        </span>
                        <span v-else>
                            {{ conversation.secondProfile.firstName }} {{ conversation.secondProfile.lastName }}
                        </span>
                    </div>
                    <div class="align-left msg-text">
                        <span v-if="conversation.lastMessage.sender.id === this.userId">
                            Ty:
                        </span>
                        <span v-if="conversation.lastMessage.messageType === 'TEXT'">
                            {{ this.$func_global.truncate(conversation.lastMessage.data, 70, "...") }}
                        </span>
                        <span v-if="conversation.lastMessage.messageType === 'IMAGE'">
                            Wysłano zdjęcie.
                        </span>
                    </div>
                </div>
                <div class="col-3 col-xl-3 align-right">
                    {{ this.$func_global.formatDateDateFromNow(conversation.lastMessage.createDate ) }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";

export default {
    name: "ConversationsComponent",
    components: {
        UserAvatarComponent
    },
    props: {
        connectionType: String,
        asSpecialist: Boolean,
    },
    data() {
        return {
            componentError: null,

            userId: "",
            conversationsPage: {},
            conversations: {},
        }
    },
    methods: {
        isConnectionTypeCorrect(){
            if(this.connectionType === "WITH_USER"       || this.connectionType === "WITH_DOCTOR" ||
                this.connectionType === "WITH_DIETICIAN" || this.connectionType === "WITH_TRAINER")
            {
                this.componentError = false;
                this.getProfile();
            } else {
                this.componentError = true;
            }
        },
        getProfile(){
            this.axios.get(`${this.apiURL}profile/my`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.userId = response.data.id;
                    this.getConversations();
                })
                .catch(e => {
                    this.componentError = true;
                    console.log(e);
                })
        },
        getConversations() {
            this.axios.get(`${this.apiURL}conversations/as-specialist/${this.asSpecialist}/type/${this.connectionType}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.conversationsPage = response.data;
                    this.conversations = response.data.objects;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        isMsgRead(conversation) {
            return conversation.lastMessage.sender.id !== this.userId && (
                        (conversation.firstProfile.id === this.userId && !conversation.readByFirstUser) ||
                        (conversation.secondProfile.id === this.userId && !conversation.readBySecondUser)
                    );
        },
    },
    created() {
        this.isConnectionTypeCorrect();
    },
}
</script>

<style scoped>

.bottom-border {
    border-bottom: 1px solid #c0c0c4;
}

.bold-text {
    font-weight: bold;
}

.msg-text {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

div.clickable:hover {
    color: #d2d2d2;
}

</style>