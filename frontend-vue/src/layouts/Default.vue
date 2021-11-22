<template>
    <div id="app-container">
        <div class="container-fluid">
            <div class="row">
                <nav-bar />
            </div>
            <div class="row content">
                <div class="col-md-2 d-none d-md-block ps-4 pe-2">
                    <side-bar />
                </div>
                <div class="col-md-8 col-sm-10 px-4">
                    <slot />
                </div>
                <div class="col-md-2 d-none d-md-block ps-2 pe-4">
                    <today-section />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import NavBar from "@/components/layout/NavBar";
import SideBar from "@/components/layout/SideBar";
import TodaySection from "@/components/layout/TodaySection";
export default {
    name: "Default",
    components: {
        NavBar,
        SideBar,
        TodaySection
    }
}
</script>

<style scoped>
#app-container {
    background-color: var(--DARK-YELLOW);
    min-height: 100vh;
}

.content {
    margin-top: 100px;
}
</style>
