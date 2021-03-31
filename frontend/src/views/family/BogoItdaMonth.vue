<template>
  <v-app>
    <FamilyAppBar />
    <v-container>
      <div class="my-5">
        <h1 style="color: #FEA601">보고잇다</h1>
        <h4>해당 날짜를 클릭하여 일일 보고서를 확인하세요</h4>
        <h4>어르신의 기능별 사용 결과를 볼 수 있어요</h4>
      </div>
      <v-card class="my-10">
        <Calendar />
      </v-card>
    </v-container>
    <div class="mb-5">
      <TopButton />
    </div>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import Calendar from '@/components/family/report/Calendar.vue'
import TopButton from '@/components/family/TopButton.vue'

export default {
  name: "BogoItdaMonth",
  components: {
    FamilyAppBar,
    Calendar,
    TopButton,
  },
}
</script>

<style>

</style>