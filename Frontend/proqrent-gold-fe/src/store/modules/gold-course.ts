import axios from "axios";
import keycloak from "@/main";
import { LocalDate } from "@js-joda/core";

export default {
  namespaced: true,
  state: {
    actualGoldCourse: undefined,
    differencePreviousMonth: undefined,
    goldCoursesDiagramValue: [],
    goldCoursesDiagramMonth: []
  },
  mutations: {
    setActualGoldCourse(state, actualGoldCourse) {
      state.actualGoldCourse = actualGoldCourse;
    },
    setDifferencePreviousMonth(state, differencePreviousMonth) {
      state.differencePreviousMonth = differencePreviousMonth;
    },
    setGoldCoursesDiagram(state, goldCourses) {
      for (let i = 0; i < goldCourses.length; i++) {
        state.goldCoursesDiagramValue[i] = goldCourses[i].goldCourse;
        state.goldCoursesDiagramMonth[i] = LocalDate.parse(goldCourses[i].timePeriod).month().name();
      }
    }
  },
  actions: {
    getActualGoldCourse({ commit }) {
      axios.get(process.env.VUE_APP_BE_CLIENT + "/gold-course/actual", { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(actualGoldCourse => commit("setActualGoldCourse", actualGoldCourse.data.goldCourse))
        .catch(console.error)
    },
    getDifferencePreviousMonth({ commit }) {
      axios.get(process.env.VUE_APP_BE_CLIENT + "/gold-course/difference-previous-month",
        { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(differencePreviousMonth => commit("setDifferencePreviousMonth", differencePreviousMonth.data))
        .catch(console.error);
    },
    getGoldCoursesForDiagram({ commit }) {
      axios.get(process.env.VUE_APP_BE_CLIENT + "/gold-course/diagram", { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(goldCourses => commit("setGoldCoursesDiagram", goldCourses.data))
        .catch(console.error);
    },
  },
  getters: {
    getActualGoldCourse(state) {
      return state.actualGoldCourse;
    },
    getDifferencePreviousMonth(state) {
      return (state.differencePreviousMonth > 0) ? "+" + state.differencePreviousMonth : state.differencePreviousMonth;
    },
    getGoldCoursesForDiagramValue(state) {
      return state.goldCoursesDiagramValue;
    },
    getGoldCoursesForDiagrammMonth(state) {
      return state.goldCoursesDiagramMonth;
    }
  }
}
