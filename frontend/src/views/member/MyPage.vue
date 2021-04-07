<template>
  <v-app>
    <!-- 어르신등록 -->
    <FamilyAppBar />
    <v-container class="my-15" style="text-align: -webkit-center;">
      <v-card class="senior-name-card1" v-if="this.$store.state.userId >= 1">
        <h1 class="pt-10 pb-2" style="color: #FEA601">어르신 등록</h1>
        <h2 class="pt-15"><span style="color: #FC5355">{{ this.userName }}</span>님이 연결되었습니다.</h2>
      </v-card>
      <v-card class="senior-name-card2" v-if="this.$store.state.userId == 0">
        <h1 class="pt-10 pb-2" style="color: #FEA601">어르신 등록</h1>
        <v-form>
          <p>어르신의 성함을 입력한 후 아이콘을 클릭하세요</p>
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
import axios from "@/service/axios.service.js";
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
    userName: '',
  }),
  created() {
    if (this.$store.state.userId >= 1) {
      this.getUserName()
    }
  },
  methods: {
    sendMessage () {
      this.seniorName = this.message
    },
    clearMessage () {
      this.message = ''
    },
    sendName () {
      // 중복된 이름이 있는지 체크하기
      axios.get('/AccessCheck/', {
        params: {
            userName:this.seniorName,
        }
      }).then((res) => {
        if(res.data.userName == this.seniorName) {
          alert("같은 이름으로 인증 절차가 진행 중입니다. 잠시 후에 다시 시도해주세요!");
        }
        else{
          // 이미 진행중인지 확인하기
          axios.get('/AccessCheck/adminId', {
            params: {
              adminId: Number(this.$store.state.adminId)
            }
          }).then((res2) => {
            console.log(res2.data);
            // 있다면 제거 후 재등록
            if(res2.data != "") {
              console.log(res2.data);
              axios.delete('/AccessCheck/',{
                params:{
                  adminId: res2.data.adminId,
                }
              }).then(() => {
              }).catch(error => {
                console.log(error);
              });
            }
            console.log(Number(this.$store.state.adminId));
            axios.post('/AccessCheck/', {
              adminId: Number(this.$store.state.adminId),
              userName: this.seniorName,              
            }).then(() => {
              this.startTimer()
              this.clickButtonSeeTimer = true
            }).catch(error => {
              console.log(error);
            });
          }).catch(error => {
            console.log(error);
          })
        }
      }).catch(error => {
        console.log(error);
      })

      
    },
    startTimer: function() {
      if(!this.clickButtonSeeTimer)
        this.timer = setInterval(() => this.countdown(), 1000);
      else
        this.totalTime = (1 * 600);
    },
    padTime: function(time) {
      return (time < 10 ? '0' : '') + time;
    },
    countdown: function() {
      if(this.totalTime >= 1) {
        this.totalTime--;
        axios.get('/useradmin/admin', {
          params: {
            adminId: Number(this.$store.state.adminId)
          }
        }).then((res) => {
          console.log(res.data[0].userId)
          if(res.data != "") {
            this.$store.commit("userLogin",res.data[0].userId)
            // this.$store.state.userId = res.data[0].userId;
            this.totalTime = 0;
            this.clickButtonSeeTimer = false;
            alert("등록이 완료되었습니다.")
          }
          }
        )} else {
        this.totalTime = 0;
        this.clickButtonSeeTimer = false;

        // user, admin 10분 제한 제거
         axios.get('/AccessCheck/adminId', {
            params: {
              adminId: Number(this.$store.state.adminId)
            }
          }).then((res) => {
            
            console.log(res.data);
            // 10분이 지났는데 DB에 있다면 제거
            if(res.data != "") {
              console.log(res.data);
              axios.delete('/AccessCheck/',{
                params:{
                  adminId: res.data.adminId,
                }
              }).then(() => {
              }).catch(error => {
                console.log(error);
              });
            }
          }).catch(error => {
            console.log(error);
          })
      }
    },
    getUserName () {
      axios.get('/user', {
        params: {
          userId: Number(this.$store.state.userId)
        }
      }).then((res) => {
        console.log(res.data.userName)
        this.userName = res.data.userName
      })
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
  .senior-name-card1 {
    width: 450px;
    height: 300px;
  }
  .senior-name-card2 {
    width: 450px;
    height: 480px;
  }
</style>