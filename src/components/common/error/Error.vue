<template>
  <div class="hello">
    {{errorMessage}}

  </div>
</template>

<script>
export default {
  name: 'Error',
  data () {
    return {
      errorMessage:'oh, had some problems fetching something from the API',


    }
  }
}
</script>


<style scoped>

</style>
