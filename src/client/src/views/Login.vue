<template>
  <div>

    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">

        <b-col cols ="5">
          <b-card>
            <b-form @submit="onLogin" @reset="onReset" v-if="show">
              <b-form-group
                  id="input-group-1"
                  label="Login:"
                  label-for="input-1"
                  description="Bla bla"
              >
                <b-form-input
                    id="input-1"
                    v-model="form.username"
                    type="text"
                    placeholder="Enter username"
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

              <b-button to="/wallet" variant="primary" class="mr-4">Login</b-button>
              <b-button type="reset" variant="danger">Cancel</b-button>
            </b-form>
          </b-card>
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
  computed: {
    getUser(){
      return this.$store.state.user;
    }
  },
  methods: {
    onLogin(event) {
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
      this.$store.state.isUserLogin = true;
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


