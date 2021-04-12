<template>
  <div class="familyQuiz">
    <div class="wrap" v-if="items.length > 0">
      <TitleBox :title="items[idx].questionContent"/>
      <img v-if="items[idx].questionImageUrl" class="question" :src="items[idx].questionImageUrl" alt="">
      <img v-if="!items[idx].questionImageUrl" class="defaultImage" src="@/assets/senior/SeniorGame.jpg" alt="">
      <ExampleBox 
        :example1="items[idx].example1"
        :example2="items[idx].example2"
        :example3="items[idx].example3"
        :example4="items[idx].example4"
      />
    </div>
    <div class="goBox">
      <GoToMainRed />
      <GoNext />
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/familyQuiz.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import ExampleBox from '@/components/senior/quiz/ExampleBox.vue';
import GoToMainRed from '@/components/senior/common/GoToMainRed.vue';
import GoNext from '@/components/senior/common/GoNext.vue';
import axios from '@/service/axios.service.js'
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "FamilyQuiz",
  components: {
    TitleBox,
    ExampleBox,
    GoToMainRed,
    GoNext,
  },
  props: {
    question_image_url: {
      type: Image,
      required: false,
    },
  },
  data() {
    return {
      idx: 0,
      items: [],
      right: [],
      wrong: [],
      rightNumbers: '',
      wrongNumbers: '',
      date: '',
      year: '',
      month: '',
      day: '',
    }
  },
  mounted() {
    this.getDate();
    this.getQuiz();
    this.connect();
  },
  methods: {
    getDate() {
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
    },
    getQuiz(){
      axios.get('/qna',{
        params:{
          userId: Number(this.$store.state.userId)
        }
      }).then((res) => {
        console.log(res.data);
        for (let i = 0; i < res.data.length; i++) {
          this.items.push({
            No: i + 1,
            questionId: res.data[i].questionId,
            userId: res.data[i].userId,
            adminId: res.data[i].adminId,
            questionContent: res.data[i].questionContent,
            questionImageUrl: res.data[i].questionImageUrl,
            example1: res.data[i].example1,
            example2: res.data[i].example2,
            example3: res.data[i].example3,
            example4: res.data[i].example4,
            answer: res.data[i].answer,
          });
        }
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
              console.log("socket connected");
              
              this.StompClient.subscribe(
                  "/socket/" + this.$store.state.ipHash + "/send",
                  (res) => {
                    console.log(res.body);
                    
                    if(res.body == "그만")
                      this.$router.push({name: 'SeniorMain'});
                    if(res.body == "1번" || res.body == "1" || res.body == "일" || res.body == "일번") // 넘겨받음
                      this.solving(1);
                    if(res.body == "2번" || res.body == "2" || res.body == "이" || res.body == "이번") // 넘겨받음
                      this.solving(2);
                    if(res.body == "3번" || res.body == "3" || res.body == "삼" || res.body == "삼번") // 넘겨받음
                      this.solving(3);
                    if(res.body == "4번" || res.body == "4" || res.body == "사" || res.body == "사번") // 넘겨받음
                      this.solving(4);
                  }
              );  
            },
            (error) => {
              // 소켓 연결 실패
              console.log("소켓 연결 실패", error);
            }
        );
    },
    solving(answer){
      console.log(answer);

      if(this.items[this.idx].answer == answer)  // 정답
        this.right.push(this.items[this.idx].questionId);
      else // 오답
        this.wrong.push(this.items[this.idx].questionId);
        
      // 정답은 ~번입니다 TTS
      this.$store.commit("TTS", "정답은" + answer + "번입니다.");

      //idx가 5일때
      if(this.idx < 4)
        this.idx += 1;
      else{
        this.getWrong();
        this.getRight();

        this.setQuizResult();

        setTimeout(() => this.$router.push({name:"FamilyQuizResult"}), 500);
      }
    },
    setQuizResult(){
      axios
      .put('/report/qna',{
          // userId: Number(this.$store.state.userId)
          userId: 1,
          reportDate: this.date,
          question1Id: this.items[0].questionId,
          question2Id: this.items[1].questionId,
          question3Id: this.items[2].questionId,
          question4Id: this.items[3].questionId,
          question5Id: this.items[4].questionId,
          rightNumbers: this.rightNumbers,
          wrongNumbers: this.wrongNumbers,
      }).then((res) => {
        console.log(res.data);
      }).catch(error => {
          console.log(error);
      });
    },
    getWrong(){
      for (let index = 0; index < this.right.length; index++) {
        this.rightNumbers += "/" + this.right[index];
      }
    },
    getRight(){
      for (let index = 0; index < this.wrong.length; index++) {
        this.wrongNumbers += "/" + this.wrong[index];
      }
    },
  },  
}
</script>