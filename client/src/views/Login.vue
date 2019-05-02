<template>
	<div>
		<div class="login-form" style="width: 30%;">
			<h3 class="text-center p-4">Ingreso a Propuestas de Capacitación</h3>       

				<b-input-group append="@afip.gob.ar" class="mb-2 mr-2">
				<b-form-input placeholder="usuario de e-mail" v-model="userLogin.username"></b-form-input>
				</b-input-group>
				<div class="text-danger">{{ validation.firstError('userLogin.username') }}</div>

				<b-form-input class="mb-2 mr-2"
					placeholder="N° legajo"
					required 
					v-model="userLogin.legajo" />
				<div class="text-danger">{{ validation.firstError('userLogin.legajo') }}</div>

				<b-form-input class="mb-2 mr-2"
					placeholder="CUIT / CUIL"
					required 
					v-model="userLogin.cuit" />
				<div class="text-danger">{{ validation.firstError('userLogin.cuit') }}</div>

				<button type="submit" class="btn btn-primary btn-block" @click="login()" >Ingresar</button>		
		</div>
		<div class="text-center text-danger" v-if="$store.state.status">{{ $store.state.messageError }}</div>		
	</div>
</template>
<script>
import {Validator} from 'simple-vue-validator'

export default {
	data(){
		return {			
			userLogin: {
				username : "dbiondi",
				legajo : 3710681,
				cuit: 20220254144
			}	
    	}
	},
    validators: {
      'userLogin.username' : function (value) {
		return Validator.value(value)
						.required()
						.regex('^[A-Za-z]*$', 'Debe ingresar solo caracteres alfabéticos');
	  },
      'userLogin.legajo' : function (value) {
		return Validator.value(value)
						.required()
						.integer();
      },
      'userLogin.cuit' : function (value) {
		return Validator.value(value)
						.required()
						.length(11, 'Debe ser numérico de 11 digitos')
						.digit();
      }	  
    }, 	
	methods: {
		async login() {
			try {
				let esValido = await this.$validate()

				if (esValido) {							
					await this.$store.dispatch('login', this.userLogin)
					this.$router.push('/')
				}
			} catch (error) {
				console.log(error)
			}
			// ---------------  A la manera de Promise ------------------------
			// this.$store.dispatch('login', this.userLogin)
			// 								.then(() => this.$router.push('/'))
			// 								.catch(err => console.log(err))
			// }
		}
	}
}
</script>
<style scoped>
		.login-form {
			width: 640px;
				margin: 50px auto;
				margin-bottom: 15px;
					background: #f7f7f7;
					box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
					padding: 30px;			
		}
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>