<template>
  <div>
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center mt-5">
        <b-col cols="5">
          <b-card header="Change password" class="text-center">
            <b-form @submit="onChange" @reset="onReset" v-if="show">
              <b-form-group
                id="input-group-1"
                label="New password:"
                label-for="input-1"
              >
                <b-form-input
                  id="input-1"
                  v-model="form.password1"
                  type="password"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                id="input-group-2"
                label="Repeat password:"
                label-for="input-2"
              >
                <b-form-input
                  id="input-2"
                  v-model="form.password2"
                  type="password"
                  required
                ></b-form-input>
              </b-form-group>

              <b-button type="submit" variant="primary" class="mr-2"
                >Change
              </b-button>
              <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  data() {
    return {
      form: {
        password1: "",
        password2: "",
      },
      show: true,
    };
  },
  computed: mapState({
    username: (state) => state.user.name,
  }),
  methods: {
    onReset() {
      this.form.password1 = "";
      this.form.password2 = "";
    },
    onChange(event) {
      if (this.form.password1 !== this.form.password2) {
        alert("Passwords do not match");
        this.onReset();
      } else {
        const vm = this;
        event.preventDefault();
        axios
          .put(
            "http://localhost:8080/users/" +
              this.username +
              "/password?password=" +
              this.form.password1 +
              ""
          )
          .then(function (response) {
            console.log(response);
            vm.onReset();
            alert("Password was changed succesfully");
            vm.$router.push("/wallet");
          })
          .catch(function (err) {
            console.log(err);
            alert("Something goes wrong.");
            vm.onReset();
          });
      }
    },
  },
};
</script>

<style scoped></style>
