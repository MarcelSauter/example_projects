import axios from "axios";
import keycloak from "../../main";

export default {
  namespaced: true,
  state: {
    activatedProject: null,
    allProjects: [],
    assignedProjects: [],
    ownedProjects: []
  },
  mutations: {
    setActivatedProject(state, project) {
      state.activatedProject = project;
    },
    getAllProjects(state, projects) {
      state.allProjects = projects;
    },
    getAllAssignedProjects(state, projects) {
      state.assignedProjects = projects;
    },
    getAllOwnedProjects(state, projects) {
      state.ownedProjects = projects;
    },
    createNewProject(state, project) {
      state.assignedProjects.push(project.data);
    }
  },
  actions: {
    setActivatedProject({ commit }, project) {
      commit("setActivatedProject", project);
    },
    getAllProjects({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/projects`)
        .then(projects => commit("getAllProjects", projects.data))
        .catch(console.error);
    },
    getAllAssignedProjects({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/projects/project-member/${keycloak.tokenParsed.sub}`)
        .then(projects => commit("getAllAssignedProjects", projects.data))
        .catch(console.error);
    },
    getAllOwnedProjects({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/projects/project-owner/${keycloak.tokenParsed.sub}`)
        .then(projects => commit("getAllOwnedProjects", projects.data))
        .catch(console.error)
    },
    createNewProject({ commit }, project) {
      axios
        .post(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/create-project`, project)
        .then(project => commit("createNewProject", project))
        .catch(console.error);
    }
  },
  getters: {
    getActivatedProject(state) {
      return state.activatedProject;
    },
    getAllProjects(state) {
      return state.allProjects;
    },
    getAllAssignedProjects(state) {
      return state.assignedProjects;
    },
    getAllOwnedProjects(state) {
      return state.ownedProjects;
    }
  }
}
