<template>
    <form @submit.prevent="addTodo">
        <input
        type="text"
        placeholder="Enter a new task"
        v-model="task"
        />
    </form>
</template>

<script>
export default {
    name: "TodoNew",
    data() {
        return {
            task: "",
            id: 0
        };
    },
    methods: {
        addTodo: function () {
            const { id, task } = this;
            this.$store.commit("addTodo", { id, task });
            this.id++;
            this.task = "";
        }
    }
}
</script>

<style scoped>

</style>