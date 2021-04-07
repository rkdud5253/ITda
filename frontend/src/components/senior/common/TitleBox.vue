<template>
  <div class="titleBox">
    <img class="nari"
      src="@/assets/senior/Nari.png"
    >
    <h1 class="title">{{ title }}</h1>
  </div>
</template>

<script>
export default {
  name: "TitleBox",
  props: {
    title: {
      type: String,
      required: true,
    },
  }
};
</script>