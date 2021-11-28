<template>
    <div id="app">
        <component :is="layout">
            <router-view :key="$route.path"/>
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
    min-height: 85vh;
}

.section-2-bg {
    background-color: var(--GREY3);
    border-radius: 20px;
    color: white;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}

.shared-section-bg {
    background-color: var(--GREY3);
    border: 1px solid var(--GREY1);
    color: white;
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
.no-bg-btn {
    background-color: transparent;
    border: none;
    color: white;
}

.no-bg-btn span:hover {
    text-decoration: underline;
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
    padding: 0.3rem 0.6rem;
}

.tab-content {
    padding: 5px;
    margin-top: 15px;
    min-height: 400px;
    border-radius: 5px;
    border-top-color: var(--DIET);
    border-top-width: 5ch;
    align-items: flex-start;
    display: flex;
    width: 100%;
}

.btn-panel-telemedic {
    background-color: var(--TELEMEDIC) !important;
}

.tab {
    background-color: var(--GREY2);
    border-radius: 5px;
    color: white;
}

.btn-panel-sport {
    background-color: var(--SPORT) !important;
}

.btn-icon-panel-sport {
    background-color: var(--SPORT) !important;
}

.btn-panel-diet {
    background-color: var(--DIET) !important;
}

.btn-icon-panel-diet {
    background-color: var(--DIET);
}

.btn-card-panel-diet {
    background-color: var(--DIET);
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

.profile-picture {
    border-radius: 50%;
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

input {
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

.style-chooser-diet .vs__clear,
.style-chooser-diet .vs__open-indicator {
    fill: #394066;
}
.style-chooser-diet .vs__selected {
    background-color: var(--DIET);
    color: white;
    background-blend-mode: color;
}
.style-chooser-diet .vs__deselect {
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
    --webkit-box-shadow: inset 0 0 6px rgba(200, 200, 200, 1);
    border-radius: 5px;
}

.sport ::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: var(--SPORT);
    --webkit-box-shadow: inset 0 0 6px rgba(90, 90, 90, 0.7);
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: var(--LIGHT-YELLOW);
    --webkit-box-shadow: inset 0 0 6px rgba(90, 90, 90, 0.7);
}

.align-left {
    text-align: left;
}

.align-right {
    text-align: right;
}

.clickable {
    cursor: pointer;
}

.telemedic-section {
    padding: 25px 20px;
    margin-bottom: 40px;
}

.pagination > li.telemedic-page > a {
    background-color: white;
    color: var(--TELEMEDIC);
}

.pagination > li.telemedic-page > a:focus,
.pagination > li.telemedic-page > a:hover,
.pagination > li.telemedic-page > span:focus,
.pagination > li.telemedic-page > span:hover {
    color: white;
    background-color: var(--TELEMEDIC);
    border-color: var(--TELEMEDIC);
    cursor: pointer;
}

.pagination > .telemedic-page.active > a {
    color: white;
    background-color: var(--TELEMEDIC) !Important;
    border: solid 1px var(--TELEMEDIC) !Important;
}

.pagination > .telemedic-page.active > a:hover {
    background-color: var(--TELEMEDIC) !Important;
    border: solid 1px var(--TELEMEDIC);
}

.pagination > li.sport-page > a {
    background-color: white;
    color: var(--SPORT);
}

.pagination > li.sport-page > a:focus,
.pagination > li.sport-page > a:hover,
.pagination > li.sport-page > span:focus,
.pagination > li.sport-page > span:hover {
    color: white;
    background-color: var(--SPORT);
    border-color: var(--SPORT);
    cursor: pointer;
}

.pagination > .sport-page.active > a {
    color: white;
    background-color: var(--SPORT) !Important;
    border: solid 1px var(--SPORT) !Important;
}

.pagination > .sport-page.active > a:hover {
    background-color: var(--SPORT) !Important;
    border: solid 1px var(--SPORT);
}


.pagination > li.diet-page > a
{
    background-color: white;
    color: var(--DIET);
}

.pagination > li.diet-page > a:focus,
.pagination > li.diet-page > a:hover,
.pagination > li.diet-page > span:focus,
.pagination > li.diet-page > span:hover
{
    color: white;
    background-color: var(--DIET);
    border-color: var(--DIET);
    cursor: pointer;
}

.pagination > .diet-page.active > a
{
    color: white;
    background-color: var(--DIET) !Important;
    border: solid 1px var(--DIET) !Important;
}

.pagination > .diet-page.active > a:hover
{
    background-color: var(--DIET) !Important;
    border: solid 1px var(--DIET);
}

.pagination > li.admin-page > a {
    background-color: white;
    color: var(--GREY3);
}

.pagination > li.admin-page > a:focus,
.pagination > li.admin-page > a:hover,
.pagination > li.admin-page > span:focus,
.pagination > li.admin-page > span:hover {
    color: white;
    background-color: var(--GREY3);
    border-color: var(--GREY3);
    cursor: pointer;
}

.pagination > .admin-page.active > a {
    color: white;
    background-color: var(--GREY3) !Important;
    border: solid 1px var(--GREY3) !Important;
}

.pagination > li.admin-page.disabled > a {
    color: var(--GREY1);
    background-color: white;
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

.circle {
    height: 15px;
    width: 15px;
    background-color: lawngreen;
    border-radius: 50%;
    display: inline-block;
}
</style>
