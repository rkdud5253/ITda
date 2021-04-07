<template>
  <div class="familyQuizResult">
    <TitleBox title="오늘의 두뇌 운동이 모두 끝났습니다." />
    <img class="seniorExercise"
      src="@/assets/senior/SeniorQuiz.jpg"
    >
    <p class="explain">참 잘하셨습니다!</p>
    <hr class="line">
  </div>
</template>

<script>
import '@/components/css/senior/familyQuizResult.scss';
import TitleBox from '@/components/senior/common/TitleBox.vue';
import axios from "@/service/axios.service.js";

export default {
  name: "SeniorMain",
  components: {
    TitleBox,
  },
  data() {
    return {
      items: [
        {
          questionId: '4',
        },
        {
          questionId: '5',
        },
        {
          questionId: '6',
        },
        {
          questionId: '7',
        },
        {
          questionId: '8',
        },
      ],
      right: [
        {
          questionId: '4',
        },
        {
          questionId: '5',
        },
        {
          questionId: '6',
        },
      ],
      wrong: [
        {
          questionId: '7',
        },
        {
          questionId: '8',
        },
      ],
      rightNumbers: '',
      wrongNumbers: '',
      date: '',
      year: '',
      month: '',
      day: '',
    }
  },
  mounted() {
    this.$store.commit("TTS", "오늘의 두뇌 운동이 모두 끝났습니다. 참 잘하셨습니다!");
    this.setQuizResult();
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
    this.getWrong();
    this.getRight();
  },
  methods: {
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
      for (let index = 0; index < this.wrong.length; index++) {
        this.rightNumbers += this.right[index];
      }
    },
    getRight(){
      for (let index = 0; index < this.wrong.length; index++) {
        this.wrongNumbers += this.wrong[index];
      }
    },
  },  
}
</script>