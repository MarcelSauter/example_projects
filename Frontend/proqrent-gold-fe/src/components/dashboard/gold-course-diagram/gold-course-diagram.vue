<template>
  <div>
    <div class="graph-container">
      <canvas id="gold-course-chart"></canvas>
    </div>
  </div>
</template>

<script>
import Chart from "chart.js";
import MockChartData from "./mock-chart-data";

export default {
  name: "gold-course-diagram",
  data() {
    return {
      mockChartData: MockChartData
    };
  },
  mounted() {
    this.mockChartData.data.datasets[0].data = this.$store.getters["goldCourse/getGoldCoursesForDiagramValue"];
    this.mockChartData.data.labels = this.$store.getters["goldCourse/getGoldCoursesForDiagrammMonth"];
    this.createChart("gold-course-chart", this.mockChartData);
  },
  methods: {
    createChart(chartId, chartData) {
      const chartContainer = document.getElementById(chartId);
      new Chart(chartContainer, {
        type: 'line',
        data: chartData.data,
        options: chartData.options
      });
    }
  }
};
</script>

<style scoped lang="scss">
.graph-container {
  border: 1px solid $text-color;
}
</style>
