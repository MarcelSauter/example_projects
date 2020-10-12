<template>
  <div class="month-picker-container">
    <vue-monthly-picker
      v-model="getActualYearAndMonth"
      dateFormat="MMMM YYYY"
      v-bind:monthLabels="monthLabels"
      selectedBackgroundColor="#303030"
      alignment="center"
      inputClass="{'Input': 'Label'}"
      :clearOption="false" />
  </div>
</template>

<script>
import VueMonthlyPicker from "vue-monthly-picker";
import { LocalDate } from "@js-joda/core";

export default {
  name: "month-picker",
  components: {
    VueMonthlyPicker
  },
  data() {
    return {
      monthLabels: ["Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"],
      startDate: ""
    };
  },
  computed: {
    getActualYearAndMonth: {
      get() {
        if (this.startDate === "") {
          return LocalDate.now().year() + "-" + LocalDate.now().month();
        } else {
          return this.startDate;
        }
      },
      set(newDate) {
        const date = new Date(newDate.toLocaleString());
        const month = date.getMonth() + 1;
        const timePeriod = date.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-01";

        this.$store.dispatch("employee/getAllEmployeesByTimePeriod", timePeriod);
        this.$emit("setPickedDate", timePeriod);

        this.startDate = newDate;
      }
    }
  }
};
</script>

<style scoped lang="scss">
.month-picker-container {
  border: 1px solid $text-color;
  margin: 10px 0;
  padding: 10px;
  transition: .5s all;
  width: 400px;

  &:hover {
    background: lighten($background-color, 10%);
  }
}
</style>
