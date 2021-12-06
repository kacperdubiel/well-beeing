<template>
    <div class="message-image-container">
        <img v-if="this.messageImage" :src="this.messageImage" alt="Message image">
    </div>
</template>

<script>
export default {
    name: "MessageImageComponent",
    props: {
        messageId: String
    },
    watch: {
        messageId: function() {
            this.downloadMessageImage();
        },
    },
    data() {
        return {
            messageImage: null
        }
    },
    methods: {
        downloadMessageImage() {
            const url = `${this.apiURL}messages/export/${this.messageId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token)
                .then(result => {
                    this.messageImage = result;
                })
        }
    },
    mounted() {
        this.downloadMessageImage();
    }
}
</script>

<style scoped>

</style>