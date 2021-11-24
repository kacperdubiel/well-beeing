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

                <span class="h6">Dieta</span>
                <div class="selected-data-diet d-flex justify-content-start flex-wrap mb-2">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.CALORIES.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.CALORIES.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE_NAMES.CALORIES.PL }}</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.CARBOHYDRATES.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.CARBOHYDRATES.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE_NAMES.CARBOHYDRATES.PL }}</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.PROTEINS.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.PROTEINS.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE_NAMES.PROTEINS.PL }}</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.FATS.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.FATS.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE_NAMES.FATS.PL }}</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN)">
                        <label class="form-check-label">{{ DIET_MEASURE_NAMES.GLYCEMIC_INDEX.PL }}</label>
                    </div>
                </div>

                <span class="h6">Trening</span>
                <div class="selected-data-training d-flex justify-content-start flex-wrap">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN)">
                        <label class="form-check-label mx-2">{{ TRAINING_MEASURE_NAMES.BURNED_CALORIES.PL }}</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN)">
                        <label class="form-check-label mx-2">{{ TRAINING_MEASURE_NAMES.TRAINING_LENGTH.PL }}</label>
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
            <input type="text" v-model="dateRange.start">
            <input type="text" v-model="dateRange.end">
        </div>
    </div>
</template>

<script>
import { DatePicker } from 'v-calendar';
import AnalysisChartComponent from "@/components/telemedic/analysis/AnalysisChartComponent";

const DIET_MEASURE_NAMES = {
    CALORIES: { EN: 'calories', PL: 'Kalorie'},
    CARBOHYDRATES: { EN: 'carbohydrates', PL: 'Węglowodany'},
    PROTEINS: { EN: 'proteins', PL: 'Białka'},
    FATS: { EN: 'fats', PL: 'Tłuszcze'},
    GLYCEMIC_INDEX: { EN: 'glycemic_index', PL: 'Indeks glikemiczny'},
};

const TRAINING_MEASURE_NAMES = {
    BURNED_CALORIES: { EN: 'burned_calories', PL: 'Spalone kalorie'},
    TRAINING_LENGTH: { EN: 'training_length', PL: 'Długość treningu'},
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
        dateRange: function() {
            this.getAnalysisData();
        },
    },
    data() {
        return {
            TRAINING_MEASURE_NAMES: TRAINING_MEASURE_NAMES,
            DIET_MEASURE_NAMES: DIET_MEASURE_NAMES,

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
            this.getTelemedicMeasures();
            this.getDieteticMeasures();
            this.getTrainingMeasures();
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
                        }
                        this.createArrayPropertyInAnalysisData(measureTypeId);
                        this.analysisData[measureTypeId].push(chartElement)
                    });

                })
                .catch(e => {
                    console.log(e);
                })
        },
        createArrayPropertyInAnalysisData(propertyName){
            if(!this.analysisData[propertyName]){
                this.analysisData[propertyName] = [];
            }
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
                            let y;
                            let desc = product.product.name;

                            this.pushNutritionalValues(x, y, desc, product);

                            y = product.product.glycemicIndexLevel;
                            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN);
                            this.analysisData[DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN].push({x: x, y: y, description: desc});

                        })

                        report.dishDetailsList.forEach(dish => {
                            let x = dish.consumingTime;
                            let y = "";
                            let desc = dish.dish.name;

                            this.pushNutritionalValues(x, y, desc, dish);

                            y = dish.dish.glycemicIndexLevel;
                            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN);
                            this.analysisData[DIET_MEASURE_NAMES.GLYCEMIC_INDEX.EN].push({x: x, y: y, description: desc});
                        })
                    });

                })
                .catch(e => {
                    console.log(e);
                })
        },
        pushNutritionalValues(x, y, desc, fromFood){
            y = fromFood.derivedNutritionalValues.derivedCalories;
            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.CALORIES.EN);
            this.analysisData[DIET_MEASURE_NAMES.CALORIES.EN].push({x: x, y: y, description: desc});

            y = fromFood.derivedNutritionalValues.derivedCarbohydrates;
            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.CARBOHYDRATES.EN);
            this.analysisData[DIET_MEASURE_NAMES.CARBOHYDRATES.EN].push({x: x, y: y, description: desc});

            y = fromFood.derivedNutritionalValues.derivedProteins;
            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.PROTEINS.EN);
            this.analysisData[DIET_MEASURE_NAMES.PROTEINS.EN].push({x: x, y: y, description: desc});

            y = fromFood.derivedNutritionalValues.derivedFats;
            this.createArrayPropertyInAnalysisData(DIET_MEASURE_NAMES.FATS.EN);
            this.analysisData[DIET_MEASURE_NAMES.FATS.EN].push({x: x, y: y, description: desc});
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

                            y = exercise.minutes;
                            this.createArrayPropertyInAnalysisData(TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN);
                            this.analysisData[TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN].push({x: x, y: y, description: desc});

                            y = exercise.caloriesBurned;
                            this.createArrayPropertyInAnalysisData(TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN);
                            this.analysisData[TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN].push({x: x, y: y, description: desc});
                        })

                        report.trainingList.forEach(training => {
                            let x = training.trainingTime;
                            let y = "";
                            let desc = training.training.name;

                            y = training.minutes;
                            this.createArrayPropertyInAnalysisData(TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN);
                            this.analysisData[TRAINING_MEASURE_NAMES.TRAINING_LENGTH.EN].push({x: x, y: y, description: desc});

                            y = training.caloriesBurned;
                            this.createArrayPropertyInAnalysisData(TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN);
                            this.analysisData[TRAINING_MEASURE_NAMES.BURNED_CALORIES.EN].push({x: x, y: y, description: desc});
                        })
                    });

                })
                .catch(e => {
                    console.log(e);
                })
                .then(()=>{
                        console.log(this.analysisData)
                })
        },
        isSelected(element){
            return this.selectedCheckboxes.indexOf(element) === -1
        }
    },
    created() {
        this.getMeasureTypes();
        this.getAnalysisData();

        this.series = [
            {
                name: "Tętno",
                data: [
                    {
                        x: "02-10-2017 GMT",
                        y: 70,
                        description: "Marchewka",
                    },
                    {
                        x: "02-11-2017 GMT",
                        y: 74,
                        description: "Danie 1",
                    },
                    {
                        x: "02-12-2017 GMT",
                        y: 92,
                    },
                    {
                        x: "02-13-2017 GMT",
                        y: 86,
                    },
                    {
                        x: "02-14-2017 GMT",
                        y: 80,
                    },
                    {
                        x: "02-15-2017 GMT",
                        y: 89
                    }
                ]
            },{
                name: "Poziom cukru",
                data: [
                    {
                        x: "02-10-2017 GMT",
                        y: 93
                    },
                    {
                        x: "02-11-2017 GMT",
                        y: 101
                    },
                    {
                        x: "02-12-2017 GMT",
                        y: 112
                    },
                    {
                        x: "02-13-2017 GMT",
                        y: 105
                    },
                    {
                        x: "02-15-2017 GMT",
                        y: 89
                    }
                ]
            }
        ];
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