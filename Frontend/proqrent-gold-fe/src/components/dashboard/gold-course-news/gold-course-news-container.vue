<template>
  <div class="gold-course-news-container">
    <h3>Goldkurs-News</h3>
    <gold-course-news-component
      v-for="(singleNews, index) in goodNews" :key="index" :good-news="singleNews" />
  </div>
</template>

<script>
import GoldCourseNewsComponent from "./gold-course-news-component";
import goodNewsModel from "@/model/good-news-model";

export default {
  name: "gold-course-news-container",
  components: {
    GoldCourseNewsComponent
  },
  props: {
    goodNews: goodNewsModel
  }
};
</script>

<style scoped lang="scss">
.gold-course-news-container {
  border: 1px solid $text-color;
  height: 100%;
}
h3 {
  text-align: center;
}
</style>
