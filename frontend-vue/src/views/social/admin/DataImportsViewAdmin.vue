<template>
    <div style="text-align: start;" class="container">
        <div class="row container my-3 py-3">
            <div class="col-lg-4">
                <p class="p-2">Wybierz typ importowanych danych</p>
            </div>
            <div class="col-lg-8">
                <select @change="clearStatus" class="form-select" id="importType" v-model="this.selectedImportType" aria-label="Default select example">
                        <option value="PRODUCTS">Produkty</option>
                        <option value="AILMENTS">Dolegliwości/stan fizyczne</option>
                        <option value="DIETS">Diety</option>
                </select>
            </div>
        </div>
        <div v-if="this.selectedImportType !== ''" class="container my-5">
            <h3> {{this.mapSelectedType()}} </h3>
            <hr/>
            <div class="row mt-5">
                <div class="col-lg-10">
                    <input
                        class="form-control"
                        type="file"
                        ref="dataFile"
                        id="dataFormFile"
                        accept=".csv"
                        @focus="clearStatus"
                    >
                </div>
                <div style="justify-content: center; display: flex;" class="col-lg-2">
                    <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#importModal"> Importuj </button>
                </div>
            </div>
            <hr class="my-5"/>
            <p class="m-3" v-if="this.loading"> loading... </p>
            <div v-if="this.importError" class="alert alert-danger m-3 alert-dismissible fade show" role="alert">
                Import nie mógł zostać przeprowadzony, wystąpił błąd: {{this.errorMessage}}
            </div>
            <div v-if="this.importSuccess" class="alert alert-success m-3 alert-dismissible fade show" role="alert">
                Przeprowadzono import: zaimportowano poprawnie wszystkie produkty.
            </div>
            <div v-if="this.importPartlySuccess" class="alert alert-warning m-3 alert-dismissible fade show" role="alert">
                Przeprowadzono import: nie udało się zaimportować części produktów: 
                <p v-for="error in this.importErrorMessages" :key="error">
                    {{error}}
                </p>
            </div>
        </div>
        
    </div>
    <div id="importModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="importModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="importModalLabel">Importuj</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p style="color: black;">Czy na pewno chcesz zaiportować dane?</p>
                        <div>
                            <button @click="this.import" style="margin: 2px;" type="button" class="btn btn-success" data-bs-dismiss="modal">TAK</button>
                            <button style="margin: 2px;" type="button" class="btn btn-danger" data-bs-dismiss="modal">NIE</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
export default {
    name: "DataImportsViewAdmin",
    data(){
        return {
            loading: false,
            importError: false,
            importSuccess: false,
            importPartlySuccess: false,
            importErrorMessages: [],
            errorMessage: '',
            selectedImportType: 'PRODUCTS'
        }
    },
    methods: {
        clearStatus(){
            this.loading= false
            this.importError= false
            this.importPartlySuccess = false
            this.importSuccess= false
            this.importErrorMessages= []
            this.errorMessage= ''
        },
        mapSelectedType(){
            if(this.selectedImportType === 'PRODUCTS')
                return 'Produkty'
            if(this.selectedImportType === 'AILMENTS')
                return 'Dolegliwości'
            if(this.selectedImportType === 'DIETS')
                return 'Diety'
        },
        import(){
            this.clearStatus()
            this.loading = true

            let type;
            if(this.selectedImportType === 'PRODUCTS')
                type = 'importProducts'
            if(this.selectedImportType === 'AILMENTS')
                type = 'importAilments'
            if(this.selectedImportType === 'DIETS')
                type = 'importDiets'

            this.$func_global.importDataFunc(this.$refs.dataFile, this.$store.getters.getToken, type, null)
            .then(
                (response) => {
                    console.log(response)
                    this.loading = false
                    this.importErrorMessages = response.data.errorMessages
                    if(this.importErrorMessages.length == 0)
                        this.importSuccess = true
                    else
                        this.importPartlySuccess = true
                    }
                )
            .catch((error) => {
                console.log(error);
                this.loading = false;
                this.importError = true;
                this.errorMessage = error.message})
        }
    }
}
</script>

<style scoped>

</style>