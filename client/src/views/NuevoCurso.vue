<template>

<div >
    <navigation-bar/>

    <h3 class="m-2">Nuevo Curso</h3>

    <div class="container">
        <b-col class="mw-6">           
              <b-input-group prepend="Titulo" class="mt-3">
                <b-form-input v-model="curso.titulo"></b-form-input>
              </b-input-group>

              <b-input-group prepend="Tipo  de Capacitación" class="mt-3">
                <b-form-select v-model="curso.tipo" :options="tipoCursoOptions"></b-form-select>
              </b-input-group>

              <b-input-group prepend="Categoría" class="mt-3">
                <b-form-select v-model="curso.categoria" :options="categoriaOptions"></b-form-select>
              </b-input-group>

              <b-input-group prepend="Descripcion" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.descripcion"></b-form-textarea>
              </b-input-group>

              <b-input-group prepend="Contenidos" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.contenidos"></b-form-textarea>
              </b-input-group>

              <b-input-group prepend="Instructores" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.instructores"></b-form-textarea>
              </b-input-group>

              <b-input-group prepend="Peril alumno" class="mt-3">
                <b-form-select v-model="perfilSelected" :options="perfilOptions"></b-form-select>
              </b-input-group>

              <b-input-group prepend="Cant. Horas" class="mt-3">
                <b-form-input v-model="curso.cantHoras"></b-form-input>
              </b-input-group>

               <div class="text-danger font-weight-bold">{{ validation.firstError('curso.cantHoras') }}</div>

              <b-input-group prepend="Max. Asistentes" class="mt-3">
                <b-form-input v-model="curso.maxAlumnos"></b-form-input>
              </b-input-group>            

        </b-col>
    </div>

    <div align="center">
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="primary" @click="guardarTemporal()">Guardado Temporal</b-button>
        <b-button style="m-3 width: 200px;"  variant="primary" @click="showModal()">Publicar</b-button>
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="danger" @click="showModal()">Eliminar</b-button>
        <b-button style="m-3 width: 200px;"  variant="primary" @click="goToHome()">Volver</b-button>
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
import {Validator} from 'simple-vue-validator'

export default {
    name: 'DetalleCurso',
    mounted() {
        this.cursoSeleccionado = this.$store.getters.cursoSeleccionado
    },
    data() {
        return {
            curso: {
                "titulo": "",
                "tipo": "",
                "categoria": "",
                "descripcion": "",
                "contenidos": "",
                "instructores": "",
                "perfilAlumno": "",
                "cantHoras": null,
                "maxAlumnos": null,
                "publicado": false
            },
            tipoCursoOptions: [
                { value: null, text: 'Seleccione tipo de Curso' },
                { value: 'Curso', text: 'Curso' },
                { value: 'Charla', text: 'Charla' },
                { value: 'Taller', text: 'Taller' },
                { value: 'Foro', text: 'Foro de Discución'}
            ],
            tipoCursoSelected: null,

            categoriaOptions: [
                { value: null, text: 'Seleccione categoría' },
                { value: 'Desarrollo', text: 'Desarrollo' },
                { value: 'Databases', text: 'Databases' },
                { value: 'Testing', text: 'Testing' },
                { value: 'Operaciones', text: 'Operaciones'}
            ],
            categoriaSelected: null,

            perfilOptions: [
                { value: null, text: 'Seleccione perfil' },
                { value: 'DEV', text: 'DEV' },
                { value: 'QA', text: 'QA' },
                { value: 'FUNC', text: 'FUNC' },
                { value: 'VARIOS', text: 'Varios' },
            ],
            perfilSelected: null,

            mailAConfirmar: "",
            cursoSeleccionado: { },

        }
    },
    validators: {
      'curso.cantHoras' : function (value) {
        return Validator.value(value).required().integer('Debe ser un valor numérico entero');
      }
    },    
    methods: {
        guardarTemporal() {
				this.$store.dispatch('createCurso', this.curso)
												.then(() => this.$router.push('/'))
												.catch(err => console.log(err))
		},
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
            this.$router.push("/")
        }
    },
    components: {
        NavigationBar
    }     
}

</script>

<style scoped>
panel {
    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    border: 5px solid #53687E;
    max-width: 80%;
    margin: auto;
    margin-bottom: 3rem;
}
</style>
