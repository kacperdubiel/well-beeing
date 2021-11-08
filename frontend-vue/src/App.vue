<template>
    <div id="app">
        <component :is="layout">
            <router-view/>
        </component>
    </div>
</template>

<script>
    const defaultLayout = "default";
    import 'vue-select/dist/vue-select.css';
    export default {
        computed: {
            layout() {
                return (this.$route.meta.layout || defaultLayout) + '-layout';
            }
        }
    }
</script>

<style>
@import "./assets/colors.css";

#app {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: var(--GREY3);
}

body {
    color: var(--GREY3);
}

.side-menu .router-link-exact-active, .side-menu .router-link-active {
    color: var(--DARK-YELLOW);
}

.section-bg {
    background-color: var(--GREY2);
    border-radius: 20px;
    color: white;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.section-2-bg {
    background-color: var(--GREY3);
    border-radius: 20px;
    color: white;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.sticky-top {
    top: 100px;
}

.btn-white {
    background-color: white;
    color: var(--GREY3);
    border-radius: 5px;
    border: none;
    padding: 0.2rem 0.5rem;
    font-size: 1rem;
}

button[class^="btn-panel-"] {
    color: white;
    border-radius: 5px;
    border: none;
    padding: 0.5rem 1rem;
    font-size: 1.25rem;
    font-weight: bold;
    background-color: var(--GREY1);
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.btn-panel-telemedic {
    background-color: var(--TELEMEDIC) !important;
}

.btn-panel-sport {
    background-color: var(--SPORT) !important;
}

.btn-panel-diet {
    background-color: var(--DIET) !important;
}

.btn-panel-social {
    background-color: var(--DARK-YELLOW) !important;
}

.btn-panel-social-outline {
    color: var(--GREY2);
    border-radius: 5px;
    border: 3px solid var(--DARK-YELLOW);
    padding: 0.3rem 0.5rem;
    font-size: 1rem;
    font-weight: bolder;
    background-color: white;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.btn-panel-social-outline:hover {
    color: white;
    background-color: var(--DARK-YELLOW);
}

p.has-error {
    color: var(--INTENSE-PINK);
}

p.success-social {
    color: var(--DARK-YELLOW);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

.table-dark {
    --bs-table-bg: var(--GREY2);
}

.no-bg {
    background-color: transparent;
    border: none;
}


#search-input {
    border-radius: 40px;
    height: 50px;
    border: none;
}
.test {
    background-color: #D83D68;
}
input{
    text-indent: 10px;
}
.style-chooser .vs__search::placeholder,
.style-chooser .vs__dropdown-toggle,
.style-chooser .vs__dropdown-menu {
    border-color: rgb(133, 133, 133);
    text-transform: lowercase;
    font-variant: small-caps;
    background-color: white;
}

.style-chooser .vs__clear,
.style-chooser .vs__open-indicator {
    fill: #394066;
}
.style-chooser .vs__selected {
    background-color: var(--SPORT);
    color: white;
    background-blend-mode: color;
}
.style-chooser .vs__deselect {
    fill: var(--GREY3);
}
::-webkit-scrollbar {
    width: 15px;
    height: 15px;
    background-color: var(--DARK-YELLOW);
}
.section-bg ::-webkit-scrollbar {
    background-color: var(--GREY2);
}
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(200,200,200,1);
    border-radius: 5px;
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: var(--LIGHT-YELLOW);
    -webkit-box-shadow: inset 0 0 6px rgba(90,90,90,0.7);
}
</style>
