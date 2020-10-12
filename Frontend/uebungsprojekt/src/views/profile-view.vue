<template>
  <div>
    <h1>Profil</h1>
    <div class="row">
      <div class="col-lg-6 col-md-12">
        <label for="username">Username</label>
        <input type="text" id="username" :placeholder="getUserInformation.username" readonly/>
      </div>
      <div class="col-lg-6 col-md-12">
        <label for="keycloakId">KeycloakID</label>
        <input type="text" id="keycloakId" :placeholder="getUserInformation.userIdKeycloak" readonly/>
      </div>
      <div class="col-lg-6 col-md-12 row">
        <label class="col-12">Profilbild</label>
        <input class="col-8" ref="file" @change="handleFileUpload" type="file" />
        <div class="col-4">
          <img :src="getUserProfilePicture" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import keycloak from "../main";

export default {
  name: "profile-view",
  data() {
    return {
      file: "",
    }
  },
  created() {
    this.$store.dispatch("user/setUser", {
      userIdKeycloak: keycloak.tokenParsed.sub,
      username: keycloak.tokenParsed.preferred_username,
    });
  },
  computed: {
    getUserInformation() {
      return this.$store.getters["user/getUser"];
    },
    getUserProfilePicture() {
      return this.$store.getters["user/getUserProfilePicture"];
    }
  },
  methods: {
    handleFileUpload(event) {
      const formData = new FormData();

      this.file = event.target.files[0];
      formData.append("image", event.target.files[0]);
      this.$store.dispatch("user/uploadProfilePicture", formData);
    }
  }
}
</script>

<style scoped>
label, input {
  display: block;
}
label {
  font-size: 20px;
}
input {
  background: none;
  border: none;
  border-bottom: 1px solid #aaaaaa;
  color: #aaaaaa;
  margin-bottom: 30px;
  width: 100%;
}
img {
  max-height: 100px;
  width: auto;
}
</style>
