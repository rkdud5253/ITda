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
import axios from "@/service/axios.service.js";

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
    this.$store.commit("TTS", "왼쪽 사진의 동작을 따라해보세요. 활짝 웃으면 사진이 찍혀요!");
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
      }).catch(error => {
          console.log(error);
      });
    },
  },
}
</script>