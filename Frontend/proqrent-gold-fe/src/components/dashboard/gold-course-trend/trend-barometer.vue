<template>
  <div>
    <div class="barometer-container">
      <div id="el" :data-value="differencePreviousMonth">
        <span id="needle"></span>
      </div>
      <p>Der Goldkurs {{ getDifferenceInformation }} um {{ differencePreviousMonth }} und kletter damit auf einen Wert von {{ actualGoldCourse }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "barometer",
  computed: {
    getDifferenceInformation() {
      if (this.differencePreviousMonth > 0) {
        return "steigt";
      }
      return "sinkt";
    }
  },
  props: {
    differencePreviousMonth: undefined,
    actualGoldCourse: undefined
  }
};
</script>

<style scoped lang="scss">
#el {
  height: 100px;
  overflow: hidden;
  position: relative;

  &:before {
    background: $pq-blue;
    border-radius: 200px 200px 0 0;
    box-shadow: 3px 1px 8px rgba(0, 0, 0, 0.15) inset;
    content: "";
    height: 100px;
    position: absolute;
    width: 200px;
  }

  &:after {
    background: $background-color;
    border-radius: 140px 140px 0 0;
    bottom: 0;
    box-shadow: 3px 1px 8px rgba(0, 0, 0, 0.15);
    color: $text-color;
    content: attr(data-value);
    font-size: 45px;
    font-weight: 200;
    height: 70px;
    left: 30px;
    line-height: 95px;
    position: absolute;
    text-align: center;
    width: 140px;
    z-index: 3;
  }

  span {
    background: $text-color;
    border-radius: 4px;
    bottom: -4px;
    box-shadow: 3px -1px 4px rgba(0, 0, 0, 0.4);
    display: block;
    height: 8px;
    left: 10px;
    position: absolute;
    width: 90px;
    transform-origin: 100% 4px;
    transition: all 1s;
    transform: rotate(120deg);
  }
}

p {
  font-weight: 200;
  text-align: center;
  width: 200px;
}
</style>
