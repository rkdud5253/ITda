<template>
  <div class="dailyExercise">
    <div class="wrap">
      <TitleBox title="오늘의 체조 시간입니다." />
      <div class="videos">
        <div class="video1">오늘의 체조 동작 사진</div>
        <div class="video2">어르신 실시간 영상</div>
      </div>
      <div class="explainBox">
        <div class="explainLine">
          <p class="explain">왼쪽 사진의 동작을 따라해보세요!</p>
          <hr class="line">
        </div>
      </div>
    </div>
    <GoToMainBlue />
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import '@/components/css/senior/dailyExercise.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import GoToMainBlue from '@/components/senior/common/GoToMainBlue.vue';
export default {
  name: "DailyExercise",
  components: {
    TitleBox,
    GoToMainBlue,
  },
  created() {
    // 사진 배열로 몇 개 하기 - 일단 3장
    setInterval(this.$store.commit("TTS", "잠시 후 오늘의 체조를 시작합니다. 왼쪽 사진의 동작에 집중하며 체조를 따라해보세요."),1000); // 몇 초 걸리나요?? 그것에 따라 send하기
    setInterval(this.send(),1000);
    // 체조 사진 
    // for문으로 몇 초마다 다음 동작으로 axios.get
    // 모든 동작이 끝나면 
    // this.router.push(~);
  },
  method: {
    send(){
      this.StompClient.send(JSON.stringify({
        sttMessage:"poseNetRun"
      }));
    },
    connect() {
        const serverURL = "http://localhost:8000/itda/vuejs";
        
        let Socket = new SockJS(serverURL);
        this.StompClient = Stomp.over(Socket);
        this.StompClient.debug = () => {};
        this.StompClient.connect(
            {},
            (frame) => {
              // 소켓 연결 성공
              this.connected = true;
              frame;
              
              this.StompClient.subscribe(
                  "/socket/" + this.$store.state.ipHash + "/send",
                  (res) => {
                    console.log(res.body);
                    
                    if(res.body == "그만")
                      this.$router.push({name: 'SeniorMain'});
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