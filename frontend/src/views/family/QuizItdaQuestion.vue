<template>
  <v-app>
    <FamilyAppBar />
    <!-- 문제 상세 페이지 -->
    <v-container>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          class="my-5"
        >
          <h1 style="color: #597ED2">문제 상세보기</h1>
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
            문제목록
          </v-btn>
        </v-col>
      </v-row>
      <Question />
    </v-container>
    <div class="mb-5">
      <TopButton />
    </div>
  </v-app>
</template>

<script>
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'
import TopButton from '@/components/family/TopButton.vue'
import Question from '@/components/family/quiz/Question.vue'

export default {
  name: "QuizItdaQuestion",
  components: {
    FamilyAppBar,
    TopButton,
    Question,
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