<template>
  <div class="familyQuiz">
    <div class="wrap">
      <TitleBox :title="items[2]"/>
      <div class="question">{{ question_image_url }}</div>
      <ExampleBox 
        example1="최고로 멋지다!"
        example2="정말로 멋지다!"
        example3="진심으로 멋지다!"
        example4="역대급 멋지다!"
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
      // 문법상 틀리더라도 TTS가 가장 자연스럽게 들리도록 띄어쓰기 해주세요!
      "첫번 째 문제입니다.",
      "  ",
      "잇다는 얼마나 멋진 서비스일까요?",
      "다음 문제입니다."
      ]
    }
  },
  mounted() {
    this.$store.commit("TTS", this.items[0] + this.items[1] + this.items[2] );
  },
}
</script>