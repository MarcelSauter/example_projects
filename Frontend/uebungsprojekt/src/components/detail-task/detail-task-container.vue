<template>
  <div>
      <div v-if="getTaskById" class="row detail-task-container">
        <div class="col-12">
          <h1>#{{ getTaskById.taskId }} {{ getTaskById.title }}</h1>
        </div>
        <div class="col-12">
          <h5>Projekt: {{ getTaskById.project.name }}</h5>
        </div>
        <div class="col-12">
          <span class="category-container" :class="getTaskById.category.toLowerCase()">
            {{ getTaskById.category }}
          </span>
        </div>
        <div class="col-12 description-container">
          <p>Beschreibung: <i>{{ getTaskById.description }}</i></p>
        </div>
        <div class="col-12 row team-member-container">
          <h6 class="col-12">Team Member</h6>
          <p class="col-1"
              v-for="(teamMember, index) in getTaskById.teamMember" :key="index">
            {{teamMember.username}}
          </p>
        </div>
        <div class="col-12 comment-container">
          <comment-container :task="getTaskById" />
        </div>
      </div>
  </div>
</template>

<script>
import CommentContainer from "./comment-container";

export default {
  name: "detail-task-container",
  components: {
    CommentContainer
  },
  props: {
    getTaskById: null
  },
  created() {
    console.log(this.getTaskById.comments.length);
  }
};
</script>

<style scoped>
.detail-task-container {
  background-color: #2b2e30;
  border-radius: 5px;
  margin: 0 auto;
  padding: 20px;
  width: 1140px;
}
.detail-task-container div {
  margin: 10px 0;
}
h1, h5 {
  font-weight: 200;
}
.category-container {
  border-radius: 5px;
  font-weight: bold;
  padding: 5px 10px;
}
.frontend {
  background-color: #FF00FF;
  color: #2b2e30;
}
.backend {
  background-color: #FFA500;
  color: #2b2e30;
}
.sonstige {
  background-color: #6B8E23;
  color: #2b2e30;
}
.team-member-container {
  padding: 0;
}
.comment-container {
  background-color: #1c1e1f;
  border-radius: 5px;
}
</style>
