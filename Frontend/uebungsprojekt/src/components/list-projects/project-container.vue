<template>
  <div>
    <div class="row project-container" @click="setActivatedProject(project)">
      <div class="col-8">
        <h4>{{ project.name }}</h4>
        <h6 class="project-owner">PO: <i>{{ project.projectOwner.username }}</i></h6>
        <span>Teamgröße: {{ project.projectMember.length }}</span>
        <p>Beschreibung: {{ project.description }}</p>
      </div>
      <div class="col-4 row">
        <span class="col-12">Project Member</span>
        <div v-for="(projectMember, index) in project.projectMember" :key="index">
          <img :src="'data:image/png;base64,' + projectMember.profilePicture" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "project-container",
  props: {
    project: null
  },
  methods: {
    setActivatedProject(project) {
      this.$store.dispatch("projects/setActivatedProject", project)
      this.$store.dispatch("tasks/getTasksByProject", project);
      this.$router.push("/show-team-tasks");
    }
  }
}
</script>

<style scoped>
.project-container {
  background-color: #2b2e30;
  border-radius: 5px;
  cursor: pointer;
  margin: 10px 0;
  padding: 10px 5px;
  transition: .8s all;
}
.project-container:hover {
  background-color: #1c1e1f;
}
h4 {
  font-weight: 200;
}
h6 {
  padding: 0;
}
.project-member-container {
  border-bottom: 1px solid #aaaaaa;
  margin: 0 30px 0 22px;
  padding: 5px 10px;
}
span {
  height: auto;
  margin: 0 0 -20px 0;
  padding: 0;
}
img {
  height: 50px;
}
</style>
