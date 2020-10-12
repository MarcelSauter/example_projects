<template>
  <div>
    <h1>Projekte</h1>

    <h4>Owned Projects</h4>
    <div v-for="project in getOwnedProjects" :key="project.projectId + project.projectOwner">
      <project-container :project="project" />
    </div>

    <h4>Assigned Projects</h4>
    <div v-for="project in getAssignedProjects" :key="project.projectId + project.name">
      <project-container :project="project" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ProjectContainer from "../components/list-projects/project-container";
// import projects from "../store/modules/projects";

export default {
  name: "list-projects",
  components: {
    ProjectContainer
  },
  computed: {
    getAssignedProjects() {
      return this.$store.getters["projects/getAllAssignedProjects"]
        .filter(project => project.projectOwner.userIdKeycloak !== this.$store.getters["user/getUser"].userIdKeycloak);
    },
    getOwnedProjects() {
      return this.$store.getters["projects/getAllOwnedProjects"];
    }
  },
  methods: {
    deleteProjectById(id) {
      axios
        .delete("http://localhost:8079/delete-project/" + id)
        .then(console.log)
        .catch(console.error);
    },
  }
}
</script>

<style scoped>
h4 {
  font-weight: 200;
}
</style>
