<template>
  <v-footer
    class="main-footer"
    padless
    style="justify-content: center; background-color: #FFF9E9;"
  >
    <v-card
      flat
      tile
      class="text-center"
      color="#FFF9E9"
    >
      <v-card-text>
        <v-btn
          class="mx-3"
          style="color: #FC5355"
          icon
        >
          <v-icon size="24px">mdi-git</v-icon>
        </v-btn>
        <v-btn
          class="mx-3"
          style="color: #FC5355"
          icon
        >
          <v-icon size="24px">mdi-information-outline</v-icon>
        </v-btn>
        <v-btn
          class="mx-3"
          style="color: #FC5355"
          icon
        >
          <v-icon size="24px">mdi-dots-horizontal-circle</v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="black--text pt-0">
        홀로어르신과 가족들을 이어주는 다리 <strong>잇다</strong>를 통해 어르신의 소중한 일상을 기록하세요
      </v-card-text>

      <v-card-text class="grey--text">
        SSAFY 4th A404
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
}
</script>

<style>

</style>