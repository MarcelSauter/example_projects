import UserModel from "./user-model.js";
import CommentModel from "./comment-model.js";

export default {
  taskId: null,
  author: UserModel,
  title: "",
  category: "",
  project: "",
  teamMember: [UserModel],
  comments: [CommentModel],
  description: "",
  status: "backlog"
};
