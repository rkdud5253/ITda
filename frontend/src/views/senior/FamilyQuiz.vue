<template>
  <div class="familyQuiz">
    <TitleBox title="잇다는 얼마나 멋진 서비스일까요?"/>
    <div class="question">{{ question_image_url }}</div>
    <ExampleBox 
      example1="최고로 멋지다!"
      example2="정말로 멋지다!"
      example3="진심으로 멋지다!"
      example4="역대급 멋지다!"
    />
  </div>
</template>

<script>
import '@/components/css/senior/familyQuiz.scss';
import TitleBox from '@/components/senior/TitleBox.vue';
import ExampleBox from '@/components/senior/family_quiz/ExampleBox.vue';
export default {
  name: "SeniorMain",
  components: {
    TitleBox,
    ExampleBox,
  },
  props: {
    question_image_url: {
      type: Image,
      required: false,
    },
  },
  data: function() {
return {
    
  };
}
}
</script>