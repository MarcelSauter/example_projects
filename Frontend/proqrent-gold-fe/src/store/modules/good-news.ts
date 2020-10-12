import axios from "axios";
import keycloak from "@/main";

export default {
  namespaced: true,
  state: {
    goodNews: undefined
  },
  mutations: {
    setGoodNews(state, goodNews) {
      state.goodNews = goodNews;
    }
  },
  actions: {
    getGoodNews({ commit }) {
      axios.get(process.env.VUE_APP_BE_CLIENT + "/good-news", { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(goodNews => commit("setGoodNews", goodNews.data))
        .catch(console.error)
    }
  },
  getters: {
    getGoodNews(state) {
      return state.goodNews;
    }
  }
}
