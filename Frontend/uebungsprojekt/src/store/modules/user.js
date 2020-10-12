import axios from "axios";
import keycloak from "../../main";

export default {
  namespaced: true,
  state: {
    user: undefined,
    userList: undefined
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setUserList(state, userList) {
      state.userList = userList;
    },
    uploadProfilePicture(state, profilePicture) {
      console.log("UploadPicture resposne: ", profilePicture);
      state.user.data.profilePicture = profilePicture;
    }
  },
  actions: {
    setUser({ commit }, userKeycloak) {
      axios
        .get(`http://localhost:8079/user-active/${ userKeycloak.username }/${ userKeycloak.userIdKeycloak }`)
        .then(user => commit("setUser", user))
        .catch(console.error)
    },
    getUserList({ commit }) {
      axios
        .get(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/all-user`, { headers: { Authorization: `${keycloak.tokenParsed.typ} ${keycloak.token}` }})
        .then(userList => commit("setUserList", userList))
        .catch(console.error)
    },
    uploadProfilePicture({ commit }, profilePicture) {
      axios
        .post(`${process.env.VUE_APP_UEBUNGSPROJEKT_BE}/user/upload-profile-picture/${keycloak.tokenParsed.sub}`, profilePicture, { headers: { "Content-Type": "multipart/form-data", Authorization: `${keycloak.tokenParsed.typ} ${keycloak.token}`}})
        .then(user => {
          commit("uploadProfilePicture", user.data.profilePicture)
        })
        .catch(console.error);
    }
  },
  getters: {
    getUsername(state) {
      if (state.user !== undefined) return state.user.data.username;
      else return "default";
    },
    getUserId(state) {
      return state.user.userIdKeycloak;
    },
    getUser(state) {
      return state.user.data;
    },
    getUserList(state) {
      return state.userList;
    },
    getUserProfilePicture(state) {
      if (state.user !== undefined) return `data:image/png;base64,${state.user.data.profilePicture}`;
    }
  }
};
