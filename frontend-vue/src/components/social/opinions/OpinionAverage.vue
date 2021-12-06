<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 py-3">
            <div class="d-flex flex-column text-start">
                <div class="text-start d-flex align-items-baseline">

                    <h3>
                        <font-awesome-icon :icon="['fa', 'star']" id="star"/>
                        {{average}}/5
                    </h3>
                    <span class="ms-2 fw-bolder">na podstawie {{opinionsNumber}} opinii</span>
                </div>

            </div>
        </div>

    </div>
</template>

<script>
export default {
    name: "OpinionAverage",
    props: {
        average: Number,
        opinionsNumber: Number
    }
}
</script>

<style scoped>
#star {
    color: var(--DARK-YELLOW);
}

span {
    font-size: 18px;
}
</style>