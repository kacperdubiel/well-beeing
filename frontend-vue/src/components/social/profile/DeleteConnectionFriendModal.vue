<template>
    <div class="modal fade" id="deleteFriendConnectionModal" tabindex="-1" aria-labelledby="deleteFriendConnectionModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="deleteFriendConnectionModalLabel">
                        <span v-if="deleteType === 'friend'">Usuń znajomego</span>
                        <span v-else-if="deleteType === 'invitationSent'">Anuluj zaproszenie</span>
                        <span v-else-if="deleteType === 'invitationReceived'">Odrzuć zaproszenie</span>
                    </h5>

                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="col mx-auto">
                            <div class="row">
                                <span v-if="deleteType === 'friend'">Czy na pewno chcesz usunąć znajomego?</span>
                                <span v-else-if="deleteType === 'invitationSent'">Czy na pewno chcesz anulować zaproszenie?</span>
                                <span v-else-if="deleteType === 'invitationReceived'">Czy na pewno chcesz odrzucić zaproszenie?</span>
                            </div>

                            <div class="row justify-content-end mt-3">
                                <div class="col-3">
                                    <button class="btn-panel- p-2" data-bs-dismiss="modal">Powrót</button>
                                </div>
                                <div class="col-3">
                                    <button
                                            class="btn-panel-social p-2"
                                            data-bs-dismiss="modal"
                                            @click="handleDelete">
                                        <span>Potwierdź</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "DeleteConnectionFriendModal",
    props: {
        deleteType: String
    },
    methods: {
        handleDelete() {
            this.$emit('delete:invitation')
        }
    }
}
</script>

<style scoped>
.modal-body {
    color: var(--GREY3) !important;
    text-align: left !important;
}

.modal-header {
    color: var(--GREY3) !important;
}
</style>