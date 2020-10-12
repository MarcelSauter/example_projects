<template>
  <div v-if="getTaskById">
    <detail-task-container :get-task-by-id="getTaskById"></detail-task-container>
  </div>
</template>

<script>
import DetailTaskContainer from "../components/detail-task/detail-task-container";

export default {
  name: "task-detail",
  components: {
    DetailTaskContainer
  },
  created() {
    this.$store.dispatch("tasks/getTaskById", this.$route.params.id);
  },
  computed: {
    getTaskById() {
      return this.$store.getters["tasks/getTaskById"];
    }
  }
};
</script>

<style scoped>

</style>
