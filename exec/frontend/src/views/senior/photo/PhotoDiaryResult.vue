<template>
  <div class="photoDiaryResult">
    <div class="wrap">
      <TitleBox title="오늘의 사진이 잘 찍혔어요!" />
      <SeniorPhoto />
      <p class="explain">사진이 마음에 드시면 "나리야 저장"</p>
      <hr class="line1">
      <p class="explain">다시 찍으시려면 “나리야 다시”</p>
      <hr class="line2">
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/photoDiaryResult.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import SeniorPhoto from '@/components/senior/photo/SeniorPhoto.vue'
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "PhotoDiaryResult",
  components: {
    TitleBox,
    SeniorPhoto,
  },
  mounted() {
    this.$store.commit("TTS", "오늘의 사진이 잘 찍혔어요!");
  },
  methods:{
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
                    
                    if(res.body == "다시")
                      this.$router.push({name: 'PhotoDiary'});
                    else if(res.body == "저장") 
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