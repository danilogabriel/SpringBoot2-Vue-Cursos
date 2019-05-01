import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import store from './store'

import VueFormGenerator from "vue-form-generator"
import 'vue-form-generator/dist/vfg.css'
import SimpleVueValidation from 'simple-vue-validator/'
//import SimpleVueValidation from 'Validator'


Vue.use(SimpleVueValidation);

Vue.use(VueFormGenerator)
Vue.config.productionTip = false
Vue.use(BootstrapVue);

Vue.prototype.$http = axios.create({
  baseURL: process.env.VUE_APP_API_ROOT
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
