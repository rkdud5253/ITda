<template>
  <div class="dailyExerciseResult">
    <div class="wrap">      
      <TitleBox title="오늘의 체조가 모두 끝났습니다." />
      <img class="seniorExercise"
        src="@/assets/senior/SeniorExercise.jpg"
      >
      <p class="explain">몸도 마음도 한결 튼튼해지셨습니다!</p>
      <hr class="line">
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import '@/components/css/senior/dailyExerciseResult.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
export default {
  name: "DailyExerciseResult",
  components: {
    TitleBox,
  },
  created() {
    this.$store.commit("TTS", "오늘의 체조가 모두 끝났습니다. 몸도 마음도 한결 튼튼해지셨네요!");
    // 5초 있다가 메인으로 가기
    // 파이썬에게 끝났다고 알리기
    this.connect();

    setTimeout(()=>this.$router.push({name: 'SeniorMain'}), 10000);
  },
  methods:{
    send(msg){
        this.StompClient.send("/socket/" + this.$store.state.ipHash + "/receive", JSON.stringify(msg), {});
      },
    connect() {
      const serverURL = "http://j4a404.p.ssafy.io:8000/itda/vuejs";
      
      let Socket = new SockJS(serverURL);
      this.StompClient = Stomp.over(Socket);
      this.StompClient.debug = () => {};
      this.StompClient.connect(
          {},
          (frame) => {
            // 소켓 연결 성공
            frame;
            
            this.StompClient.subscribe(
                "/socket/" + this.$store.state.ipHash + "/send",
                () => {
                }
            );
          },
          (error) => {
            // 소켓 연결 실패
            console.log("소켓 연결 실패", error);
          }
      );
    },
  }
}
</script>