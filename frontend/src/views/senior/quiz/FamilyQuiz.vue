<template>
  <div class="familyQuiz">
    <div class="wrap">
      <TitleBox :title="items[i].questionContent"/>
      <div v-if="questionImageUrl" class="question">{{ questionImageUrl }}</div>
      <img v-if="!questionImageUrl" class="defaultImage" :src="items[i].questionImageUrl">
      <ExampleBox 
        :example1="items[i].example1"
        :example2="items[i].example2"
        :example3="items[i].example3"
        :example4="items[i].example4"
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
import axios from "@/service/axios.service.js";

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
      i: 0,
      items: [],
    }
  },
  created() {
    this.getQuiz();
    this.onChangeImages();
  },
  watch: {
    items: function() {
      // console.log(this.items[this.i].questionContent)
      this.$store.commit("TTS", this.items[this.i].questionContent);
    }
  },
  methods: {
    getQuiz(){
      axios.get('/qna',{
        params:{
          // userId: Number(this.$store.state.userId)
          userId: 4
        }
      }).then((res) => {
        console.log(res.data);
        for (let i = 0; i < res.data.length; i++) {
          this.items.push({
            No: i + 1,
            questionId: res.data[i].questionId,
            userId: res.data[i].userId,
            adminId: res.data[i].adminId,
            questionContent: res.data[i].questionContent,
            questionImageUrl: res.data[i].questionImageUrl,
            example1: res.data[i].example1,
            example2: res.data[i].example2,
            example3: res.data[i].example3,
            example4: res.data[i].example4,
            answer: res.data[i].answer,
          });
        }
      }).catch(error => {
          console.log(error);
      });
    },
    onChangeImages(e) {
      const file = e;
    
      let reader = new FileReader()
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.items[this.i].questionImageUrl = reader.result
      }
    },
  },  
}
</script>