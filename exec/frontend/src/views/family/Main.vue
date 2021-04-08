<template>
  <v-app>
    <!-- 가족들화면 메인페이지 -->
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
  mounted(){
    console.log(this.$store.state.adminId);
  }
}
</script>

<style>

</style>