<template>
        <b-navbar type="dark" variant="primary">
            <b-navbar-brand to="/home">
                <img src="../assets/logo-afip-subdi.png">
            </b-navbar-brand>

            <b-navbar-brand to="/home">
                    <h5 class="titulo">
                        Sistema de Propuestas<br>de Capacitación
                    </h5>
            </b-navbar-brand>

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
<style scoped>
.titulo {
    font-size: 16px;
    color: #fff;
    font-family: 'Ubuntu', sans-serif;
    text-align: left;
    border-left: 2px solid #eee;
    padding: 0px 20px;
}
</style>