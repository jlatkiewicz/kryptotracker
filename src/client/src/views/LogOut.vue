<template>
  <div>

    <b-container class="bv-example-row">
<b-row class="justify-content-md-center mt-5">
  <b-col cols="5">
    <b-card bg-variant="light" header="Logging out" class="text-center">
      <b-row class="justify-content-md-center mt-5">
          <b-card-text>Are you sure, {{ username }}?</b-card-text>
      </b-row>
      <b-row class="justify-content-md-center mt-5">
        <b-button v-on:click="onClick" to="/" variant="primary" >LogOut</b-button>
      </b-row>
    </b-card>
  </b-col>
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
      username: this.$store.state.user.name,

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


