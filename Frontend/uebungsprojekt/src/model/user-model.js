import TaskModel from "./task-model";
import ProjectModel from "./project-model.js";

export default {
  userId: null,
  userIdKeycloak: "",
  username: "",
  profilePicture: null,
  projectOwner: [ProjectModel],
  projects: [ProjectModel],
  tasks: [TaskModel]
}
