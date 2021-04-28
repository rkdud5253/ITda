<template>
  <v-app>
    <FamilyAppBar />
    <!-- 퀴즈잇다 내가 낸 문제 리스트 페이지 -->
    <v-container>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          class="my-5"
        >
          <h1 style="color: #597ED2">퀴즈잇다</h1>
          <h4>내가 낸 문제들을 확인할 수 있어요</h4>
          <h4>문제내기 기능을 통해 직접 문제를 출제해 보세요</h4>
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
            @click="goQuizMake"
          >
            문제내기
          </v-btn>
        </v-col>
      </v-row>
      <QuestionList />
    </v-container>
    <div class="mb-5">
      <TopButton />
    </div>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import TopButton from '@/components/family/TopButton.vue'
import QuestionList from '@/components/family/quiz/QuestionList.vue'

export default {
  name: "QuizItdaList",
  components: {
    FamilyAppBar,
    TopButton,
    QuestionList,
  },
  methods: {
    goQuizMake() {
      this.$router.push({name: 'QuizItdaMakeQuestion'});
    },
  },
}
</script>

<style>

</style>