<template>
    <div id="app">
        <component :is="layout">
            <router-view/>
        </component>
    </div>
</template>

<script>
    const defaultLayout = "default";

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
    color: #2c3e50;
}

.side-menu .router-link-exact-active, .side-menu .router-link-active {
    color: var(--DARK-YELLOW);
}

.section-bg {
    background-color: var(--GREY2);
    border-radius: 40px;
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

p.has-error {
    color: var(--INTENSE-PINK);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

.table-dark {
    --bs-table-bg: var(--GREY2);
}

</style>
