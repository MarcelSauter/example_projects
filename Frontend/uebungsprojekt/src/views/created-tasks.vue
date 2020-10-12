<template>
  <div>
    <h1>Erstellte Tickets</h1>
    <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-12" v-for="(task, index) in getAllTasks" :key="index">
        <task-container :task="task" @deleteTaskById="deleteTaskById" :board="false"/>
      </div>
    </div>
  </div>
</template>

<script>
import TaskContainer from "../components/all-tasks/task-container";

export default {
  name: "show-tasks",
  components: { TaskContainer },
  created() {
    this.$store.dispatch("tasks/getAllTasks");
  },
  computed: {
    getAllTasks() {
      return this.$store.getters["tasks/getAllTasks"];
    }
  },
  methods: {
    deleteTaskById(taskId) {
      this.$store.dispatch("tasks/deleteTaskById", taskId);
    }
  }
};
</script>

<style scoped></style>
