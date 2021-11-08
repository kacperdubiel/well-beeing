<template>
    <div
         :id="trainingSource.trainingId"
    >
        <div class="row mt-2">
            <div class="col-8">
                <p>{{trainingSource.name}}</p>
            </div>
            <div class="col-4">
                <button class="btn-white" @click="openInfoModal(trainingSource)" data-bs-toggle="modal" data-bs-target="#infoTrainingModal">
                    <font-awesome-icon :icon="['fa', 'info']" />
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-12 additional">
                {{this.$func_global.mapTrainingDifficulty(trainingSource.trainingDifficulty)}}
            </div>

        </div>
        <div class="row">
            <div class="col-12 additional">
                {{this.$func_global.getTimePrettyFromSeconds(trainingSource.totalTrainingTimeSeconds)}} | {{trainingSource.caloriesBurned}} kcal
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: "TrainingNode",
    props: {
        trainingSource: Object,
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
            this.$emit('set:training', training)
        }
    }
}
</script>

<style scoped>
.row.mt-2 {
    height: 50px;
}
.additional {
    font-size: 12px;
}
.btn-white {
    font-size: x-small;
}
</style>
