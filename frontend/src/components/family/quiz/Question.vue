<template>
  <v-card class="mx-2 mt-1 mb-5">
    <!-- 문제보기 컴포넌트 -->
    <v-container>
      <v-card color="#FFF9E9" flat>
        <v-card-title style="justify-content: center;">
          <v-card class="mx-5 my-5" color="#FFDD71" flat style="width: 100%;">
            <h3 class="question-title">{{ content }}</h3>
          </v-card>
        </v-card-title>
        
        <!-- 이미지 있을때 보여주고, 아니면 안보여지게 해야함 -->
        <div v-if="imageUrl !== ''">
          <v-img 
            class="mx-10"
          >
          </v-img>
        </div>
        <!-- 보기 -->
        <div class="mx-10 my-10">
          <v-row class="question-num">
            <v-col>
              <h3><v-icon color="black">mdi-numeric-1-box</v-icon>{{example1}}</h3>
            </v-col>
            <v-col>
              <h3><v-icon color="black">mdi-numeric-2-box</v-icon>{{example2}}</h3>
            </v-col>
          </v-row>
          <v-row class="question-num">
            <v-col v-if="this.example3 != ''">
              <h3><v-icon color="black">mdi-numeric-3-box</v-icon>{{example3}}</h3>
            </v-col>
            <v-col v-if="this.example4 != ''">
              <h3><v-icon color="black">mdi-numeric-4-box</v-icon>{{example4}}</h3>
            </v-col>
          </v-row>
        </div>

        <!-- 정답 -->
        <div style="text-align-last: center;">
          <v-chip
            class="ma-2 my-5"
            color="#FEA601"
            dark
            label
            large
          >
            <v-icon left>
              mdi-check-circle
            </v-icon>
            정답 : {{ answer }}번
          </v-chip>
        </div>
      </v-card>
    </v-container>

    <!-- 버튼 -->
    <div style="text-align-last: center;">
      <v-btn
        class="mx-2 my-10"
        color="#597ED2"
        dark
        @click="goQuizModify"
      >
        수정하기
      </v-btn>
      <v-btn
        class="mx-2 my-10"
        color="#FC5355"
        dark
      >
        삭제하기
      </v-btn>
    </div>
  </v-card>
</template>

<script>
import axios from "@/service/axios.service.js";

export default {
  name: "Question",
  data () {
    return {
      content:'',
      imageUrl:'',
      example1:'',
      example2:'',
      example3:'',
      example4:'',
      answer:0
    }
  },
  created(){
    this.getQuiz();
  },
  methods: {
    getQuiz(){
        axios.get('/qna/one',{
            params:{
                questionId: Number(this.$route.params.questionId)
            }
        }).then((res) => {
            console.log(res.data);
            this.content = res.data.questionContent;
            this.imageUrl = res.data.questionImageUrl;
            this.example1 = res.data.example1;
            this.example2 = res.data.example2;
            this.example3 = res.data.example3;
            this.example4 = res.data.example4;
            this.answer = res.data.answer;
        }).catch(error => {
            console.log(error);
        });
    },
    goQuizModify() {
      this.$router.push({name: 'QuizItdaModifyQuestion', params: Number(this.$route.params.questionId)})
    },
  },
}
</script>

<style>
  .question-title {
    margin: inherit;
    text-align: center;
  }
  .v-img {
    display: block;
    width: 100vw;
    height: 56.25vw;
    max-height: 100vh;
  }
  .question-num {
    text-align-last: center;
    text-align: center;
  }
</style>