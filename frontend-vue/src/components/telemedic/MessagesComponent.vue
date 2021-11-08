<template>
    <div class="container px-3 pb-1">
        <div id="messages-box">
            <div v-for="message in messages" v-bind:key="message.id" class="message-row">
                <div class="message-datetime">
                    {{ formatDate(message.createDate) }}
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
    </div>
</template>

<script>
import moment from "moment";

export default {
    name: "MessagesComponent",
    props: {
        conversation: Object,
        userId: String,
    },
    data() {
        return {
            messagesPage: {},
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
            this.axios.get(`http://localhost:8090/conversations/${this.conversation.id}/messages`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.messagesPage = response.data;
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
        markAsRead(){
            this.axios({
                method: 'put',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `http://localhost:8090/conversations/${this.conversation.id}/mark-as-read`
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
                    lastMessage.scrollIntoView();
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
                    url: `http://localhost:8090/messages`,
                    data: data
                })
                    .then(() => {
                        this.getMessages();
                        this.messageText = "";
                    })
                    .catch(e => {
                        console.log(e);
                    })
            }
        },
        formatDate(date){
            if (date) {
                return moment(String(date)).format('DD/MM/YYYY HH:mm')
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

</style>