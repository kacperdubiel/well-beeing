<template>
    <ul>
        <li
        v-for="todo in todos"
        :key="todo.id"
        >
            {{ todo.task }}
        </li>
    </ul>
</template>

<script>
export default {
    name: "TodoList",
    computed: {
        todos() {
            return this.$store.getters.getTodos;
        }
    }
}
</script>

<style scoped>

</style>