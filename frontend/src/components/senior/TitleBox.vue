<template>
  <div class="titleBox">
      <h1 class="title">{{ title }}</h1>
  </div>
</template>

<script>
import "@/components/css/senior/titleBox.scss";
export default {
  name: "titleBox",
  props: {
    title: {
      type: String,
      required: true,
    },
  }
};
</script>
