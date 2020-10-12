<template>
  <div>
    <h4>Kommentare:</h4>
    <div class="comment-container" v-for="(comment, index) in getAllComments" :key="index">
      <img :src="'data:image/png;base64,' + comment.user.profilePicture" />
      <span class="comment-content">{{ comment.comment }}</span>
      <!-- <span class="comment-username">{{ comment.user.username }}</span> -->
    </div>
    <form class="row" v-on:submit.prevent="addNewComment(comment)">
      <input class="col-lg-10" type="text" v-model="comment.comment" placeholder="Comment">
      <button type="submit" class="col-lg-2">Senden</button>
    </form>
  </div>
</template>

<script>
import CommentModel from "../../model/comment-model";

export default {
  name: "comment-container",
  data() {
    return {
      comment: CommentModel
    }
  },
  props: {
    task: null
  },
  created() {
    console.log(this.task.comments);
    this.$store.dispatch("comments/getAllComments", this.task.comments);
  },
  computed: {
    getAllComments() {
      return this.$store.getters["comments/getAllComments"];
    }
  },
  methods: {
    addNewComment(comment) {
      comment.user = this.$store.getters["user/getUser"];
      comment.task = this.task;
      delete comment.task.teamMember;
      this.$store.dispatch("comments/addNewComment", comment);
    }
  }
}
</script>

<style scoped>
.row {
  padding: 0 15px;
}
.comment-container {
  border-bottom: 1px solid #2b2e30;
}
img {
  height: 50px;
  margin-right: 10px;
}
input {
  background: none;
  border: none;
  border-bottom: 1px solid #aaaaaa;
  color: #aaaaaa;
  width: 100%;
}
button {
  background: none;
  border: 1px solid #aaaaaa;
  /*border-bottom-right-radius: 5px;
  border-top-right-radius: 5px;
  border-top-left-radius: 5px;*/
  color: #aaaaaa;
  height: 40px;
  transition: .5s all;
}
button:hover {
  background-color: #aaaaaa;
  color: #1c1e1f;
}
</style>
