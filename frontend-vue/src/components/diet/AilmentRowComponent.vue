<template>
    <div class="ailment-row">
        <div class="col-lg-4 ailment-name-column">
            <span>{{ailment.name}}</span>
        </div>
        <div class="col-lg-1">
            <button class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#ailmentModal" @click="sendModalData"><font-awesome-icon :icon="['fa', 'info']"/></button>
        </div>
    </div>
</template>

<script>

export default {
    name: "AilmentRowComponent",
    props: {
        ailment: {
            type: Object
        }
    },
    methods:{
        sendModalData(){
            this.$emit("show:modal", this.ailment)
        },
        updateItems() {
            {
                setTimeout(() => {
                    const sliders = document.querySelectorAll('.items');
                    let isDown = false;
                    let startX;
                    let scrollLeft;
                    console.log(sliders)
                    if(sliders != null)
                    {
                        sliders.forEach(slider => {
                            slider.addEventListener('mousedown', (e) => {
                                isDown = true;
                                slider.classList.add('active');
                                startX = e.pageX - slider.offsetLeft;
                                scrollLeft = slider.scrollLeft;
                            });
                            slider.addEventListener('mouseleave', () => {
                                isDown = false;
                                slider.classList.remove('active');
                            });
                            slider.addEventListener('mouseup', () => {
                                isDown = false;
                                slider.classList.remove('active');
                            });
                            slider.addEventListener('mousemove', (e) => {
                                if(!isDown) return;
                                e.preventDefault();
                                const x = e.pageX - slider.offsetLeft;
                                const walk = (x - startX); //scroll-fast
                                slider.scrollLeft = scrollLeft - walk;
                            });
                        })

                    }
                    else
                        console.log('EMPTY');
                }, 500)

            }
        },
    }
}
</script>

<style scoped>
    .ailment-row{
        align-items: flex-start;
        display: flex;
        margin-bottom: 3px;
    }

    .ailment-name-column{
        align-items: flex-start;
        display: flex;
        margin-bottom: 3px;
    }

</style>
