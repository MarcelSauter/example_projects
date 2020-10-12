import axios from "axios";
import keycloak from "@/main";

const LocalDate = require("@js-joda/core").LocalDate;

export default {
  namespaced: true,
  state: {
    employees: undefined,
  },
  mutations: {
    setEmployees(state, employees) {
      state.employees = employees;
    },
    synchronizeEmployeeWithKeycloak(state, employees) {
      employees.forEach(newEmployee => {
        let included = false;
        state.employees.forEach(employee => {
          newEmployee.id === employee.id ? included = true : "";
        })
        !included ? state.employees.push(newEmployee) : "";
      })
    }
  },
  actions: {
    getAllEmployeesFromKeycloak({ commit }) {
      axios.get(process.env.VUE_APP_AUTH_SERVICE +
        "/admin/realms/proQrentGold/clients/b620887e-6cc5-43e3-88ca-7419069e7ec3/roles/employee/users",
        { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(employees => commit("synchronizeEmployeeWithKeycloak", employees.data))
        .catch(console.error);
    },
    getAllEmployeesByTimePeriod({ commit }, timePeriod = "") {
      const time = timePeriod === "" ? LocalDate.now().withDayOfMonth(1) : timePeriod;
      axios.get(process.env.VUE_APP_BE_CLIENT + "/employees/planned-hours/" + time,
        { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(employees => commit("setEmployees", employees.data))
        .catch(console.error);
    },
    savePlannedHoursEmployees({ commit }, employees) {
      axios.post(process.env.VUE_APP_BE_CLIENT + "/employees/planned-hours", employees,
        { headers: { Authorization: `Bearer ${keycloak.token}`}})
        .then(employees => console.log(employees.data))
        .catch(console.error);
    }
  },
  getters: {
    getAllEmployeesByTimePeriod(state) {
      return state.employees;
    },
  }
}
