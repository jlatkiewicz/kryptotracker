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
                    <b-button size="sm" :pressed="false" disabled variant="outline-success">ADMIN</b-button>
                  </b-col>
                </b-row>
              </b-card-text>
            </b-card>
          </b-row>
        </b-col>
        <b-col cols="8" variant="info">
          <b-card bg-variant="light" header="Users" class="text-center">
            <b-card-text>
<!--              tutaj v-for po arrajce userow, ktore dostane z rest api
                  przyjac zalozenie ze nie bedzie wiecej userow niz 6, wiec nie bawimy sie w zagniezdzanie :P
                  albo po prostu bierzemy pierwszych 10

                 min wersja
                 okienko - wpisuje username, klikam load
                 wczytuja mi sie dane
-->
            </b-card-text>
          </b-card>
        </b-col>
      </b-row>


    </b-container>
  </div>
</template>

<script>
import {mapState} from "vuex"


export default {
  name: "Admin",
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
    status: state => state.user.status,
    money: state => state.wallet.money,
    bitcoin: state => state.wallet.bitcoin
  }),
};
</script>

<style scoped></style>
