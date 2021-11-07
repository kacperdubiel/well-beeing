<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img :src="imageSrc" alt="Bee image"  id="profile-picture" height="100" width="100">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex justify-content-between ms-3">
                    <h3>{{this.profileSource.firstName}} {{this.profileSource.lastName}}</h3>

                    <button class="btn-white ms-auto fw-bolder">
                        Edytuj profil
                    </button>
                </div>

                <div class="text-start d-flex flex-row ms-2">
                    <div class="p-2 mx-2 tag-sport fw-bolder" v-if="this.profileSource.esportTag !== 'NONE'">
                        {{this.profileSource.esportTag}}
                    </div>
                    <div class="p-2 mx-2 tag-nutrition fw-bolder" v-if="this.profileSource.enutritionTag !== 'NONE'">
                        {{this.profileSource.enutritionTag}}
                    </div>
                </div>

            </div>
        </div>
        <div class="row text-start px-4 py-3">
            <p>
                {{this.profileSource.description}}
            </p>
        </div>
    </div>

</template>

<script>
export default {
    name: "ProfileInfo",
    data() {
        return {
            imageSrc: ''
        }
    },
    props: {
        profileSource: Object
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.imageSrc = result)
            console.log('imageSrc' + this.imageSrc)
        }
    },
    mounted() {
        this.downloadProfilePicture()
    }
}
</script>

<style scoped>
#profile-picture {
    border-radius: 50%;
}

div[class*="tag-"] {
    border-radius: 20px;
}

.tag-nutrition {
    background-color: var(--DIET);
}

.tag-sport {
    background-color: var(--SPORT);
}
</style>