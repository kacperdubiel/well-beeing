<template>
    <div class="modal fade" backdrop="static" id="profileCardFormModal" tabindex="-1" aria-labelledby="cardModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content card-form-modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title black-text ms2" id="cardModalLabel">Zmień dane karty</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="weightInput">Waga [kg]</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="weightInput"
                                            type="number"
                                            v-model="this.formCardData.weight"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="heightInput">Wzrost [cm]</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="heightInput"
                                            type="number"
                                            v-model="this.formCardData.height"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="ageInput">Wiek [lata]</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="ageInput"
                                            type="number"
                                            v-model="this.formCardData.age"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="activityTraining">Treningi [minuty/tydzień]</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="activityTrainingInput"
                                            type="number"
                                            v-model="this.formCardData.trainingActivity"
                                        />
                                    </div>
                                </div>
                                <hr/>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text form-check-label" for="veganInput">Dieta wegańska</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            id="veganInput"
                                            type="checkbox"
                                            v-model="this.formCardData.isVegan"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text form-check-label" for="vegetarianInput">Dieta wegetariańska</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            id="vegetarianInput"
                                            type="checkbox"
                                            v-model="this.formCardData.isVegetarian"
                                        />
                                    </div>
                                </div>
                                <hr/>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="activityLevelInput">Poziom aktywności</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="activityLevelInput" v-model="this.formCardData.activityLevel" style="width: 260px;" class="form-select" aria-label="Default select example">
                                            <option value="VERY_LOW">{{this.$func_global.mapActivity("VERY_LOW")}}</option>
                                            <option value="LOW">{{this.$func_global.mapActivity("LOW")}}</option>
                                            <option value="MEDIUM">{{this.$func_global.mapActivity("MEDIUM")}}</option>
                                            <option value="HIGH">{{this.$func_global.mapActivity("HIGH")}}</option>
                                            <option value="VERY_HIGH">{{this.$func_global.mapActivity("VERY_HIGH")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="dietGoalInput">Cel</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="dietGoalInput" v-model="this.formCardData.dietGoal" style="width: 260px;" class="form-select" aria-label="Default select example">
                                            <option value="FAST_LOSE_WEIGHT">{{this.$func_global.mapDietGoal("FAST_LOSE_WEIGHT")}}</option>
                                            <option value="LOSE_WEIGHT">{{this.$func_global.mapDietGoal("LOSE_WEIGHT")}}</option>
                                            <option value="KEEP_WEIGHT">{{this.$func_global.mapDietGoal("KEEP_WEIGHT")}}</option>
                                            <option value="GAIN_WEIGHT">{{this.$func_global.mapDietGoal("GAIN_WEIGHT")}}</option>
                                            <option value="FAST_GAIN_WEIGHT">{{this.$func_global.mapDietGoal("FAST_GAIN_WEIGHT")}}</option>
                                            <option value="GAIN_MUSCLES">{{this.$func_global.mapDietGoal("GAIN_MUSCLES")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="sexInput">Płeć</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="sexInput" v-model="this.formCardData.sex" style="width: 260px;" class="form-select" aria-label="Default select example">
                                            <option value="WOMAN">{{this.$func_global.mapSex("WOMAN")}}</option>
                                            <option value="MAN">{{this.$func_global.mapSex("MAN")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <hr/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
export default {
    name: "ProfileCardFormComponent",
    props:{
        formCardData: {
            type: Object
        }
    }
}
</script>

<style scoped>
 .black-text {
     color: black;
 }
 .card-form-modal-content{
     background-color: white;
     color: black;
     border-radius: 25px;
     padding: 15px;
 }
</style>