<template>
  <div class="exampleBox">
    <p class="example">① {{ example1 }}</p>
    <p class="example">② {{ example2 }}</p>
    <p v-if="example3" class="example">③ {{ example3 }}</p>
    <p v-if="example4" class="example">④ {{ example4 }}</p>
  </div>
</template>

<script>
// import '@/components/css/senior/familyQuiz.scss';
import '@/components/css/senior/exampleBox.scss';
export default {
  name: "titleBox",
  props: {
    example1: {
      type: String,
      required: true,
    },
    example2: {
      type: String,
      required: true,
    },
    example3: {
      type: String,
      required: false,
    },
    example4: {
      type: String,
      required: false,
    },
  }
};
</script>
