import UserModel from "./user-model";
import TaskModel from "./task-model.js";

export default {
  projectId: null,
  projectOwner: UserModel,
  name: "",
  description: "",
  projectMember: [UserModel],
  tasks: [TaskModel]
}
