<template>
    <div class="section-bg sticky-top">
        <div class="side-menu">
            <router-link to="/social">
                <div class="row mx-4 pt-3 pb-2 sidebar-item">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'user']" />
                    </div>
                    <div class="col-9 offset-1">
                        Mój profil
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'user-friends']" />
                    </div>
                    <div class="col-9 offset-1">
                        Znajomi
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'users']" />
                    </div>
                    <div class="col-9 offset-1">
                        Obserwacje
                    </div>
                </div>
            </router-link>
        </div>


        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'comments']" />
                    </div>
                    <div class="col-9 offset-1">
                        Wiadomości
                    </div>
                </div>
            </router-link>
        </div>
        <div class="mt-4 side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'utensils']" />
                    </div>
                    <div class="col-9 offset-1">
                        Diety
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/sport">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'dumbbell']" />
                    </div>
                    <div class="col-9 offset-1">
                        Ćwiczenia
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2">
                    <div class="col-2 ps-2">
                        <font-awesome-icon :icon="['fa', 'briefcase-medical']" />
                    </div>
                    <div class="col-9 offset-1">
                        Zdrowie
                    </div>
                </div>
            </router-link>
        </div>


        <div class="mt-4 side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col ps-2">
                        PANEL DIETETYKA
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col ps-2">
                        PANEL TRENERA
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 py-2 sidebar-item">
                    <div class="col ps-2">
                        PANEL LEKARZA
                    </div>
                </div>
            </router-link>
        </div>
        <div class="side-menu">
            <router-link to="/">
                <div class="row mx-4 pt-2 pb-3">
                    <div class="col ps-2">
                        ZŁÓŻ PODANIE
                    </div>
                </div>
            </router-link>
        </div>


    </div>
</template>

<script>
export default {
    name: "SideBar"
}
</script>

<style scoped>
a {
    color: white;
    font-size: 130%;
    text-decoration: none;
    text-align: left;
}

router-link {
    text-align: left;
}

.sidebar-item {
    border-bottom: 1px solid white;
}
</style>