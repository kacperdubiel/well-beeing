<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <button aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                    class="navbar-toggler" data-bs-target="#navbarSupportedContent"
                    data-bs-toggle="collapse" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :to="{name: 'RoleRequestsViewAdmin'}" class="nav-link">Prośby o nadanie roli
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{name: 'DataImportsViewAdmin'}" class="nav-link">Import danych dietetycznych</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{name: 'SportDataImportsViewAdmin'}" class="nav-link">Import danych
                            sportowych
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{name: 'LoginAdminView'}" class="nav-link">Inne</router-link>
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
