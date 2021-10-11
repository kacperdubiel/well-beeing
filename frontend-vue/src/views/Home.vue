<template>
    <div class="home">
        <!--    <img alt="Vue logo" src="../assets/logo.png">-->
        <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
        <TodoNew />
        <TodoList />
    </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import TodoNew from "@/components/TodoNew.vue";
import TodoList from "@/components/TodoList.vue";

export default {
    name: 'Home',
    components: {
        // HelloWorld,
          TodoNew,
          TodoList
    }
}
</script>
