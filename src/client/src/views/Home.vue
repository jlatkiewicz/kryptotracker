<template>
<div>
  <b-row class="justify-content-center">
    <div>
      <b-card
          align-h="center"
          border-variant="dark"
          bg-variant="light"
          tag="article"
          style="max-width: 70rem;"
          class="mb-2 mx-auto"

      >
        <b-img thumbnail fluid src="../assets/pexels-krypto-home.jpg" alt="Image 1"></b-img>
        <b-card-text >
          <p v-if="isUserLogin() === true">Czesc {{}}</p>
          <p v-else>Smutno</p>
        </b-card-text>


        <b-button to="/" variant="primary">Go home again</b-button>

      </b-card>

<!--      <b-jumbotron header="BootstrapVue" lead="Bootstrap v4 Components for Vue.js 2">-->
<!--        <p>For more information visit website</p>-->
<!--        <b-button variant="primary" href="#">More Info</b-button>-->
<!--      </b-jumbotron>-->
    </div>
  </b-row>
</div>
</template>

<script>

export default {
  name: "Home",
  methods: {
    isUserLogin(){
      return this.$store.state.isUserLogin
    }
  }
};
</script>
