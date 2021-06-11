import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isUserLogin: false,
    user: {
      id: 0,
      name: "",
      state: "",
      isAdmin: false,
    },
    wallet: {
      money: 0,
      bitcoin: 0,
    },
    users: [
      {
        username: "bea1234",
        state: "ACTIVE",
      },
      {
        username: "jacekp",
        state: "TERMINATED",
      },
      {
        username: "user23",
        state: "LOCKED",
      },
      {
        username: "annakowal",
        state: "ACTIVE",
      },
    ],
    auth: {
      username: "",
      password: "",
    },
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
    setMoney(state, string) {
      const strArr = string.toString().split(".", 2);
      const firstPart = strArr[0];
      const secondPart = strArr[1].substr(0, 2);
      const prettyMoney = firstPart.concat(".", secondPart);
      state.wallet.money = prettyMoney;
    },
    setUsername(state, name) {
      state.user.name = name;
    },
    setState(state, userState) {
      state.user.state = userState;
    },
    userLogin(state) {
      state.isUserLogin = true;
    },
    userLogout(state) {
      state.isUserLogin = false;
    },
    adminLogged(state) {
      state.user.isAdmin = true;
    },
    userLogged(state) {
      state.user.isAdmin = false;
      state.user.state = "ACTIVE";
    },
    setAuth(state, password) {
      state.auth.username = state.user.name;
      state.auth.password = password;
    },
    setDefault(state) {
      state.isUserLogin = false;
      state.user.isAdmin = false;
      state.user.name = "";
      state.user.state = "";
      state.auth.username = "";
      state.auth.password = "";
    },
    loadUsersData(state, users) {
      state.users = users;
    },
  },
  actions: {
    changeBitcoins(context, bitcoins) {
      context.commit("setBitcoins", bitcoins);
    },
    changeMoney(context, string) {
      context.commit("setMoney", string);
    },
    logout(context) {
      context.commit("setDefault");
    },
    loadUsers(context, users) {
      context.commit("loadUsersData", users);
    },
    setUserState(context, state) {
      context.commit("setState", state);
    },
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
