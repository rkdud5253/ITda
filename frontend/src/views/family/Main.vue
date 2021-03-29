<template>
  <v-app>
    <FamilyAppBar />
    <FamilyServiceInfo />
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import FamilyServiceInfo from '@/components/family/FamilyServiceInfo.vue'

export default {
  name: "Main",
  components: {
    FamilyAppBar,
    FamilyServiceInfo,
  },
}
</script>

<style>

</style>