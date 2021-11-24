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
                               :value="DIET_MEASURE_NAMES.CALORIES"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.CALORIES)">
                        <label class="form-check-label">Kalorie</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.CARBOHYDRATES"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.CARBOHYDRATES)">
                        <label class="form-check-label">Węglowodany</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.PROTEINS"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.PROTEINS)">
                        <label class="form-check-label">Białka</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.FATS"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.FATS)">
                        <label class="form-check-label">Tłuszcze</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="DIET_MEASURE_NAMES.GLYCEMIC_INDEX"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(DIET_MEASURE_NAMES.GLYCEMIC_INDEX)">
                        <label class="form-check-label">Indeks glikemiczny</label>
                    </div>
                </div>

                <span class="h6">Trening</span>
                <div class="selected-data-training d-flex justify-content-start flex-wrap">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="TRAINING_MEASURE_NAMES.BURNED_CALORIES"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(TRAINING_MEASURE_NAMES.BURNED_CALORIES)">
                        <label class="form-check-label mx-2">Spalone kalorie</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox"
                               v-model="selectedCheckboxes"
                               :value="TRAINING_MEASURE_NAMES.TRAINING_LENGTH"
                               :disabled="selectedCheckboxes.length >= maxSelected
                                            && isSelected(TRAINING_MEASURE_NAMES.TRAINING_LENGTH)">
                        <label class="form-check-label mx-2">Długość treningu</label>
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
    CALORIES: 'calories',
    CARBOHYDRATES: 'carbohydrates',
    PROTEINS: 'proteins',
    FATS: 'fats',
    GLYCEMIC_INDEX: 'glycemic-index'
};

const TRAINING_MEASURE_NAMES = {
    BURNED_CALORIES: 'burned-calories',
    TRAINING_LENGTH: 'training-length',
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

        isSelected(element){
            return this.selectedCheckboxes.indexOf(element) === -1
        }
    },
    created() {
        this.getMeasureTypes();

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