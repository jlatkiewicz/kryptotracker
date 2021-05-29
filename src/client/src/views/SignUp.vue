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

              <b-form-group
                  id="input-group-2"
                  label="Password:"
                  label-for="input-2"
              >
                <b-form-input
                    id="input-2"
                    v-model="form.password"
                    type="password"
                    placeholder="Enter password"
                    required
                ></b-form-input>
              </b-form-group>

              <b-button type="submit" variant="primary" class="mr-2"
              >Register
              </b-button
              >
              <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
          </b-card>
        </b-col>
      </b-row>
      <b-row class="justify-content-md-center mt-5">
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
        username: "",
        password: "",
      },
      show: true,
    };
  },
  methods: {
    reset() {
      this.form.username = "";
      this.form.password = "";
    },
    onRegister(event) {
      const v = this;
      event.preventDefault();
      axios
          .post("/users/register", this.form)
          .then(function (response) {
            console.log(response);
            v.reset();
            alert("Register successfully");
            v.$router.push("/login");
          }).catch(function (err) {
        if (err.response.status === 401) {
          alert("Wrong password or login");
        } else {
          console.log(err)
          alert("Something goes wrong.");
          v.reset();
        }
      })
    },
    onReset(event) {
      event.preventDefault();
      this.reset();
    }
    ,
  },
};
</script>

<style scoped></style>
