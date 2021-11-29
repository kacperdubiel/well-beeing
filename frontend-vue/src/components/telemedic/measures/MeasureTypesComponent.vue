<template>
    <div>
        <div class="container">
            <div class="row justify-content-between">
                <div class="pt-1 align-left">
                    <button class="btn-panel-telemedic" data-bs-toggle="modal" data-bs-target="#addMeasureTypeModal">
                        Dodaj typ pomiaru
                    </button>
                </div>
            </div>

            <!-- Modal - AddMeasureType -->
            <div class="modal fade" id="addMeasureTypeModal" tabindex="-1" aria-labelledby="addMeasureTypeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="addMeasureTypeModalLabel"> Dodaj typ pomiaru </h5>
                            <button id="close-add-modal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="input-group mb-2">
                                        <input
                                            type="text"
                                            v-model="measureType.name"
                                            class="form-control"
                                            placeholder="Nazwa"
                                        />
                                    </div>
                                    <div class="input-group mb-2">
                                        <input
                                            type="text"
                                            v-model="measureType.unit"
                                            class="form-control"
                                            placeholder="Jednostka"
                                        />
                                    </div>
                                    <div class="align-left">Minimalna wartość pomiaru</div>
                                    <div class="input-group mb-2">
                                        <input
                                            type="number"
                                            v-model="measureType.minValue"
                                            class="form-control"
                                        />
                                    </div>
                                    <div class="align-left">Maksymalna wartość pomiaru</div>
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureType.maxValue"
                                            class="form-control"
                                        />
                                    </div>
                                    <div class="align-left">Precyzja pomiaru</div>
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureType.precision"
                                            class="form-control"
                                        />
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="clearInputs">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" @click="addMeasureType">Dodaj</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - EditMeasureType -->
            <div class="modal fade" id="editMeasureTypeModal" tabindex="-1" aria-labelledby="editMeasureTypeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="editMeasureTypeModalLabel"> Edytuj typ pomiaru </h5>
                            <button id="close-edit-modal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid" id="modal-container">
                                <div class="col-11 mx-auto">
                                    <div class="input-group mb-2">
                                        <input
                                            type="text"
                                            v-model="measureType.name"
                                            class="form-control"
                                            placeholder="Nazwa"
                                        />
                                    </div>
                                    <div class="input-group mb-2">
                                        <input
                                            type="text"
                                            v-model="measureType.unit"
                                            class="form-control"
                                            placeholder="Jednostka"
                                        />
                                    </div>
                                    <div class="align-left">Minimalna wartość pomiaru</div>
                                    <div class="input-group mb-2">
                                        <input
                                            type="number"
                                            v-model="measureType.minValue"
                                            class="form-control"
                                        />
                                    </div>
                                    <div class="align-left">Maksymalna wartość pomiaru</div>
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureType.maxValue"
                                            class="form-control"
                                        />
                                    </div>
                                    <div class="align-left">Precyzja pomiaru</div>
                                    <div class="input-group">
                                        <input
                                            type="number"
                                            v-model="measureType.precision"
                                            class="form-control"
                                        />
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="clearInputs">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" @click="editMeasureType">Zmień</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal - DeleteMeasureType -->
            <div class="modal fade" id="deleteMeasureTypeModal" tabindex="-1" aria-labelledby="deleteMeasureTypeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="deleteMeasureTypeModalLabel"> Usuń typ pomiaru </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        Czy na pewno chcesz usunąć ten typ pomiaru?
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal" @click="deleteMeasureType">Usuń</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <table class="table measures-table">
                        <thead>
                            <tr>
                                <th scope="col">Nazwa</th>
                                <th scope="col">Jednostka</th>
                                <th scope="col" class="w-15">Minimalna wartość</th>
                                <th scope="col" class="w-15">Maksymalna wartość</th>
                                <th scope="col" class="w-15">Precyzja</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="mType in measureTypes" v-bind:key="mType.id">
                                <td>{{ mType.name }}</td>
                                <td>{{ mType.unit }}</td>
                                <td>{{ mType.minValue }}</td>
                                <td>{{ mType.maxValue }}</td>
                                <td>{{ mType.precision }}</td>
                                <td class="align-right">
                                    <button class="btn-white m-r-5 btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#editMeasureTypeModal"
                                            @click="selectMeasureType(mType)">
                                        <font-awesome-icon :icon="['fa', 'pen']" />
                                    </button>
                                    <button class="btn-white btn-hover"
                                            data-bs-toggle="modal" data-bs-target="#deleteMeasureTypeModal"
                                            @click="selectMeasureType(mType)">
                                        <font-awesome-icon :icon="['fa', 'trash']" />
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div v-if="measureTypes && measureTypes.length === 0" class="row mb-2">
                        Brak wpisów.
                    </div>
                </div>
            </div>

            <!-- Modal - Info -->
            <div class="modal fade" id="infoModal" tabindex="-1" aria-labelledby="infoModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="infoModalLabel"> Błąd </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="col-11 mx-auto">
                                    <div class="row">
                                        Typ pomiaru nie został usunięty. Prawdopodobnie istnieją pomiary o tym typie.
                                    </div>

                                    <div class="row justify-content-end mt-3">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-3">
                                            <button class="btn-panel-telemedic p-2" data-bs-dismiss="modal">Potwierdź</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="modal-opener" data-bs-toggle="modal" data-bs-target="#infoModal"></div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'MeasureTypesComponent',
    data() {
        return {
            measureTypes: {},
            selectedMeasureType: null,

            measureType: {
                name: "",
                unit: "",
                minValue: 0.0,
                maxValue: 100.0,
                precision: 0,
            }
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
        selectMeasureType(mType){
            this.selectedMeasureType = mType;
            this.measureType.name = mType.name;
            this.measureType.unit = mType.unit;
            this.measureType.minValue = mType.minValue;
            this.measureType.maxValue = mType.maxValue;
            this.measureType.precision = mType.precision;
        },
        addMeasureType() {
            const data = {
                "name": this.measureType.name,
                "unit": this.measureType.unit,
                "minValue": this.measureType.minValue,
                "maxValue": this.measureType.maxValue,
                "precision": this.measureType.precision,
            }

            this.axios({
                method: 'post',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measure-types`,
                data: data
            })
                .then(() => {
                    this.getMeasureTypes();
                    this.clearInputs();
                    this.closeAddModal();
                }).catch(e => {
                    console.log(e);
                })
        },
        editMeasureType() {
            const data = {
                "id": this.selectedMeasureType.id,
                "name": this.measureType.name,
                "unit": this.measureType.unit,
                "minValue": this.measureType.minValue,
                "maxValue": this.measureType.maxValue,
                "precision": this.measureType.precision,
            }

            this.axios({
                method: 'put',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measure-types`,
                data: data
            })
                .then(() => {
                    this.getMeasureTypes();
                    this.clearInputs();
                    this.closeEditModal();
                }).catch(e => {
                    console.log(e);
                })
        },
        deleteMeasureType(){
            this.axios({
                method: 'delete',
                headers: { Authorization: 'Bearer ' + this.$store.getters.getToken },
                url: `${this.apiURL}measure-types/${this.selectedMeasureType.id}`,
            })
                .then(() => {
                    this.getMeasureTypes();
                    this.clearInputs();
                }).catch(e => {
                    document.getElementById('modal-opener').click();
                    console.log(e);
                })
        },
        closeAddModal() {
            document.getElementById('close-add-modal').click();
        },
        closeEditModal() {
            document.getElementById('close-edit-modal').click();
        },
        clearInputs(){
            this.measureType.name = "";
            this.measureType.unit = "";
            this.measureType.minValue = 0.0;
            this.measureType.maxValue = 100.0;
            this.measureType.precision = 0;
        }
    },
    created(){
        this.getMeasureTypes();
    },
}
</script>

<style scoped>
    .m-r-5 {
        margin-right: 5px
    }
    .w-15 {
        width: 15%;
    }

    .btn-panel-telemedic{
        font-size: medium;
    }

    .telemedic-icon {
        color: white;
        cursor: pointer;
    }

    .telemedic-icon:hover {
        color: var(--TELEMEDIC);
    }

    .btn-hover:hover {
        color: var(--GREY1);
    }

    .modal-header, .modal-body {
        color: black;
    }

    .modal-dialog-centered {
        min-height: calc(60% - 3.5rem);
    }

    .measures-table {
        color: white;
        margin-top: 20px;
    }

    .measures-table tbody tr:hover {
        background-color: var(--TELEMEDIC);
    }

</style>