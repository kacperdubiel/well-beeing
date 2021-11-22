<template>
    <div class="container px-3 pb-1">
        <div id="messages-box">
            <div v-if="navigation.totalPages > 1 && navigation.currentPage + 1 !== navigation.totalPages" class="mb-4">
                <button class="btn-white shadow px-3 py-1" @click="loadMoreMessages">
                    Wczytaj więcej...
                </button>
            </div>
            <div v-for="message in messages" v-bind:key="message.id" class="message-row">
                <div class="message-datetime">
                    {{ this.$func_global.formatDateTime(message.createDate) }}
                </div>
                <div class="message" :class="getClass(message)">
                    <span v-if="message.messageType === 'TEXT'">
                        {{ message.data }}
                    </span>
                    <span v-if="message.messageType === 'IMAGE'">
                        Wysłano zdjęcie.
                    </span>
                </div>

            </div>
        </div>
        <div class="input-group mt-3">
            <input v-model="messageText" v-on:keyup.enter="submitMessage"
                   type="text" class="form-control" placeholder="Napisz wiadomość..."
            >
        </div>

        <!-- Modal - Info -->
        <div class="modal fade" id="infoModal" tabindex="-1" aria-labelledby="infoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="infoModalLabel"> Błąd </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="col-11 mx-auto">
                                <div class="row">
                                    Wiadomość nie została wysłana. Prawdopodobnie użytkownik nie jest zapisany do specjalisty.
                                </div>

                                <div class="row justify-content-end mt-3">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-3">
                                        <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Potwierdź</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="modal-opener" data-bs-toggle="modal" data-bs-target="#infoModal"></div>
    </div>
</template>

<script>

export default {
    name: "MessagesComponent",
    props: {
        conversation: Object,
        userId: String,
    },
    data() {
        return {
            navigation: {},
            messages: {},

            messageText: "",
        }
    },
    methods: {
        getClass(message){
            if(message.sender.id !== this.userId){
                return [ 'their-message' ];
            } else {
                const connType = message.conversation.connectionType;
                if(connType === "WITH_DOCTOR"){
                    return [ 'your-message', 'your-message-telemedic' ];
                }
                if(connType === "WITH_DIETICIAN"){
                    return [ 'your-message', 'your-message-diet' ];
                }
                if(connType === "WITH_TRAINER"){
                    return [ 'your-message', 'your-message-sport' ];
                }

                return [ 'your-message', 'your-message-social' ];
            }
        },
        getMessages(){
            this.axios.get(`${this.apiURL}conversations/${this.conversation.id}/messages`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.navigation = response.data;
                    const messagesResult = response.data.objects;
                    this.messages = messagesResult.reverse();
                })
                .then(()=> {
                    this.markAsRead();
                    this.scrollToLastMessage();
                })
                .catch(e => {
                    console.log(e);
                })
        },
        loadMoreMessages(){
            this.axios.get(`${this.apiURL}conversations/${this.conversation.id}/messages`
                + `?page=${this.navigation.currentPage + 1}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.navigation = response.data;
                    let messagesResult = response.data.objects;
                    messagesResult.reverse().push(...this.messages)
                    this.messages = messagesResult;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        markAsRead(){
            this.axios({
                method: 'put',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}conversations/${this.conversation.id}/mark-as-read`
            })
                .catch(e => {
                    console.log(e);
                })
        },
        scrollToLastMessage(){
            const messageBox = document.getElementById('messages-box');
            if(messageBox){
                const lastMessage = messageBox.lastElementChild;
                if(lastMessage){
                    lastMessage.scrollIntoView({ block: 'nearest', inline: 'start' });
                }
            }
        },
        submitMessage(){
            if(this.messageText.length > 0){
                const data = {
                    "messageType": "TEXT",
                    "data": this.messageText,
                    "conversation": {
                        "id": this.conversation.id,
                    },
                }

                this.axios({
                    method: 'post',
                    headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                    url: `${this.apiURL}messages`,
                    data: data
                })
                    .then(() => {
                        this.getMessages();
                        this.messageText = "";
                    })
                    .catch(e => {
                        document.getElementById('modal-opener').click();
                        console.log(e);
                    })
            }
        },
    },
    created() {
        this.getMessages();
    }
}
</script>

<style scoped>
#messages-box {
    height: 45vh;
    overflow-y: auto;
    padding-right: 10px;
}

.btn-panel-telemedic{
    font-size: medium;
}

.message-row {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.message-datetime {
    font-size: smaller;
    color: #b4b4b7;
}

.message {
    max-width: 90%;
    border-radius: 25px;
    padding: 9px 14px;
    margin-bottom: 5px;
    text-align: left;

}

.their-message {
    background-color: #3C454C;
    margin-right: auto;
}

.your-message {
    margin-left: auto;
}

.your-message-social {
    background-color: var(--DARK-YELLOW);
}

.your-message-telemedic {
    background-color: var(--TELEMEDIC);
}

.your-message-diet {
    background-color: var(--DIET);
}

.your-message-sport {
    background-color: var(--SPORT);
}

.modal-header, .modal-body {
    color: black;
}

.modal-dialog-centered {
    min-height: calc(60% - 3.5rem);
}

</style>