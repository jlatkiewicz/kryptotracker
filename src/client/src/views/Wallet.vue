<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="4" variant="primary">
          <b-card bg-variant="light" class="text-center">
            <b-img
              fluid
              thumbnail
              src="../assets/pixabays_user.png"
              alt="Image 1"
            ></b-img>
            <b-card-text class="mt-3">Hello {{ username }}!</b-card-text>
            <b-button size="sm" to="/" variant="primary"
              >Change password</b-button
            >
          </b-card>
        </b-col>
        <b-col cols="4" variant="info">
          <b-card bg-variant="light" header="Your wallet" class="text-center">
            <b-card-text>
              <p>Your balance in bitcoin: {{wallet.crypto}}</p>
              <p>Your balance in PLN:   {{wallet.money}}</p>
            </b-card-text>
<!--            <b-form @submit="onCalculate">-->
<!--             <b-button type="submit" size="sm" variant="primary">Calculate</b-button>-->
<!--            </b-form>-->
          </b-card>
        </b-col>

        <b-col cols="4" variant="info">
          <b-card bg-variant="light" header="Change your wallet" class="text-center">
            <b-card-text>
              <b-form @submit="onSubmit" >
                <b-form-group
                    id="input-group-1"
                    label="New balance:"
                    label-for="input-1"
                >
                  <b-form-input
                      id="input-1"
                      v-model="wallet.crypto"
                      type="text"
                      placeholder="Enter new value"
                      required
                  ></b-form-input>
                </b-form-group>
                <b-button type="submit" variant="primary" class="mr-2">Change & Calculate</b-button>
              </b-form>

            </b-card-text>
          </b-card>
        </b-col>

      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Wallet",
  data() {
    return {
      username: this.$store.state.user.name,
      wallet: {
        money: this.$store.state.wallet.currency,
        crypto: this.$store.state.wallet.crypto
      }
    };
  },
  methods: {
    async onCalculate(event) {
      event.preventDefault();
     const v = this;
      await axios
          .get("/price" )
          .then(async function (response) {
            v.wallet.money = response.data.bitcoinPriceInPln * v.wallet.crypto;
            console.log(v.wallet.money);
            console.log(response);
          }).catch(function (err) {
            console.log(err.response);
            alert("Something goes wrong.");
      })
    },
    async onSubmit(event) {
      event.preventDefault();
      const v = this;
      await axios
          .post("/wallet/set/" + this.username + "/" + this.wallet.crypto )
          .then(function (response) {
            v.wallet.crypto = response.data.bitcoinAmount;
            console.log(v.wallet.crypto);
            v.onCalculate(event);
            console.log(response);
          }).catch(function (err) {
            console.log(err.response);
            alert("Something goes wrong.");
        })
    }
}
};
</script>

<style scoped></style>
