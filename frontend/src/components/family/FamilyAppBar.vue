<template>
  <!-- 상단바 컴포넌트 --> 
  <v-app-bar
    app
    flat
    color="#FFDD71"
  >
    <!-- 크기 줄였을 때 메뉴 햄버거바 -->
    <v-app-bar-nav-icon
      class="hidden-md-and-up"
      @click.stop="drawer = !drawer"
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
          @click="goQuiz"
        >
          <h4>퀴즈잇다</h4>
        </v-btn>

        <v-spacer />

        <!--로그인시 마이페이지, 로그아웃-->
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
        >
          <h4>마이페이지</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
        >
          <h4>로그아웃</h4>
        </v-btn>

        <!-- 비로그인시 회원가입, 로그인 -->
        <!-- <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
        >
          <h4>회원가입</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
        >
          <h4>로그인</h4>
        </v-btn> -->

      </v-row>
    </v-container>

    <!-- 모바일일때 상단바 drawer 메뉴들 -->
    <v-navigation-drawer
      class="nav-drawer"
      v-model="drawer"
      absolute
      temporary
      color="#FFDD71"
    >
      <v-list-item>
        <v-list-item-avatar @click.stop="drawer = !drawer">
          <v-img :src="require('@/assets/family/itda.png')"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>메뉴</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          link
          @click="goMain"
        >
          <v-list-item-icon>
            <v-icon>mdi-home</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>서비스소개</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          link
          @click="goReport"
        >
          <v-list-item-icon>
            <v-icon>mdi-calendar-text</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>보고잇다</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          link
          @click="goQuiz"
        >
          <v-list-item-icon>
            <v-icon>mdi-lead-pencil</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>퀴즈잇다</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <!-- 로그인, 로그이웃, 회원가입, 마이페이지 -->
      <v-list dense>
        <v-list-item
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-box</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>마이페이지</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-off</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <!-- 비 로그인시 이거 보이게.. -->
      <v-list dense>
        <v-list-item
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>회원가입</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-check</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

    </v-navigation-drawer>
  </v-app-bar>
</template>

<script>
export default {
  name: 'FamilyAppBar',
  data () {
    return {
      drawer: null,
    }
  },
  methods: {
    goMain() {
      this.$router.go(this.$router.push({name: 'Main'}))
      // this.$vuetify.goTo(0)
    },
    goReport() {
      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}))
    },
    goQuiz() {
      this.$router.go(this.$router.push({name: 'QuizItdaList'}))
    },
  },
}
</script>

<style>
  .nav-drawer {
    height: 1200px !important;
  }

</style>