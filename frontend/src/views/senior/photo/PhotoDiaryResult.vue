<template>
  <div class="photoDiaryResult">
    <div class="wrap">
      <TitleBox title="오늘의 사진이 잘 찍혔어요!" />
      <img class="seniorPhoto" :src="fileUrl.fileUrl">
      <p class="explain">사진이 마음에 드시면 "저장"</p>
      <hr class="line1">
      <p class="explain">다시 찍으시려면 “다시”</p>
      <hr class="line2">
    </div>
    <div class="goBox">
      <Again />
      <Save />
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/photoDiaryResult.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import Again from '@/components/senior/photo/Again.vue';
import Save from '@/components/senior/photo/Save.vue';
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from '@/service/axios.service.js'

export default {
  name: "PhotoDiaryResult",
  components: {
    TitleBox,
    Again,
    Save,
  },
  data() {
    return {
      fileUrl:'',
      date:'',
      year:'',
      month:'',
      day:''
    }
  },
  computed: {
    myImage: function() {
      return this.image
    },
  },
  created(){

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

    this.getImage();

    this.connect();
  },
  mounted() {
    // this.$store.commit("TTS", "오늘의 사진이 잘 찍혔어요!");
    // setTimeout(()=>this.$router.push({name: 'SeniorMain'}), 8000);
  },
  methods:{
     getImage() {
    axios
      .get(`/files/image`, {
        params: {
          // userId : this.$store.state.userId,
          // 현재 테스트용으로 userId 1로 설정 중
          userId : 1,
          fileDate : this.date,
        }
      })
      .then((response) => {
        console.log(response.data);
        this.fileUrl = response.data;  
      })
      .catch((error) => {
        console.log(error);
      });
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
              
              if(res.body == "다시") {
                
                if (this.StompClient !== null) {
                  this.StompClient.disconnect();
                } 
                this.$router.push({name: 'PhotoDiary'});
              }
              
              else if(res.body == "저장") {
                
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
  }
}
</script>