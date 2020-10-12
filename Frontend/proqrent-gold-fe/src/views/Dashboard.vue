<template>
  <div class="dashboard-container">
    <h1>proQrent-Gold</h1>
    <div class="md-layout">
      <div class="gold-course-container gold-course-news md-layout-item md-size-30 md-medium-size-100">
        <gold-course-news :good-news="getGoodNews" />
      </div>
      <div class="gold-course-container gold-course-diagram md-layout-item md-size-70 md-medium-size-100">
        <gold-course-diagram />
      </div>
      <div class="gold-course-container gold-course-overview md-layout-item md-size-70 md-medium-size-100">
        <gold-course-overview :gold-course-actual="getActualGoldCourse" :difference-previous-month="getDifferencePreviousMonth" />
      </div>
      <div class="gold-course-container gold-course-trend md-layout-item md-size-30 md-medium-size-100">
        <gold-course-trend :actual-gold-course="getActualGoldCourse" :difference-previous-month="getDifferencePreviousMonth" />
      </div>
    </div>
  </div>
</template>

<script>
import GoldCourseNews from "../components/dashboard/gold-course-news/gold-course-news-container";
import GoldCourseDiagram from "../components/dashboard/gold-course-diagram/gold-course-diagram";
import GoldCourseOverview from "../components/dashboard/gold-course-overview/gold-course-overview-container";
import GoldCourseTrend from "../components/dashboard/gold-course-trend/gold-course-trend-container";

export default {
  name: "Dashboard",
  components: {
    GoldCourseNews,
    GoldCourseDiagram,
    GoldCourseOverview,
    GoldCourseTrend
  },
  created() {
    this.$store.dispatch("goldCourse/getActualGoldCourse");
    this.$store.dispatch("goldCourse/getDifferencePreviousMonth");
    this.$store.dispatch("goldCourse/getGoldCoursesForDiagram");
    this.$store.dispatch("goodNews/getGoodNews");
  },
  computed: {
    getActualGoldCourse() {
      return this.$store.getters["goldCourse/getActualGoldCourse"];
    },
    getDifferencePreviousMonth() {
      return this.$store.getters["goldCourse/getDifferencePreviousMonth"];
    },
    getGoodNews() {
      return this.$store.getters["goodNews/getGoodNews"];
    }
  }
};
</script>

<style scoped lang="scss">
.dashboard-container {
  width: 100%;

  .gold-course {
    &-container {
      padding: 20px 40px 20px 0;
    }

    &-news {
      float: left;
      width: 35%;
    }
  }
}
</style>
