<template>
  <div class="md-layout">
    <gold-course-overview-component
      class="md-layout-item md-small-size-100"
      title="Goldkurs All-Time High"
      value="103.45"
      :date="new Date().toLocaleDateString()" />
    <gold-course-overview-component
      class="md-layout-item md-small-size-100"
      title="Aktueller Goldkurs"
      :value="goldCourseActual"
      :date="new Date().toLocaleDateString()" />
    <gold-course-overview-component
      class="md-layout-item md-small-size-100"
      title="Differenz zum vorherigen Monat"
      :value="differencePreviousMonth"
      :date="new Date().toLocaleDateString()" />
  </div>
</template>

<script>
import GoldCourseOverviewComponent from "./gold-course-overview-component";

export default {
  name: "gold-course-overview-container",
  components: {
    GoldCourseOverviewComponent
  },
  props: {
    goldCourseActual: undefined,
    differencePreviousMonth: undefined
  }
};
</script>

<style scoped lang="scss">
div {
  height: 100%;

  @media (max-width: $small) {
    height: auto;
  }
}
</style>
