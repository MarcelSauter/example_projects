import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import Keycloak from "keycloak-js";
import VueMaterial from "vue-material";
import "vue-material/dist/vue-material.min.css";
import "vue-material/dist/theme/default-dark.css";

Vue.config.productionTip = false;
Vue.use(VueMaterial);

const initOptions = {
  url: process.env.VUE_APP_AUTH_SERVICE,
  realm: process.env.VUE_APP_AUTH_REALM,
  clientId: process.env.VUE_APP_AUTH_CLIENT_ID
}
const keycloak = Keycloak(initOptions);

keycloak.init({ onLoad: "login-required" }).then(auth => {

  if (!auth) {
    window.location.reload();
  }

  new Vue({
    router,
    store,
    template: "<h1>Test</h1>",
    render: h => h(App)
  }).$mount("#app");

  if (keycloak.token != null && keycloak.refreshToken !== undefined) {
    localStorage.setItem("vue-token", keycloak.token);
    localStorage.setItem("vue-refresh-token", keycloak.refreshToken);
  }

  setInterval(() => {
    keycloak
      .updateToken(70)
      .catch(() => {
        console.error("Failed to refresh token");
      });
  }, 60000);
})
.catch(() => {
  console.error("Authenticated Failed");
});

export default keycloak;
