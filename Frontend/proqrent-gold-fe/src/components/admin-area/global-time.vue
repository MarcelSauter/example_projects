<template>
  <div>
    <form v-on:submit.prevent="saveGlobalTime(baseData.globalTime)" class="md-layout global-time-container">
      <h5 class="md-layout-item md-size-80">
        Globale Zeit
        <input v-model="baseData.globalTime" @change="changed = true" />
      </h5>
      <div class="md-layout-item md-size-20 global-time-save-button-container">
        <button type="submit" v-if="changed">Speichern</button>
      </div>
    </form>
  </div>
</template>

<script>
import BaseDataModel from "@/model/base-data-model";

export default {
  name: "global-time",
  data() {
    return {
      changed: false
    };
  },
  props: {
    baseData: BaseDataModel
  },
  methods: {
    saveGlobalTime(globalTime) {
      this.$emit("saveGlobalTime", globalTime);
    }
  }
};
</script>

<style scoped lang="scss">
.global-time {
  &-container {
    h5 {
      font-size: 15px;
      font-weight: 200;

      input {
        background: none;
        border: 1px solid $text-color;
        color: $text-color;
        padding: 5px 10px;
        width: 50px;
      }
    }
  }

  &-save-button-container {
    display: flex;
    align-items: center;
    justify-content: flex-end;

    button {
      background: none;
      border: 1px solid $text-color;
      color: $text-color;
      cursor: pointer;
      float: right;
      padding: 10px 40px;
      transition: .5s all;

      &:hover {
        background: $text-color;
        color: $background-color;
      }
    }
  }
}
</style>
