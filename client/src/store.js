import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);

const store =  new Vuex.Store({ 

    state: {        
        isLoggedIn: false,
        messageError: '',
        status: '',
        token: '',
        user : { },
        cursoSeleccionado: { }
    },
	mutations: {
		auth_request(state){
	    	state.status = 'loading'
	  	},
	  	auth_success(state, payload){
            state.messageError = '',
            state.isLoggedIn = true
		    state.status = 'success'
            state.token = payload.token            
            state.user = payload.usuario
            //state.user = Object.assign({}, state.user, payload.usuario)
            //Vue.set(state.user, payload.usuario)
	  	},
	  	auth_error(state, message){
	    	state.messageError = message
	  	},
	  	logout(state){
            state.isLoggedIn = false
	    	state.status = ''
	    	state.token = ''
        },
        setCursoSeleccionado(state, cursoSelec){
            state.cursoSeleccionado = cursoSelec
        }
          
	},    
	actions: {

        login({commit}, userLogin){
            return new Promise((resolve, reject) => {
              //reject("error forzado")
              commit('auth_request')      
              this._vm.$http({ method: "POST", data: userLogin, "url": "api/auth/login" })
                        .then(resp => {
                            const token = resp.data.token
                            const usuario = resp.data.usuario                            
                            if (usuario != null) {
                                this._vm.$http.defaults.headers.common['Authorization'] = token
                                commit('auth_success', resp.data)
                                resolve(resp)                                                
                            } else {
                                commit('auth_error', resp.data.message)    
                            }
                        }, error => {
                            commit('auth_error', "Error interno del servidor")
                            console.error(error);
                        });
            })
        },
        logout({commit}){
            return new Promise((resolve, reject) => {
                    commit('logout')
                    delete axios.defaults.headers.common['Authorization']
                    resolve()
            })
        },
        createCurso({commit, state}, curso){
            //self = this
            return new Promise((resolve, reject) => {
                this._vm.$http({ method: "POST", data: curso, "url": "api/cursos/usuario/" + state.user.legajo })
                          .then(resp => {
                            resolve(resp)                    
                          }, error => {
                              console.error(error);
                          });
            })            
        }
    },
	getters : {
        cursoSeleccionado: state => state.cursoSeleccionado,
        isLoggedIn: state => state.isLoggedIn

    }    
})
export default store