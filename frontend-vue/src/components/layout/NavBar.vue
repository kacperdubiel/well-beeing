<template>
    <nav class="navbar navbar-expand-lg fixed-top">
<!--        <div class="container-fluid">-->
<!--            <div class="row w-100">-->
                <div class="col-1">
                    <router-link :to="{ name: 'FeedView' }">
                        <img src="@/assets/bee.png" alt="Bee image" height="70">
                    </router-link>
<!--                    <a href="/feed">-->
<!--                        -->
<!--                    </a>-->

                </div>
                <div class="col-md-8 col-sm-6 align-self-center">
                    <input
                        type="text"
                        placeholder="Wyszukaj..."
                        id="search-input"
                        class="w-100 shadow"
                    />
                </div>
                <div class="col-1 align-self-center">
                    <font-awesome-icon :icon="['far', 'bell']" size="2x" class="navbar-icon"/>
                </div>
                <div class="col-2 d-flex align-self-center align-items-center">
                    <img v-if="this.$store.getters.getProfileImageSrc" :src="this.$store.getters.getProfileImageSrc" alt="Profile picture"  class="profile-picture" height="40" width="40">
                    <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="40" width="40">
                    <div class="dropdown">
                        <a class="dropdown-toggle ms-2" href="#" role="button" id="dropdown-profile" data-bs-toggle="dropdown" aria-expanded="false">
                            Cześć, {{this.$store.getters.getFirstName}}!
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown-profile">
                            <li>
                                <router-link class="dropdown-item" :to="{ name: 'ProfileEdit' }">
                                    Ustawienia
                                </router-link>
                            </li>
                            <li><a @click="handleLogout" class="dropdown-item" href="#">Wyloguj się</a></li>
                        </ul>
                    </div>
                </div>
<!--            </div>-->
<!--        </div>-->



    </nav>
</template>

<script>
export default {
    name: "NavBar",
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.$store.commit('setProfileImageSrc', result))
        }
    },
    mounted() {
        this.downloadProfilePicture
    }
}
</script>

<style scoped>
.navbar {
    background-color: var(--DARK-YELLOW);
}

#search-input {
    border-radius: 40px;
    height: 50px;
    border: none;
}

input{
    text-indent: 10px;
}

.navbar-icon {
    color: white;
}

#dropdown-profile {
    color: white;
    font-weight: bold;
    font-size: 100%;
    text-decoration: none;
}

.profile-picture {
    border: 2px solid white;
}

#dropdown-profile {
    font-size: 115%;
}

</style>