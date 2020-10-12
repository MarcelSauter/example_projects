<template>
  <div>
    <h3>Du hast kein Projekt aktiviert</h3>
    <span>Gehe zu
      <router-link class="router-link" to="/projects">deinen Projekten</router-link>
      und wähle dort eines aus.</span>
  </div>
</template>

<script>
export default {
  name: "project-not-activated"
}
</script>

<style scoped>
h3 {
  font-size: 40px;
  font-weight: 200;
}
span {
  font-size: 20px;
}
.router-link {
  color: #aaaaaa;
  font-weight: bold;
}
</style>
