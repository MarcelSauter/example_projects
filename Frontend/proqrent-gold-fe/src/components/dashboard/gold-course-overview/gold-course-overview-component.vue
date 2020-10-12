<template>
  <div class="overview-container">
    <h3>{{ title }}</h3>
    <div class="overview-value">
      <span>{{ value }}</span>
    </div>
    <div class="overview-date">
      <span>{{ date }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "gold-course-overview-all-time-high",
  props: {
    title: null,
    value: null,
    date: null
  }
};
</script>

<style scoped lang="scss">
.overview {
  &-container {
    border: 1px solid $text-color;
    float: left;
    text-align: center;
  }

  &-value {
    font-size: 75px;
    font-weight: 900;
    margin: 50px 0;
  }
}
</style>
