<template>
    <div id="app-container">
        <div class="container-fluid">
            <div class="row">
                <nav-bar />
            </div>
            <div class="row content">
                <div class="col-md-3 d-none d-md-block">
                    <side-bar />
                </div>
                <div class="col-md-6 col-sm-10">
                    <slot />
                </div>
                <div class="col-md-3 d-none d-md-block">
                    <today-section />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import SideBar from "@/components/SideBar";
import TodaySection from "@/components/TodaySection";
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
}

.content {
    margin-top: 100px;
}
</style>