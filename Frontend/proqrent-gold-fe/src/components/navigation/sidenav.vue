<template>
  <div class="sidenav-container">
    <div class="icon-container">
      <router-link to="/dashboard" exact-active-class="active-route">
        <md-icon>insert_chart_outlined</md-icon>
      </router-link>
    </div>
    <div class="icon-container">
      <router-link to="/admin-area" exact-active-class="active-route">
        <md-icon>account_balance_wallet</md-icon>
      </router-link>
    </div>
    <div class="icon-container">
      <router-link to="#" exact-active-class="active-route">
        <md-icon>payment</md-icon>
      </router-link>
    </div>
    <div class="icon-container logout-button" @click="logout">
      <md-icon>exit_to_app</md-icon>
    </div>
  </div>
</template>

<script>
import keycloak from "../../main";

export default {
  name: "sidenav",
  methods: {
    logout() {
      keycloak.logout();
    }
  }
};
</script>

<style scoped lang="scss">
.sidenav-container {
  background-color: $pq-blue;
  float: left;
  height: 100%;
  position: fixed;
  width: 80px;

  .icon-container {
    align-items: center;
    cursor: pointer;
    display: flex;
    justify-content: center;
    padding-top: 30px;

    i {
      color: rgba(197, 172, 172, .3);
      font-size: 30px !important;
    }
  }

  .logout-button {
    bottom: 20px;
    left: 28px;
    margin: 0 auto;
    position: absolute;

    i {
      color: $text-color;
    }
  }
}
.active-route i {
  color: $text-color !important;
}
</style>
