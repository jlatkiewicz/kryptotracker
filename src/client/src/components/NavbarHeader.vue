<template>
  <div class="mb-4 bg-dark">
    <b-container>
      <b-navbar type="dark" variant="dark" text="Lang">
        <b-navbar-brand to="/">KryptoTracker</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" class="mr-auto" is-nav>
          <b-navbar-nav class="ml-auto" v-if="isUserLogin === false">
            <b-nav-item to="/login">Login</b-nav-item>
            <b-nav-item to="/signup">SignUp</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else-if="isAdmin===false">
            <b-nav-item to="/wallet">Wallet</b-nav-item>
            <b-nav-item to="/logout">Logout</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-if="isAdmin===true">
            <b-nav-item to="/admin">Admin</b-nav-item>
            <b-nav-item to="/logout">Logout</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </b-container>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "NavbarHeader",
  computed: mapState({
    isUserLogin: state => state.isUserLogin,
    isAdmin: state => state.user.isAdmin
  }),
};
</script>

<style scoped></style>
