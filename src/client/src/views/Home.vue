<template>
  <div>
    <b-row class="justify-content-center">
      <div>
        <b-card
          align-h="center"
          border-variant="light"
          bg-variant="light"
          tag="article"
          style="max-width: 70rem"
          class="mb-2 mx-auto"
        >
          <b-img-lazy
            style="max-width: 50rem"
            thumbnail
            fluid
            src="../assets/pexels-krypto-home.jpg"
            alt="Image 1"
          ></b-img-lazy>
          <b-card-text>
            <h2 v-if="isUserLogin() === true" class="my-2">
              Hello again, {{ username }}!
            </h2>
            <p v-else>Are you new here?</p>
          </b-card-text>

          <b-button v-if="isUserLogin() === true" to="/wallet" variant="primary"
            >Let's check your wallet</b-button
          >
          <b-button v-else to="/signup" variant="primary"
            >Let's register!</b-button
          >
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
  data() {
    return {
      username: this.$store.state.user.name,
    };
  },
  methods: {
    isUserLogin() {
      return this.$store.state.isUserLogin;
    },
  },
};
</script>
