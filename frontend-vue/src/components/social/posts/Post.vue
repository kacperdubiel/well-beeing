<template>
    <div class="section-2-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex align-items-baseline ms-3">
                    <h5>{{this.postSource.creator.firstName}} {{this.postSource.creator.lastName}}</h5>
                    <h6 class="ms-3">| {{this.$func_global.formatDateDateFromNow(this.postSource.addedDate)}}</h6>
                    <div class="dropdown ms-auto dropstart" v-if="isPostMine">
                        <button class="no-bg" type="button" id="more" data-bs-toggle="dropdown" aria-expanded="false">
                            <font-awesome-icon :icon="['fa', 'ellipsis-h']"/>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="more">
                            <li><a class="dropdown-item" @click="handleEdit(this.postSource)" data-bs-toggle="modal" data-bs-target="#postEditModal">Edytuj</a></li>
                            <li><a class="dropdown-item" @click="deletePost(this.postSource.postId)">Usuń</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row text-start px-4 pt-2 pb-1">
            <div class="col">
                <p id="post-content">
                    {{this.postSource.postContent}}
                </p>
            </div>
        </div>
        <div class="row text-start pb-3" v-if="this.postSource.postImgPath">
            <div class="col">
                <img :src="postPictureSrc" alt="Post picture"  id="post-picture" class="w-100">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Post",
    props: {
        postSource: Object
    },
    data() {
        return {
            profilePictureSrc: "",
            postPictureSrc: "",
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.postSource.creator.id}`
            const token = this.$store.getters.getToken;
            console.log('profile')
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        downloadPostPicture () {
            if (this.postSource.postImgPath) {
                const url = `${this.apiURL}post/export/${this.postSource.postId}`
                const token = this.$store.getters.getToken;
                console.log('post')
                this.$func_global.downloadPhoto(url, token).then(result => this.postPictureSrc = result)
            }
        },
        deletePost(postId) {
            const url = `${this.apiURL}post/${postId}/delete`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.$parent.$parent.getMyPosts()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        handleEdit(post) {
            this.$emit('edit:post', post)
        }
    },
    mounted() {
        this.downloadProfilePicture()
        this.downloadPostPicture()
    },
    watch: {
        postSource: function (){
            this.downloadPostPicture()
        }
    },
    computed: {
        isPostMine() {
            return this.postSource.creator.id === this.$store.getters.getProfileId
        }
    }
}
</script>

<style scoped>
h6 {
    color: var(--GREY1);
}

#post-content {
    text-align: justify;
}

#more {
    color: white;
}
</style>