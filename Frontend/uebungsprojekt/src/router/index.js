import Vue from "vue";
import VueRouter from "vue-router";
import LandingPage from "../views/landing-page";
import ShowTasks from "../views/created-tasks.vue";
import CreateTask from "../views/create-task";
import DetailTask from "../views/detail-task";
import PermissionDenied from "../shared/permission-denied";
import ShowTeamTasks from "../views/project-board";
import ListProjects from "../views/list-projects";
import CreateProject from "../views/create-project";
import ProfileView from "../views/profile-view";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "LandingPage",
    component: LandingPage
  },
  {
    path: "/show-tasks",
    name: "ShowTasks",
    component: ShowTasks
  },
  {
    path: "/create-task",
    name: "CreateTask",
    component: CreateTask
  },
  {
    path: "/detail-task/:id",
    name: "DetailTask",
    component: DetailTask
  },
  {
    path: "/permission-denied",
    name: "PermissionDenied",
    component: PermissionDenied
  },
  {
    path: "/show-team-tasks",
    name: "ShowTeamTasks",
    component: ShowTeamTasks
  },
  {
    path: "/projects",
    name: "Projects",
    component: ListProjects
  },
  {
    path: "/create-project",
    name: "CreateProject",
    component: CreateProject
  },
  {
    path: "/profile-view",
    name: "ProfileView",
    component: ProfileView
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
