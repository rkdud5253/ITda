<template>
  <div class="photoDiaryLoading">
    <MenuLoading speech="오늘의 사진 일기를 남겨보세요.
    가족들에게 전송해드립니다!"/>
  </div>
</template>

<script>
import '@/components/css/senior/photoDiary.scss';
import MenuLoading from '@/components/senior/MenuLoading.vue';
export default {
  name: "PhotoDiaryLoading",
  components: {
    MenuLoading,
  },
  mounted() {
    this.$store.commit("TTS", "오늘의 사진 일기를 남겨보세요.");
  }
}
</script>