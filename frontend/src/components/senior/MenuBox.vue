<template>
  <div class="menuBox">
    <img class="menuImage"
      src="@/assets/senior/SeniorMain1.jpg"
    >
    <div class="menuBorder"></div>
    <button class="menuButton">{{ menuButton }}</button>
  </div>
</template>

<script>
import "@/components/css/senior/menuBox.scss";
export default {
  name: "menuBox",
  props: {
    menuButton: {
      type: String,
      required: true,
    },
  }
};
</script>
