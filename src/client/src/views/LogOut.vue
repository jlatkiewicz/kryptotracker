<template>
  <div>
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">
        <b-col cols="5">
          <b-card bg-variant="light" header="Log out" class="text-center">
            <b-row class="justify-content-md-center mt-5">
              <b-card-text>Are you sure, {{ username }}?</b-card-text>
            </b-row>
            <b-row class="justify-content-md-center mt-5">
              <b-button v-on:click="onClick" to="/" variant="primary"
                >Log out</b-button
              >
            </b-row>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
    };
  },
  computed: mapState({
    username: (state) => state.user.name,
  }),
  methods: {
    async onClick() {
      await this.$store.dispatch("logout");
    },
  },
};
</script>

<style scoped></style>
