<template>
  <div>
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">
          <span @click="goToDetailTask(task)">#{{task.taskId}} Titel: {{ task.title }}</span>
          <span class="delete-button float-right"
                @click="deleteTaskById(task.taskId)">X</span>
        </h5>
        <h6 class="card-subtitle mb-2" :class="task.category">Kategorie: {{ task.category }}</h6>
        <span>Projektname: {{ task.project.name }}</span><br />
        <span>Autor: {{ task.author.username }}</span>
        <p class="card-text">Beschreibung: {{ task.description }}</p>
        <div v-if="board" class="arrow-container">
          <task-arrows :task="task" @changeStatusAhead="changeStatusAhead" @changeStatusBehind="changeStatusBehind"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TaskArrows from "./task-arrows";

export default {
  name: "task-container",
  components: {
    TaskArrows
  },
  props: {
    task: null,
    board: null
  },
  methods: {
    deleteTaskById(taskId) {
      this.$emit("deleteTaskById", taskId);
    },
    changeStatusAhead(task) {
      this.$emit("changeStatusAhead", task);
    },
    changeStatusBehind(task) {
      this.$emit("changeStatusBehind", task);
    },
    goToDetailTask(task) {
      this.$router.push({name: 'DetailTask', params: { id: task.taskId }});
    }
  }
};
</script>

<style scoped>
.card {
  background-color: #2b2e30;
  margin-bottom: 30px;
}
.assigned-task {
  border: 1px solid red;
}
.card-title {
  cursor: pointer;
}
.delete-button {
  cursor: pointer;
  color: #aaaaaa;
}
.task-status {
  border: 1px solid #3c4043;
  border-radius: 5px;
  padding: 5px 10px;
  transition: .8s all;
}
.task-status:hover {
  background-color: #3c4043;
}
span {
  color: #aaaaaa;
  cursor: pointer;
}
svg {
  color: #aaaaaa;
}
.Frontend {
  color: #FF00FF;
}
.Backend {
  color: #FFA500;
}
.Sonstige {
  color: #6B8E23;
}
.glyphicon {
  color: #aaaaaa;
}
.move-to-progress-button {
  background: none;
  border: 1px solid #aaaaaa;
  border-radius: 5px;
  color: #aaaaaa;
  padding: 5px 10px;
  transition: .8s all;
}
.move-to-progress-button:hover {
  background: #aaaaaa;
  color: #2b2e30;
}
</style>
