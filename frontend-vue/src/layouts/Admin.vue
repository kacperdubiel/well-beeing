<template>
    <div id="layout-admin">
        <navbar-admin/>
        <div class="content">
            <slot/>
        </div>
    </div>

</template>

<script>
import NavbarAdmin from "@/components/layout/NavbarAdmin";
export default {
    name: "Admin",
    components: {
        NavbarAdmin
    }
}
</script>

<style scoped>
.content {
    margin-top: 70px;
}
</style>