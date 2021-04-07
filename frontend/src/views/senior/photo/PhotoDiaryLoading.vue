<template>
  <div class="photoDiaryLoading">
    <MenuLoading speech="오늘의 사진 일기를 남겨보세요.
    가족들에게 전송해드립니다!"/>
  </div>
</template>

<script>
import '@/components/css/senior/photoDiary.scss';
import MenuLoading from '@/components/senior/common/MenuLoading.vue';
export default {
  name: "PhotoDiaryLoading",
  components: {
    MenuLoading,
  },
  created() {
    setInterval(this.TTS("오늘의 사진 일기를 남겨보세요."),1000);
  },
  methods:{
    TTS(speechText){
      if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
        alert("이 브라우저는 음성 합성을 지원하지 않습니다.");
        console.log("이브라우저");
        return;
      }
      console.log(1);
      // window.speechSynthesis.cancel(); // 현재 읽고있다면 초기화

      const speechMsg = new SpeechSynthesisUtterance();
      speechMsg.rate = 1;
      speechMsg.pitch = 1;
      speechMsg.lang = "ko-KR";
      speechMsg.text = speechText;
      console.log(1);
      // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행  
      window.speechSynthesis.speak(speechMsg);
      // window.speechSynthesis.pause();  
      // window.speechSynthesis.resume();
      console.log(1);
    }
  }
}
</script>