<template>
  <div class="familyQuiz">
    <TitleBox :title="items[1]"/>
    <div class="question">{{ question_image_url }}</div>
    <div class="answer">
      <GoToMainRed />
      <ExampleBox 
        example1="최고로 멋지다!"
        example2="정말로 멋지다!"
        example3="진심으로 멋지다!"
        example4="역대급 멋지다!"
      />
      <GoNext />
    </div>
  </div>
</template>

<script>
import '@/components/css/senior/familyQuiz.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import ExampleBox from '@/components/senior/family_quiz/ExampleBox.vue';
import GoToMainRed from '@/components/senior/GoToMainRed.vue';
import GoNext from '@/components/senior/GoNext.vue';
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
      items: [
      "첫 번째 문제입니다.",
      "잇다는 얼마나 멋진 서비스일까요?",
      "다음 문제입니다."
      ]
    }
  },
  mounted() {
    this.$store.commit("TTS", this.items[0] + this.items[1] );
  },
}
</script>