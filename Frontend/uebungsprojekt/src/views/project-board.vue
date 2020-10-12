<template>
  <div>
    <div v-if="getActivatedProject">
      <h1>{{ getActivatedProject.name }} Board</h1>
      <div class="row">
        <div class="col-lg-3 col-md-6 col-sm-12 status-container">
          <h3>Backlog</h3>
          <task-container @deleteTaskById="deleteTaskById"
                          @changeStatusAhead="changeStatusAhead"
                          v-for="(assignedTask, index) in getBacklogAssignedTasks"
                          :key="index"
                          :task="assignedTask"
                          :board="true"/>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 status-container">
          <h3>Progress</h3>
          <task-container @deleteTaskById="deleteTaskById"
                          @changeStatusAhead="changeStatusAhead"
                          @changeStatusBehind="changeStatusBehind"
                          v-for="(assignedTask, index) in getOpenAssignedTasks"
                          :key="index"
                          :task="assignedTask"
                          :board="true"/>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 status-container">
          <h3>Review</h3>
          <task-container @deleteTaskById="deleteTaskById"
                          @changeStatusAhead="changeStatusAhead"
                          @changeStatusBehind="changeStatusBehind"
                          v-for="(assignedTask, index) in getReviewAssignedTasks"
                          :key="index"
                          :task="assignedTask"
                          :board="true"/>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 status-container">
          <h3>Closed</h3>
          <task-container @deleteTaskById="deleteTaskById"
                          @changeStatusAhead="changeStatusAhead"
                          @changeStatusBehind="changeStatusBehind"
                          v-for="(assignedTask, index) in getAssignedClosedTasks"
                          :key="index"
                          :task="assignedTask"
                          :board="true"/>
        </div>
      </div>
    </div>
    <div v-if="!getActivatedProject">
      <project-not-activated />
    </div>
  </div>
</template>

<script>
import TaskContainer from "../components/all-tasks/task-container";
import ProjectNotActivated from "../components/all-tasks/project-not-activated";

export default {
  name: "show-team-tasks",
  components: {
    TaskContainer,
    ProjectNotActivated
  },
  created() {
    if (this.$store.getters["projects/getActivatedProject"] !== null) this.$store.dispatch("tasks/getTasksByProject", this.$store.getters["projects/getActivatedProject"]);
  },
  computed: {
    getBacklogAssignedTasks() {
      return this.$store.getters["tasks/getBacklogAssignedTasks"];
    },
    getOpenAssignedTasks() {
      return this.$store.getters["tasks/getOpenAssignedTasks"];
    },
    getReviewAssignedTasks() {
      return this.$store.getters["tasks/getReviewAssignedTasks"];
    },
    getAssignedClosedTasks() {
      return this.$store.getters["tasks/getClosedAssignedTasks"];
    },
    getActivatedProject() {
      return this.$store.getters["projects/getActivatedProject"];
    }
  },
  methods: {
    deleteTaskById(taskId) {
      this.$store.dispatch("tasks/deleteTaskById", taskId);
    },
    changeStatusAhead(task) {
      task.status = this.getAheadStatus(task.status);
      this.$store.dispatch("tasks/changeDoneStatus", task);
    },
    changeStatusBehind(task) {
      task.status = this.getBehindStatus(task.status);
      this.$store.dispatch("tasks/changeDoneStatus", task);
    },
    getAheadStatus(status) {
      switch (status) {
        case "backlog":
          status = "progress";
          break;
        case "progress":
          status = "review";
          break;
        case "review":
          status = "done";
          break;
        default:
          console.error("Something wnt wrong");
          break;
      }
      return status;
    },
    getBehindStatus(status) {
      switch (status) {
        case "closed":
          status = "review"
          break;
        case "review":
          status = "progress";
          break;
        case "progress":
          status = "backlog";
          break;
        default:
          console.error("Something went wrong");
          break;
      }
      return status;
    }
  }
};
</script>

<style scoped>
.status-container:nth-child(2) {
  border-left: 1px solid #2b2e30;
  border-right: 1px solid #2b2e30;
  min-height: 500px;
}
.status-container:nth-child(3) {
  border-right: 1px solid #2b2e30;
  min-height: 500px;
}
h3 {
  font-weight: 200;
}
</style>
