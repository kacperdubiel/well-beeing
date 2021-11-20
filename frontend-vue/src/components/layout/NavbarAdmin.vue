<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'RoleRequestsViewAdmin'}">Prośby o nadanie roli</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'DataImportsViewAdmin'}">Import danych</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'LoginAdminView'}">Inne</router-link>
                    </li>

                </ul>
                <form class="d-flex">
                    <button class="btn btn-outline-light">Wyloguj się</button>
                </form>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    name: "NavbarAdmin"
}
</script>

<style scoped>
.nav-link.router-link-exact-active, .nav-link.router-link-active {
    color: var(--DARK-YELLOW);
}
</style>