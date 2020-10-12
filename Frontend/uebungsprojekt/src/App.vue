<template>
  <div id="app">
    <navbar />
    <div class="container-wrapper">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from './shared/navbar';

export default {
  components: { Navbar }
};
</script>

<style>
html {
  background-color: #3c4043;
}
#app {
  background-color: #3c4043;
  color: #aaaaaa;
  font-weight: 200;
  min-height: 100vh;
}
.container-wrapper {
  margin: 40px;
}
</style>
