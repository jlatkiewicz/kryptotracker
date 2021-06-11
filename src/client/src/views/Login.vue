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
import {mapState} from "vuex";

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
    username: state => state.user.name,
    status: state => state.user.state,
    money: state => state.wallet.money,
    bitcoin: state => state.wallet.bitcoin,
    isUserLogin: state => state.isUserLogin,
    auth: state => state.auth
  }),
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
          vm.$store.dispatch(
            "changeMoney",
            response.data.bitcoinPriceInPln * vm.bitcoin
          );
          console.log(vm.money);
          console.log(response);
        })
        .catch(function (err) {
          console.log(err.response);
          alert("Przeliczanie poszlo nie tak.");
        });
    },
    async setWallet() {
      const vm = this;
      const username = vm.username;
      await axios
        .get("/wallet/" + username)
        .then(async function (response) {
          vm.$store.commit("setBitcoins", response.data.bitcoinAmount);
          await vm.calculate();
          console.log(vm.bitcoin);
          console.log(response);
        })
        .catch(function (err) {
          console.log(err.response);
        });
    },
    async onLogin(event) {
      const vm = this;
      event.preventDefault();
      await axios
        .post("/users/login", this.form)
        .then(function (response) {
          vm.$store.commit('userLogin');
          vm.$store.commit('setUsername', vm.form.username)
          vm.$store.commit("setAuth", vm.form.password);
          if(response.data.isAdmin === true){
            vm.adminLogged();
          }
          else{
            vm.userLogged();
          }
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
    adminLogged(){
      this.$store.commit('adminLogged');
      alert("Login successfully");
      this.$router.push("/admin");
    },
    userLogged(){
      this.$store.commit('userLogged');
      this.setWallet();
      this.calculate();
      alert("Login successfully");
      this.$router.push("/wallet");
    }
  },
};
</script>

<style scoped></style>
