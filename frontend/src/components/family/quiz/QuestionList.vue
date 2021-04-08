<template>
  <v-card class="mx-2 mt-1 mb-5">
    <!-- 내가 낸 문제 리스트 컴포넌트 -->
    <v-card-title>
      문제 목록
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="문제 제목"
        single-line
        hide-details
        color="#597ED2"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="question"
      :search="search"
      @click:row="goQuizDetail"
      style="cursor:pointer"
    ></v-data-table>
  </v-card>
</template>

<script>
import axios from "@/service/axios.service.js";

export default {
  name: "QuestionList",
  components: {

  },
  data () {
    return {
      search: '',
      headers: [
        { 
          text: 'No',
          align: 'start',
          value: 'No' 
        },
        {
          text: '문제',
          align: 'start',
          sortable: false,
          value: 'name',
        },
      ],
      question: [
      ],
    }
  },
  created(){
    this.getList();
  },
  methods:{
    getList(){
      const adminId = this.$store.state.adminId;
      
      axios.get('/qna/list/admin', {
        params: {
          adminId: adminId
        }
      }).then((res) => {
        console.log(res);
        for(let i = 0; i < res.data.length; i++)
          this.question.push({
            Id: res.data[i].questionId,
            No: i + 1,
            name: res.data[i].questionContent});
      }).catch(error => {
        console.log(error);
      });

    },
    goQuizDetail: function(idx) {
      this.$router.push({path: `/family/quiz/detail/${idx.Id}`})
    },
  }
}
</script>

<style>

</style>