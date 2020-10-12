<template>
  <div>
    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-left float-left arrow-svg" fill="currentColor" xmlns="http://www.w3.org/2000/svg"
         v-if="task.status !== 'backlog'" @click="changeStatusBehind(task)">
      <path fill-rule="evenodd" d="M5.854 4.646a.5.5 0 0 1 0 .708L3.207 8l2.647 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 0 1 .708 0z"/>
      <path fill-rule="evenodd" d="M2.5 8a.5.5 0 0 1 .5-.5h10.5a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
    </svg>
    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-right float-right arrow-svg" fill="currentColor" xmlns="http://www.w3.org/2000/svg"
         v-if="task.status !== 'closed'" @click="changeStatusAhead(task)">
      <path fill-rule="evenodd" d="M10.146 4.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 8l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
      <path fill-rule="evenodd" d="M2 8a.5.5 0 0 1 .5-.5H13a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 8z"/>
    </svg>
  </div>
</template>

<script>
  export default {
    name: "task-arrows",
    props: {
      task: null
    },
    methods: {
      changeStatusAhead(task) {
        this.$emit("changeStatusAhead", task);
      },
      changeStatusBehind(task) {
        this.$emit("changeStatusBehind", task);
      }
    }
  }
</script>

<style scoped>
.arrow-svg {
  border: 1px solid #aaaaaa;
  border-radius: 5px;
  cursor: pointer;
  font-size: 20px;
  transition: .3s all;
}
.arrow-svg:hover {
  background-color: #aaaaaa;
  color: #2b2e30;
}
</style>
