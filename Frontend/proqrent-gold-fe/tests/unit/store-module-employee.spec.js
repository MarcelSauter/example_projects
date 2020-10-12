import employeeStore from "../../src/store/modules/employee";

const EMPLOYEES = [
  {
    id: 1,
    firstName: "Marcel",
    lastName: "Sauter",
    scheduledHours: "160",
    reachedHours: null
  },
  {
    id: 2,
    firstName: "Jonathan",
    lastName: "Schell",
    scheduledHours: "120",
    reachedHours: null
  },
  {
    id: 3,
    firstName: "Steffen",
    lastName: "Ebert",
    scheduledHours: "165",
    reachedHours: null
  }
];

let url = "";
let body = {};

jest.mock("axios", () => ({
  get: (_url, _body) => {
    return new Promise((resolve) => {
      url = _url;
      body = _body;
      resolve(true);
    });
  }
}));

describe("Employee Store", () => {
  describe("Mutations", () => {
    it("should set employees to state.employees", () => {
      const state = { employees: [] };
      employeeStore.mutations.setEmployees(state, EMPLOYEES);
      expect(state.employees).toBe(EMPLOYEES);
    });
  });

  describe("Actions", () => {
    it("should getAllEmployees", () => {
      const commit = jest.fn();
      employeeStore.actions.getAllEmployees({ commit });
      expect(url).toBe(process.env.VUE_APP_BE_CLIENT + "/list-employees");
    });
  });

  describe("Getters", () => {
    it("should return EMPLOYEES", () => {
      const state = { employees: EMPLOYEES };
      const employees = employeeStore.getters.getAllEmployees(state);
      expect(employees).toBe(EMPLOYEES);
    });
  });
});
