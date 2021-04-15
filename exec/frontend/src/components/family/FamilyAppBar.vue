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
          @click="myPage"
          v-if="loginStateResult==true"
        >
          <h4>어르신등록</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
          @click="logout"
          v-if="loginStateResult==true"
        >
          <h4>로그아웃</h4>
        </v-btn>

        <!-- 비로그인시 회원가입, 로그인 -->
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
          @click="signUp"
          v-if="loginStateResult==false"
        >
          <h4>회원가입</h4>
        </v-btn>
        <v-btn
          :ripple="false"
          text
          class="hidden-sm-and-down"
          @click="login"
          v-if="loginStateResult==false"
        >
          <h4>로그인</h4>
        </v-btn>

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

      <!-- 로그인 시 -->
      <v-list dense>
        <v-list-item
          link
          @click="myPage"
          v-if="loginStateResult==true"
        >
          <v-list-item-icon>
            <v-icon>mdi-account-box</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>어르신등록</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          link
          v-if="loginStateResult==true"
          @click="logout"
        >
          <v-list-item-icon>
            <v-icon>mdi-account-off</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <!-- 비로그인 시 -->
      <v-list dense>
        <v-list-item
          link
          @click="signUp"
          v-if="loginStateResult==false"
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
          @click="login"
          v-if="loginStateResult==false"
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

    <!-- 모달창 -->
    <v-dialog
      v-model="dialog"
      max-width="300"
    >
      <v-card>
        <v-card-title style="justify-content: center; color: #FEA601;">
          <h2 class="modalFont my-2"></h2>
        </v-card-title>
        <v-card-text 
          class="modalFont my-2" 
          style="text-align-last: center;"
        >
          <h4>연결된 어르신이 없습니다.<br>어르신 등록을 통해 연결해 주세요.</h4>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="modalFont"
            color="#FEA601"
            dark
            block
            @click="myPage"
          >
            등록하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app-bar>
</template>

<script>
export default {
  name: 'FamilyAppBar',
  data () {
    return {
      drawer: null,
      dialog: false,
    }
  },
  computed: {
    // 로그인 여부 판단
    loginStateResult : function () {
      if(this.$store.state.adminId >= 1) {
        return true
      } else {
        return false
      }
    },
  },
  methods: {
    goMain() {
      this.$router.go(this.$router.push({name: 'Main'}))
    },
    goReport() {
      if (this.$store.state.adminId >= 1) {
        if (this.$store.state.userId == 0) {
          console.log(this.$store.state.userId)
          this.dialog = true
        } else {
          this.$router.go(this.$router.push({ name: 'BogoItdaMonth' }))
        }
      } else {
        this.$router.go(this.$router.push({ name: 'Login' }))
      }
    },
    goQuiz() {
      if (this.$store.state.adminId >= 1) {
        if (this.$store.state.userId == 0) {
          this.dialog = true
        } else {
          this.$router.go(this.$router.push({ name: 'QuizItdaList' }))
        }
      } else {
        this.$router.go(this.$router.push({ name: 'Login' }))
      }
    },
    login() {
      this.$router.go(this.$router.push({name: 'Login'}))
    },
    signUp() {
      this.$router.go(this.$router.push({name: 'SignUp'}))
    },
    myPage() {
      this.$router.go(this.$router.push({name: 'MyPage'}))
    },
    logout() {
      localStorage.clear()
      this.$router.go(this.$router.push({name: 'Main'}))
    },
  },
}
</script>

<style>
  .nav-drawer {
    height: 1200px !important;
  }

</style>