<template>
  <div>
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">
        <b-col cols="5">
          <b-card>
            <b-form @submit="onLogin" @reset="onReset">
              <b-form-group
                  id="input-group-1"
                  label="Login:"
                  label-for="input-1"
              >
                <b-form-input
                    id="input-1"
                    v-model="form.username"
                    type="text"
                    placeholder="Enter username"
                    required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                  id="input-group-2"
                  label="Password:"
                  label-for="input-2"
              >
                <b-form-input
                    id="input-2"
                    v-model="form.password"
                    type="password"
                    placeholder="Enter password"
                    required
                ></b-form-input>
              </b-form-group>

              <b-button type="submit" variant="primary" class="mr-4"
              >Login
              </b-button
              >
              <b-button type="reset" variant="danger">Cancel</b-button>
            </b-form>
          </b-card>
          <b-card class="mt-3" header="Form Data Result">
            <pre class="m-0">{{ form }}</pre>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
    };
  },
  computed: {
    getUser() {
      return this.$store.state.user;
    },
  },
  methods: {
    reset() {
      this.form.username = "";
      this.form.password = "";
    },
    async calculate() {
      const v = this;
      await axios
          .get("http://localhost:8080/price")
          .then(function (response) {
            v.$store.state.wallet.currency = response.data.bitcoinPriceInPln * v.$store.state.wallet.crypto;
            console.log(v.$store.state.wallet.currency);
            console.log(response);
          }).catch(function (err) {
            console.log(err.response);
            alert("Something goes wrong.");
          })
    },
    setWallet() {
      const v = this;
      axios.get("http://localhost:8080/wallet/" + this.$store.state.user.name)
          .then(async function (response) {
            v.$store.state.wallet.crypto = response.data.bitcoinAmount;
            v.calculate();
            console.log(v.$store.state.wallet.crypto);
            console.log(response);
          }).catch(function (err) {
            console.log(err.response);
      });

    },
    onLogin(event) {
      const v = this;
      event.preventDefault();
      axios
          .post("http://localhost:8080/users/login", this.form)
          .then(function (response) {
            console.log(response);
            v.$store.state.isUserLogin = true;
            v.$store.state.user.name = v.form.username;
            v.reset();
            alert("Login successfully");
            v.setWallet();
            v.calculate();
            v.$router.push("/wallet");
          }).catch(function (err) {
            console.log(err);
            alert("Something goes wrong.");
            v.reset();
      });

    },
    onReset(event) {
      event.preventDefault();
      this.reset();
    },
  },
};
</script>

<style scoped></style>
