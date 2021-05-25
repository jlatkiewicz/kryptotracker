<template>
  <div>
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">

        <b-col cols="5">
          <b-card>
            <b-form @submit="onRegister" @reset="onReset" v-if="show">
              <b-form-group
                  id="input-group-1"
                  label="Username:"
                  label-for="input-1"
              >
                <b-form-input
                    id="input-1"
                    v-model="form.username"
                    type="text"
                    placeholder="Enter username here"
                    required
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-2" label="Password:" label-for="input-2">
                <b-form-input
                    id="input-2"
                    v-model="form.password"
                    type="password"
                    placeholder="Enter password"
                    required
                ></b-form-input>
              </b-form-group>

              <b-button type="submit" variant="primary" class="mr-2">Register</b-button>
              <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
          </b-card>
        </b-col>


      </b-row>
      <b-row class = "justify-content-md-center mt-5">
        <b-col cols="5">
          <b-card class="mt-3" header="Form Data Result">
            <pre class="m-0">{{ form }}</pre>
          </b-card>
        </b-col>

      </b-row>


    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      show: true
    }
  },
  methods: {
    onRegister(event) {
      event.preventDefault()
      axios.post("https://jsonplaceholder.typicode.com/posts", this.form)
      .then(response => console.log(response))
          .catch(err => {
            if (err.response) {
              alert(err.response)
            } else if (err.request) {
              alert(err.response)
            } else {
              alert("sth goes wrong")
            }
          })
      alert("Pomyslnie!")
      this.form.username = ''
      this.form.password = ''
    },

    onReset(event) {
      event.preventDefault()
      this.form.username = ''
      this.form.password = ''
    }
  }
}
</script>

<style scoped>

</style>