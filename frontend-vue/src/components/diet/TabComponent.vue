<template>
    <div class="tab-content" v-show="title == selectedTitle">
        <slot></slot>
    </div>
</template>

<script>
import { inject } from 'vue'
export default {
    name: "TabComponent",
    props: ['title'],
    setup(){
        const selectedTitle = inject("selectedTitle")
        return {
            selectedTitle
        }
    }
}
</script>

<style scoped>
    .tab-content {
        min-height: 400px;
        padding: 30px;
        border-radius: 5px;
        border-top-color: var(--DIET);
        border-top-width: 5ch;
        align-items: flex-start;
        margin-left: 10px;
        margin-right: 10px;
        display: flex;
    }
 </style>