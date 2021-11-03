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
            <div class="col-12">
<!--                <div class="row mb-3 px-3 mt-3 mw-100">-->
<!--                    <div class="col-md-6 search-info">-->
<!--                        <span id="search-results">Wyniki wyszukiwania dla frazy: </span>-->
<!--&lt;!&ndash;                        <span class="fst-italic">"{{this.activeSearchWord}}"</span>&ndash;&gt;-->
<!--                        <button class="btn btn-sm btn-outline-4 size" type="button" id="button-cancel">-->
<!--                            <font-awesome-icon :icon="['fa', 'info']" />-->
<!--                        </button>-->
<!--                    </div>-->
<!--                </div>-->
                <div class="row justify-content-center mw-100 mt-3">
                    <div class="col-lg-12 col-md-9 col-sm-7 col-10">
                        <div class="row align-content-center">
                            <div class="col-xl-3 col-lg-4 col-md-6 col-12 px-2 py-2 mx-0 section-bg training"
                                 v-for="tr in trainingsSource" :key="tr.trainingId"
                                 v-bind:class="{'selected-training' : (mode === 'toPlan' && this.$store.getters.getPlanTrainingId === tr.trainingId) }"
                                 v-on:click="mode === 'toPlan' ? this.$store.commit('setPlanTrainingId', tr.trainingId) : null">
                                <training-node :mode="mode" :training-source="tr" @set:training="setTraining"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <TrainingDetails :training="training"/>
    </div>
</template>

<script>
import TrainingNode from "@/components/sport/training/TrainingNode";
import TrainingDetails from "@/components/sport/training/TrainingDetails";

export default {
    name: "TrainingsGridComponent",
    components: {TrainingDetails, TrainingNode},
    data () {
        return {
            exercises: [this.exercise],
            training: Object
        }

    },
    props: {
        trainingsSource: Array,
        mode: String
    },
    methods: {
        setTraining(training) {
            this.training = training
        }
    }
}
</script>

<style scoped>

.training{
    font-weight: bold;
    text-align: left;
    border-radius: 2px;
    background-color: var(--GREY3);
}
.selected-training {
    background-color: var(--SPORT);
}
</style>
