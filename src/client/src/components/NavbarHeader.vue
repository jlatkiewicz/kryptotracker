<template>
  <div class="mb-4 bg-dark">
    <b-container>
      <b-navbar type="dark" variant="dark" text="Lang">
        <b-navbar-brand to="/">KryptoTracker</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" class="mr-auto" is-nav>
          <b-navbar-nav class="ml-auto" v-if="this.$store.state.isUserLogin === false">
              <b-nav-item to="/login">Login</b-nav-item>
              <b-nav-item to="/signup">SignUp</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item to="/wallet">Wallet</b-nav-item>
            <b-nav-item to="/logout">Logout</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "NavbarHeader"
}
</script>

<style scoped>

</style>