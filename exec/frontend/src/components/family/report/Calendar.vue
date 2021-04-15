<template>
  <!-- 보고서 달력 컴포넌트 -->
  <v-row class="fill-height mx-2">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            class="mr-4"
            color="white"
            disabled
          ></v-btn>
          <v-spacer></v-spacer>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="load">
            {{ title }}년
          </v-toolbar-title>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            outlined
            color="grey darken-2"
            @click="setToday"
          >
            Today
          </v-btn>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="#FEA601"
          :type="type"
          @click:date="viewDay"
        ></v-calendar>

        <v-dialog
          v-model="dialog"
          max-width="300"
        >
          <v-card>
            <v-card-title style="justify-content: center; color: #FEA601;">
              <h2 class="modalFont">일일 보고서</h2>
            </v-card-title>
            <!-- 세가지 기능 중 하나라도 사용하셨을 때 -->
            <v-card-text 
              class="modalFont my-2" 
              style="text-align-last: center;"
              v-if="dailyData==true"
            >
              <h3>오늘의 보고서를 확인하시려면<br><span style="color: #FEA601">보고서 보기</span>를 클릭하세요!</h3>
            </v-card-text>
            <!-- 세가지 기능 모두 사용하지 않았을 때 -->
            <v-card-text 
              class="modalFont my-2" 
              style="text-align-last: center;"
              v-if="dailyData==false"
            >
              <h3>어르신께서 오늘의 기능을<br>사용하지 않으셨네요<span style="color: black">😲</span><br><span style="color: #FC5355">다른 날짜</span>를 확인해 보세요~</h3>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                class="modalFont"
                v-if="dailyData==true"
                color="#FEA601"
                dark
                block
                @click="goDailyReport"
              >
                보고서 보기
              </v-btn>
              <v-btn
                class="modalFont"
                v-if="dailyData==false"
                color="#FC5355"
                text
                @click="goBack"
              >
                돌아가기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import axios from '@/service/axios.service.js';
export default {
  name: "Calendar",
  data() {
    return{ 
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
      },
      load: false,
      todaydate: '',
      dialog: false,
      reportData1: '',
      reportData2: '',
      }
    },
    mounted () {
      // 캘린더 정보 불러오기
      this.load = true;
      this.$refs.calendar.checkChange()
    },
    computed: {
      title : function () {
        return this.$refs.calendar.title;
      },
      dailyData :  function () {
        return (this.reportData1 === 'success') || (this.reportData2 === 'success');
      }
    },
    methods: {
      viewDay ({ date }) {
        this.focus = date
        this.dialog = true
        this.todaydate = date

        // 오늘의 체조, 가족 오락관
        axios
          .get(`/report/daily`, {
            params: {
              reportDate  : date,
              userId : this.$store.state.userId,
            }
          })
          .then((response) => {
            this.reportData1 = response.data
          })
          .catch((error) => {
            console.log(error);
          });

        // 사진 일기장
        axios
          .get(`/files/daily`, {
            params: {
              fileDate : date,
              userId : this.$store.state.userId,
            }
          })
          .then((response) => {
            this.reportData2 = response.data
          })
          .catch((error) => {
            console.log(error);
          });
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      goBack() {
        this.dialog = false
      },
      goDailyReport() {
        this.$router.push({
            name: 'BogoItdaDaily',
            query: {
              date: this.todaydate,
          },
        })
      },
    },
}
</script>

<style>
  .theme--light.v-btn.v-btn--disabled.v-btn--has-bg {
    background-color: rgba(0, 0, 0, 0) !important;
  }
  @font-face {
    font-family: 'ELAND_Choice_M';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/ELAND_Choice_M.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  .modalFont {
    font-family: 'ELAND_Choice_M';
  }
</style>
