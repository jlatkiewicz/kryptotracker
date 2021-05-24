<template>
<div>
  <b-row class="justify-content-center">
    <div>
      <b-card
          align-h="center"
          border-variant="dark"
          bg-variant="light"
          title="Let's start journey"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 70rem;"
          class="mb-2 mx-auto"

      >
        <b-card-text>
          Some quick example text to build on the card title and make up the bulk of the card's content.
        </b-card-text>

        <b-button to="/" variant="primary">Go home again</b-button>

        <div class="container">
          <h1>My First Bootstrap Page</h1>
          <p>This is some text.</p>
          <p><font-awesome-icon icon="bitcoin"/></p>
        </div>
      </b-card>

<!--      <b-jumbotron header="BootstrapVue" lead="Bootstrap v4 Components for Vue.js 2">-->
<!--        <p>For more information visit website</p>-->
<!--        <b-button variant="primary" href="#">More Info</b-button>-->
<!--      </b-jumbotron>-->
    </div>
  </b-row>
</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home",
};
</script>
