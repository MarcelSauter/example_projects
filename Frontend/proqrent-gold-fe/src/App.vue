<template>
  <div class="wrapper">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <div class="navbar-container">
      <sidenav></sidenav>
    </div>
    <div class="content-container">
      <router-view />
    </div>
  </div>
</template>

<script>
import Sidenav from "./components/navigation/sidenav";

export default {
  components: {
    Sidenav
  }
};
</script>

<style lang="scss">
.wrapper {
  font-family: Calibri;
}
.navbar-container {
  width: auto;
}
.content-container {
  color: $text-color;
  padding: 10px 90px 0 200px;
  width: auto;

  @media (max-width: $medium) {
    padding: 10px 30px 0 150px;
  }

  @media (max-width: $small) {
    padding: 10px 20px 0 120px;
  }

  h1 {
    font-size: 50px;
    font-weight: 200;
  }
}
</style>
