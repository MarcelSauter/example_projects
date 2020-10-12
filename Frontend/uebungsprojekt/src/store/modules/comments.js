import axios from "axios";
import keycloak from "../../main";

export default {
  namespaced: true,
  state: {
    comments: []
  },
  mutations: {
    getAllComments(state, comments) {
      // state.comments = [];
      state.comments = comments;
    },
    addNewComment(state, comment) {
      console.log("Add new Comment", comment);
      state.comments.push(comment);
    }
  },
  actions: {
    getAllComments({ commit }, comments) {
      commit("getAllComments", comments);
    },
    addNewComment({ commit }, comment) {
      console.log(comment);
      axios
        .post(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/create-comment`, comment, { headers: { Authorization: `${keycloak.tokenParsed.typ} ${keycloak.token}`}})
        .then(comment => commit("addNewComment", comment.data))
        .catch(console.error);
    }
  },
  getters: {
    getAllComments(state) {
      return state.comments;
    }
  }
}
