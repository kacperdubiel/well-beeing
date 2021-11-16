<template>
    <div class="section-2-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
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
        <div class="d-flex flex-row px-4 py-2 align-items-center">
            <div class="d-flex flex-column text-start" v-if="this.postSource.likes.length > 0">
                <button data-bs-toggle="modal" data-bs-target="#likesListModal" class="no-bg-open-modal" @click="handleGetLikes(this.postSource.likes)">
                    <div class="text-start d-flex align-items-center ms-3 interact" >
                        <font-awesome-icon id="heart-icon" :icon="['fa', 'heart']" class="me-2"/>
                        <span>
                            {{this.postSource.likes.length}}
                        </span>

                    </div>
                </button>
            </div>
            <div class="d-flex flex-column text-start ms-auto pe-3">
                <button class="no-bg-open-modal">
                    <span>
                        {{this.postSource.comments.length}} komentarzy
                    </span>

                </button>
            </div>
            <div class="d-flex flex-column text-start">
                <button class="no-bg-open-modal">
                    <span>
                        {{this.postSource.comments.length}} udostępnień
                    </span>
                </button>
            </div>
        </div>

        <div class="row mx-4 py-2 align-items-center" id="interactions">
            <div class="col px-1">
                <button class="no-bg interact w-100" @click="reactToPost(this.postSource.postId)">
                    <font-awesome-icon :icon="['fa', 'heart']" class="me-2" v-if="isLikedByMe"/>
                    <font-awesome-icon :icon="['far', 'heart']" class="me-2" v-else/>
                    Lubię to
                </button>
            </div>
            <div class="col px-1">
                <button class="no-bg interact w-100">
                    <font-awesome-icon :icon="['far', 'comment-alt']" class="me-2"/>
                    Dodaj komentarz
                </button>
            </div>
            <div class="col px-1 interact">
                <button class="no-bg interact w-100">
                    <font-awesome-icon :icon="['far', 'share-square']" class="me-2"/>
                    Udostępnij
                </button>
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
            postPictureSrc: "",
            profilePictureSrc: "",
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
                this.$parent.$parent.getPostsAfterDelete()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        reactToPost(postId) {
            const url = `${this.apiURL}post/${postId}/react`
            const token = this.$store.getters.getToken;
            this.axios.post(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)

                this.$parent.$parent.getEditedPost(postId)
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        handleEdit(post) {
            this.$emit('edit:post', post)
        },
        handleGetLikes(likes) {
            this.$emit('get:likes', likes)
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
        },
        isLikedByMe() {
            let likers = this.postSource.likes.map(like => like.liker.id)
            return likers.includes(this.$store.getters.getProfileId)
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

#heart-icon {
    color: #D83D68;
    font-size: 1.6rem;
}

.interact {
    font-size: 1.2rem;
}

#interactions {
    border-top: 1px solid var(--GREY2);
}

.no-bg:hover {
    background-color: var(--GREY2);
}
.no-bg {
    color: white;
    border-radius: 5px;
}

.no-bg-open-modal {
    background-color: transparent;
    border: none;
    color: white;
}

.no-bg-open-modal span:hover {
    border-bottom: 1px solid white;
}
</style>