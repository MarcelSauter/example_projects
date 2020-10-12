<template>
  <div>
    <form v-on:submit.prevent="addNewProject(project)">
      <div class="row">
        <div class="col-12">
          <input v-model="project.name" type="text" placeholder="Project Name">
        </div>
        <div class="col-12">
            <textarea v-model="project.description" placeholder="Project Description"></textarea>
        </div>
        <div class="col-12">
          <div class="row">
            <search-team-member @addTeamMember="addTeamMember" v-for="(user, index) in getUserList.data" :key="index" :user="user"/>
          </div>
        </div>
        <div class="col-12">
          <button type="submit" name="submit-button">Add Project</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import ProjectModel from "../../model/project-model";
import SearchTeamMember from "../create-task/search-team-member";

export default {
  name: "create-project-form",
  components: {
    SearchTeamMember
  },
  data() {
    return {
      project: ProjectModel
    }
  },
  created() {
    this.project.projectMember = [];
  },
  computed: {
    getUserList() {
      return this.$store.getters["user/getUserList"];
    }
  },
  methods: {
    addNewProject(project) {
      project.projectOwner = this.$store.getters["user/getUser"];
      this.$store.dispatch("projects/createNewProject", project);
    },
    addTeamMember(user, activated) {
      console.log(user);
      if (activated) {
        const removeIndex = this.project.projectMember.indexOf(user);
        this.project.projectMember.splice(removeIndex, 1);
      } else {
        this.project.projectMember.push(user);
      }
    },
  }
}
</script>

<style scoped>
  input {
    background: none;
  }
  input[type=text] {
    border: none;
    border-bottom: 1px solid #aaaaaa;
    border-radius: 0;
    color: #aaaaaa;
    font-weight: 200;
    height: 25px;
    margin: 20px 0 40px 0;
    width: 100%;
  }
  .team-member-container {
    margin-bottom: 30px;
  }
  .custom-select {
    cursor: pointer;
  }
  select {
    background: none;
    border: none;
    border-bottom: 1px solid #aaaaaa;
    border-radius: 0;
    color: #aaaaaa;
    font-weight: 200;
    height: 25px;
    margin: 20px 0 40px 0;
    padding: 0 10px;
  }
  option {
    background: #aaaaaa;
    color: #2b2e30;
  }
  textarea {
    background: none;
    border: none;
    border-bottom: 1px solid #aaaaaa;
    color: #aaaaaa;
    font-weight: 200;
    height: 100px;
    width: 100%;
  }
  label {
    margin-right: 40px;
  }
  input[type=checkbox] {
    margin-top: 40px;
  }
  .button-container {
    float: right;
  }
  button {
    background: none;
    border: 1px solid #aaaaaa;
    border-radius: 5px;
    color: #aaaaaa;
    float: right;
    height: 40px;
    margin-top: 40px;
    transition: .8s all;
    width: 300px;
  }
  button:hover {
    background-color: #2b2e30;
  }
</style>
