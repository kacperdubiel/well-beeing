<template>
    <div class="modal fade" id="acceptFriendModal" tabindex="-1" aria-labelledby="acceptFriendModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="acceptFriendModalLabel">
                        <span>Zaakceptuj zaproszenie</span>
                    </h5>

                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="col mx-auto">
                            <div class="row">
                                <span>Czy na pewno chcesz zaakceptować zaproszenie do znajomych?</span>
                            </div>

                            <div class="row justify-content-end mt-3">
                                <div class="col-3">
                                    <button class="btn-panel- p-2" data-bs-dismiss="modal">Powrót</button>
                                </div>
                                <div class="col-3">
                                    <button
                                        class="btn-panel-social p-2"
                                        data-bs-dismiss="modal"
                                        @click="handleAccept">
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
    name: "AcceptFriendModal",
    methods: {
        handleAccept() {
            this.$emit('accept:invitation')
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