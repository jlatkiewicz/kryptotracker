<template>
<div>
  <b-container>
    <b-row>
      <b-col cols="4" variant = "primary">
        <b-card bg-variant="light"  class="text-center">
          <b-img fluid thumbnail   src="../assets/pixabays_user.png" alt="Image 1"></b-img>
          <b-card-text class="mt-3">Czesc {{username}}!</b-card-text>
          <b-button size="sm" to="/" variant="primary">Change password</b-button>
        </b-card>
      </b-col>
      <b-col cols="8" variant = "info">
        <b-card bg-variant="light" header="Your wallet" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</div>
</template>


<script>
export default {
  name: "Wallet",
  data() {
    return {
      username: this.$store.state.user.name,
    }
  }
}
</script>

<style scoped>

</style>