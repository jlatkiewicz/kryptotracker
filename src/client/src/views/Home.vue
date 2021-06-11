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
        <b-col b-col cols="6" variant="info">
          <b-card
              align-h="center"
              border-variant="light"
              bg-variant="light"
              tag="article"
              style="max-width: 70rem"
              class="mb-2 mx-auto"
          >
            <b-card-text>
              <h2 v-if="isUserLogin === true" class="my-2">
                Hello, {{ username }}!
              </h2>
              <h2 v-else>Hey you!</h2>

                <div v-if="isNormalUserLogin() === true">
                  <b-button to="/wallet" variant="primary" class="my-2">
                    Let's check your wallet
                  </b-button>
                </div>

                <div v-else-if="isAdmin === true">
                  <b-button to="/admin" variant="primary" class="my-2">
                    Go to your panel!
                  </b-button>
                </div>

                <div v-else>
                  <div>
                    <b-button to="/signup" variant="primary" class="my-2">
                      I am new here!
                    </b-button>
                  </div>
                  <div>
                    <b-button to="/login" variant="primary" class="my-2">
                      Wanna login again
                    </b-button>
                  </div>
                </div>

            </b-card-text>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Home",
  data() {
    return {
      username: this.$store.state.user.name,
    };
  },
  computed: mapState({
    username: (state) => state.user.name,
    isUserLogin: (state) => state.isUserLogin,
    isAdmin: (state) => state.user.isAdmin
  }),
  methods: {
    isNormalUserLogin() {
      return this.isUserLogin && this.isAdmin
    },
  },
};
</script>
