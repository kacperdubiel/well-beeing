<template>
    <div class="calculations-container">
        <div>
            <p> BMI <span>{{Math.round(this.calculations.bmi * 100) / 100}}</span></p>
            <p> BMI RESULT  <span>{{this.$func_global.mapBMIResult(this.calculations.bmiResultType)}}</span></p>
            <p> BASIC METABOLISM <span>{{Math.round(this.calculations.basicMetabolism)}} </span> </p>
            <p> CALORIES <span>{{Math.round(this.calculations.suggestedCalories)}} </span> </p>
        </div>
        <hr/>
        <br/>
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
        <br/>
        <hr/>
        <br/>
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
            doughnutChart.data.labels = ['Proteins [g]', 'Carbohydrates[g]', 'Fats [g]']
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
            var canvas = document.getElementById("doughnut");
            var ctx = canvas.getContext("2d");
            ctx.font = "30px Arial";
            ctx.fillText("2137KCAL", 10, 50);
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
                this.updateChart(Math.round(this.calculations.suggestedProteins),
                                 Math.round(this.calculations.suggestedCarbohydrates),
                                 Math.round(this.calculations.suggestedFats))
            })
            .catch(e => alert(e))
        }
    }
}
</script>

<style scoped>
.table-rounded {
    border-radius: 10px;
    background-color: whitesmoke;
    color: var(--GREY2);
    width: 100%;
}

.table tr:last-child {
    border-bottom-color: var(--GREY2);
}

.calculations-container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
}


</style>