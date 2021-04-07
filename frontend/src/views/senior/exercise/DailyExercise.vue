<template>
  <div class="dailyExercise">
    <div class="wrap">
      <TitleBox title="오늘의 체조 시간입니다." />
      <div class="videos">
        <div class="video1">오늘의 체조 동작 사진</div>
        {{fileInfo[i].fileUrl}}
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
import '@/components/css/senior/dailyExercise.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import GoToMainBlue from '@/components/senior/common/GoToMainBlue.vue';
import axios from '@/service/axios.service.js'
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
export default {
  name: "DailyExercise",
  components: {
    TitleBox,
    GoToMainBlue,
  },
  data() {
    return {
      i: 0,
      fileInfo: [
        {
          fileUrl: '',
          fileId: '',
          fileName: '',
        },
      ],
      date: '',
      year: '',
      month: '',
      day: '',
    }
  },
  created() {
    this.date = new Date();
    this.year = this.date.getFullYear();
    this.month = (this.date.getMonth()+1);
    this.day = this.date.getDate();
    if(this.month < 10){
      this.month = '0' + this.month;
    }
    if(this.day < 10){
      this.day = '0' + this.day;
    }
    this.date = this.year + '-' + this.month + '-' + this.day;
    this.getFileInfo();
  },
  mounted() {
    // 사진 배열로 몇 개 하기 - 일단 3장
    this.$store.commit("TTS", "잠시 후 오늘의 체조를 시작합니다. 왼쪽 사진의 동작에 집중하며 체조를 따라해보세요.");
    setInterval(this.send("poseNetRun"),7000); // 대사 끝나면 poseNet 실행
    // 체조 사진 
    // for문으로 몇 초마다 다음 동작으로 axios.get
    axios.get('/exercise',{

    })
    // 모든 동작이 끝나면 
    // this.$router.push({name: 'DailyExerciseResult'});
  },
  methods: {
    send(msg){
      this.StompClient.send(JSON.stringify({
        sttMessage:msg
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
    getFileInfo(){
      axios
      .get('/files/exercise',{
        params:{
          fileDate: this.date,
        }
      }).then((res) => {
        this.fileInfo[this.i].fileUrl = res.data[this.i].fileUrl;
        this.fileInfo[this.i].fileId = res.data[this.i].fileId;
        this.fileInfo[this.i].fileName = res.data[this.i].fileName;
      }).catch(error => {
          console.log(error);
      });
    },
  }
}
</script>