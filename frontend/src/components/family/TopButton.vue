<template>
  <v-container style="text-align-last: end;">
    <v-btn
      color="#FEA601"
      dark
      @click="$vuetify.goTo(0)"
    >
      <v-icon>mdi-arrow-up</v-icon>
    </v-btn>
  </v-container>
</template>

<script>
export default {
  name: 'TopButton',
}
</script>

<style>

</style>