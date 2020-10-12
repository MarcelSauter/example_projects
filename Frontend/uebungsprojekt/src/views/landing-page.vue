<template>
  <div>
    <div class="row">
      <h1 class="col-12">Projekte</h1>

      <div class="col-lg-6 col-md-12">
        <h4>Owned Projects</h4>
        <div v-for="project in getOwnedProjects" :key="project.projectId + project.projectOwner">
          <project-container :project="project" />
        </div>
      </div>

      <div class="col-lg-6 col-md-12">
        <h4>Assigned Projects</h4>
        <div v-for="project in getAssignedProjects" :key="project.projectId + project.name">
          <project-container :project="project" />
        </div>
      </div>

      <div class="col-lg-3 col-md-6 col-sm-12 counter-task" v-for="(counter, index) in counterData" :key="index">
        <counter-container :title="counter.title" :counter="counter.counter" :to="counter.to"></counter-container>
      </div>
    </div>
  </div>
</template>

<script>
import CounterContainer from "../components/landing-page/counter-container";
import ProjectContainer from "../components/list-projects/project-container";

export default {
  name: "landing-page",
  components: {
    CounterContainer,
    ProjectContainer
  },
  data() {
    return {
      counterData: []
    };
  },
  created() {
    this.counterData = [
      { title: "Backlog Tasks", counter: this.getBacklogTasks.length, to:"/show-tasks" },
      { title: "Progress Tasks", counter: this.getProgressTasks.length, to: "/show-team-tasks" },
      { title: "Review Tasks", counter: this.getReviewTasks.length, to: "/show-team-tasks" },
      { title: "Closed Tasks", counter: this.getClosedTasks.length, to: "/show-team-tasks" }
    ]
  },
  computed: {
    getAssignedProjects() {
      return this.$store.getters["projects/getAllAssignedProjects"]
        .filter(project => project.projectOwner.userIdKeycloak !== this.$store.getters["user/getUser"].userIdKeycloak);
    },
    getOwnedProjects() {
      return this.$store.getters["projects/getAllOwnedProjects"];
    },
    getBacklogTasks() {
      return this.$store.getters["tasks/getBacklogAssignedTasks"];
    },
    getProgressTasks() {
      return this.$store.getters["tasks/getOpenAssignedTasks"];
    },
    getReviewTasks() {
      return this.$store.getters["tasks/getReviewAssignedTasks"];
    },
    getClosedTasks() {
      return this.$store.getters["tasks/getClosedAssignedTasks"];
    }
  }
};
</script>

<style scoped>
h4 {
  font-weight: 200;
}
.counter-task {
  margin-top: 100px;
  text-align: center;
}
</style>
