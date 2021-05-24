<template>
  <div class="mb-4">
    <b-navbar toggleable="lg" type="dark" variant="secondary">
      <b-navbar-brand to="/">KryptoTracker</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" class="mr-auto" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item to="/login">Login</b-nav-item>
          <b-nav-item to="/signup">SignUp</b-nav-item>
        </b-navbar-nav>
      </b-collapse>

    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "NavbarHeader"
}
</script>

<style scoped>

</style>