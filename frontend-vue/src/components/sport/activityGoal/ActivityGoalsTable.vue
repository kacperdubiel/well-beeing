<template>
    <div>
        <table class="table table-hover table-success">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Typ</th>
                <th scope="col">Wartość liczbowa</th>
                <th scope="col">Wartość tekstowa</th>
                <th scope="col">Data rozpoczęcia</th>
                <th scope="col">Data zakończenia</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="activityGoal in activityGoalSource" :key="activityGoal.activityGoalId">
                <td>{{ activityGoal.activityGoalId }}</td>
                <td>{{
                        this.$func_global.mapActivityGoal(activityGoal.goalType)
                    }}
                </td>
                <td>{{ activityGoal.numericValue }}</td>
                <td>{{ activityGoal.textValue }}</td>
                <td>{{ this.$func_global.formatDate(activityGoal.goalCreationDate) }}</td>
                <td>{{ this.$func_global.formatDate(activityGoal.goalTargetDate) }}</td>
                <td>
                    <button class="btn-white btn-outline-success mx-2"
                            @click="chooseActivityGoal(activityGoal)">
                        Wybierz
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: "ActivityGoalsTable",
    data() {
        return {}
    },
    props: {
        activityGoalSource: Array
    },
    methods: {
        chooseActivityGoal(goal) {
            this.$emit('choose:goal', goal)
        }
    }
}
</script>

<style scoped>

</style>
