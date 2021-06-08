import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import SignUp from "@/views/SignUp";
import Login from "@/views/Login";
import Wallet from "@/views/Wallet";
import Logout from "@/views/LogOut";
//import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      requireAuth: false,
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      requireAuth: false,
    },
  },
  {
    path: "/logout",
    name: "LogOut",
    component: Logout,
    meta: {
      requireAuth: false,
    },
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp,
    meta: {
      requireAuth: false,
    },
  },
  {
    path: "/wallet",
    name: "Wallet",
    component: Wallet,
    meta: {
      requireAuth: true,
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach((to, from, next) => {
//   if (to.matched.some((record) => record.meta.requireAuth)) {
//     if (store.state.isUserLogin === true) next();
//     //else next("/login");
//   } else next();
// });
export default router;
