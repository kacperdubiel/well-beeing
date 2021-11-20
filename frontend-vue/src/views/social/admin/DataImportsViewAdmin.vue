<template>
    <div style="text-align: start;" class="container">
        <hr/>
        <div class="container my-5">
            <h3> Produkty </h3>
            <div>
                <input
                    class="form-control"
                    type="file"
                    ref="productsFile"
                    id="productsFormFile"
                    accept="application/csv"
                    @focus="clearStatusProducts"
                >
                <button @click="importProducts"> importuj </button>
            </div>
            <p v-if="this.productsLoading"> loading... </p>
            <div v-if="this.productsError" class="alert alert-danger alert-dismissible fade show" role="alert">
                Błąd importu {{this.error}}
            </div>
            <div v-if="this.productsSuccess" class="alert alert-success alert-dismissible fade show" role="alert">
                Zaimportowano produkty
            </div>
        </div>
        <hr/>
        <div class="container my-5">
            <h3> Dolegliwości </h3>
            <div>
                <input
                    class="form-control"
                    type="file"
                    ref="ailmentsFile"
                    id="ailmentsFormFile"
                    accept="application/csv"
                    @focus="clearStatusAilments"
                >
            </div>
        </div>
        <hr/>
        <div class="container my-5">
            <h3> Diety </h3>
            <div>
                <input
                    class="form-control"
                    type="file"
                    ref="dietsFile"
                    id="dietsFormFile"
                    accept="application/csv"
                    @focus="clearStatusDiets"
                >
            </div>
        </div>
        <hr/>
    </div>
</template>

<script>
export default {
    name: "DataImportsViewAdmin",
    data(){
        return {
            productsLoading: false,
            productsError: false,
            error: null,
            productsSuccess: false
        }
    },
    methods: {
        clearStatusDiets(){
            console.log("clearStatus")
        },

        clearStatusAilments(){
            console.log("clearStatus")
        },

        clearStatusProducts(){
            this.productsError = false;
            this.productsSuccess = false;
        },

        importProducts(){
            this.productsLoading = true;
            this.productsError = false;
            this.productsSuccess = false;

            this.$func_global.importDataFunc(this.$refs.productsFile, this.$store.getters.getToken, 'importProducts', null)
            .then((response) => console.log(response))
            .catch((error) => {console.log(error); this.productsLoading = false; this.error = error; this.productsError = true;})
        }
    }
}
</script>

<style scoped>

</style>