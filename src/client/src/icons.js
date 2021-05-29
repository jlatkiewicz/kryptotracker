import Vue from "vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faBitcoin } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faBitcoin);
Vue.component("font-awesome-icon", FontAwesomeIcon);
