<template>

<div>
    <navigation-bar/>
    <b-row class="m-2">
        <b-col><h3>Detalle del Curso</h3></b-col>
    </b-row>

    <div>
        <b-table stacked :items="displayCurso"></b-table>
    </div>
    <div align="center">
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="primary" @click="goToHome()">Volver</b-button>
        <b-button style="m-3 width: 200px;"  variant="primary" @click="showModal()">Estoy interesado</b-button>
    </div>

    <b-modal
      id="modal-prevent"
      ref="confirmacion-modal"
      title="Si está de acuerdo, confirme su email"
      @ok="handleConfirmModal"
    >
      <form @submit.stop.prevent="handleSubmit">
        <b-form-input v-model="mailAConfirmar" placeholder="Ingrese email"></b-form-input>
      </form>
    </b-modal>

</div>

</template>

<script>

import NavigationBar from '@/components/NavigationBar.vue'

export default {
    name: 'DetalleCurso',
    mounted() {
        this.cursoSeleccionado = this.$store.getters.cursoSeleccionado
        this.displayCurso.push(this.cursoSeleccionado)
    },
    data() {
        return {
            mailAConfirmar: "",
            cursoSeleccionado: { },

            displayCurso: [
            ]
        }
    },
    methods: {
        handleConfirmModal() {
            console.log("confirm")
            
            this.$nextTick(() => {

                this.hideModal()
                this.goToHome()

            // Wrapped in $nextTick to ensure DOM is rendered before closing
            })
        },
        showModal() {
            this.$refs['confirmacion-modal'].show()
        },
        hideModal() {
            this.$refs['confirmacion-modal'].hide()
        },        
        goToHome() {
            this.$router.push("/home");
        }
    },
    components: {
        NavigationBar
    }     
}

</script>

<style scoped>

</style>
