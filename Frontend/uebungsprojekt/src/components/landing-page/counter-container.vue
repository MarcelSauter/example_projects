<template>
  <div @click="routeToCounterPart">
    <div class="counter-task-wrapper">
      <div class="counter-task-container">
        <span class="counter-task-number">{{ counter }}</span>
      </div>
    </div>
    <h5>{{ title }}</h5>
  </div>
</template>

<script>
export default {
  name: "counter-container",
  props: {
    title: null,
    counter: null,
    to: null
  },
  methods: {
    routeToCounterPart() {
      this.$router.push(this.to);
    }
  }
}
</script>

<style scoped>
.counter-task-wrapper {
  align-items: center;
  display: flex;
  justify-content: center;
}
.counter-task-container {
  align-items: center;
  border: 1px solid #2b2e30;
  border-radius: 5px;
  cursor: pointer;
  display: flex;
  height: 300px;
  justify-content: center;
  transition: .8s all;
  width: 70%;
}
.counter-task-container:hover {
  background-color: #2b2e30;
}
.counter-task-number {
  color: #2b2e30;
  font-size: 200px;
  font-weight: bold;
  transition: .8s all;
}
.counter-task-number:hover {
  color: #aaaaaa;
}
h5 {
  font-size: 40px;
  font-weight: 200;
  margin-top: 10px;
}
</style>
