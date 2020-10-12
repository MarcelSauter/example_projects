<template>
  <div>
    <h1>CreateTask</h1>
    <create-task-forum @addNewTask="addNewTask"></create-task-forum>
  </div>
</template>

<script>
import taskModel from "../model/task-model";
import CreateTaskForum from "../components/create-task/create-task-form";

export default {
  name: "create-task",
  components: {
    CreateTaskForum
  },
  data() {
    return {
      task: taskModel,
      addedTask: false
    };
  },
  /*beforeRouteLeave(to, from, next) {
    if (this.addedTask) {
      next(true);
    } else {
      const response = confirm(`You have not added your new task!`);
      next(response);
    }
  },*/
  methods: {
    addNewTask(task) {
      this.$store.dispatch("tasks/addNewTask", task);
      this.addedTask = true;
      this.$router.push("/show-team-tasks");
    }
  }
};
</script>

<style scoped></style>
