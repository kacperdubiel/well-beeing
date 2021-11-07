<template>
    <div class="section-bg">
        <div class="text-start p-3">
            <button class="no-bg">
                <router-link :to="{ name: 'MyProfileView' }" class="d-inline-flex align-items-center">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" />
                    <h3 class="ms-2 mb-0">Profil</h3>
                </router-link>

            </button>

        </div>
        <div class="accordion accordion-flush p-3" id="accordion-profile-edit">
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-mail">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-mail" aria-expanded="true" aria-controls="collapse-mail">
                        E-mail
                    </button>
                </h2>
                <div id="collapse-mail" class="accordion-collapse collapse show" aria-labelledby="heading-mail" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-password">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-password" aria-expanded="false" aria-controls="collapse-password">
                        Hasło
                    </button>
                </h2>
                <div id="collapse-password" class="accordion-collapse collapse" aria-labelledby="heading-password" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-photo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-photo" aria-expanded="false" aria-controls="collapse-photo">
                        Zdjęcie profilowe
                    </button>
                </h2>
                <div id="collapse-photo" class="accordion-collapse collapse" aria-labelledby="heading-photo" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-personal">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-personal" aria-expanded="false" aria-controls="collapse-personal">
                        Dane personalne
                    </button>
                </h2>
                <div id="collapse-personal" class="accordion-collapse collapse" aria-labelledby="heading-personal" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-tags">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-tags" aria-expanded="false" aria-controls="collapse-tags">
                        Tagi
                    </button>
                </h2>
                <div id="collapse-tags" class="accordion-collapse collapse" aria-labelledby="heading-tags" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-privacy">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-privacy" aria-expanded="false" aria-controls="collapse-privacy">
                        Ustawienia prywatności
                    </button>
                </h2>
                <div id="collapse-privacy" class="accordion-collapse collapse" aria-labelledby="heading-privacy" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: "ProfileEdit",
    data() {
        return {

        }
    },
    methods: {

    }
}
</script>

<style scoped>
.accordion-item, .accordion-button {
    background-color: var(--GREY2);
    color: white;
}

.accordion-button:not(.collapsed) {
    color: var(--DARK-YELLOW);
    font-weight: bolder;
}

.accordion-button:not(.collapsed)::after {
    background-image: url("data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23F9BA0F'><path fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/></svg>");
}

.accordion-button:focus {
    box-shadow: none;
}

.accordion-button::after {

    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23ffffff'%3e%3cpath fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/%3e%3c/svg%3e");
}
a {
    color: white;
    text-decoration: none;
}
</style>