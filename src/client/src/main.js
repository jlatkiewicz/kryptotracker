import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from 'axios'
import VueAxios from 'vue-axios'
import "./icons.js"
import "./plugins/bootstrap-vue";


Vue.config.productionTip = false;
Vue.use(VueAxios, axios)

new Vue({
  router,
  store,
  render: (h) => h(App),
  data : {
    homeImg: "./assets/pexels-krypto-home.jpg"
  }
}).$mount("#app");
