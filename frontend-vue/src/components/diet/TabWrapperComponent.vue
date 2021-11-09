<template>
    <div class="tabs">
        <ul class="tabs_header">
            <li v-for="title in tabTitles"
            :key="title"
            :class="{selected: title == selectedTitle}"
            @click="selectedTitle = title">
                {{title}}
            </li>
        </ul>
        <slot></slot>
    </div>
</template>

<script>
import { ref, provide } from 'vue'
export default {
    name: "TabWrapperComponent",
    setup(props, { slots }){
        const tabTitles = ref(slots.default().map((tab) => tab.props.title))
        const selectedTitle =ref(tabTitles.value[0])
        provide("selectedTitle", selectedTitle)
        return {
            selectedTitle,
            tabTitles
        }
    }
}
</script>

<style scoped>
    .tabs {
        margin: 0, auto;
    }

    .tabs_header {
        margin-left: 20px;
        margin-right: 20px;
        list-style: none;
        padding: 0px;
        display: flex;
        align-items: flex-start;
        border-bottom: 1px solid var(--DIET); 
    }

    .tabs_header li {
        margin-top: 20px;
        margin-left: 0px;
        width: 100px;
        text-align: center;
        padding: 20px, 20px;
        margin-right: 1px;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
        cursor: pointer;
        transition: 0.4s all ease-out;
    }

    .tabs_header li.selected {
        width: 100px;
        background-color: var(--DIET);
        color: white;
        text-align: center;
        padding: 20px, 20px;
        margin-right: 1px;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
        cursor: pointer;
        transition: 0.4s all ease-out;
    }

 </style>