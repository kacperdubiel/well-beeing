<template>
    <div class="modal fade" id="likesListModal" tabindex="-1" aria-labelledby="likesListModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="likesListModalLabel">Polubienia</h4>
                    <button type="button" class="btn-close" id="likes-modal-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid p-0">
                        <div v-for="like in likesSource" :key="like.liker.id">
                            <profile-on-list-item :profile-source="like.liker" class="my-2 ms-1" @redirect:profile="redirectToProfile"/>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ProfileOnListItem from "@/components/social/ProfileOnListItem";
export default {
    name: "LikesList",
    props: {
        likesSource: Array,
    },
    components: {
        ProfileOnListItem
    },
    methods: {
        redirectToProfile(id) {
            document.getElementById('likes-modal-close').click();
            console.log('prifileIDDDDD', id)
            this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        }
    }
}
</script>

<style scoped>
.modal-body {
    color: var(--GREY3);
    text-align: left;
}

.modal-header {
    color: var(--GREY3);
}

</style>