<template>
  <div v-if="getAllEmployees && getBaseData">
    <h1>Admin-Konsole</h1>
    <employee-working-time-table :employees="getAllEmployees" :base-data="getBaseData" />
  </div>
</template>

<script>
import EmployeeWorkingTimeTable from "../components/admin-area/employee-working-time-table";

export default {
  name: "AdminArea",
  components: {
    EmployeeWorkingTimeTable
  },
  created() {
    this.$store.dispatch("employee/getAllEmployeesByTimePeriod");
    this.$store.dispatch("baseData/getBaseData");
  },
  computed: {
    getBaseData() {
      return this.$store.getters["baseData/getBaseData"];
    },
    getAllEmployees() {
      const employees = this.$store.getters["employee/getAllEmployeesByTimePeriod"];
      employees ? employees.forEach(employee => (employee.plannedHours === this.$store.getters["baseData/getBaseData"].globalTime) ? delete employee.plannedHours : "") : "";
      return employees;
    }
  }
};
</script>

<style scoped>
</style>
