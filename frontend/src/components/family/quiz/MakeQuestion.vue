<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
  <v-card class="mx-2 mt-1 mb-5">
    <!-- 문제내기 컴포넌트 -->
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
                v-model="question1"
                :counter="20"
                :rules="questio1nRules"
                label="선택지를 입력하세요"
                prepend-icon="mdi-numeric-1-box"
                color="black"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="question2"
                :counter="20"
                :rules="question2Rules"
                label="선택지를 입력하세요"
                prepend-icon="mdi-numeric-2-box"
                color="black"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="question-num">
            <v-col>
              <v-text-field
                v-model="question3"
                :counter="20"
                :rules="question3Rules"
                label="선택지를 입력하세요"
                prepend-icon="mdi-numeric-3-box"
                color="black"
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="question4"
                :counter="20"
                :rules="question4Rules"
                label="선택지를 입력하세요"
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
        등록하기
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

  <!--
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="name"
      :counter="10"
      :rules="nameRules"
      label="Name"
      required
    ></v-text-field>

    <v-select
      v-model="select"
      :items="items"
      :rules="[v => !!v || 'Item is required']"
      label="Item"
      required
    ></v-select>

    <v-checkbox
      v-model="checkbox"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Do you agree?"
      required
    ></v-checkbox>

    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      Validate
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn>

    <v-btn
      color="warning"
      @click="resetValidation"
    >
      Reset Validation
    </v-btn>
  </v-form>
  -->

</template>

<script>

export default {
  name: "MakeQuestion",
  components: {

  },
  data: () => ({
      valid: true,
      rules: [
        value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!',
      ],
      name: '',
      nameRules: [
        v => !!v || '문제 제목은 필수입니다',
        v => (v && v.length <= 50) || '제목은 50자 미만으로 작성해 주세요',
      ],
      question1: '',
      question1Rules: [
        v => !!v || '선택지 두 개 입력은 필수입니다',
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      question2: '',
      question2Rules: [
        v => !!v || '선택지 두 개 입력은 필수입니다',
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      question3: '',
      question3Rules: [
        v => (v && v.length <= 20) || '객관식 답안은20자 미만으로 작성해 주세요',
      ],
      question4: '',
      question4Rules: [
        v => (v && v.length <= 20) || '객관식 답안은 20자 미만으로 작성해 주세요',
      ],
      select: null,
      items: [
        '1번',
        '2번',
        '3번',
        '4번',
      ],
      checkbox: false,
  }),
  methods: {
    goQuizList() {
      this.$router.go(this.$router.push({name: 'QuizItdaList'}))
    },
    validate () {
      this.$refs.form.validate()
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