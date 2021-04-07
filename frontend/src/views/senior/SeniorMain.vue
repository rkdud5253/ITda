<template>
  <div class="seniorMain">
    <TitleBox :title="username + ment" />
    <div class="menuContainer">
      <DailyExerciseMenu />
      <PhotoDiaryMenu />
      <FamilyGameMenu />
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import '@/components/css/senior/seniorMain.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import DailyExerciseMenu from '@/components/senior/main/DailyExerciseMenu.vue';
import PhotoDiaryMenu from '@/components/senior/main/PhotoDiaryMenu.vue';
import FamilyGameMenu from '@/components/senior/main/FamilyGameMenu.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    DailyExerciseMenu,
    PhotoDiaryMenu,
    FamilyGameMenu,
  },
  data () {
    return {
      username: '김싸피',
      ment: '님, 나리를 불러서 원하는 기능을 실행하세요!'
    }
  },
  mounted() {
    this.$store.commit("TTS", this.username + "님 나리를 불러서 원하는 기능을 실행하세요");
  },
  methods:{
    goDailyExercise(){
      this.$router.go(this.$router.push({name: 'DailyExerciseLoading'}))
    },
    connect() {
        const serverURL = "http://localhost:8000/itda/vuejs";
        
        let recordSocket = new SockJS(serverURL);
        this.recordStompClient = Stomp.over(recordSocket);
        this.recordStompClient.debug = () => {};
        this.recordStompClient.connect(
            {},
            (frame) => {
              // 소켓 연결 성공
              this.connected = true;
              frame;
              
              this.recordStompClient.subscribe(
                  "/socket/{" + this.$store.state.ipHash + "}/send",
                  (res) => {
                    console.log(res.body);
                    // 어르신 이름이 제대로 들어왔다면
                    // AccessCheck에서 확인 후,
                    // AccessCheck 제거 및 userAdmin 생성
                    // this.$store.state.userId = 요거;
                    // this.$store.state.adminId = 요거;
                    // 그리고 SeniorMain으로 이동
                    if(res.body == "오늘의 체조")
                      this.$router.go(this.$router.push({name: 'BogoItdaMonth'}));
                  }
              );
            },
            (error) => {
              // 소켓 연결 실패
              console.log("소켓 연결 실패", error);
              this.connected = false;
            }
        );
    },
  }
}
</script>