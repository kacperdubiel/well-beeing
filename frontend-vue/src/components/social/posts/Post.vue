<template>
    <div class="section-2-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc !== ''" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex align-items-baseline ms-3">
                    <h5 class="clickable">{{this.postSource.creator.firstName}} {{this.postSource.creator.lastName}}</h5>
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
                <p id="post-content" v-html="this.$func_global.convertNewLines(this.postSource.postContent)">
                </p>
            </div>
        </div>
        <div class="row text-start pb-3" v-if="this.postSource.postImgPath">
            <div class="col">
                <img :src="postPictureSrc" alt="Post picture"  id="post-picture" class="w-100">
            </div>
        </div>
        <div class="row text-start pb-3" v-if="this.postSource.originalPost">
            <div class="col">
                <shared-post :post-source="this.postSource.originalPost" class="my-2 mx-4"/>
            </div>
        </div>
        <div class="d-flex flex-row px-4 py-2 align-items-center">

            <div class="d-flex flex-column text-start" v-if="this.postSource.likes.length > 0">
                <button data-bs-toggle="modal" data-bs-target="#likesListModal" class="no-bg-btn" @click="handleGetLikes(this.postSource.likes)">
                    <div class="text-start d-flex align-items-center ms-3 interact" >
                        <font-awesome-icon id="heart-icon" :icon="['fa', 'heart']" class="me-2"/>
                        <span class="text-center">
                            {{this.postSource.likes.length}}
                        </span>

                    </div>
                </button>
            </div>
            <div class="d-flex flex-column text-start ms-auto pe-3" v-if="this.commentsNavigation.totalElements > 0">
                <button class="no-bg-btn" @click="showComments" >
                    <span>
                        {{this.commentsNavigation.totalElements}} {{this.$func_global.mapCommentForm(this.commentsNavigation.totalElements)}}
                    </span>

                </button>
            </div>
<!--            <div class="d-flex flex-column text-start">-->
<!--                <button class="no-bg-btn">-->
<!--                    <span>-->
<!--                        {{this.postSource.sharingCounter}} {{this.$func_global.mapShareForm(this.postSource.sharingCounter)}}-->
<!--                    </span>-->
<!--                </button>-->
<!--            </div>-->
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
                <button class="no-bg interact w-100" @click="addNewComment">
                    <font-awesome-icon :icon="['far', 'comment-alt']" class="me-2"/>
                    Dodaj komentarz
                </button>
            </div>
            <div class="col px-1 interact">
                <button class="no-bg interact w-100" @click="handleShare(this.postSource.postId)" data-bs-toggle="modal" data-bs-target="#postShareModal">
                    <font-awesome-icon :icon="['far', 'share-square']" class="me-2"/>
                    Udostępnij
                </button>
            </div>

        </div>
        <new-comment v-if="addingComment" :post-id="this.postSource.postId" @refresh:comments="getComments"/>
        <comments-list v-if="displayComments && comments !== []" :comments-source="comments"/>
        <div class="row mb-3">
            <button
                class="no-bg-btn text-start ms-4 mb-3"
                v-if="!commentsNavigation.isLast && displayComments"
                @click="getComments(this.commentsNavigation.nextPage, 0)"
            >
                <span >Załaduj więcej komentarzy</span>
            </button>
        </div>


    </div>
</template>

<script>
import NewComment from "@/components/social/comments/NewComment";
import CommentsList from "@/components/social/comments/CommentsList";
import SharedPost from "@/components/social/posts/SharedPost";
export default {
    name: "Post",
    props: {
        postSource: Object
    },
    components: {
        NewComment,
        CommentsList,
        SharedPost
    },
    data() {
        return {
            postPictureSrc: "",
            profilePictureSrc: "",
            addingComment: false,
            displayComments: false,
            comments: [],
            commentsNavigation: {
                totalElements: 0,
                nextPage: 0,
                pageSize: 5,
                isFirst: false,
                isLast: false,
                currentPage: 0
            },
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.postSource.creator.id}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        downloadPostPicture () {
            if (this.postSource.postImgPath) {
                const url = `${this.apiURL}post/export/${this.postSource.postId}`
                const token = this.$store.getters.getToken;
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
        handleShare(postId) {
            this.$emit('share:post', postId)
        },
        handleGetLikes(likes) {
            this.$emit('get:likes', likes)
        },
        addNewComment() {
            this.addingComment = true
            this.displayComments = true
        },
        showComments() {
            this.displayComments = true
        },
        getCommentsAfterDelete() {
            this.commentsNavigation.nextPage = 0
            const pages = this.commentsNavigation.currentPage
            this.getComments(this.commentsNavigation.nextPage, pages+1)
        },
        getEditedComment(commentId) {
            const url = `${this.apiURL}comment/${commentId}`
            const token = this.$store.getters.getToken;

            return this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                const index = this.comments.findIndex((com => com.commentId === commentId));
                this.comments[index] = response.data
            })
        },
        getComments(page, pagesAfterDelete) {
            const url = `${this.apiURL}post/comments`
            const token = this.$store.getters.getToken;
            const myParams = {
                page: page,
                size: this.commentsNavigation.pageSize,
                postId: this.postSource.postId
            }
            if(this.commentsNavigation.last)
                return

            return this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.commentsNavigation.isLast = response.data['last']
                this.commentsNavigation.isFirst = response.data['first']
                this.commentsNavigation.currentPage = response.data['number']
                this.commentsNavigation.totalElements = response.data['totalElements']

                if(this.commentsNavigation.isFirst)
                    this.comments = response.data['content']
                else
                    this.comments = this.comments.concat(response.data['content'])

                if (!this.commentsNavigation.isLast)
                    this.commentsNavigation.nextPage = this.commentsNavigation.currentPage+1
                else
                    this.commentsNavigation.nextPage = 0

                if(pagesAfterDelete>1) {
                    this.getComments(this.commentsNavigation.nextPage, true, pagesAfterDelete-1)
                }
            })
        },

    },
    mounted() {
        this.downloadProfilePicture()
        this.downloadPostPicture()
        this.getComments(0, 0)
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
</style>