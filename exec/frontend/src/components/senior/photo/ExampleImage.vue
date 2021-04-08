<template>
  <div class="exampleImage">
    <img class="image"
      :src="myFileInfo.fileUrl"
    >
  </div>
</template>

<script>
export default {
  name: "ExampleImage",
  props: ['fileInfo',],
  computed: {
    myFileInfo: function() {
      return this.fileInfo
    },
  },
}
</script>