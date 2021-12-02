<template>
    <div class="col-12 mb-2 header">
        Podsumowanie Twojej aktywności
    </div>
    <div class="row">
        <div class="col">
            <date-picker v-model="dateRange" :model-config="datePickerConfig" is-range/>
        </div>
        <div class="col">
            <button class="btn-panel-sport" @click="getUsersStatistics">
                Pobierz podsumowanie
            </button>
        </div>

    </div>
    <div v-if="noDataInRangeError" class="alert alert-warning m-3 alert-dismissible fade show"
         role="alert">
        Nie masz zarejestrowanej aktywności fizycznej w tym okresie
    </div>
    <div v-if="userStatistics != null && !noDataInRangeError">
        <div class="container my-4">
            <div class="row header">
                Od {{ this.$func_global.formatDate(userStatistics ? userStatistics.from : dateRange.start) }}
                do {{ this.$func_global.formatDate(userStatistics ? userStatistics.to : dateRange.end) }}
            </div>
            <div class="row mt-5">
                <div class="col-4 border-end border-2">
                    <label class="stat-label" for="calories">Spaliłeś</label>
                    <p id="calories" class="stat-value">{{ Math.trunc(userStatistics.userCalories) }} kcal</p>

                </div>
                <div class="col-4 border-end border-2">
                    <label class="stat-label" for="calories-average">Inni użytkownicy spalili średnio</label>
                    <p id="calories-average" class="stat-value">{{ Math.trunc(userStatistics.totalCaloriesAverage) }}
                        kcal</p>
                </div>
                <div class="col-4 justify-content-center">
                    <label class="stat-label" for="calories-percent">Spaliłeś więcej kalorii od</label>
                    <p id="calories-percent" class="stat-value justify-content-center">
                        {{ Math.trunc(userStatistics.userCaloriesBetterThanPercent * 100) }}
                        %</p>
                    <label class="stat-label" for="calories-percent">pozostałych użytkowników</label>

                </div>
            </div>

            <div class="row mt-5">
                <div class="col-4 border-end border-2">
                    <label class="stat-label" for="trainings">Trenowałeś przez</label>
                    <p id="trainings" class="stat-value">{{ Math.trunc(userStatistics.totalTrainingMinutes) }} minut</p>

                </div>
                <div class="col-4 border-end border-2">
                    <label class="stat-label" for="trainings-average">Inni użytkownicy trenowali średnio</label>
                    <p id="trainings-average" class="stat-value">
                        {{ Math.trunc(userStatistics.totalTrainingMinutesAverage) }}
                        minut</p>
                </div>
                <div class="col-4 justify-content-center">
                    <label class="stat-label" for="trainings-percent">Trenowałeś dłużej od</label>
                    <p id="trainings-percent" class="stat-value justify-content-center">
                        {{ Math.trunc(userStatistics.userTrainingMinutesBetterThanPercent * 100) }}
                        %</p>
                    <label class="stat-label" for="trainings-percent">pozostałych użytkowników</label>

                </div>
            </div>

            <div class="row mt-5 justify-content-center">
                <div v-if="userStatistics.userFavouriteTraining" class="col-5 border-end border-2">
                    <label class="stat-label" for="favourite-training">Twoim ulubionym treningiem był</label>
                    <p id="favourite-training" class="stat-value">{{ userStatistics.userFavouriteTraining.name }}</p>

                </div>
                <div v-if="userStatistics.userFavouriteTraining" class="col-5">
                    <label class="stat-label" for="favourite-training-count">Zrealizowałeś go</label>
                    <p id="favourite-training-count" class="stat-value">{{ userStatistics.userFavouriteTrainingCount }}
                        {{ userStatistics.userFavouriteTrainingCount > 1 ? 'razy' : 'raz' }} </p>
                </div>
            </div>
            <div class="row mt-5 justify-content-center">
                <div class="col-5 border-end border-2">
                    <label class="stat-label" for="days">Byłeś aktywny przez</label>
                    <p id="days" class="stat-value justify-content-center">
                        {{ userStatistics.userTrainingDays }} {{
                            userStatistics.userTrainingDays > 1 ? 'dni' : 'dzień'
                        }}</p>

                </div>
                <div class="col-5 ">
                    <label class="stat-label" for="days-average">Pozostali byli aktywni średnio przez</label>
                    <p id="days-average" class="stat-value justify-content-center">
                        {{ Math.trunc(userStatistics.avgTrainingDays) }} {{
                            userStatistics.avgTrainingDays > 1 ? 'dni' : 'dzień'
                        }}</p>

                </div>
            </div>
            <div class="row mt-5 justify-content-center">
                <p>Twoja najdłuższa seria aktywności trwała {{ userStatistics.userOverallLongestTrainingStreak }}
                    {{ userStatistics.userOverallLongestTrainingStreak > 1 ? 'dni' : 'dzień' }}</p>
                <p v-if="userStatistics.userOverallLongestTrainingStreak > 1">
                    {{ this.$func_global.formatDate(userStatistics.userOverallLongestTrainingStreakDates[0]) }} -
                    {{
                        this.$func_global.formatDate(userStatistics.userOverallLongestTrainingStreakDates[userStatistics.userOverallLongestTrainingStreakDates.length - 1])
                    }}
                </p>
            </div>

        </div>

    </div>
</template>

<script>
import moment from "moment";
import {DatePicker} from "v-calendar";

export default {
    name: "SportStatsView",
    components: {
        DatePicker,
    },
    data() {
        return {
            dateRange: {
                start: moment().startOf('month').format('YYYY-MM-DD'),
                end: moment().endOf('month').format('YYYY-MM-DD')
            },
            datePickerConfig: {
                start: {
                    timeAdjust: '00:00:00',
                },
                end: {
                    timeAdjust: '23:59:59',
                },
            },
            userStatistics: null,
            noDataInRangeError: false
        }
    },
    methods: {
        async getUsersStatistics() {
            const url = `${this.apiURL}sport/report/stats`
            const token = this.$store.getters.getToken;
            let params = {
                date_from: moment(this.dateRange.start).format('YYYY-MM-DD'),
                date_to: moment(this.dateRange.end).format('YYYY-MM-DD')
            }
            await this.axios.get(url, {
                params: params,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                this.noDataInRangeError = false
                this.userStatistics = response.data
            }).catch(error => {
                console.log(error.response);
                if (error.response.status === 409) {
                    this.noDataInRangeError = true
                }
            });
        }
    },
    mounted() {
        this.getUsersStatistics()
    }
}
</script>

<style scoped>

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}

p.stat-value {
    background: var(--SPORT);
    width: 250px;
    height: 250px;
    border-radius: 50%;
    display: inline-flex; /* or inline-flex */
    align-items: center;
    justify-content: center;
    font-weight: bold;
    font-size: 40px;
    opacity: 0.9;
}

.stat-label {
    height: 50px;
    color: white;
    font-weight: bold;
    font-size: 20px;
    margin-bottom: 10px;
}

.border-end {
    border-right-color: var(--SPORT) !important;
}
</style>
