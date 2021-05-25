<template>
<div>
  <b-container>
    <b-row>
      <b-col cols="4" variant = "primary">
        <b-card bg-variant="light" header="Light" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
          <b-button to="/" variant="primary">Change password</b-button>
        </b-card>
      </b-col>
      <b-col cols="8" variant = "info">
        <b-card bg-variant="light" header="Light" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</div>
</template>

<script>
export default {
  name: "Wallet"
}
</script>

<style scoped>

</style>