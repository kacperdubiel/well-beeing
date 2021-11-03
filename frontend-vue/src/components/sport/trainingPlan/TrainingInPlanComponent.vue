<template>
            <div class="col ">
                <div class="section-bg p-2 training" @click="openInfoModal(trainingPosition.training)" data-bs-toggle="modal" data-bs-target="#infoTrainingModal">
                    <div class="row justify-content-center align-items-center">
                            <div class="col-5 offset-3 training-time-day text-uppercase text-center ">{{trainingPosition.timeOfDay}}
                            </div>
                            <div class="col-3 form-switch justify-content-end">
                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" :disabled="trainingPosition.trainingStatus === 'COMPLETED'" :checked="trainingPosition.trainingStatus === 'COMPLETED'">
                            </div>
                    </div>
                    <div class="training-name">{{trainingPosition.training.name}}</div>
                    <div class="training-descr">{{trainingPosition.training.description}}</div>
                    <div class="training-additional">{{this.$func_global.getTimePrettyFromSeconds(trainingPosition.training.totalTrainingTimeSeconds)}} | {{trainingPosition.training.caloriesBurned}} kcal</div>
                </div>
            </div>

</template>

<script>
export default {
    name: "TrainingInPlan",
    components: {},
    data() {
        return {
            training: {
                week:33,
                name:"Cardio training",
                description:"Lorem ipsum dolor sit amet, conseteturas sadipscing elitr, sed diam nonumy",
                timeOfDay:"popołudnie",
                duration:"10 min",
                kcal:"325",
                isCompleted: false
            },
            timeUnits: "mins"
        }
    },
    props: {
        trainingPosition: Object
    },
    methods: {
        getTimePrettyFromSeconds(position) {
            let seconds = position.training.totalTrainingTimeSeconds;
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
            console.log('Dotarł 0', training)
            this.$emit('set:training', training)
        }
    }

}
</script>

<style scoped>
.col {

}
.training{
    font-weight: bold;
    text-align: left;
    border-radius: 2px;
}
.training-name {
    font-size: 18px;
}
.training-additional{
    font-size: 16px;
}
.training-descr, .training-time-day {
    font-size: 8px;
}
.test {
    background-color: red;
}
#flexSwitchCheckDefault:checked {
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}
</style>
