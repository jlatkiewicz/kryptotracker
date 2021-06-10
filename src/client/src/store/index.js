import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isUserLogin: false,
    user: {
      id: 0,
      name: "",
      status: "ACTIVE",
      isAdmin: false,
    },
    wallet: {
      money: 0,
      bitcoin: 0,
    },
    users: [
      {
        "username": "bea1234",
        "state": "ACTIVE"
      },
      {
        "username": "jacekp",
        "state": "TERMINATED"
      },
      {
        "username": "user23",
        "state": "LOCKED"
      },
      {
        "username": "annakowal",
        "state": "ACTIVE"
      },
    ]
  },
  getters: {
    getUsername: (state) => {
      return state.user.name;
    },
    isUserLogin: (state) => {
      return state.isUserLogin;
    },
    getMoney: (state) => {
      return state.wallet.money;
    },
    getBitcoin: (state) => {
      return state.wallet.bitcoin;
    },
  },
  mutations: {
    setBitcoins(state, value) {
      state.wallet.bitcoin = value;
    },
    setMoney(state, value) {
      state.wallet.money = value;
    },
    setUsername(state, name) {
      state.user.name = name;
    },
    userLogin(state){
      state.isUserLogin = true;
    },
    userLogout(state){
      state.isUserLogin = false;
    }
  },
  actions: {
    changeBitcoins(context, value){
      context.commit('setBitcoins', value);
    },
    changeMoney(context, value){
      context.commit('setMoney', value);
    }
  },
  modules: {},
});



// function printMoney(string) {
//   const strArr = string.split('.', 2)
//   const firstPart = strArr[0]
//   const secondPart = strArr[1]
//   return firstPart.concat(secondPart);
// }
//
// const bitcoinInPLN = printMoney(response.data.bitcoinPriceInPln * vm.tmp.bitcoin)
// await vm.$store.dispatch(
//     "changeMoney",
//     bitcoinInPLN
// );