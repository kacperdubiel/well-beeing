<template>
    <div id="addLabelModal" aria-hidden="true" aria-labelledby="addLabelModalLabel" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="addLabelModalLabel" class="modal-title ms-2">Dodawanie nowej etykiety</h5>
                    <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"
                            @click="clearInputs(); clearStatusWithTimeout()"></button>
                </div>
                <div class="modal-body">
                    <div id="modal-container" class="container-fluid">
                        <div class="col-11 mx-auto form-group">
                            <div class="row">
                                <p class="form-label">Nazwa</p>
                                <input
                                    id="name-form"
                                    v-model="name"
                                    :class="{ 'has-error': submittingCreateLabel && invalidName || nameTaken}"
                                    class="register-input p-2"
                                    placeholder="Nazwa etykiety"
                                    type="text"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                        </div>
                        <div class="col-11 mt-2 mx-auto form-group">
                            <div class="row">
                                <div class="col-5 text-start px-0">
                                    <p class="form-label">Choroba</p>
                                    <select
                                        v-model="ailment"
                                        class="register-form style-chooser p-2"
                                        label="name">

                                        <option v-for="ailment in ailmentsSource" :key="ailment.id" :value="ailment">
                                            {{ ailment.name }}
                                        </option>

                                    </select>
                                </div>
                                <div class="col-5 text-start px-0">
                                    <p class="form-label">Waga połączenia</p>
                                    <input
                                        v-model="weight"
                                        :class="{ 'has-error': submittingCreateLabel && invalidWeight}"
                                        class="register-input p-2"
                                        max="1.0"
                                        min="0.0"
                                        step="0.1"
                                        type="number"
                                        @focus="clearStatus"
                                        @keypress="clearStatus">
                                </div>
                                <div class="col-2 px-0 align-self-end">
                                    <button class="btn-panel-sport" @click="addConnection(ailment, weight)">
                                        <font-awesome-icon :icon="['fa', 'plus-circle']"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div v-if="ailment_labels.length > 0" class="col-11 mt-2 mx-auto form-group ">
                            <div class="row">
                                <div class="col-4 text-start px-0">
                                    <p class="form-label ">Połączenia</p>
                                </div>
                            </div>
                            <table class=" table table-hover text-start px-0">
                                <thead>
                                <tr>
                                    <th scope="col">Nazwa</th>
                                    <th scope="col">Waga</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr
                                    v-for="connection in ailment_labels" :key="connection">
                                    <td>{{ connection.ailment.name }}</td>
                                    <td>{{ connection.weight }}</td>
                                    <td>
                                        <button class="btn-white mx-2" @click="removeConnection(connection)">
                                            <font-awesome-icon :icon="['fa', 'trash']"/>
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="row">
                                <button class="btn-panel-sport" @click="addLabel">
                                    Dodaj etykietę
                                </button>
                            </div>
                        </div>
                        <div v-if="errorCreateLabel" class="row text-end">
                            <p class="has-error m-0">
                                Proszę uzupełnić wszystkie dane poprawnie!
                            </p>
                        </div>

                        <div v-if="successCreateLabel" class="col-11 mx-auto submit-correct">
                            <p>
                                Dodano nowe ćwiczenie!
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LabelForm",
    data() {
        return {
            name: '',
            ailment: '',
            weight: 0.5,
            ailment_labels: [],
            submittingCreateLabel: false,
            errorCreateLabel: false,
            successCreateLabel: false
        }
    },
    methods: {
        clearInputs() {
            this.name = ''
            this.ailment = ''
            this.weight = 0.0
            this.ailment_labels = []
        },
        clearStatus() {
            this.successCreateLabel = false
            this.errorCreateLabel = false
        },
        addConnection(ailment, weight) {
            this.ailment_labels.push({ailment: ailment, weight: weight})
        },
        removeConnection(connection) {
            this.ailment_labels = this.ailment_labels.filter(c => c !== connection)
        },
        addLabel() {
            this.submittingCreateLabel = true
            this.clearStatus()
            if (this.invalidName || this.invalidAilments) {
                this.errorCreateLabel = true
                return
            }
            const data = {
                sportLabel: {
                    name: this.name
                },
                ailments: this.ailment_labels.map(c => ({
                    'ailmentId': c.ailment.id,
                    'weight': c.weight
                }))
            }
            const url = `${this.apiURL}sport/exercise/label`
            const token = this.$store.getters.getToken;
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successCreateLabel = true
                this.clearInputs()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateLabel = false
        }
    },
    props: {
        ailmentsSource: Array
    },
    computed: {
        invalidName() {
            return this.name === ''
        },
        invalidAilments() {
            return this.ailment_labels.length <= 0
        }
    },
}
</script>

<style scoped>
.pink-btn {
    background: var(--INTENSE-PINK);
}

.grey-btn {
    background: var(--GREY2);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

textarea.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    /*font-weight: bold;*/
}

.submit-correct {
    color: var(--TELEMEDIC);
    /*font-weight: bold;*/
}

.modal-title {
    color: var(--SPORT);
}

.form-label {
    color: var(--GREY3);
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}

.register-btn {
    color: white;
    font-weight: bold;
}
</style>
