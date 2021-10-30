<template>
<div class="container my-3">
    <div class="row justify-content-center">
        <div class="col-md-7 col-sm-6 align-self-center">
            <input
                type="text"
                placeholder="Wyszukaj..."
                id="search-input"
                class="w-100 shadow"
            />
        </div>
        <div class="col-md-5 col-sm-6 align-self-center">
            FILTRY
        </div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Czas</th>
                    <th scope="col">Trudność</th>
                    <th scope="col">kcal/h</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tr in trainingsSource" :key="tr.name">
                    <td>{{ tr.trainingId }}</td>
                    <td>{{ tr.name }}</td>
                    <td>{{ tr.time }}</td>
                    <td>{{ tr.trainingDifficulty }}</td>
                    <td>{{ tr.owner }}</td>
                    <td>
                        <button class="btn-white mx-2">
                            <font-awesome-icon :icon="['fa', 'info']" />
                        </button>
                        <button class="btn-white">
                            <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                        </button>
                        <button class="btn-white mx-2">
                            <font-awesome-icon :icon="['fa', 'trash']" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</template>

<script>
export default {
    name: "TrainingsListComponent",
    data () {
        return {
            exercise: {
                trainingId:453,
                name:"Martwy ciąg",
                difficultyLevel:"Siłowe",
                time:345,
                caloriesBurned:345,
                owner:""
            }
        }

    },
    props: {
        trainingsSource: Array
    }
}
</script>

<style scoped>
.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}
th, td {
    color: var(--bs-white);
}
table {
    --bs-table-hover-color: none;
    color: white;
    border-bottom: none;
}
</style>
