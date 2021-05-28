import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isUserLogin: false,
    user: {
      id: 0,
      name: "",
      isAdmin: false,
    },
    wallet: {
      currency: 0,
      crypto: 0,
    }
  },
  mutations: {},
  actions: {},
  modules: {},
});
