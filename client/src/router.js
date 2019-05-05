import Vue from 'vue'
import Router from 'vue-router'
import store from './store'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import DetalleCurso from './views/DetalleCurso.vue'
import NuevoCurso from './views/NuevoCurso.vue'
import FormCurso from './views/FormCurso.vue'
import SetPassword from './views/SetPassword.vue'


Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },    
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: { 
        requiresAuth: true
      }  
    },    
    {
      path: '/login',
      name: 'login',
      component: Login
    },    
    {

      path: '/acceso', 
      name: 'acceso' , 
      component: SetPassword, 
      props: true  
  
      // path: '/acceso',
      // name: 'acceso',
      // component: SetPassword
    },       {
      path: '/detallecurso',
      name: 'detallecurso',
      component: DetalleCurso,
      meta: { 
        requiresAuth: true
      }      
    },
    {
      path: '/formcurso',
      name: 'formcurso',
      component: FormCurso,
      props: true,
      meta: { 
        requiresAuth: true
      }      
    }    

  ]
})
router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters.isLoggedIn) {
      return next()
    }
    next('/login') 
  } else {
    next() 
  }
})
export default router
