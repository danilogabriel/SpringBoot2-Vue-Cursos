<template>
        <b-navbar type="dark" variant="primary">
            <b-navbar-brand to="/home">Capacita2</b-navbar-brand>

            <b-navbar-nav>
                <b-nav-item to="/formcurso">Nuevo</b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <span class="mr-3 bg-primary text-white font-weight-bold">{{ $store.state.user.nombre}}</span>
                <b-button size="sm" class="my-2 my-sm-0" type="submit" @click="logout()">Cerrar sesión</b-button>
            </b-navbar-nav>
        </b-navbar>            
</template>

<script>
export default {
  name: 'NavigationBar',
  props: {
    
  },
  methods: {
      	logout() {
		   	this.$store.dispatch('logout')
		   			    .then(() => this.$router.push('/login'))
		   				.catch(err => console.log(err))		              
		}
  }
}
</script>