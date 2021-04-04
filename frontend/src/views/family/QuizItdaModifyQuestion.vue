<template>
  <v-app>
    <FamilyAppBar />
    <!-- 퀴즈잇다 문제내기 페이지 -->
    <v-container>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          class="my-5"
        >
          <h1 style="color: #597ED2">문제내기</h1>
          <h4>어르신께 출제될 문제를 직접 만들어 볼 수 있어요</h4>
          <h4>질문과 정답을 입력하여 가족과 관련된 문제를 등록해 보세요</h4>
        </v-col>
        <v-col
          cols="12"
          sm="6"
          style="text-align-last: end; place-self: flex-end;"
          class="my-5"
        >
          <v-btn
            color="#FEA601"
            dark
            @click="goQuizList"
          >
            목록보기
          </v-btn>
        </v-col>
      </v-row>
      <ModifyQuestion />
    </v-container>
    <div class="mb-5">
      <TopButton />
    </div>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import TopButton from '@/components/family/TopButton.vue'
import ModifyQuestion from '@/components/family/quiz/ModifyQuestion.vue'

export default {
  name: "QuizItdaModifyQuestion",
  components: {
    FamilyAppBar,
    TopButton,
    ModifyQuestion,
  },
  methods: {
    goQuizList() {
      this.$router.go(this.$router.push({name: 'QuizItdaList'}))
    },
  },
}
</script>

<style>

</style>