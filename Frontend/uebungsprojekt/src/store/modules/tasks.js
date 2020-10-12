import axios from "axios";
import keycloak from "../../main";

function errorHandler(error) {
  if (error === 403) {
    alert("PERMISSION DENIED - Du hast keine Berechtigungen für diesen Zugriff");
  } else if (error === 404) {
    alert("NOT FOUND - Deine Suchanfrage wurde nicht gefunden");
  }
}

export default {
  namespaced: true,
  state: {
    tasks: [],
    task: null,
    assignedTasks: [],
    projectTasks: []
  },
  mutations: {
    getAllTasks(state, tasks) {
      state.tasks = tasks;
    },
    getTaskById(state, task) {
      state.task = task;
    },
    deleteTaskById(state, taskId) {
      const deleteTask = state.projectTasks.filter(t => t.taskId === taskId);
      state.projectTasks.splice(state.projectTasks.indexOf(deleteTask[0]), 1)
    },
    addNewTask(state, task) {
      const assignedTask = task.data.teamMember.filter(member => member.userIdKeycloak === keycloak.tokenParsed.sub.toString());

      if (assignedTask.length > 0) state.assignedTasks.push(task);
      state.tasks.push(task.data);
    },
    changeDoneStatus(state, task) {
      const changeDoneStatusTask = state.tasks.filter(t => t.taskId === task.id);
      changeDoneStatusTask.status = task.data.status;
    },
    getAssignedTasks(state, assignedTasks) {
      state.assignedTasks = assignedTasks;
    },
    getTasksByProject(state, projectTasks) {
      state.projectTasks = projectTasks;
    }
  },
  actions: {
    getAllTasks({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/all-tasks/${keycloak.tokenParsed.sub}`)
        .then(tasks => commit("getAllTasks", tasks.data))
        .catch((error) => errorHandler(error.response.status));
    },
    getTaskById({ commit }, taskId) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/task/${taskId}`)
        .then(task => {
          commit("getTaskById", task.data);
        })
        .catch(console.error);
    },
    deleteTaskById({ commit }, taskId) {
      axios
        .delete(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/delete-task/${taskId}`)
        .then(() => {
          commit("deleteTaskById", taskId);
          console.log(taskId);
        })
        .catch(console.error);
    },
    addNewTask({ commit }, task) {
      console.log(JSON.stringify(task));
      axios
        .post(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/add-task`, task)
        .then(task => commit("addNewTask", task))
        .catch(console.error);
    },
    changeDoneStatus({ commit }, task) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/change-status/${task.status}/${task.taskId}`)
        .then(task => commit("changeDoneStatus", task))
        .catch(console.error);
    },
    getAssignedTasks({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/user-assigned-tasks/${keycloak.tokenParsed.sub}`)
        .then(assignedTasks => commit("getAssignedTasks", assignedTasks.data))
        .catch(console.error);
    },
    getTasksByProject({ commit }, project) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/tasks/project/${project.projectId}`)
        .then(tasks => commit("getTasksByProject", tasks.data))
        .catch(console.error);
    }
  },
  getters: {
    getAllTasks(state) {
      return state.tasks;
    },
    getTaskById(state) {
      return state.task;
    },
    getBacklogAssignedTasks(state) {
      return state.projectTasks.filter(assignedTask => assignedTask.status === "backlog");
    },
    getOpenAssignedTasks(state) {
      return state.projectTasks.filter(assignedTask => assignedTask.status === "progress");
    },
    getReviewAssignedTasks(state) {
      return state.projectTasks.filter(assignedTask => assignedTask.status === "review");
    },
    getClosedAssignedTasks(state) {
      return state.projectTasks.filter(assignedTask => assignedTask.status === "closed");
    },
    getProjectTasks(state) {
      return state.projectTasks;
    }
  },
};
