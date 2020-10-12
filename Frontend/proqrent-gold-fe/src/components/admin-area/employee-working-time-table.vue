<template>
  <div v-if="employees">
    <global-time v-on:saveGlobalTime="setGlobalTime" :base-data="baseData" />

    <form v-on:submit.prevent="savePlannedHours(employees)">
      <md-table v-model="employees" md-sort="name" md-sort-order="asc" md-card>
        <md-table-toolbar>
          <h4 class="md-title">Users</h4>
          <month-picker class="month-picker" v-on:setPickedDate="setPickedDate"/>
          <md-button v-on:click="synchroniseWithKeycloak()" type="button">Mit Keycloak synchronisieren</md-button>
        </md-table-toolbar>

        <md-table-row slot="md-table-row" slot-scope="{ item }">
          <md-table-cell md-label="Mitarbeiter" md-sort-by="lastName">{{ item.firstName }} {{ item.lastName }}</md-table-cell>
          <md-table-cell md-label="Globale Zeit Mitarbeiter" md-sort-by="plannedHours">
            <md-field>
              <md-input :disabled="!sameDate" v-model="item.plannedHours" type="number"></md-input>
            </md-field>
          </md-table-cell>
          <md-table-cell md-label="Zeit Korrektur" md-sort-by="">
            <md-field>
              <md-input :disabled="!sameDate" v-model="item.timeCorrection" type="number"></md-input>
            </md-field>
          </md-table-cell>
        </md-table-row>
      </md-table>
      <md-button type="submit" v-if="sameDate">Speichern</md-button>
    </form>
  </div>
</template>

<script>
import EmployeeModel from "@/model/employee-model";
import GlobalTime from "./global-time";
import MonthPicker from "./month-picker";
import BaseDataModel from "@/model/base-data-model";
import { LocalDate } from "@js-joda/core";

export default {
  name: "employee-working-time-table",
  components: {
    GlobalTime,
    MonthPicker
  },
  data() {
    return {
      timePeriod: LocalDate.now().withDayOfMonth(1),
      pickedDate: undefined
    };
  },
  props: {
    employees: EmployeeModel,
    baseData: BaseDataModel
  },
  computed: {
    sameDate() {
      return (this.pickedDate === undefined || this.timePeriod.compareTo(this.pickedDate) === 0);
    }
  },
  methods: {
    savePlannedHours(employees) {
      employees.forEach(
        employee => (!employee.plannedHours) ? employee.plannedHours = this.$store.getters["baseData/getBaseData"].globalTime.toString() : ""
      );
      this.$store.dispatch("employee/savePlannedHoursEmployees", employees);
    },
    setGlobalTime(globalTime) {
      this.$store.dispatch("baseData/setGlobalTime", + globalTime);
    },
    synchroniseWithKeycloak() {
      this.$store.dispatch("employee/getAllEmployeesFromKeycloak");
    },
    setPickedDate(pickedDate) {
      this.pickedDate = LocalDate.parse(pickedDate);
    }
  }
};
</script>

<style scoped lang="scss">
.month-picker {
  color: darken($background-color, 10%);
  margin: 10px auto;
}
.md-table {
  margin-bottom: 30px;
  min-height: 300px;

  .md-input {
    color: $text-color !important;
    font-size: 13px !important;
  }
}
.md-button {
  border: 1px solid $text-color;
  float: right;
  height: 42px;
  text-transform: none;
}
</style>
