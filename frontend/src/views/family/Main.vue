<template>
  <v-app>
    <FamilyAppBar />
    <FamilyServiceInfo />
    <AboutTeam />
    <TopButton />
    <Footer />
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import FamilyServiceInfo from '@/components/family/FamilyServiceInfo.vue'
import AboutTeam from '@/components/family/AboutTeam.vue'
import Footer from '@/components/family/Footer.vue'
import TopButton from '@/components/family/TopButton.vue'

export default {
  name: "Main",
  components: {
    FamilyAppBar,
    FamilyServiceInfo,
    AboutTeam,
    TopButton,
    Footer,
  },
}
</script>

<style>

</style>