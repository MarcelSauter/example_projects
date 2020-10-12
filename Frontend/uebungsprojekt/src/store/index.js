import Vue from "vue";
import Vuex from "vuex";
import tasksModule from "./modules/tasks";
import userModule from "./modules/user";
import projectModule from "./modules/projects";
import commentModule from "./modules/comments";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    user: userModule,
    tasks: tasksModule,
    projects: projectModule,
    comments: commentModule
  }
});
