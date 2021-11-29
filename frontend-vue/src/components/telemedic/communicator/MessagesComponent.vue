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
                <div :class="getClass(message)">
                    <span v-if="message.messageType === 'TEXT'">
                        {{ message.data }}
                    </span>
                    <span v-if="message.messageType === 'IMAGE'" @click="openImage(message.id)">
                        <message-image-component :messageId="message.id"/>
                    </span>
                </div>

            </div>
        </div>
        <div class="input-group mt-3">
            <input v-model="messageText" v-on:keyup.enter="submitMessage"
                   type="text" class="form-control" placeholder="Napisz wiadomość..."
            >
            <button class="btn-white btn-hover" @click="clearImgHolder" data-bs-toggle="modal" data-bs-target="#imgUploadModal">
                <font-awesome-icon :icon="['fa', 'image']" size="2x"/>
            </button>
        </div>

        <!-- Modal - ImgUpload -->
        <div class="modal fade" id="imgUploadModal" tabindex="-1" aria-labelledby="imgUploadModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="imgUploadModalLabel"> Wyślij zdjęcie </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearImgHolder"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="col-11 mx-auto">
                                <div class="row d-flex justify-content-center">
                                    <div id="img-preview" class="mb-2">
                                        <img v-if="imgUrl" :src="imgUrl"  alt="image preview"/>
                                    </div>
                                    <label for="input-picture" class="form-label">Wybierz zdjęcie:</label>
                                    <input
                                        class="form-control"
                                        type="file"
                                        id="input-picture"
                                        ref="imgFile"
                                        accept="image/png, image/jpeg, image/png"
                                        @change="onImgChange"
                                    >
                                </div>

                                <div class="row justify-content-end mt-3">
                                    <div class="col-3">
                                        <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="clearImgHolder">Anuluj</button>
                                    </div>
                                    <div class="col-3">
                                        <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="uploadImg">Wyślij</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal - OpenImg -->
        <div class="modal fade" id="openImgModal" tabindex="-1" aria-labelledby="openImgModalLabel" aria-hidden="true">
            <div class="open-img-modal-dialog modal-dialog">
                <div class="open-img-modal-content modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="open-img-modal-body modal-body">
                        <div class="mx-auto">
                            <div class="row d-flex justify-content-center">
                                <div v-if="imageMessageId" id="open-img">
                                    <message-image-component :message-id="imageMessageId"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="img-modal-opener" data-bs-toggle="modal" data-bs-target="#openImgModal"></div>

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

import MessageImageComponent from "@/components/telemedic/communicator/MessageImageComponent";

export default {
    name: "MessagesComponent",
    components: {
        MessageImageComponent,
    },
    props: {
        conversation: Object,
        userId: String,
    },
    data() {
        return {
            navigation: {},
            messages: {},

            messageText: "",
            imgUrl: null,
            imageMessageId: null
        }
    },
    methods: {
        getClass(message){
            let arr = [];
            if(message.messageType === 'TEXT'){
                arr.push('message');
            } else if (message.messageType === 'IMAGE'){
                arr.push('message-image');
                arr.push('clickable');
            }

            if(message.sender.id !== this.userId){
                arr.push('their-message');
            } else {
                arr.push('your-message');
                const connType = message.conversation.connectionType;
                if(connType === "WITH_DOCTOR"){
                    arr.push('your-message-telemedic');
                }
                else if(connType === "WITH_DIETICIAN"){
                    arr.push('your-message-diet');
                }
                else if(connType === "WITH_TRAINER"){
                    arr.push('your-message-sport');
                }
                else {
                    arr.push('your-message-social');
                }
            }
            return arr;
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

                    setTimeout(()=>{
                        lastMessage.scrollIntoView({ block: 'nearest', inline: 'start' });
                    }, 100);
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

        onImgChange() {
            if(this.$refs.imgFile.files.length > 0){
                const file = this.$refs.imgFile.files[0];
                this.imgUrl = URL.createObjectURL(file);
            }
        },
        uploadImg(){
            if(this.$refs.imgFile.files.length > 0) {
                const file = this.$refs.imgFile.files[0];
                let formData = new FormData();
                formData.append("file", file);

                const data = {
                    "messageType": "IMAGE",
                    "data": "image uploading...",
                    "conversation": {
                        "id": this.conversation.id,
                    },
                }

                this.axios({
                    method: 'post',
                    headers: {
                        Authorization: 'Bearer ' + this.$store.getters.getToken
                    },
                    url: `${this.apiURL}messages`,
                    data: data
                })
                .then((response) => {
                    console.log(response);
                    console.log(response.data);
                    let messageId = response.data.id;

                    this.axios({
                        method: 'post',
                        headers: {
                            Authorization: 'Bearer ' + this.$store.getters.getToken,
                            'Content-Type': 'multipart/form-data',
                        },
                        url: `${this.apiURL}messages/import/${messageId}`,
                        data: formData
                    })
                    .then(() => {
                        this.getMessages();
                        this.messageText = "";
                    })
                    .catch(e => {
                        console.log(e);
                    })
                })
                .catch(e => {
                    console.log(e);
                })

            }
        },
        clearImgHolder(){
            this.$refs.imgFile.value = null;
            this.imgUrl = null;
        },
        openImage(messageId){
            this.imageMessageId = messageId;
            document.getElementById('img-modal-opener').click();
        }
    },
    created() {
        this.getMessages();
    }
}
</script>

<style scoped>

.btn-hover:hover {
    color: var(--GREY1);
}

#messages-box {
    height: 50vh;
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

.message-image {
    border-radius: 25px;
    padding: 0;
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

#img-preview {
    max-height: 50vh;
    max-width: 50vw;
}

#img-preview img {
    max-width: 100%;
    max-height: 100%;
}

.open-img-modal-dialog {
    max-width: 65vw;
    max-height: 75vh;
}

.open-img-modal-body {

}

</style>

<style>
.message-image .message-image-container img {
    width: auto;
    height: auto;
    max-width: 150px;
    max-height: 150px;
    border-radius: 25px;
}

#open-img .message-image-container {
    max-width: 65vw;
    max-height: 75vh;
}

#open-img .message-image-container img {
    width: auto;
    height: auto;

    max-width: 100%;
    max-height: 74vh;
}

</style>