<template>
  <div class="menuLoading">
    <img class="nari"
      src="@/assets/senior/Nari.png"
    >
    <div class="speechBubble">
      <img class="bubbleImage"
      src="@/assets/senior/SpeechBubble.png"
      >
      <h1 class="speech">{{ speech }}</h1>
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/menuLoading.scss';
export default {
  name: "MenuLoading",
  props: {
    speech: {
      type: String,
      required: true,
    },
  }
};
</script>