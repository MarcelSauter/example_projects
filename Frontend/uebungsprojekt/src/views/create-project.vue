<template>
  <div>
    <h1>Create Project</h1>
    <create-project-form />
  </div>
</template>

<script>
import CreateProjectForm from "../components/create-project/create-project-form";

export default {
  name: "create-project",
  components: {
    CreateProjectForm
  }
}
</script>

<style scoped></style>
