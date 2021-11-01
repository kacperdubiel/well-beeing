<template>
            <div class="col ">
                <div class="section-bg p-2 training">
                    <div class="row justify-content-center align-items-center">
                            <div class="col-5 offset-3 training-time-day text-uppercase text-center ">{{trainingPosition.timeOfDay}}
                            </div>
                            <div class="col-3 form-switch justify-content-end">
                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                            </div>
                    </div>
                    <div class="training-name">{{trainingPosition.training.name}}</div>
                    <div class="training-descr">{{trainingPosition.training.description}}</div>
                    <div class="training-additional">3 min | 0 kcal</div>
                </div>
            </div>

</template>

<script>
export default {
    name: "TrainingInPlan",
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
            }
        }
    },
    props: {
        trainingPosition: Object
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
</style>
