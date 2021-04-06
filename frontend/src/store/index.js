import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    adminId:1,
    userId:1,
  },
  mutations: {
    TTS(state, speechText){
      if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
        alert("이 브라우저는 음성 합성을 지원하지 않습니다.");
        return;
      }
      
      window.speechSynthesis.cancel(); // 현재 읽고있다면 초기화

      const speechMsg = new SpeechSynthesisUtterance();
      speechMsg.rate = 1;
      speechMsg.pitch = 1;
      speechMsg.lang = "ko-KR";
      speechMsg.text = speechText;
      
      // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행  
      window.speechSynthesis.speak(speechMsg);
    }
  },
  actions: {},
  modules: {}
})