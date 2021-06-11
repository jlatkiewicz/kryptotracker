<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="4" variant="primary">
          <b-row>
            <b-card bg-variant="light" class="text-center">
              <b-img
                  fluid
                  thumbnail
                  src="../assets/pixabays_user.png"
                  alt="Image 1"
                  ma
              ></b-img>
              <b-card-text class="mt-3">
                <b-row>
                  <b-col cols="6" class="text-center my-1">Hello {{ username }}!</b-col>
                  <b-col cols="6">
                    <b-button size="sm" :pressed="false" disabled variant="outline-success"
                              v-if="status === 'ACTIVE'">{{ status }}
                    </b-button>
                  </b-col>
                </b-row>
              </b-card-text>
            </b-card>
          </b-row>
        </b-col>
        <b-col cols="4" variant="info">
          <b-card bg-variant="light" header="Your wallet" class="text-center">
            <b-card-text>
              <p>Your balance in bitcoin: {{ bitcoin }}</p>
              <p>Your balance in PLN: {{ money }}</p>
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4" variant="info">
          <b-card
              bg-variant="light"
              header="Change your wallet"
              class="text-center"
          >
            <b-card-text>
              <b-form @submit="onSubmit">
                <b-form-group
                    id="input-group-1"
                    label="New balance:"
                    label-for="input-1"
                >
                  <b-form-input
                      id="input-1"
                      v-model="tmp.bitcoin"
                      type="text"
                      placeholder="Enter new value"
                      required
                  ></b-form-input>
                </b-form-group>
                <b-button type="submit" variant="primary" class="mr-2"
                >Change & Calculate
                </b-button
                >
              </b-form>
            </b-card-text>
          </b-card>
        </b-col>
      </b-row>

      <b-row class="mt-3">
        <b-col cols="4" class="p-0">
          <b-card bg-variant="light">
            <b-button size="sm" to="/password" variant="primary">Change password</b-button>
          </b-card>
        </b-col>
        <b-col cols="4">.</b-col>
        <b-col cols="4">.</b-col>
      </b-row>

    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import {mapState} from "vuex"


export default {
  name: "Wallet",
  data() {
    return {
      tmp: {
        bitcoin: "",
        money: ""
      },
    };
  },
  computed: mapState({
    username: state => state.user.name,
    status: state => state.user.state,
    money: state => state.wallet.money,
    bitcoin: state => state.wallet.bitcoin
  }),
  methods: {
    // async cutString(string) {
    //   const strArr = string.toString().split('.', 2)
    //   const firstPart = strArr[0]
    //   const secondPart = strArr[1].substr(0, 2)
    //   return firstPart.concat('.', secondPart);
    // },
    async onCalculate() {
      // event.preventDefault(event);
      const vm = this;
      await axios
          .get("/price")
          .then(async function (response) {
            const money = response.data.bitcoinPriceInPln * vm.tmp.bitcoin
            await vm.$store.dispatch(
                "changeMoney",
                money
            );
            console.log(vm.tmp.bitcoin);
            console.log(response);
          })
          .catch(function (err) {
            console.log(err.response);
            alert("Something goes wrong.");
          });
    },
    async onSubmit(event) {
      event.preventDefault(event);
      const vm = this;
      await axios
          .post("/wallet/set/" + this.$store.getters.getUsername + "/" + this.tmp.bitcoin)
          .then(function (response) {
            vm.$store.dispatch('changeBitcoins', response.data.bitcoinAmount);
            console.log(vm.tmp.bitcoin);
            vm.onCalculate(event);
            console.log(response);
          })
          .catch(function (err) {
            console.log(err.response);
            alert("Something goes wrong.");
          });

    },
  },
};
</script>

<style scoped></style>
