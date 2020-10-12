<template>
  <div class="col-lg-2 col-md-4 col-sm-12 team-member-wrapper">
    <div class="team-member-container" v-bind:class="[activated ? 'activated' : '']" @click="addTeamMember(user); activated = !activated">
      <img :src="'data:image/png;base64,' + user.profilePicture" />
      <h5>{{ user.username }}</h5>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-team-member",
  data() {
    return {
      activated: false
    };
  },
  props: {
    user: null
  },
  methods: {
    addTeamMember(user) {
      this.$emit("addTeamMember", { userId: user.userId, userIdKeycloak: user.userIdKeycloak, username: user.username }, this.activated);
    }
  }
};
</script>

<style scoped>
.team-member-wrapper {
  padding: 0 10px;
}
.team-member-container {
  align-items: center;
  border-radius: 5px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
  transition: .8s all;
}
.activated {
  background-color: #2b2e30;
}
.team-member-container:hover {
  background-color: #2b2e30;
}
h5 {
  font-weight: 200;
  margin: 5px;
}
img {
  border-radius: 5px;
  margin-right: 10px;
  height: 50px;
}
</style>
