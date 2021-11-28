<template>
    <div>
        <div class="d-flex">
            <div>
                <date-picker v-model="dateRange" :model-config="datePickerConfig" is-range />
            </div>
            <div class="selected-data align-left">
                <span class="h6">Zdrowie:</span>
                <div class="selected-data-telemedic d-flex justify-content-start flex-wrap mb-2">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Tętno</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Temperatura</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Poziom cukru</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Ciśnienie rozkurczowe</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Ciśnienie skurczowe</label>
                    </div>
                </div>

                <span class="h6">Dieta</span>
                <div class="selected-data-telemedic d-flex justify-content-start flex-wrap mb-2">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Kalorie</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Węglowodany</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Białka</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Tłuszcze</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label">Indeks glikemiczny</label>
                    </div>
                </div>

                <span class="h6">Trening</span>
                <div class="selected-data-telemedic d-flex justify-content-start flex-wrap">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
                        <label class="form-check-label mx-2">Spalone kalorie</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox">
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
            series: [],
            hideYAxis: false,
        }
    },
    methods: {

    },
    created() {
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


</style>