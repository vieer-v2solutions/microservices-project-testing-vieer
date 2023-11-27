<template>
  <div>
    <h3>Login</h3>
    <div class="form-container">


        <div class="form-group">
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" class="form-control" placeholder="username" v-model="email" required autofocus />
        </div>
        <div class="form-group">
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" class="form-control" placeholder="password" v-model="password" />
        </div>
        <div class="form-group">
          <button class="btn btn-primary" @click="login">Login</button>
          <button class="btn btn-link" @click="cancel">Cancel</button>
        </div>

    </div>


  </div>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        email:'',
        password:'',
      }
    },
    methods:{
      cancel(){

      },
      login(){

      }
    }
  }
</script>


<style scoped>

</style>
