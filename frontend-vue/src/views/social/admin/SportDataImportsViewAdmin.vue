<template>
    <div class="container" style="text-align: start;">
        <div class="row container my-3 py-3">
            <div class="col-lg-4">
                <p class="p-2">Wybierz typ importowanych danych</p>
            </div>
            <div class="col-lg-8">
                <select id="importType" v-model="this.selectedImportType" aria-label="Default select example"
                        class="form-select" @change="clearStatusAndFile">
                    <option value="EXERCISES">Ćwiczenia</option>
                    <option value="TRAININGS">Treningi</option>
                </select>
            </div>
        </div>
        <div v-if="this.selectedImportType !== ''" class="container my-5">
            <h3> {{ this.mapSelectedType() }} </h3>
            <hr/>
            <div class="row mt-5">
                <div class="col-lg-10">
                    <input
                        id="dataFormFile"
                        ref="dataFile"
                        accept=".csv"
                        class="form-control"
                        type="file"
                        @focus="clearStatus"
                    >
                </div>
                <div class="col-lg-2" style="justify-content: center; display: flex;">
                    <button class="btn btn-secondary" data-bs-target="#importModal" data-bs-toggle="modal"> Importuj
                    </button>
                </div>
            </div>
            <hr class="my-5"/>
            <p v-if="this.loading" class="m-3"> loading... </p>
            <div v-if="this.importError" class="alert alert-danger m-3 alert-dismissible fade show" role="alert">
                Import nie mógł zostać przeprowadzony, wystąpił błąd: {{ this.errorMessage }}
            </div>
            <div v-if="this.importSuccess" class="alert alert-success m-3 alert-dismissible fade show" role="alert">
                Przeprowadzono import: zaimportowano poprawnie wszystkie dane.
            </div>
            <div v-if="this.emptyFileError" class="alert alert-danger m-3 alert-dismissible fade show" role="alert">
                Wybierz plik.
            </div>
            <div v-if="this.importPartlySuccess" class="alert alert-warning m-3 alert-dismissible fade show"
                 role="alert">
                Przeprowadzono import: nie udało się zaimportować części danych:
                <p v-for="error in this.importErrorMessages" :key="error">
                    {{ error }}
                </p>
            </div>
        </div>

    </div>
    <div id="importModal" aria-hidden="false" aria-labelledby="importModalLabel" class="modal fade"
         data-bs-backdrop="static"
         data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="importModalLabel" class="modal-title" style="color: black;">Importuj</h5>
                    <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                </div>
                <div class="modal-body">
                    <p style="color: black;">Czy na pewno chcesz zaiportować dane?</p>
                    <div>
                        <button class="btn btn-success" data-bs-dismiss="modal" style="margin: 2px;" type="button"
                                @click="this.import">TAK
                        </button>
                        <button class="btn btn-danger" data-bs-dismiss="modal" style="margin: 2px;" type="button">NIE
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SportDataImportsViewAdmin",
    data() {
        return {
            loading: false,
            importError: false,
            importSuccess: false,
            importPartlySuccess: false,
            importErrorMessages: [],
            errorMessage: '',
            emptyFileError: false,
            selectedImportType: 'EXERCISES'
        }
    },
    methods: {
        clearStatus() {
            this.loading = false
            this.importError = false
            this.importPartlySuccess = false
            this.importSuccess = false
            this.importErrorMessages = []
            this.errorMessage = ''
            this.emptyFileError = false
        },
        clearStatusAndFile() {
            this.clearStatus()
            document.getElementById('dataFormFile').value = null;
        },
        mapSelectedType() {
            if (this.selectedImportType === 'EXERCISES')
                return 'Ćwiczenia'
            if (this.selectedImportType === 'TRAININGS')
                return 'Treningi'
            if (this.selectedImportType === 'DIETS')
                return 'Diety'
        },
        import() {
            this.clearStatus()
            this.loading = true

            let type;
            if (this.selectedImportType === 'EXERCISES')
                type = 'importExercises'
            if (this.selectedImportType === 'TRAININGS')
                type = 'importTrainings'

            if (this.$refs.dataFile.files.length == 0) {
                this.emptyFileError = true;
                this.loading = false
                return
            }
            this.$func_global.importSportDataFunc(this.$refs.dataFile, this.$store.getters.getToken, type, null)
                .then(
                    (response) => {
                        console.log(response)
                        this.loading = false
                        this.importSuccess = true
                    }
                )
                .catch((error) => {
                    console.log(error);
                    this.loading = false;
                    this.importError = true;
                    this.errorMessage = error.message
                })
        }
    }
}
</script>

<style scoped>

</style>
