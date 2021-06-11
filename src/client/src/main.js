import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import VueAxios from "vue-axios";
import Vuex, { mapState } from "vuex";
import "./icons.js";
import "./plugins/bootstrap-vue";

axios.defaults.baseURL = "http://localhost:8080/";

Vue.config.productionTip = false;
Vue.use(VueAxios, axios);
Vue.use(Vuex);

new Vue({
  router,
  store: store,
  render: (h) => h(App),
  data: {
    homeImg: "./assets/pexels-krypto-home.jpg",
  },
  computed: mapState({
    username: (state) => state.user.name,
    bitcoin: (state) => state.wallet.bitcoin,
    money: (state) => state.wallet.money,
  }),
}).$mount("#app");
