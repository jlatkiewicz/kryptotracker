<template>
  <div>
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">
        <b-col cols="5">
          <b-card  header="Login in" class="text-center">
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
              </b-button>
              <b-button type="reset" variant="danger">Cancel</b-button>
            </b-form>
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
      const vm = this;
      await axios
        .get("/price")
        .then(function (response) {
          vm.$store.commit(
            "setBalance",
            response.data.bitcoinPriceInPln * vm.$store.state.wallet.bitcoin
          );
          console.log(vm.$store.state.wallet.money);
          console.log(response);
        })
        .catch(function (err) {
          console.log(err.response);
          alert("Something goes wrong.");
        });
    },
    setWallet() {
      const vm = this;
      console.log("chce zmieniac portfel");
      const username = vm.$store.state.user.name;
      console.log(username)
      axios
        .get("/wallet/" + username)
        .then(async function (response) {
          vm.$store.commit("setBalance", response.data.bitcoinAmount);
          await vm.calculate();
          console.log(vm.$store.state.wallet.bitcoin);
          console.log(response);
        })
        .catch(function (err) {
          console.log(err.response);
        });
    },
    onLogin(event) {
      const vm = this;
      event.preventDefault();
      axios
        .post("/users/login", this.form)
        .then(function (response) {
          console.log(response);
          /**
           * Na poczatku trzeba bedzie sprawdzic czy loguje sie Admin
           * isAdmin -> jesli true to jedno
           *         -> jesli false, to to co pod spodem
           * jesli Admin
           *    - nie ma wyliczania portfela
           *    - przechodzimy do panelu Admina
           *    - zmienia sie Navbar
           */

          console.log(vm.$store.state.isUserLogin);
          vm.$store.commit('userLogin');
          console.log(vm.$store.state.isUserLogin);
          vm.$store.commit('setUsername', vm.form.username)
          vm.reset();
          alert("Login successfully");
          vm.setWallet();
          vm.calculate();
          vm.$router.push("/wallet");
        })
        .catch(function (err) {
          console.log(err);
          alert("Something goes wrong.");
          vm.reset();
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
