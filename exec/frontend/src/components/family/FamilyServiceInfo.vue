<template>
  <v-container>
    <!-- 서비스소개 컴포넌트-->
    <div class="main-wrap">
      <div class="main-img">
        <v-img
          class="my-5"
          :src="require('@/assets/family/image1Small.jpg')"
          gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
          height="60%"
          max-height="500"
          width="100%"
        ></v-img>
      </div>
      <div class="main-text">
        <h2 class="h2"><span class="mb-1" style="background-color: white;">홀로 어르신의</span></h2>
        <h2 class="h2"><span class="mb-1" style="background-color: white;">일상과 건강을 책임지는</span></h2>
        <h2 class="h2"><span class="mb-1" style="background-color: white;">감성돌봄 서비스</span></h2>
        <h1 class="h1"><span class="mb-1" style="color: #FC5355; background-color: white;">잇다</span></h1>
      </div>
    </div>

    <!-- 기능별소개 -->
    <div class="my-2">
      <v-row>
        <!-- 오늘의체조 -->
        <v-col 
          cols="12"
          sm="4"
        >
          <v-img
            class="img-function"
            :src="require('@/assets/family/image2Small.jpg')"
            gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            max-height="500"
          >
            <v-row
              class="fill-height text-right ma-0"
            >
              <v-col cols="12">
                <v-chip
                  label
                  class="text-uppercase my-1"
                  color="#FFFFFF"
                  text-color="#FC5355"
                  large
                >
                  <h1 class="h1">오늘의체조</h1>
                </v-chip>
                
                <div class="caption my-1">
                  오늘의 체조 영상과 정확도를 확인하세요
                </div>
              </v-col>
            </v-row>
          </v-img>
        </v-col>
        <!-- 사진일기장 -->
        <v-col 
          cols="12"
          sm="4"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/image3Small.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
              max-height="500"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                    text-color="#FEA601"
                    large
                  >
                    <h1 class="h1">사진일기장</h1>
                  </v-chip>
                  
                  <div class="caption my-1">
                    매일 기록되는 소중한 사진과 영상을 볼 수 있어요
                  </div>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>
        <!-- 가족오락관 -->
        <v-col 
          cols="12"
          sm="4"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/image4Small.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
              max-height="500"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                    text-color="#597ED2"
                    large
                  >
                    <h1 class="h1">가족오락관</h1>
                  </v-chip>
                  
                  <div class="caption my-1">
                    어르신이 오늘 어떤 문제를 푸셨는지 확인하고<br>가족과 관련된 문제를 출제해 보세요
                  </div>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>
      </v-row>
    </div>

    <v-divider class="my-10"></v-divider>

    <div class="my-10" style="text-align-last: center; padding: 20px;">
      <h2 style="color: #FC5355;">감성돌봄 서비스 <span style="font-size: xx-large;">잇다</span></h2>
      <h3 class="h2">홀로 어르신과 가족들을 이어주는 다리</h3>
    </div>
    
    <div class="my-10 mb-10" style="text-align-last: center;">
      <h3>정신, 인지, 생활 건강을 책임지는 어르신용 화면 ↔ 일상, 건강상태, 추억을 확인하는 가족들용 웹 서비스</h3>
      <strong>보고잇다</strong> - 어르신들의 오늘의체조, 사진일기장, 가족오락관 사용 기록을 보고서로 확인하세요<br>
      <strong>퀴즈잇다</strong> - 가족오락관에 출제되는 문제를 직접 만들어 볼 수 있어요
    </div>

    <v-divider class="my-10"></v-divider>

  </v-container>
</template>

<script>
export default {
  name: "FamilyServiceInfo",
};
</script>

<style>
  .main-wrap {
    position: relative;
  }
  .main-img {
    width: 100%;
    vertical-align: middle;
  }
  .main-text {
    text-align: left;
    position: absolute;
    top: 35%;
    left: 10%;
  }
  .caption {
    color: white;
    font-weight: bold;
  }
  .h1 {
    font-size: 2rem;
  }
  .h2 {
    font-size: 1.5em;
  }
  @media (max-width: 960px) {
    .h1 {
      font-size: 1.5rem;
    }
    .h2 {
      font-size: 0.8rem;
    }
  } 
  .img-function {
    transition: .3s linear;
  }
</style>
