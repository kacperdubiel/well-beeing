<template>
    <nav class="navbar navbar-expand-lg fixed-top">
<!--        <div class="container-fluid">-->
<!--            <div class="row w-100">-->
                <div class="col-1">
                    <a href="/">
                        <img src="@/assets/bee.png" alt="Bee image" height="70">
                    </a>

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
                    <font-awesome-icon :icon="['far', 'user-circle']" size="3x" class="navbar-icon"/>
                    <div class="dropdown">
                        <a class="dropdown-toggle ms-2" href="#" role="button" id="dropdown-profile" data-bs-toggle="dropdown" aria-expanded="false">
                            Cześć, Klaudia!
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown-profile">
                            <li><a class="dropdown-item" href="#">Ustawienia</a></li>
                            <li><a class="dropdown-item" href="#">Wyloguj się</a></li>
                        </ul>
                    </div>
                </div>
<!--            </div>-->
<!--        </div>-->



    </nav>
</template>

<script>
export default {
    name: "NavBar"
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
</style>