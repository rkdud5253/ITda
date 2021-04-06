<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
  <v-card class="mx-2 mt-1 mb-5">
    <!-- 문제등록 컴포넌트 -->
    <v-container>
      <v-card color="#FFF9E9" flat>
        <v-card-title style="justify-content: center;">
          <v-card class="mx-5 my-5" color="#FFDD71" flat style="width: 100%;">
            <v-text-field
              v-model="name"
              :counter="50"
              :rules="nameRules"
              label="문제를 입력하세요"
              required
              color="#597ED2"
            ></v-text-field>
          </v-card>
        </v-card-title>
        
        <!-- 이미지 넣기 -->
        <v-file-input
          class="mx-10 my-5"
          :rules="rules"
          accept="image/png, image/jpeg, image/bmp"
          placeholder="사진을 추가할 수 있습니다"
          prepend-icon="mdi-camera"
          label="사진"
          color="#597ED2"
        ></v-file-input>

        <!-- 보기 -->
        <div class="mx-10 my-10">
          <v-row class="question-num">
            <v-col>
              <v-text-field
                v-model="example1"
                :counter="20"
                :rules="example1Rules"
                label="보기를 입력하세요"
                prepend-icon="mdi-numeric-1-box"
                color="black"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="example2"
                :counter="20"
                :rules="example2Rules"
                label="보기를 입력하세요"
                prepend-icon="mdi-numeric-2-box"
                color="black"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="question-num">
            <v-col>
              <v-text-field
                v-model="example3"
                :counter="20"
                :rules="example3Rules"
                label="보기를 입력하세요"
                prepend-icon="mdi-numeric-3-box"
                color="black"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="example4"
                :counter="20"
                :rules="example4Rules"
                label="보기를 입력하세요"
                prepend-icon="mdi-numeric-4-box"
                color="black"
              ></v-text-field>
            </v-col>
          </v-row>
        </div>

        <!-- 정답 -->
        <v-select
          class="mx-10 my-10"
          v-model="select"
          :items="items"
          :rules="[v => !!v || '정답 선택은 필수입니다']"
          label="정답"
          required
        ></v-select>

      </v-card>
    </v-container>

    <!-- 버튼 -->
    <div style="text-align-last: center;">
      <v-btn
        class="mx-2 mb-10"
        color="#597ED2"
        dark
        @click="validate"
      >
        수정하기
      </v-btn>
      <v-btn
        class="mx-2 mb-10"
        color="grey lighten-1"
        dark
        @click="goQuizList"
      >
        취소하기
      </v-btn>
    </div>

  </v-card>
  </v-form>

</template>

<script>
import axios from "@/service/axios.service.js";

export default {
  name: "ModifyQuestion",
  components: {
    
  },
  data: () => (
    {
      valid: true,
      rules: [
        value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!',
      ],
      name: '',
      nameRules: [
        v => !!v || '문제 제목은 필수입니다',
        v => (v && v.length <= 50) || '제목은 50자 미만으로 작성해 주세요',
      ],
      image: '',
      example1: '',
      example1Rules: [
        v => !!v || '보기 두 개 입력은 필수입니다',
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      example2: '',
      example2Rules: [
        v => !!v || '보기 두 개 입력은 필수입니다',
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      example3: '',
      example3Rules: [
        v => (v && v.length <= 20) || '객관식 답안은20자 미만으로 작성해 주세요',
      ],
      example4: '',
      example4Rules: [
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      select: null,
      items: [
        1,
        2,
        3,
        4,
      ],
      checkbox: false,
  }),
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
            this.name = res.data.questionContent;
            this.image = res.data.questionImageUrl;
            this.example1 = res.data.example1;
            this.example2 = res.data.example2;
            this.example3 = res.data.example3;
            this.example4 = res.data.example4;
            this.select = res.data.answer;
        });
    },
    goQuizList() {
      this.$router.go(this.$router.push({name: 'QuizItdaList'}))
    },
    validate () {
      const adminId = this.$store.state.adminId;
      const userId = this.$store.state.userId;
      axios.put('/qna', {
          questionId:  Number(this.$route.params.questionId),
          userId: Number(userId),
          adminId: Number(adminId),
          questionContent: this.name,
          questionImageUrl: this.image,
          example1 : this.example1,
          example2 : this.example2,
          example3 : this.example3,
          example4 : this.example4,
          answer: this.select,
      }).then((res) => {
        console.log(res);
      }).catch(error => {
        console.log(error);
      });
      
      alert("문제 수정이 완료되었습니다!");
      this.$router.go(this.$router.push({name: 'QuizItdaList'}))
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  },
}
</script>

<style>

</style>