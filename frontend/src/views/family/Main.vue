<template>
  <v-app>
    <FamilyAppBar />
    <FamilyServiceInfo />
    <AboutTeam />
    <Footer />
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import FamilyServiceInfo from '@/components/family/FamilyServiceInfo.vue'
import AboutTeam from '@/components/family/AboutTeam.vue'
import Footer from '@/components/family/Footer.vue'

export default {
  name: "Main",
  components: {
    FamilyAppBar,
    FamilyServiceInfo,
    AboutTeam,
    Footer,
  },
}
</script>

<style>

</style>