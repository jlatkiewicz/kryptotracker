<template>
  <div>

    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">
        Are you sure?
      </b-row>
      <b-row class="justify-content-md-center mt-5">
        <b-button v-on:click="onClick" to="/" variant="primary" class="mr-4">LogOut</b-button>
      </b-row>
    </b-container>

  </div>
</template>

<script>

export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      show: true
    }
  },
  computed: {
    getUser(){
      return this.$store.state.user;
    }
  },
  methods: {
    onClick(){
      this.$store.state.isUserLogin = false;
    }
  }

}
</script>

<style scoped>

</style>


