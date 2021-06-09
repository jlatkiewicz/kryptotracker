<template>
  <div>
    <b-container>
      <b-row class="justify-content-center">
      <b-col cols="6" variant="info">
          <b-img-lazy
              thumbnail
              fluid
              src="../assets/pexels-krypto-home.jpg"
              alt="Image 1"
          ></b-img-lazy>
      </b-col>
      <b-col b-col cols="6" variant="info" >
        <b-card
            align-h="center"
            border-variant="light"
            bg-variant="light"
            tag="article"
            style="max-width: 70rem"
            class="mb-2 mx-auto"
        >
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
      </b-col>

    </b-row>
    </b-container>
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
