<template>
<div class="container-fluid" >
    <div class="calculations-container">
        <div v-if="this.calculations == ''" class="alert alert-danger alert-dismissible fade show" role="alert">
                Uzupełnij swoją kartę!
        </div>
        <div v-if="this.calculations != ''">
            <hr style="margin-top: 0px;" class="title-line"/>
            <div style="align-items: flex-start; display: flex">
                <h6 class="title">SUGEROWANE SPOŻYCIE KALORII</h6>
            </div>
            <div style="align-items: center;">
                <h2>{{Math.round(this.calculations.suggestedCalories)}} kcal </h2>
                <p> BMR <span>{{Math.round(this.calculations.basicMetabolism)}} kcal <button class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#basicMetabolismModal"><font-awesome-icon :icon="['fa', 'info']"/></button></span></p>    
            </div>
            <hr class="title-line"/>
            <div style="align-items: flex-start; display: flex">
                <h6 class="title">BMI</h6>
            </div>
            <div style="align-items: center;">
                <h2 :class="{bad: this.calculations.bmiResultType != 'HEALTHY'}"> BMI <span>{{Math.round(this.calculations.bmi * 100) / 100}} kg/m<sup>2</sup> </span></h2>
                <p  :class="{bad: this.calculations.bmiResultType != 'HEALTHY'}"><span>{{this.$func_global.mapBMIResult(this.calculations.bmiResultType)}} <button class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#bmiModal"><font-awesome-icon :icon="['fa', 'info']"/></button></span></p>
            </div>
            <hr class="title-line"/>
            <div style="align-items: flex-start; display: flex">
                <h6 class="title">MAKROELEMENTY</h6>
            </div>
            <div class="row">
                <div style="align-items: center; display: flex;" class="col-sm-5">
                    <table style="color: white; text-align: start;" class="table">
                        <thead>
                            <tr>
                                <th scope="col">Makro</th>
                                <th scope="col">Gramy</th>
                                <th scope="col">Kcal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Białka</th>
                                <td>{{Math.round(this.calculations.suggestedProteins)}}</td>
                                <td>{{Math.round(this.calculations.suggestedProteins * this.$func_global.proteinCalories())}}</td>
                            </tr>
                            <tr>
                                <th scope="row">Węglowodany</th>
                                <td>{{Math.round(this.calculations.suggestedCarbohydrates)}}</td>
                                <td>{{Math.round(this.calculations.suggestedCarbohydrates * this.$func_global.carbCalories())}}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tłuszcze</th>
                                <td>{{Math.round(this.calculations.suggestedFats)}}</td>
                                <td>{{Math.round(this.calculations.suggestedFats * this.$func_global.fatCalories())}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-7">
                    <vue3-chart-js
                        style="color: white;"
                        ref="chartRef"
                        :id="doughnutChart.id"
                        :type="doughnutChart.type"
                        :data="doughnutChart.data">
                    </vue3-chart-js>
                </div>
            </div>
            <hr class="title-line"/>
            <div style="align-items: flex-start; display: flex">
                <h6 class="title">POSIŁKI</h6>
            </div>
            <div>
                <table class="table table-rounded">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Posiłek</th>
                            <th scope="col">Kalorie</th>
                            <th scope="col">Indeks glikemiczny</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="meal in this.calculations.dietCalcMealSuggestions"
                            :key="meal.id">
                            <th scope="row">{{this.$func_global.mapMeal(meal.mealType)}}</th>
                            <td>{{Math.round(meal.numberOfCalories)}}</td>
                            <td>{{this.$func_global.mapGlycemicIndex(meal.glycemicIndexLevel)}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="basicMetabolismModal" class="modal fade" tabindex="-1" aria-labelledby="basicMetabolismModalLabel" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-bmr-content">
                        <h3>Podstawowy metabolizm</h3>
                        <span style="text-align: left;">Wskaźnik podstawowej przemiany materii – Basal metabolic rate (BMR) jest miarą wydatku energii przez organizm w stanie odpoczynku. Określa on ile kalorii trzeba spożyć każdego dnia, aby utrzymać lub zmniejszyć wagę. Jest to liczba kalorii potrzebnych organizmowi do prawidłowego działania podstawowych funkcji życiowych.</span>
                    </div>
                </div>
            </div>
            <div id="bmiModal" class="modal fade" tabindex="-1" aria-labelledby="bmiModalLabel" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-bmr-content">
                        <h3>BMI</h3>
                        <span style="text-align: left;">Wskaźnik masy ciała, wskaźnik Queteleta II – współczynnik powstały przez podzielenie masy ciała podanej w kilogramach przez kwadrat wysokości podanej w metrach. Klasyfikacja wskaźnika BMI została opracowana wyłącznie dla dorosłych i nie może być stosowana u dzieci.</span>
                        <br/>
                        <table class="table table-rounded">
                            <thead>
                                <tr>
                                    <th scope="col">Wynik</th>
                                    <th scope="col">Min</th>
                                    <th scope="col">Max</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">Wychudzenie</th>
                                    <td>0</td>
                                    <td>16</td>
                                </tr>
                                <tr>
                                    <th scope="row">Niedowaga</th>
                                    <td>16</td>
                                    <td>18.5</td>
                                </tr>
                                <tr>
                                    <th scope="row">Waga w normie</th>
                                    <td>18.5</td>
                                    <td>25</td>
                                </tr>
                                <tr>
                                    <th scope="row">Nadwaga</th>
                                    <td>25</td>
                                    <td>30</td>
                                </tr>
                                <tr>
                                    <th scope="row">Otyłość</th>
                                    <td>30</td>
                                    <td>-</td>
                                </tr>
                            </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import { ref } from 'vue'
import Vue3ChartJs from '@j-t-mcc/vue3-chartjs'
export default {
    name: "CalculationsComponent",
    components: {
        Vue3ChartJs
    },
    data() {
        return {
            calculations: Object,
            loaded: false,
        }
    },
    mounted() {
        this.getProfileCalculations()
        console.log(this.calculations)
    },
    setup () {
        const chartRef = ref(null)
        const doughnutChart = {
            id: 'doughnut',
            type: 'doughnut',
            data: {
            }
        }
        const updateChart = (a, b, c) => {
            doughnutChart.data.labels = ['Białka [kcal]', 'Węglowodany[kcal]', 'Tłuszcze [kcal]']
            doughnutChart.data.datasets = [
                {
                    backgroundColor: [
                        '#41B883',
                        '#E46651',
                        '#00D8FF'
                    ],
                    data: [a, b, c]
                }
            ]
            chartRef.value.update()
        }

        return {
            doughnutChart,
            updateChart,
            chartRef,   
        }
    },
    methods: {
        getProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.calculations = data.data
                this.updateChart(Math.round(this.calculations.suggestedProteins) * this.$func_global.proteinCalories(),
                                 Math.round(this.calculations.suggestedCarbohydrates) * this.$func_global.carbCalories(),
                                 Math.round(this.calculations.suggestedFats) * this.$func_global.fatCalories())
            })
            .catch(e => alert(e))
        }
    }
}
</script>

<style scoped>
.table-rounded {
    border-radius: 20px;
    background-color: white;
    color: var(--GREY2);
    width: 100%;
}

.table tr:last-child {
    border-bottom-color: transparent;
}

.calculations-container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
}

.modal-bmr-content {
       background-color: white;
       color: black;
       width: 100%;
       height: 100%;
       border-radius: 25px;
       display: flex;
       flex-direction: column;
       align-content: flex-start;
       align-items: flex-start;
       padding: 30px;
}
.title-line {
    margin-bottom: 2px
}

.title {
    color: var(--GREY1);
    margin-top: 1px;
    margin-bottom: 20px;
    font-size: 10pt;
}

.bad {
    color: red
}

</style>