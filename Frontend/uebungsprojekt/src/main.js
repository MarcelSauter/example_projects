import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import Keycloak from "keycloak-js";
import axios from "axios";

Vue.component("font-awesome-icon", FontAwesomeIcon);

Vue.config.productionTip = false;

let initOptions = {
  url: process.env.VUE_APP_AUTH_SERVICE,
  realm: process.env.VUE_APP_AUTH_REALM,
  clientId: process.env.VUE_APP_AUTH_CLIENT_ID
};

let keycloak = Keycloak(initOptions);

keycloak
  .init({ onLoad: "login-required" })
  .success(auth => {

    if (!auth) {
      window.location.reload();
    } else {
      console.log("Authenticated");
    }

    new Vue({
      router,
      store,
      render: h => h(App)
    }).$mount("#app");

    localStorage.setItem("vue-token", keycloak.token);
    localStorage.setItem("vue-refresh-token", keycloak.refreshToken);
    console.log(keycloak.token);

    axios.defaults.headers.common['Authorization'] = `${keycloak.tokenParsed.typ} ${keycloak.token}`;
    axios.get(`http://localhost:8079/user-active/${keycloak.tokenParsed.preferred_username}/${keycloak.tokenParsed.sub}`)

    setInterval(() => {
      keycloak
        .updateToken(70)
        .success(refreshed => {
          if (refreshed) {
            console.debug("Token refreshed" + refreshed);
          } else {
            console.warn(
              "Token not refreshed, valid for " +
                Math.round(
                  keycloak.tokenParsed.exp +
                    keycloak.timeSkew -
                    new Date().getTime() / 1000
                ) +
                " seconds"
            );
          }
        })
        .error(() => {
          console.error("Failed to refresh token");
        });
    }, 60000);
  })
  .error(() => {
    console.error("Authenticated Failed");
  });

export default keycloak;
