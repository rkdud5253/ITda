<template>
  <div class="dailyExercise">
    <div class="wrap">
      <TitleBox title="오늘의 체조 시간입니다." />
      <div class="videos">
        <img
        class="exampleImage"
        :src="fileInfo[0].fileUrl"
        alt=""
        >
        <div class="seniorExercise">
          <div class="seniorPhoto"></div>
          <img class="exerciseIcon" src="@/assets/senior/ExerciseIcon.png">
        </div>
        <!-- <div class="video">
          <h3 class="explain">
            이곳에는 PoseNet을 이용한<br> 어르신 실시간 영상이 들어갑니다.<br>
            실행을 위해서는<br> Raspberry Pi가 필요하므로,<br>
            발표 시 시연으로<br> 보여드릴 예정입니다.
          </h3>
        </div> -->
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

    this.connect();
  },
  mounted() {
    // 체조 사진 
    setTimeout(()=>this.sendCommand(),3000);
    setTimeout(()=>this.getFileInfo(),7000);
    
    setTimeout(()=>this.getFileInfo(),17000);

    setTimeout(()=>this.getFileInfo(),27000);
    setTimeout(() => {
      if (this.StompClient !== null) {
        this.StompClient.disconnect();
      } 
    }, 36000);
    setTimeout(()=>this.$router.push({name:"DailyExerciseResult"}),37000);
  },
  methods: {
    sendCommand(){
      axios.get("/order",{
      params:{
        hashIp:this.$store.state.ipHash
      }
    }).then((res) => {
      console.log(res);
      if(res.data.command != null) {
          axios.delete("/order",{
            params:{
              hashIp:this.$store.state.ipHash
            }
          }).then(() => {
            
            // userId 전달
            axios.post("/order",{
              hashIp:this.$store.state.ipHash,
              command:"exercise"
            }).then(() => {

            })
          })
        }
        else{
          
         // userId 전달  
          axios.post("/order",{
            hashIp:this.$store.state.ipHash,
            command:"exercise"
          }).then(() => {

          })
        }
    })
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
                  (res) => {
                    console.log(res.body);
                    
                    if(res.body == "그만"){
                      if (this.StompClient !== null) {
                        this.StompClient.disconnect();
                      } 
                      this.$router.push({name: 'SeniorMain'});
                    }
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
          // 원래는 이거
          fileDate: this.date
        }
      }).then((res) => {
        console.log(res.data);
        this.fileInfo[0].fileUrl = res.data[this.i].fileUrl;
        this.fileInfo[0].fileId = res.data[this.i].fileId;
        this.fileInfo[0].fileName = res.data[this.i].fileName;
        this.i+=1;
      }).catch(error => {
          console.log(error);
      });
    },
  }
}
</script>