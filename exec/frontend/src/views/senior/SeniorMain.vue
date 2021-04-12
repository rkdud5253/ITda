<template>
  <div class="seniorMain">
    <div class="wrap">
      <TitleBox v-if="username" :title="username + definedMent" />
      <TitleBox v-if="!username" :title="undefinedMent" />
      <div class="menuContainer">
        <DailyExerciseMenu />
        <PhotoDiaryMenu />
        <FamilyGameMenu />
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from '@/service/axios.service.js'
import '@/components/css/senior/seniorMain.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
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
      username: '',
      definedMent: '님, 나리를 불러서 원하는 기능을 실행하세요!',
      undefinedMent: '어르신 등록 후 잇다를 이용해주세요.'
    }
  },
  created() {
    this.connect();

    // userId 전달
    axios.post("/order",{
      hashIp:this.$store.state.ipHash,
      command:"userId=" + this.$store.state.userId
    }).then(() => {

    })
  },
  methods:{
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
              
              if(res.body == "오늘의 체조")
                this.$router.push({name: 'DailyExerciseLoading'});
                
              if(res.body == "가족오락관")
                this.$router.push({name: 'FamilyQuizLoading'});
                
              if(res.body == "사진 일기장")
                this.$router.push({name: 'PhotoDiaryLoading'});
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