<template>
    <div class="col ">
        <div class="section-bg p-2 training">
            <div class="row justify-content-center align-items-center">
                <div class="col-5  training-time-day text-uppercase text-center "
                     v-bind:class="{'col-8':create || details, 'offset-3':!create && !details}">
                    {{ this.$func_global.mapTimeOfDay(trainingPosition.timeOfDay) }}
                </div>
                <div v-if="!create && !details" class="col-3 form-switch justify-content-end">
                    <input id="flexSwitchCheckDefault" :checked="trainingPosition.trainingStatus === 'COMPLETED'"
                           :disabled="trainingPosition.trainingStatus === 'COMPLETED'" class="form-check-input"
                           type="checkbox"
                           @change="check($event)">
                </div>
            </div>
            <div v-if="!inModal" class="training-name" data-bs-target="#infoTrainingModal" data-bs-toggle="modal"
                 @click="openInfoModal(trainingPosition.training)">{{ trainingPosition.training.name }}
            </div>
            <div v-else class="training-name in-modal">
                {{ trainingPosition.training.name }}
            </div>
            <div class="training-descr">{{
                    trainingPosition.training.description != null ? trainingPosition.training.description.substring(0, 35) : " "
                }}...
            </div>
            <div class="training-additional">
                {{ this.$func_global.getTimePrettyFromSeconds(trainingPosition.training.totalTrainingTimeSeconds) }} |
                {{ trainingPosition.training.caloriesBurned }} kcal
            </div>
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
                week: 33,
                name: "Cardio training",
                description: "Lorem ipsum dolor sit amet, conseteturas sadipscing elitr, sed diam nonumy",
                timeOfDay: "popołudnie",
                duration: "10 min",
                kcal: "325",
                isCompleted: false
            },
            timeUnits: "mins"
        }
    },
    props: {
        trainingPosition: Object,
        create: Boolean,
        details: Boolean,
        inModal: Boolean
    },
    methods: {
        closeModal() {
            document.getElementById('infoTrainingModalClose').modal('show');

        },
        check() {
            if (this.trainingPosition.trainingStatus !== 'COMPLETED') {
                this.updateTrainingStatus()
            }
        },
        async updateTrainingStatus() {
            const url = `${this.apiURL}sport/training-plan/${this.trainingPosition.trainingPositionId}/update-position-status?newStatus=COMPLETED`
            const token = this.$store.getters.getToken;
            await this.axios.patch(url, {}, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.trainings = response.data['content']
                this.$emit('update:active')
            }).catch(error => {
                console.log(error.response);
            });
            // /training-plan/115/update-position-status?newStatus=COMPLETED
        },
        getTimePrettyFromSeconds(position) {
            let seconds = position.training.totalTrainingTimeSeconds;
            if (seconds < 60) {
                return seconds + ' s'
            } else if (seconds < 3600) {
                return Math.floor(seconds / 60) + ' min'
            } else if (seconds >= 3600) {
                let hours = Math.floor(seconds / 3600)
                let minutes = Math.floor((seconds - hours * 3600) / 60)
                return hours + ' h ' + (minutes !== 0 ? minutes + ' min' : '')
            }
        },
        openInfoModal(training) {
            this.$emit('set:training', training)
        }
    }

}
</script>

<style scoped>
.col {

}

.training {
    font-weight: bold;
    text-align: left;
    border-radius: 2px;
    min-height: 0;
}

.training-name {
    font-size: 18px;
}

.training-name:hover,
.training-name.in-modal {
    font-size: 18px;
    color: var(--SPORT);
}

.training-additional {
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
