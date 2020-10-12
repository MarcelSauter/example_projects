<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark">
      <router-link class="navbar-brand" to="/">TaskManager</router-link>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <router-link class="nav-link" exact-active-class="active-class" to="/">Home</router-link>
          </li>
          <li class="nav-item active">
            <router-link class="nav-link" exact-active-class="active-class" to="/projects">Projekte</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" exact-active-class="active-class" to="/show-team-tasks">
              Board<span class="badge badge-dark" v-if="getOpenAssignedTasks.length > 0">Progess: {{ getOpenAssignedTasks.length }}</span>
            </router-link>
          </li>
          <li class="nav-item active">
            <router-link class="nav-link" exact-active-class="active-class" to="/create-task">Ticket erstellen</router-link>
          </li>
          <li class="nav-item active">
            <router-link class="nav-link" exact-active-class="active-class" to="/create-project">Projekt erstellen</router-link>
          </li>
        </ul>

        <div class="dropdown show">
          <a class="btn dropdown-toggle navbar-dropdown" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <img :src="getUserProfilePicture" />
            {{ getUsername }}
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <router-link class="dropdown-item" to="/profile-view">Einstellungen</router-link>
            <a class="dropdown-item" href="#" @click="logout">Logout</a>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import keycloak from "../main";

export default {
  name: 'navbar',
  created() {
    this.$store.dispatch("user/setUser", {
      userIdKeycloak: keycloak.tokenParsed.sub,
      username: keycloak.tokenParsed.preferred_username,
    });
    this.$store.dispatch("user/getUserList");
    this.$store.dispatch("tasks/getAssignedTasks");
    this.$store.dispatch("projects/getAllAssignedProjects");
    this.$store.dispatch("projects/getAllOwnedProjects");
  },
  computed: {
    getOpenAssignedTasks() {
      return this.$store.getters["tasks/getOpenAssignedTasks"];
    },
    getUsername() {
      return this.$store.getters["user/getUsername"];
    },
    getUserProfilePicture() {
      return this.$store.getters["user/getUserProfilePicture"];
    }
  },
  methods: {
    logout() {
      keycloak.logout();
    }
  }
};
</script>

<style scoped>
.navbar {
  background-color: #2b2e30;
}
.nav-link {
  color: #aaa !important;
}
.active-class {
  border-bottom: 1px solid #aaa;
}
.badge {
  color: #aaaaaa;
  font-weight: 200;
  margin-left: 5px;
}
.navbar-dropdown {
  background: none;
  border-radius: 5px;
  color: #aaaaaa;
  height: 40px;
  transition: .5s all;
  width: 150px;
}
.navbar-dropdown:hover {
  background-color: #3c4043;
}
.navbar-dropdown img {
  border-radius: 5px;
  height: 100%;
}
</style>
