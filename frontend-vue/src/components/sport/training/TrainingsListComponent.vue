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
                <tr v-for="tr in trainingsSource" :key="tr.name"
                    v-bind:class="{'selected-training' : (mode === 'toPlan' && this.$store.getters.getPlanTrainingId === tr.trainingId) }"
                    v-on:click="mode === 'toPlan' ? this.$store.commit('setPlanTrainingId', tr.trainingId) : null">
                    <td>{{ tr.trainingId }}</td>
                    <td>{{ tr.name }}</td>
                    <td>{{ this.$func_global.getTimePrettyFromSeconds(tr.totalTrainingTimeSeconds) }}</td>
                    <td>{{ tr.trainingDifficulty }}</td>
                    <td>{{ tr.caloriesBurned }}</td>
                    <td>
                        <button class="btn-white mx-2" @click="openInfoModal(tr)" data-bs-toggle="modal" href="#infoTrainingModal">
                            <font-awesome-icon :icon="['fa', 'info']" />
                        </button>
                        <button class="btn-white" v-if="enableButtons">
                            <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                        </button>
                        <button class="btn-white mx-2" v-if="enableButtons">
                            <font-awesome-icon :icon="['fa', 'trash']" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <TrainingDetails :training="training"/>
</div>
</template>

<script>
import TrainingDetails from "@/components/sport/training/TrainingDetails";
export default {
    name: "TrainingsListComponent",
    components: {TrainingDetails},
    data () {
        return {
            training: {},
            enableButtons: false
        }

    },
    props: {
        trainingsSource: Array,
        mode: String
    },
    methods: {
        getTimePrettyFromSeconds(seconds) {
            if (seconds < 60) {
                return seconds + ' s'
            }
            else if (seconds < 3600) {
                return Math.floor(seconds/60) + ' min'
            }
            else if (seconds >= 3600) {
                let hours = Math.floor(seconds/3600)
                let minutes = Math.floor((seconds - hours*3600)/60)
                return hours + ' h ' + (minutes !== 0 ? minutes + ' min': '')
            }
        },
        openInfoModal(training) {
            this.training = training
        }
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
.selected-training {
    background-color: var(--SPORT);
}
</style>
