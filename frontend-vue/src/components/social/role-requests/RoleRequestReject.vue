<template>
    <div class="modal fade" id="roleRequestRejectModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="exampleModalLabel">Szczegóły prośby</h4>
                    <button type="button" class="btn-close" id="modal-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col fw-bolder">
                                Data:
                            </div>
                            <div class="col fw-bolder">
                                Rola:
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                {{ this.$func_global.formatDate(this.roleRequestSource.addedDate )}}
                            </div>
                            <div class="col">
                                {{ this.$func_global.mapRole(this.roleRequestSource.role) }}
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col fw-bolder">
                                Dokument:
                            </div>
                            <div class="col fw-bolder">
                                Status:
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <button class="no-bg" @click="handleDownloadFile(this.roleRequestSource.roleReqId)">
                                    <font-awesome-icon id="document-icon" :icon="['far', 'file-pdf']" size="2x" class="navbar-icon" />
                                </button>
                            </div>
                            <div class="col">
                                {{ this.$func_global.mapRoleRequestStatus(this.roleRequestSource.status) }}
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-12 fw-bolder">
                                Komentarz:
                            </div>
                            <div class="col-12">
                                <textarea class="form-control" id="comment-input" rows="3" v-model="comment"></textarea>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col text-end">
                                <button class="btn-dark" @click="handleReject(this.roleRequestSource.roleReqId, this.comment)">Odrzuć prośbę</button>
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
    name: "RoleRequestReject",
    data () {
        return {
            comment: ""
        }
    },
    props: {
        roleRequestSource: Object
    },
    methods: {
        handleDownloadFile(id) {
            this.$emit('download:file', id)
        },
        handleReject(id, comment) {
            this.$emit('reject:role-request', id, comment)
            document.getElementById('modal-close').click();
        }
    }
}
</script>

<style scoped>
#document-icon {
    color: var(--GREY3)
}
.modal-body {
    color: var(--GREY3);
    text-align: left;
}

.modal-header {
    color: var(--GREY3);
}
</style>