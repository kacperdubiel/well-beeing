<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 py-3">
            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100 ms-3">
                <div class="row mb-2">
                    <star-rating
                        v-model:rating="opinion.rating"
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
                        class="textarea js-autoresize w-100"
                        id="opinion-content"
                        v-model="opinion.opinionContent"
                        placeholder="Jeśli chcesz, uzasadnij swoją ocenę..."
                        :class="{ 'has-error': submittingOpinion && invalidRating}"
                        @focus="clearStatusOpinion"
                        @keypress="clearStatusOpinion"
                    >
                    </textarea>
                </div>
            </div>
        </div>
        <div class="row text-start mb-3 px-2" v-if="errorOpinion">
            <div class="col">
                <p class="has-error m-0">
                    Proszę wybrać ocenę!
                </p>
            </div>
        </div>
        <div class="row justify-content-end px-4 pb-3">
            <div class="col-12 col-md-5 align-self-end text-end">
                <button class="btn-panel-social-outline px-5" @click="addOpinion">
                    Dodaj opinię
                </button>
            </div>
        </div>

    </div>
</template>

<script>
import {setResizeListeners} from "@/helpers/auto-resize";
import StarRating from 'vue-star-rating';
export default {
    name: "NewOpinion",
    components: {
        StarRating
    },
    data() {
        return {
            opinion: {
                opinionContent: "",
                rating: 0

            },
            profilePictureSrc: "",
            submittingOpinion: false,
            successOpinion: false,
            errorOpinion: false
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        addOpinion() {
            this.submittingOpinion = true
            this.clearStatusOpinion()

            if (this.invalidRating) {
                this.errorOpinion = true
                return
            }

            const url = `${this.apiURL}opinion/${this.$route.params.profileId}`
            const token = this.$store.getters.getToken
            this.axios.post(url, this.opinion, {headers: {Authorization: `Bearer ${token}`}}).then(() => {
                this.clearInputs()
                this.successOpinion = true
                this.submittingOpinion = false
                document.getElementById('opinion-content').style.height = '80px'
            }).catch(error => {
                console.log(error.response)
            });
        },
        clearStatusOpinion() {
            this.successOpinion = false
            this.errorOpinion = false
        },
        clearInputs() {
            this.opinion.opinionContent = ""
            this.opinion.rating = 0

        }

    },
    mounted() {
        this.downloadProfilePicture()
        setResizeListeners(this.$el, ".js-autoresize")
    },
    computed: {
        invalidRating() {
            return this.opinion.rating === 0
        },
    }
}
</script>

<style scoped>
textarea {
    background-color: var(--GREY2);
    border: none;
    border-radius: 30px;
    padding: 1rem 1rem;
    text-align: left;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    color: white;

}

.grow-wrap > textarea {
    resize: none;
    overflow: hidden;
}

textarea::placeholder {
    color: white;
}
</style>