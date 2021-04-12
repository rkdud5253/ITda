<template>
  <div class="photoDiary">
    <div class="wrap">
      <TitleBox title="왼쪽 사진의 동작을 따라한 뒤 웃어주세요!"/>
      <div class="photos">
        <ExampleImage :fileInfo = "fileInfo"/>
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
  name: "PhotoDiary",
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
      ],
      fileInfo: {
        fileUrl: '',
        fileId: '',
        fileName: '',
      },
      date: '',
      year: '',
      month: '',
      day: '',
    }
  },
  created() { 
    this.connect();
    
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
    this.onChangeImages();
  },
  mounted() {
    // this.$store.commit("TTS", "왼쪽 사진의 동작을 따라해보세요. 활짝 웃으면 사진이 찍혀요!");
    // setTimeout(()=>this.send("smileNetRun"), 6000); // 대사 끝나고 smileNet 실행
  },
  methods: {
    getFileInfo(){
      axios
      .get('/files',{
        params:{
          fileDate: this.date,
        }
      }).then((res) => {
        this.fileInfo.fileUrl = res.data[0].fileUrl;
        this.fileInfo.fileId = res.data[0].fileId;
        this.fileInfo.fileName = res.data[0].fileName;
        console.log(this.date)
      }).catch(error => {
          console.log(error);
      });
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
                    else if(res.body == "찰칵") // 넘겨받음
                      this.$router.push({name: 'PhotoDiaryResult'});
                  }
              );
            },
            (error) => {
              // 소켓 연결 실패
              console.log("소켓 연결 실패", error);
            }
        );
    },
    onChangeImages(e) {
      const file = e;
      let reader = new FileReader()
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.fileInfo.fileUrl = reader.result
      }
     
    },
  }
}
</script>