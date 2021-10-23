<template>
    <div>
        <nav-bar />
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <side-bar />
                </div>
                <div class="col-6">
                    <slot />
                </div>
                <div class="col-3">
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

</style>