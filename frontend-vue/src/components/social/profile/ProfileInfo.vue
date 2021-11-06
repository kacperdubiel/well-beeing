<template>
    <div class="section-2-bg">
        <div class="row px-4 pt-3">

            <div class="col-3">
                <img src="@/assets/bee.png" alt="Bee image"  id="profile-picture" height="100">
            </div>

            <div class="col-9 align-self-center">

                <div class="text-start d-flex">
                    <h3>{{this.profileSource.firstName}} {{this.profileSource.lastName}}</h3>

                    <button class="btn-white ms-auto fw-bolder">
                        Edytuj profil
                    </button>
                </div>

                <div class="text-start d-flex flex-row">
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
    props: {
        profileSource: Object
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