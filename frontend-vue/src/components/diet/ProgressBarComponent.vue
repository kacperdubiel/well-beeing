<template>
    <div>
        <div style="height: 25px;" class="progress">
            <div class="progress-bar" 
            v-bind:class="{
                'bg-danger' : this.countPercentage(actualValue, expectedValue) > 110,
                'bg-success' : this.countPercentage(actualValue, expectedValue) >= 90,
                'bg-warning' : this.countPercentage(actualValue, expectedValue) < 90
                }" 
            role="progressbar" 
            :aria-valuenow="this.countPercentage(actualValue, expectedValue)" 
            aria-valuemin="0" 
            aria-valuemax="100" 
            :style="'width:' + this.countPercentage(actualValue, expectedValue) + '%;'">
                {{this.actualValue}} {{this.measure}}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ProgressBarComponent",
    props: {
        actualValue: {
            type: Number
        },
        expectedValue: {
            type: Number
        },
        measure: {
            type: String
        }
    },
    methods: {
        countPercentage(actualValue, expectedValue){
            return Math.round((actualValue / expectedValue)*100)
        }
    }
}
</script>

<style scoped>

</style>