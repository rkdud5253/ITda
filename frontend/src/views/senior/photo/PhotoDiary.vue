<template>
  <div class="photoDiary">
    <div class="wrap">
      <TitleBox title="왼쪽 사진의 동작을 따라한 뒤 웃어주세요!"/>
      <div class="photos">
        <ExampleImage />
        <SeniorPhoto />
      </div>
      <div class="loaderExplain">
        <Loader />
        <p class="explain">{{ items[i] }}</p>
        <!-- <p v-for="(ment, i) in items"
          :key="i"
          class="explain"
        >{{ ment }}</p> -->
      </div>
      <hr class="line">
    </div>
    <GoToMainBlue />
  </div>
</template>

<script>
import '@/components/css/senior/photoDiary.scss';
import Loader from '@/components/senior/photo/Loader.vue';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import ExampleImage from '@/components/senior/photo/ExampleImage.vue';
import SeniorPhoto from '@/components/senior/photo/SeniorPhoto.vue';
import GoToMainBlue from '@/components/senior/common/GoToMainBlue.vue';
import axios from '@/service/axios.service.js'
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "SeniorMain",
  components: {
    Loader,
    TitleBox,
    ExampleImage,
    SeniorPhoto,
    GoToMainBlue,
  },
  data() {
    return {
      i: 0,
      items: [
      "활짝 웃으면 사진이 찍혀요😄",
      "카메라를 보고 웃어주세요😁"
      ]
    }
  },
  created() {
    this.$store.commit("TTS", "왼쪽 사진의 동작을 따라해보세요. 활짝 웃으면 사진이 찍혀요!");
    setInterval(this.send("poseNetRun"), 4000); // 대사 끝나고 smileNet 실행
    // 체조 사진 
    // for문으로 몇 초마다 다음 동작으로 axios.get
    axios.get('/exercise',{

    })
    // 모든 동작이 끝나면 
    // this.$router.push({name: 'DailyExerciseResult'});
  },
  method: {
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
  }
}
</script>