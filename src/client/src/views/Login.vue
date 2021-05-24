<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <p></p>

      </b-row>
      <b-row class="justify-content-md-center mt-2">
        <b-col col lg="1"></b-col>
        <b-col cols="8" md="auto">
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group
                id="input-group-1"
                label="Login:"
                label-for="input-1"
                description="Bla bla"
            >
              <b-form-input
                  id="input-1"
                  v-model="form.email"
                  type="email"
                  placeholder="Enter email"
                  required
              ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-2" label="Password:" label-for="input-2">
              <b-form-input
                  id="input-2"
                  v-model="form.name"
                  placeholder="Enter password"
                  required
              ></b-form-input>
            </b-form-group>

            <b-button type="submit" variant="primary" class="mr-2">Login</b-button>
            <b-button type="reset" variant="danger">Cancel</b-button>
          </b-form>
        </b-col>
        <b-col col lg="1"></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        email: '',
        name: '',
        food: null,
        checked: []
      },
      foods: [{text: 'Select One', value: null}, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
      show: true
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.email = ''
      this.form.name = ''
      this.form.food = null
      this.form.checked = []
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style scoped>

</style>