<template>
  <div class="gold-course-trend-container">
    <h3>Trend</h3>
    <div class="barometer-container">
      <trend-barometer :difference-previous-month="differencePreviousMonth" :actual-gold-course="actualGoldCourse" />
    </div>
  </div>
</template>

<script>
import TrendBarometer from "./trend-barometer";

export default {
  name: "gold-course-trend-container",
  props: {
    differencePreviousMonth: undefined,
    actualGoldCourse: undefined
  },
  components: {
    TrendBarometer
  }
};
</script>

<style scoped lang="scss">
.gold-course-trend-container {
  border: 1px solid $text-color;

  h3 {
    text-align: center;
  }

  .barometer-container {
    align-items: center;
    display: flex;
    justify-content: center;
  }
}
</style>
