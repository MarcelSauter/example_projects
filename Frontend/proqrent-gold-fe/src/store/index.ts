import Vue from "vue";
import Vuex from "vuex";
import employeeModule from "./modules/employee";
import goldCourseModule from "./modules/gold-course";
import goodNewsModule from "./modules/good-news";
import baseDataModule from "./modules/base-data";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    employee: employeeModule,
    goldCourse: goldCourseModule,
    goodNews: goodNewsModule,
    baseData: baseDataModule
  }
});
