<template>
    <div class="section-bg">
        <div class="row justify-content-center w-100">
            <div class="col-11">
                <div class="row my-3">
                    <div class="col-md-3 col-sm-6 text-start">
                        <label for="sport-tag-select" class="form-label">Tag sportowy:</label>
                        <select
                            v-model="filters.sportTagFilter"
                            class="p-2 w-100"
                            id="sport-tag-select"
                            @change="getProfilesFiltered()"
                        >
                            <option v-for="sportTag in filters.allSportTagFilters" :key="sportTag.label" :value="sportTag.value">{{ sportTag.label }}</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-6 text-start">
                        <label for="nutrition-tag-select" class="form-label">Tag żywieniowy:</label>
                        <select
                            v-model="filters.nutritionTagFilter"
                            class="p-2 w-100"
                            id="nutrition-tag-select"
                            @change="getProfilesFiltered()"
                        >
                            <option v-for="nutTag in filters.allNutritionTagFilters" :key="nutTag.label" :value="nutTag.value">{{ nutTag.label }}</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-6 text-start ms-auto">
                        <label for="sort-select" class="form-label">Sortowanie:</label>
                        <select
                            v-model="filters.sortBy"
                            class="p-2 w-100"
                            id="sort-select"
                            @change="getProfilesFiltered()"
                        >
                            <option v-for="sort in filters.sortByOptions" :key="sort.label" :value="sort.value">{{ sort.label }}</option>
                        </select>
                    </div>
                </div>
                <div class="row mt-5" v-if="this.$store.getters.getSearchProfileResult.content.length !== 0">
                    <h5 class="text-start">Wyniki wyszukiwania:</h5>
                    <div v-for="profile in this.$store.getters.getSearchProfileResult.content" :key="profile.id" >
                        <profile-on-list-item :profile-source="profile" class="my-2 ms-1" />
<!--                        @redirect:profile="redirectToProfile"-->
                    </div>
                </div>
                <div class="row mt-5" v-else>
                    <p>Brak wyników wyszukiwania</p>
                </div>

            </div>

        </div>
    </div>
</template>

<script>
import ProfileOnListItem from "@/components/social/ProfileOnListItem";
export default {
    name: "SearchProfilesView",
    components: {
        ProfileOnListItem
    },
    data () {
        return {
            filters: {
                allNutritionTagFilters: [
                    {label:'-', value:''},
                    {label:'Dieta wegetariańska', value:'VEGETARIAN'},
                    {label:'Dieta wegańska', value:'VEGAN'},
                    {label:'Dieta bezglutenowa', value:'GLUTEN_FREE'}
                ],
                nutritionTagFilter: '',
                allSportTagFilters: [
                    {label:'-', value:''},
                    {label:'Trening siłowy', value:'WEIGHT_TRAINING'},
                    {label:'Kardio', value:'CARDIO'},
                    {label:'Pilates', value:'PILATES'},
                    {label:'Joga', value:'YOGA'}
                ],
                sportTagFilter: '',
                sortByOptions: [
                    {label:'A-Z', value:'fullName,asc'},
                    {label:'Z-A', value:'fullName,desc'}],
                sortBy: 'fullName,asc'
            },
        }
    },
    methods: {
        getResults() {
            console.log(this.$store.getters.getSearchProfileResult)
        },
        getProfilesFiltered () {
            const url = `${this.apiURL}profile`
            const token = this.$store.getters.getToken;
            const myParams = {
                fullName: this.$store.getters.getSearchPhrase,
                sort: this.filters.sortBy,
                eSportTag: this.filters.sportTagFilter,
                eNutritionTag: this.filters.nutritionTagFilter
            }
            this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.$store.commit('setSearchProfileResult', response.data);

            }).catch(error => {
                console.log(error.response.status)
            });
        }
    },
    beforeRouteLeave (to, from, next) {
        this.$store.commit('setSearchPhrase', "")
        next()
    },
    mounted() {
        this.getResults()
    },

}
</script>

<style scoped>

</style>