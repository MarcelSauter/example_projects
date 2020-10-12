<template>
  <div class="news-container">
    <span class="news-headline">{{ goodNews.title }}</span>
    <span class="news-date">{{ goodNews.date }}</span>
    <p class="news-description">{{ goodNews.description }}</p>
    <span class="news-increase">+{{ goodNews.influence }}</span>
  </div>
</template>

<script>
import goodNewsModel from "@/model/good-news-model";

export default {
  name: "gold-course-news-component",
  props: {
    goodNews: goodNewsModel
  }
};
</script>

<style scoped lang="scss">
.news-container {
  background-color: $pq-blue;
  border-bottom: 1px solid $text-color;
  border-top: 1px solid $text-color;
  margin-bottom: 10px;
  padding: 10px 20px 20px 20px;
  width: 100%;

  .news {
    &-headline {
      font-size: 20px;
    }

    &-date {
      float: right;
      font-size: 12px;
    }

    &-description {
      font-weight: 200;
      margin: 0;
    }

    &-increase {
      color: $increase-green;
      font-weight: bold;
      float: right;
      margin-bottom: 10px;
    }
  }
}
</style>
