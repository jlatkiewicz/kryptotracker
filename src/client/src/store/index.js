import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isUserLogin: true,
    user: {
      id: null,
      name: '',
      isAdmin: false
    }
  },
  mutations: {},
  actions: {},
  modules: {},
});
