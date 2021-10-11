import { createStore} from "vuex";

export default createStore({
    state () {
        return {
            todos: []
        }
    },
    mutations: {
        addTodo (state, item) {
            state.todos.unshift(item);
        }
    },
    getters: {
        getTodos (state) {
            return state.todos;
        },
        todoCount (state) {
            return state.todos.length;
        }
    }
});
