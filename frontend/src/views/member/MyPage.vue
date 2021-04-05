<template>
  <v-app>
    <!-- 어르신등록 -->
    <FamilyAppBar />
    <v-container class="my-15" style="text-align: -webkit-center;">
      <v-card class="senior-name-card">
        <h1 class="pt-10 pb-2" style="color: #FEA601">어르신 등록</h1>
        <p>어르신의 성함을 입력한 후 아이콘을 클릭하세요</p>
        <v-form>
          <v-container>
          <v-row>
            <v-col cols="12">
            <v-text-field
              class="mx-10"
              v-model="message"
              :append-outer-icon="message ? 'mdi-send' : ''"
              filled
              clear-icon="mdi-close-circle"
              clearable
              label="어르신 성함"
              type="text"
              @click:append-outer="sendMessage"
              @click:clear="clearMessage"
              @keypress.enter="sendMessage"
              color="#FEA601"
            ></v-text-field>
            </v-col>
          </v-row>
          </v-container>
          <div v-if="this.seniorName !== '성함'">
            <h2 style="color: #FC5355">{{ this.seniorName }}<span style="color: black">님이 맞으신가요?</span></h2>
            <p>맞으시다면 인증하기 버튼을 누른 뒤,<br>어르신 화면에서 성함을 입력하여 로그인 하세요<br>인증 시간은 10분입니다</p>
            <v-btn
              class="mx-2 my-3"
              color="#FC5355"
              dark
              @click="sendName"
            >
              인증하기
            </v-btn>
            <h2 v-if="clickButtonSeeTimer">{{ minutes }}:{{ seconds }}</h2>
          </div>
      </v-form>
       
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'

export default {
  name: "MyPage",
  components: {
    FamilyAppBar,
  },
  data: () => ({
    password: 'Password',
    show: false,
    message: '',
    seniorName: '성함',
    timer: null,
    totalTime: (1 * 600),
    clickButtonSeeTimer: false,
  }),
  methods: {
    sendMessage () {
      this.seniorName = this.message
    },
    clearMessage () {
      this.message = ''
    },
    sendName () {
      this.startTimer()
      this.clickButtonSeeTimer = true
    },
    startTimer: function() {
      this.timer = setInterval(() => this.countdown(), 1000);
    },
    padTime: function(time) {
      return (time < 10 ? '0' : '') + time;
    },
    countdown: function() {
      if(this.totalTime >= 1) {
        this.totalTime--;
      } else {
        this.totalTime = 0;
      }
    },
  },
  computed: {
    minutes: function() {
      const minutes = Math.floor(this.totalTime / 60);
      return this.padTime(minutes);
    },
    seconds: function() {
      const seconds = this.totalTime - (this.minutes * 60);
      return this.padTime(seconds);
    }
  }
}
</script>

<style>
  .senior-name-card {
    width: 450px;
    height: 480px;
  }
</style>