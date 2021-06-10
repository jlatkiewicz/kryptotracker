<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="3" variant="primary">
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
                    <b-button size="sm" :pressed="false" disabled variant="outline-info">ADMIN</b-button>
                  </b-col>
                </b-row>
              </b-card-text>
            </b-card>
          </b-row>
        </b-col>
        <b-col cols="4" variant="info">
          <b-card bg-variant="light" header="Users" class="text-center">
            <b-card-text>
              <b-list-group>
                    <b-list-group-item v-for="user in users" :key="user.username" class="d-flex justify-content-between align-items-center">
                        {{ user.username }}

                        <b-button size="sm" :pressed="false"
                                  disabled variant="outline-success"
                                  v-if="user.state === 'ACTIVE'"
                                  pill class="align-items-baseline">{{ user.state }}</b-button>

                        <b-button size="sm" :pressed="false"
                                  disabled variant="outline-secondary"
                                  v-if="user.state === 'LOCKED'"
                                  pill
                        >{{ user.state }}</b-button>

                        <b-button size="sm" :pressed="false"
                                  disabled variant="outline-danger"
                                  v-else-if="user.state === 'TERMINATED'"
                                  pill
                        >{{ user.state }}</b-button>

                      </b-list-group-item>
              </b-list-group>
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="5" >
            <b-card bg-variant="light" header="Load user" class="text-center">
              <b-form inline @submit="onLoad">
                <b-form-group>
                  <b-form-input
                      id="inline-form-input-name"
                      v-model="user.username"
                      type="text"
                      placeholder="Enter username"
                      required
                  ></b-form-input>
                </b-form-group>

                <b-button type="submit"  variant="primary" class="mx-2"
                >Load
                </b-button>
              </b-form>
            </b-card>


          <b-card bg-variant="light" class="text-center mt-3">
            <b-form @submit="onChange">
              <b-form-group
                  id="input-group-1"
                  label-for="input-1"
              >
                <b-form-input
                    id="input-1"
                    v-model="user.username"
                    type="text"
                    required
                ></b-form-input>
              </b-form-group>

              <b-form-group>
<!--                <b-form-input-->
<!--                    id="input-1"-->
<!--                    v-model="user.state"-->
<!--                    type="text"-->
<!--                    required-->
<!--                ></b-form-input>-->
                <b-form-select v-model="selected" :options="options">DDFDFDF</b-form-select>
              </b-form-group>
              <b-button type="submit"  variant="primary" class="mx-2"
              >Change
              </b-button>

            </b-form>
          </b-card>



        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import {mapState} from "vuex"
// import axios from "axios";
export default {
  name: "Admin",
  data() {
    return {
      user: {
        username: "ddgdfgdf",
        state: "TERMINATED"
      },
      selected: 'ACTIVE',
      options: [
        { value: null, text: '' },
        { value: 'ACTIVE', text: 'ACTIVE' },
        { value: 'LOCKED', text: 'LOCKED' },
        { value: 'TERMINATED', text: 'TERMINATED' }
      ]
    };
  },
  computed: mapState({
    username: state => state.user.name,
    users: state => state.users,
    status: state => state.user.status,
  }),
  methods: {
     onLoad(event) {
      event.preventDefault(event);
      this.selected = this.user.state

      // const vm = this;
      // await axios
      //     .post("/wallet/set/" + this.$store.getters.getUsername + "/" + this.tmp.bitcoin)
      //     .then(function (response) {
      //       vm.$store.dispatch('changeBitcoins', response.data.bitcoinAmount);
      //       console.log(vm.tmp.bitcoin);
      //       vm.onCalculate(event);
      //       console.log(response);
      //     })
      //     .catch(function (err) {
      //       console.log(err.response);
      //       alert("Something goes wrong.");
      //     });

    },
    async onChange(event) {
      event.preventDefault(event);
      // const vm = this;
      // await axios
      //     .post("/wallet/set/" + this.$store.getters.getUsername + "/" + this.tmp.bitcoin)
      //     .then(function (response) {
      //       vm.$store.dispatch('changeBitcoins', response.data.bitcoinAmount);
      //       console.log(vm.tmp.bitcoin);
      //       vm.onCalculate(event);
      //       console.log(response);
      //     })
      //     .catch(function (err) {
      //       console.log(err.response);
      //       alert("Something goes wrong.");
      //     });

    },
  }
};
</script>

<style scoped></style>
