<template>
    <div class="shared-section-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc !== ''" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">
            </div>

            <div class="d-flex flex-column align-self-center w-100">
                <div class="text-start d-flex align-items-baseline ms-3">
                    <span id="creator" class="fw-bolder clickable" @click="redirectToProfile(this.postSource.creator.id)">{{this.postSource.creator.firstName}} {{this.postSource.creator.lastName}}</span>
                    <span id="time" class="ms-3 fw-bolder">| {{this.$func_global.formatDateDateFromNow(this.postSource.addedDate)}}</span>
                </div>
            </div>

        </div>

        <div class="row text-start px-4 pt-2 pb-1">
            <div class="col">
                <p id="post-content" v-html="this.$func_global.convertNewLines(this.postSource.postContent)"></p>
            </div>
        </div>

        <div class="row text-start pb-3" v-if="this.postSource.postImgPath">
            <div class="col">
                <img :src="postPictureSrc" alt="Post picture"  id="post-picture" class="w-100">
            </div>
        </div>
        <div class="row text-start pb-3" v-else-if="this.postSource.originalTrainingPlan">
            <div class="col">
                <training-plan-week v-if="this.postSource.originalTrainingPlan.trainingPlanId != null" :days="this.$func_global.days"
                                    :plan="this.postSource.originalTrainingPlan" :plan-type="'details'"
                                    :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(this.postSource.originalTrainingPlan.beginningDate))"
                                    @update:items="updateItems"  @set:training="setTraining"/>
            </div>
        </div>
        <div class="row text-start pb-3" v-else-if="this.postSource.originalNutritionPlan">
            <div class="col">
                <nutrition-plan-component :userId="null" :from-post="true" :nutritionPlanId="this.postSource.originalNutritionPlan.id"/>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingPlanWeek from "@/components/sport/trainingPlan/TrainingPlanWeek";
import NutritionPlanComponent from "@/components/diet/plans/NutritionPlanComponent";

export default {
    name: "SharedPost",
    props: {
        postSource: Object
    },
    components: {
        TrainingPlanWeek,
        NutritionPlanComponent
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
                this.$func_global.downloadPhoto(url, token).then(result => this.postPictureSrc = result)
            }
        },
        redirectToProfile(id) {
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        },
    },
    mounted() {
        this.downloadProfilePicture()
        this.downloadPostPicture()
    },
    watch: {
        postSource: function (){
            this.downloadPostPicture()
        }
    }
}
</script>

<style scoped>
#time {
    color: var(--GREY1);
    font-size: 14px;
}

#post-content {
    text-align: justify;
}

.no-bg:hover {
    background-color: var(--GREY2);
}

.no-bg {
    color: white;
    border-radius: 5px;
}
</style>