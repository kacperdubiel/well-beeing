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
    border-radius: 35px;
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
}

button[class^="btn-icon-panel-"] {
    color: white;
    border-radius: 5px;
    border: none;
    font-weight: bold;
    width: 27px;
    height: 27px;
    margin-left: 5px;
}

button[class^="btn-card-panel-"] {
    color: white;
    border-radius: 5px;
    border: none;
    font-weight: bold;
    margin: 5px;
    padding: 0.2rem 0.5rem;
}


.btn-panel-telemedic {
    background-color: var(--TELEMEDIC);
}

.tab {
    background-color: var(--GREY2);
    border-radius: 5px;
    color: white;
}

.btn-panel-sport {
    background-color: var(--SPORT);
}

.btn-panel-diet {
    background-color: var(--DIET);
}

.btn-icon-panel-diet {
    background-color: var(--DIET);
}

.btn-card-panel-diet {
    background-color: var(--DIET);
}

.btn-panel-social {
    background-color: var(--DARK-YELLOW);
}

 p.has-error {
    color: var(--INTENSE-PINK);
 }

 .modal-diet-content {
        background-color: white;
        color: black;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        align-content: flex-start;
        align-items: flex-start;
        justify-content: start;
        padding: 20px;
        border-radius: 20px;
}
.modal-content {
    border-radius: 20px;
}
</style>
