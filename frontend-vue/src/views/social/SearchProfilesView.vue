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
                            @change="getProfilesFiltered(0, false)"
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
                            @change="getProfilesFiltered(0, false)"
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
                            @change="getProfilesFiltered(0, false)"
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
            navigation: {
                totalElements: 0,
                totalPages: 0,
                isFirst: false,
                isLast: false,
                isEmpty: false,
                currentPage: 0,
                pageSize: 20
            },
            scrolledToBottom: true,
            loaded: false
        }
    },
    methods: {
        getResults() {
            console.log(this.$store.getters.getSearchProfileResult)
            this.navigation.totalElements = this.$store.getters.getSearchProfileResult.totalElements
            this.navigation.totalPages = this.$store.getters.getSearchProfileResult.totalPages
            this.navigation.isFirst = this.$store.getters.getSearchProfileResult.first
            this.navigation.isLast = this.$store.getters.getSearchProfileResult.last
            this.navigation.isEmpty = this.$store.getters.getSearchProfileResult.empty
            this.navigation.currentPage = this.$store.getters.getSearchProfileResult.number
            this.navigation.pageSize = this.$store.getters.getSearchProfileResult.size
        },
        getProfilesFiltered (page, isScroll) {
            const url = `${this.apiURL}profile`
            const token = this.$store.getters.getToken;
            const myParams = {
                fullName: this.$store.getters.getSearchPhrase,
                page: page,
                sort: this.filters.sortBy,
                eSportTag: this.filters.sportTagFilter,
                eNutritionTag: this.filters.nutritionTagFilter
            }

            if(this.navigation.isLast && isScroll)
                return 'sth'

            if(page === 0)
                this.scrolledToBottom = true

            this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                if(!this.navigation.isLast && isScroll)
                    this.$store.commit('addSearchProfileResult', response.data['content']);
                else if (!isScroll)
                    this.$store.commit('setSearchProfileResult', response.data);

                this.loaded = true
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']

                return 'sth'
            })
        },
        scroll () {
            window.onscroll = () => {
                let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) +
                    window.innerHeight + 5 >= document.documentElement.offsetHeight
                if (bottomOfWindow) {
                    if (!this.navigation.isLast && this.scrolledToBottom && this.loaded) {
                        this.scrolledToBottom = false
                        this.getProfilesFiltered(this.navigation.currentPage+1, true).then((response) => {
                            setTimeout(() => {
                                this.scrolledToBottom = true
                            }, 300)
                            console.log(response)
                        }, error => {
                            console.log(error)
                        })
                    }
                }
            }
        }
    },
    beforeRouteLeave (to, from, next) {
        this.$store.commit('setSearchPhrase', "")
        next()
    },
    mounted() {
        this.getResults()
        this.loaded = true
        this.scroll()
    },

}
</script>

<style scoped>

</style>