<template>
    <div class="ailment-row">
        <div class="col-lg-4 ailment-name-column">
            <span>{{ailment.name}}</span>
        </div>
        <div class="col-lg-1">
            <button class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#ailmentModal" @click="sendModalData"><font-awesome-icon :icon="['fa', 'info']"/></button>
        </div>
    </div>
</template>

<script>

export default {
    name: "AilmentRowComponent",
    props: {
        ailment: {
            type: Object
        }
    },
    methods:{
        sendModalData(){
            this.$emit("show:modal", this.ailment)
        }
    }
}
</script>

<style scoped>
    .ailment-row{
        align-items: flex-start;
        display: flex;
        margin-bottom: 3px;
    }

    .ailment-name-column{
        align-items: flex-start;
        display: flex;
        margin-bottom: 3px;
    }

</style>