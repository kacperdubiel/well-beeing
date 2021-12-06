<template>
    <div class="section-2-bg" v-if="opinionSource">
        <div class="d-flex flex-row px-4 py-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc !== ''" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100" v-if="!isEdit">
                <div class="text-start d-flex align-items-baseline ms-3">
                    <h5 class="clickable" @click="redirectToProfile(this.opinionSource.giver.id)">
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
                            <li><a class="dropdown-item" @click="changeEditMode" data-bs-toggle="modal" data-bs-target="#postEditModal">Edytuj</a></li>
                            <li><a class="dropdown-item" @click="deleteOpinion(this.opinionSource.opinionId)">Usuń</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-column align-self-center w-100 ms-3" v-else>
                <div class="row mb-2">
                    <star-rating
                        v-model:rating="editedOpinion.rating"
                        :rounded-corners="true"
                        :show-rating="false"
                        active-color="#F9BA0F"
                        border-color="#d8d8d8"
                        active-border-color="#F9BA0F"
                        :star-size="40"
                        :border-width="4"

                    >
                    </star-rating>
                </div>
                <div class="grow-wrap">
                    <textarea
                        class="textarea w-100"
                        id="opinion-content"
                        v-model="editedOpinion.opinionContent"
                        placeholder="Jeśli chcesz, uzasadnij swoją ocenę..."
                        :class="{ 'has-error': submittingOpinion && invalidRating}"
                        @focus="clearStatusOpinion"
                        @keypress="clearStatusOpinion"
                    >
                    </textarea>
                </div>
                <div class="row text-start mb-3 px-2" v-if="errorOpinion">
                    <div class="col">
                        <p class="has-error m-0">
                            Proszę wybrać ocenę!
                        </p>
                    </div>
                </div>
            </div>

        </div>

        <div class="row text-start px-4 pt-2 pb-1" v-if="!isEdit && this.opinionSource.opinionContent !== ''">
            <div class="col">
                <p id="opinion-content" v-html="this.$func_global.convertNewLines(this.opinionSource.opinionContent)">
                </p>
            </div>
        </div>
        <div class="row justify-content-end px-4 pb-3" v-else-if="isEdit">
            <div class="col-12 col-md-5 align-self-end text-end">
                <button class="btn-panel-social-outline px-5" @click="updateOpinion(editedOpinion.opinionId)">
                    Zatwierdź zmiany
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import StarRating from "vue-star-rating";

export default {
    name: "Opinion",
    props: {
        opinionSource: Object
    },
    components: {
        StarRating
    },
    data() {
        return {
            profilePictureSrc: "",
            isEdit: false,
            editedOpinion: null,

            submittingOpinion: false,
            successOpinion: false,
            errorOpinion: false
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
        },
        deleteOpinion(opinionId) {
            const url = `${this.apiURL}opinion/${opinionId}/delete`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.$emit('delete:opinion')
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        changeEditMode() {
            this.isEdit = !this.isEdit
        },
        clearStatusOpinion() {
            this.successOpinion = false
            this.errorOpinion = false
        },
        clearInputs() {
            this.editedOpinion.opinionContent = ""
            this.editedOpinion.rating = 0
        },
        updateOpinion(opinionId) {
            const url = `${this.apiURL}opinion/${opinionId}`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, this.editedOpinion, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.changeEditMode()
                this.$emit('update:opinion')
            }).catch(error => {
                console.log(error.response.status)
            });
        }
    },
    mounted() {
        this.downloadProfilePicture()
        this.editedOpinion = this.opinionSource
    },
    computed: {
        isOpinionMine() {
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

textarea {
    background-color: var(--GREY2);
    border: none;
    border-radius: 30px;
    padding: 1rem 1rem;
    text-align: left;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    color: white;

}

/*.grow-wrap > textarea {*/
/*    resize: none;*/
/*    overflow: hidden;*/
/*}*/

textarea::placeholder {
    color: white;
}
</style>