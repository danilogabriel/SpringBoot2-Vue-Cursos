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
/*         this.cursoSeleccionado = {
            "titulo": this.$store.getters.cursoSeleccionado
        }
 */        this.displayCurso.push(this.$store.getters.cursoSeleccionadoReducido)
    },
    data() {
        return {
            displayCurso: [ ],

            mailAConfirmar: "",

 /*            displayCurso: [
                { 
                    "titulo": cursoSeleccionado.titulo,
                    "tipo": cursoSeleccionado.tipo,
                    "categoria": cursoSeleccionado.categoria,
                    "descripcion": cursoSeleccionado.descripcion,
                    "contenidos": cursoSeleccionado.contenidos,
                    "instructores": cursoSeleccionado.instructores,
                    "perfilAlumno": cursoSeleccionado.perfilAlumno,
                    "cantHoras": cursoSeleccionado.cantHoras,
                    "maxAlumnos": cursoSeleccionado.maxAlumnos,
                    "publicado": cursoSeleccionado.publicado
                }
            ] */
        }
    },
    computed: {

    },
    methods: {
        async handleConfirmModal() {
            if (this.mailAConfirmar==this.$store.state.user.username+'@afip.gob.ar'){
                try {
                    var response = await this.$http({ 
                                                method: "POST", 
                                                data: { 
                                                    legajo: this.$store.state.user.legajo,
                                                    idcurso: this.$store.state.cursoSeleccionado.id
                                                }, 
                                                "url": "api/inscripcion"})
                    this.hideModal()
                    this.$bvToast.toast('Se ha registrado tu interés en el curso. Te contactaremos por email.', {
                    title: 'Advertencia',
                    variant: 'primary',
                    toaster: 'b-toaster-top-center',
                    solid: true
                    })                 
                    setTimeout( () => this.$router.push({ path: '/home'}), 5000);
                } catch (error) {
                    console.log(error)
                }
            } else {
                this.$bvToast.toast('El email ingresado no coincide con su email registrado.No se ha confirmado la operación', {
                  title: 'Advertencia',
                  variant: 'danger',
                  toaster: 'b-toaster-top-center',
                  solid: true
                })   

            }
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
