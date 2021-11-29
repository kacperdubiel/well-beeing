<template>
    <div>
        <div class="d-flex">
            <div>
                <date-picker v-model="dateRange" :model-config="datePickerConfig" is-range />
            </div>
            <div class="selected-data align-left">
                <span v-if="measureTypes.length > 0" class="h6">Zdrowie:</span>
                <div v-if="measureTypes.length > 0" class="selected-data-telemedic d-flex justify-content-start flex-wrap mb-2">
                    <div class="form-check form-switch" v-for="mType in measureTypes" v-bind:key="mType.id">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="mType.id"
                               :disabled="selectedCheckboxes.length >= maxSelected && selectedCheckboxes.indexOf(mType.id) === -1">
                        <label class="form-check-label">{{ mType.name }}</label>
                    </div>
                </div>

                <span v-if="DIET_MEASURES" class="h6">Dieta</span>
                <div v-if="DIET_MEASURES" class="selected-data-diet d-flex justify-content-start flex-wrap mb-2">
                    <div class="form-check form-switch" v-for="DIET_MEASURE in DIET_MEASURES" v-bind:key="DIET_MEASURE.EN">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE.PL }}</label>
                    </div>
                </div>

                <span v-if="TRAINING_MEASURES" class="h6">Trening</span>
                <div v-if="TRAINING_MEASURES" class="selected-data-training d-flex justify-content-start flex-wrap">
                    <div class="form-check form-switch" v-for="TRAINING_MEASURE in TRAINING_MEASURES" v-bind:key="TRAINING_MEASURE.EN">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="TRAINING_MEASURE.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(TRAINING_MEASURE.EN)">
                        <label class="form-check-label mx-2">{{ TRAINING_MEASURE.PL }}</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="pt-2">
            <analysis-chart-component :data="series" :hide-y-axis="hideYAxis"/>
        </div>
        <div class="row">
            <div class="form-check form-switch d-flex justify-content-center">
                <input v-model="hideYAxis" class="form-check-input" type="checkbox">
                <label class="form-check-label mx-2">Ukryj osie Y</label>
            </div>
        </div>
    </div>
</template>

<script>
import { DatePicker } from 'v-calendar';
import AnalysisChartComponent from "@/components/telemedic/analysis/AnalysisChartComponent";

const DIET_MEASURES = {
    CALORIES: { EN: 'calories', PL: 'Kalorie', UNIT: "kcal", PRECISION: 0},
    FIBER: { EN: 'fiber', PL: 'Błonnik', UNIT: "g", PRECISION: 2},
    SALT: { EN: 'salt', PL: 'Sól', UNIT: "g", PRECISION: 2},
    CHOLESTEROL: { EN: 'cholesterol', PL: 'Cholesterol', UNIT: "g", PRECISION: 3},
    CAFFEINE: { EN: 'caffeine', PL: 'Kofeina', UNIT: "g", PRECISION: 3},
    CARBOHYDRATES: { EN: 'carbohydrates', PL: 'Węglowodany', UNIT: "g", PRECISION: 2},
    PROTEINS: { EN: 'proteins', PL: 'Białka', UNIT: "g", PRECISION: 2},
    FATS: { EN: 'fats', PL: 'Tłuszcze', UNIT: "g", PRECISION: 2},
    SUGAR: { EN: 'sugar', PL: 'Cukry', UNIT: "g", PRECISION: 2},
    SATURATED_FATS: { EN: 'saturated_fats', PL: 'Tłuszcze nasycone', UNIT: "g", PRECISION: 2},
};

const TRAINING_MEASURES = {
    BURNED_CALORIES: { EN: 'burned_calories', PL: 'Spalone kalorie', UNIT: "kcal", PRECISION: 0},
    TRAINING_LENGTH: { EN: 'training_length', PL: 'Długość treningu', UNIT: "min", PRECISION: 1},
};

export default {
    name: "AnalysisComponent",
    components: {
        AnalysisChartComponent,
        DatePicker,
    },
    props: {
        userId: String
    },
    watch: {
        dateRange: function () {
            this.getAnalysisData();
        },
        selectedCheckboxes: function() {
            this.updateSeries();
        },
        isUpdated: function() {
            this.updateSeries();
        },
    },
    data() {
        return {
            DIET_MEASURES: DIET_MEASURES,
            TRAINING_MEASURES: TRAINING_MEASURES,

            dateRange: {
                start: new Date(),
                end: new Date()
            },
            datePickerConfig: {
                start: {
                    timeAdjust: '00:00:00',
                },
                end: {
                    timeAdjust: '23:59:59',
                },
            },

            measureTypes: [],

            selectedCheckboxes: [],
            maxSelected: 4,

            analysisData: {},
            isDataUpdated: {
                telemedic: false,
                diet: false,
                training: false,
            },
            isUpdated: false,

            series: [],
            hideYAxis: false,
        }
    },
    methods: {
        getMeasureTypes() {
            this.axios.get(`${this.apiURL}measure-types`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.measureTypes = response.data;
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getAnalysisData(){
            this.analysisData = {};
            this.createPropertiesInAnalysisData();
            this.getTelemedicMeasures();
            this.getDieteticMeasures();
            this.getTrainingMeasures();
        },
        createPropertiesInAnalysisData(){
            for(const measureType in this.measureTypes){
                this.createArrayPropertyInAnalysisData(this.measureTypes[measureType].id);
            }
            for(const MEASURE in DIET_MEASURES){
                this.createArrayPropertyInAnalysisData(DIET_MEASURES[MEASURE].EN);
            }
            for(const MEASURE in TRAINING_MEASURES){
                this.createArrayPropertyInAnalysisData(TRAINING_MEASURES[MEASURE].EN);
            }
        },
        createArrayPropertyInAnalysisData(propertyName){
            if(!this.analysisData[propertyName]){
                this.analysisData[propertyName] = [];
            }
        },
        checkAllUpdated(){
            if(this.isDataUpdated.telemedic === true && this.isDataUpdated.diet === true
                && this.isDataUpdated.training === true)
            {
                this.isUpdated = true;
            }
        },
        getTelemedicMeasures(){
            this.axios.get(`${this.apiURL}measures/user/${this.userId}`
                + `/from/${this.dateRange.start.toJSON()}/to/${this.dateRange.end.toJSON()}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    let measures = response.data;

                    measures.forEach(measure => {
                        let measureTypeId = measure.measureType.id;
                        let chartElement = {
                            x: measure.measureDate,
                            y: measure.value,
                            min: measure.measureType.minValue,
                            precision: measure.measureType.precision,
                        }

                        this.analysisData[measureTypeId].push(chartElement)
                    });

                })
                .catch(e => {
                    console.log(e);
                })
            .then(() => {
                this.isDataUpdated.telemedic = true;
                this.checkAllUpdated();
            });
        },
        getDieteticMeasures(){
            this.axios.get(`${this.apiURL}report/profile/${this.userId}`
                + `/from/${this.dateRange.start.toJSON()}/to/${this.dateRange.end.toJSON()}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    let reports = response.data;

                    reports.forEach(report => {
                        report.productDetailsList.forEach(product => {
                            let x = product.consumingTime;
                            let y = 0;
                            let desc = product.product.name;

                            this.pushNutritionalValues(x, y, desc, product);
                        })

                        report.dishDetailsList.forEach(dish => {
                            let x = dish.consumingTime;
                            let y = 0;
                            let desc = dish.dish.name;

                            this.pushNutritionalValues(x, y, desc, dish);
                        })
                    });
                })
                .catch(e => {
                    console.log(e);
                })
                .then(() => {
                    this.isDataUpdated.diet = true;
                    this.checkAllUpdated();
                });
        },
        pushNutritionalValues(x, y, desc, fromFood){
            y = fromFood.derivedNutritionalValues.derivedCalories;
            this.analysisData[DIET_MEASURES.CALORIES.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.CALORIES.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedFiber;
            this.analysisData[DIET_MEASURES.FIBER.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.FIBER.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedSalt;
            this.analysisData[DIET_MEASURES.SALT.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.SALT.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedCholesterol;
            this.analysisData[DIET_MEASURES.CHOLESTEROL.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.CHOLESTEROL.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedCaffeine;
            this.analysisData[DIET_MEASURES.CAFFEINE.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.CAFFEINE.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedCarbohydrates;
            this.analysisData[DIET_MEASURES.CARBOHYDRATES.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.CARBOHYDRATES.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedProteins;
            this.analysisData[DIET_MEASURES.PROTEINS.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.PROTEINS.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedFats;
            this.analysisData[DIET_MEASURES.FATS.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.FATS.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedSugar;
            this.analysisData[DIET_MEASURES.SUGAR.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.SUGAR.PRECISION});

            y = fromFood.derivedNutritionalValues.derivedSaturatedFats;
            this.analysisData[DIET_MEASURES.SATURATED_FATS.EN].push({x: x, y: y, description: desc,
                precision: DIET_MEASURES.SATURATED_FATS.PRECISION});
        },
        getTrainingMeasures(){
            this.axios.get(`${this.apiURL}sport/report/profile/${this.userId}`
                + `/from/${this.dateRange.start.toJSON()}/to/${this.dateRange.end.toJSON()}`, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    let reports = response.data;

                    reports.forEach(report => {
                        report.exerciseList.forEach(exercise => {
                            let x = exercise.exercisingTime;
                            let y = "";
                            let desc = exercise.exercise.name;

                            this.pushTrainingValues(x, y, desc, exercise);
                        })

                        report.trainingList.forEach(training => {
                            let x = training.trainingTime;
                            let y = "";
                            let desc = training.training.name;

                            this.pushTrainingValues(x, y, desc, training);
                        })
                    });

                })
                .catch(e => {
                    console.log(e);
                })
                .then(() => {
                    this.isDataUpdated.training = true;
                    this.checkAllUpdated();
                });
        },
        pushTrainingValues(x, y, desc, trainForm){
            y = trainForm.minutes;
            this.analysisData[TRAINING_MEASURES.TRAINING_LENGTH.EN].push({x: x, y: y, description: desc,
                precision: TRAINING_MEASURES.TRAINING_LENGTH.PRECISION});

            y = trainForm.caloriesBurned;
            this.analysisData[TRAINING_MEASURES.BURNED_CALORIES.EN].push({x: x, y: y, description: desc,
                precision: TRAINING_MEASURES.BURNED_CALORIES.PRECISION});
        },
        updateSeries(){
            this.series = [];
            this.selectedCheckboxes.forEach(selected => {
                let seriesName = this.getSeriesName(selected);

                if(this.analysisData[selected]){
                    let sortedData = this.analysisData[selected].sort(function(a,b){
                        return new Date(b.x) - new Date(a.x);
                    });

                    this.series.push({name: seriesName, data: sortedData});
                }
            });
            this.isUpdated = false;
            this.isDataUpdated.telemedic = false;
            this.isDataUpdated.diet = false;
            this.isDataUpdated.training = false;
        },
        getSeriesName(selected){
            let seriesName = "";

            let measureType = this.measureTypes.find(m => m.id === selected);
            if(measureType) {
                seriesName = measureType.name + " [" + measureType.unit + "]"
            }
            else {
                for(const MEASURE in DIET_MEASURES){
                    if(selected === DIET_MEASURES[MEASURE].EN){
                        seriesName = DIET_MEASURES[MEASURE].PL + " [" + DIET_MEASURES[MEASURE].UNIT + "]";
                    }
                }

                for(const MEASURE in TRAINING_MEASURES){
                    if(selected === TRAINING_MEASURES[MEASURE].EN){
                        seriesName = TRAINING_MEASURES[MEASURE].PL + " [" + TRAINING_MEASURES[MEASURE].UNIT + "]";
                    }
                }
            }
            return seriesName;
        },
        isSelected(element){
            return this.selectedCheckboxes.indexOf(element) === -1
        },
    },
    created() {
        this.getMeasureTypes();
        this.getAnalysisData();
    }
}
</script>

<style scoped>

.selected-data {
    height: 270px;
    padding: 0 10px 0 10px;
    width: 100%;
    overflow-y: auto;
    margin-left: 10px;
}

.selected-data-telemedic {
    gap: 0 15px;
}

.selected-data-diet {
    gap: 0 15px;
}

.selected-data-training {
    gap: 0 15px;
}


</style>