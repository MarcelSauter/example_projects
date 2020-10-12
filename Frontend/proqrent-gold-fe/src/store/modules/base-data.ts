import axios from "axios";
import keycloak from "@/main";

export default {
  namespaced: true,
  state: {
    baseData: undefined
  },
  mutations: {
    setBaseData(state, baseData) {
      state.baseData = baseData;
    }
  },
  actions: {
    getBaseData({ commit }) {
      axios.get(process.env.VUE_APP_BE_CLIENT + "/base-data/get-base-data", { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(baseData => commit("setBaseData", baseData.data))
        .catch(console.error);
    },
    setGlobalTime({ commit }, globalTime: number) {
      axios.post(process.env.VUE_APP_BE_CLIENT + "/base-data/set-global-time", globalTime,
        { headers: { Authorization: `Bearer ${keycloak.token}`, "Content-Type": "application/json" }})
        .then(baseData => commit("setBaseData", baseData.data))
        .catch(console.error);
    }
  },
  getters: {
    getBaseData(state) {
      return (state.baseData != undefined) ? state.baseData : 160;
    }
  }
}
