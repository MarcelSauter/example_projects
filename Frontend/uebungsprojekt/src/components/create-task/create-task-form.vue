<template>
  <div>
    <form v-on:submit.prevent="addNewTask(task)">
      <div class="row">
        <div v-if="errors.length">
          <ul>
            <li v-for="(error, index) in errors" :key="index">{{ error }}</li>
          </ul>
        </div>
        <div class="col-lg-12">
          <input v-model="task.title" type="text" name="task-title" value="" placeholder="Task Title" />
        </div>
        <div class="col-12">
          <select v-model="task.project" class="custom-select">
            <option value="" disabled selected>Choose Project</option>
            <option v-for="(project, index) in getAllProjects"
                    :key="index"
                    :value="project">
              {{ project.name }}
            </option>
          </select>
        </div>
        <div class="col-12 dropdown-container">
          <select v-model="task.category" class="custom-select">
            <option value="" disabled selected>Choose Category</option>
            <option value="Frontend">Frontend</option>
            <option value="Backend">Backend</option>
            <option value="Sonstige">Sonstige</option>
          </select>
        </div>
        <div class="col-lg-12">
          <h3>Teammitglieder</h3>
          <div class="row team-member-container">
            <search-team-member @addTeamMember="addTeamMember" v-for="(user, index) in getUserList.data" :key="index" :user="user"></search-team-member>
          </div>
        </div>
        <div class="col-12">
          <textarea v-model="task.description" name="task-description" placeholder="Task Description"></textarea>
        </div>
        <!--<div class="col-12">
          <label for="task-done">Erledigt</label>
          <input v-model="task.done" type="checkbox" name="task-done" id="task-done" />
        </div>-->
        <div class="col-12">
          <button type="submit" name="submit-button">Add Task</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import taskModel from "../../model/task-model";
import SearchTeamMember from "./search-team-member";

export default {
  name: "create-task-forum",
  components: { SearchTeamMember },
  data() {
    return {
      task: taskModel,
      errors: []
    };
  },
  created() {
    this.task.teamMember = [];
    this.$store.dispatch("projects/getAllProjects");
  },
  computed: {
    getUserList() {
      return this.$store.getters["user/getUserList"];
    },
    getAllProjects() {
      return this.$store.getters["projects/getAllProjects"];
    }
  },
  methods: {
    addNewTask(task) {
      if (this.checkValidation()) {
        task.author = this.$store.getters["user/getUser"];
        task.comments = [];
        delete task.project.projectOwner;
        delete task.project.projectMember;
        delete task.project.tasks;
        delete task.author.profilePicture;
        this.$emit("addNewTask", task);
      }
    },
    addTeamMember(user, activated) {
      if (activated) {
        const removeIndex = this.task.teamMember.indexOf(user);
        this.task.teamMember.splice(removeIndex, 1);
      } else {
        this.task.teamMember.push(user);
      }
    },
    checkValidation() {
      this.errors = [];

      if (this.task.title === "") this.errors.push("Title required");
      if (this.task.description === "") this.errors.push("Description required");
      if (this.task.category === "") this.errors.push("Category required");
      if (this.task.teamMember[0] === undefined) this.errors.push("You need at least 1 Team Member");
      if (this.task.project === "") this.errors.push("You need to chose one Project");

      if (this.errors.length > 0) return false;
      return true;
    }
  }
};
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
