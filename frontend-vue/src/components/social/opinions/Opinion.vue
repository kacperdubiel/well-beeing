<template>
    <div class="section-2-bg" v-if="opinionSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100">
                <div class="text-start d-flex align-items-baseline ms-3">
                    <h5 @click="redirectToProfile(this.opinionSource.giver.id)">
                        {{this.opinionSource.giver.firstName}} {{this.opinionSource.giver.lastName}}
                    </h5>
                    <h5 class="ms-2">|
                        <font-awesome-icon :icon="['fa', 'star']" id="star"/>
                        {{this.opinionSource.rating}}</h5>
                    <h6 class="ms-2">| {{this.$func_global.formatDate(this.opinionSource.addedDate)}}</h6>
                    <div class="dropdown ms-auto dropstart" v-if="isOpinionMine">
                        <button class="no-bg" type="button" id="more" data-bs-toggle="dropdown" aria-expanded="false">
                            <font-awesome-icon :icon="['fa', 'ellipsis-h']"/>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="more">
                            <li><a class="dropdown-item" @click="handleEdit(this.opinionSource)" data-bs-toggle="modal" data-bs-target="#postEditModal">Edytuj</a></li>
                            <li><a class="dropdown-item" @click="deletePost(this.opinionSource.opinionId)">Usuń</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <div class="row text-start px-4 pt-2 pb-1">
            <div class="col">
                <p id="opinion-content" v-html="this.$func_global.convertNewLines(this.opinionSource.opinionContent)">
                </p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Opinion",
    props: {
        opinionSource: Object
    },
    data() {
        return {
            profilePictureSrc: "",
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.opinionSource.giver.id}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        redirectToProfile(id) {
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        }
    },
    mounted() {
        this.downloadProfilePicture()
    },
    computed: {
        isPostMine() {
            return this.opinionSource.giver.id === this.$store.getters.getProfileId
        }
    }
}
</script>

<style scoped>
h6 {
    color: var(--GREY1);
}

#opinion-content {
    text-align: justify;
}

#more {
    color: white;
}

.no-bg:hover {
    background-color: var(--GREY2);
}

.no-bg {
    color: white;
    border-radius: 5px;
}

#star {
    color: var(--DARK-YELLOW);
}

h5:hover {
    text-decoration: underline;
}
</style>