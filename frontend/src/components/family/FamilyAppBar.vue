<template>
  <!-- 상단바 --> 
  <v-app-bar
    app
    flat
    color="#FFDD71"
  >
    <!-- 크기 줄였을 때 메뉴 햄버거바 -->
    <v-app-bar-nav-icon
      class="hidden-md-and-up"
    />

    <!-- 로고 및 메뉴 -->
    <v-container class="mx-auto py-0">
      <v-row align="center">
        <!-- 로고 -->
        <v-img
          :src="require('@/assets/family/itda.png')"
          class="mr-5"
          contain
          height="50"
          width="50"
          max-width="50"
          @click="goMain"
        />

        <!-- 페이지 메뉴버튼 -->
        <v-btn
          class="hidden-sm-and-down"
          :ripple="false"
          text
          @click="goMain"
        >
          <h4>서비스소개</h4>
        </v-btn>
        <v-btn
          class="hidden-sm-and-down"
          :ripple="false"
          text
          @click="goReport"
        >
          <h4>보고잇다</h4>
        </v-btn>
        <v-btn
          class="hidden-sm-and-down"
          :ripple="false"
          text
        >
          <h4>퀴즈잇다</h4>
        </v-btn>

        <v-spacer />

        <!--로그인시 마이페이지, 로그아웃-->
        <v-btn
          :ripple="false"
          text
        >
          <h4>마이페이지</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
        >
          <h4>로그아웃</h4>
        </v-btn>

        <!-- 비로그인시 회원가입, 로그인 -->
        <!-- <v-btn
          :ripple="false"
          text
        >
          <h4>회원가입</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
        >
          <h4>로그인</h4>
        </v-btn> -->

      </v-row>
    </v-container>
  </v-app-bar>
</template>

<script>
export default {
  name: 'FamilyAppBar',
  methods: {
    goMain() {
      this.$router.go(this.$router.push({name: 'Main'}))
      // this.$vuetify.goTo(0)
    },
    goReport() {
      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}))
    },
  },
}
</script>

<style>

</style>