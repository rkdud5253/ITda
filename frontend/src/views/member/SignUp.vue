<template>
  <v-app>
    <!-- 회원가입 페이지 -->
    <FamilyAppBar />
    <v-container class="my-15" style="text-align: -webkit-center;">
      <v-card class="signup-card">
        <v-form class="mx-10" ref="form" v-model="valid" lazy-validation>
          <v-text-field
            class="pt-15 pb-1"
            v-model="member.email"
            :counter="30"
            :rules="emailRules"
            label="이메일"
            required
            type="email"
            hint="이메일 주소 입력"
            color="#FC5355"
            outlined
            dense
          ></v-text-field>
          <v-text-field
            class="py-1"
            :rules="passwordRules"
            required
            v-model="member.password"
            label="비밀번호"
            type="password"
            :counter="20"
            hint="영문, 숫자 조합 8~20자 이내"
            color="#FC5355"
            outlined
            dense
          ></v-text-field>
          <v-text-field
            class="py-1"
            v-model="member.name"
            :counter="10"
            :rules="nameRules"
            label="이름"
            required
            type="text"
            color="#FC5355"
            outlined
            dense
          ></v-text-field>
          <v-text-field
            class="py-1"
            v-model="member.phone"
            :counter="11"
            :rules="phoneRules"
            label="전화번호"
            required
            hint="숫자만 입력해주세요"
            type="text"
            color="#FC5355"
            outlined
            dense
          ></v-text-field>
          <v-btn
            style="width:100%; height:40px; margin-top: 20px;"
            dark
            color="#FC5355"
            @click="signUp"
            >회원가입
          </v-btn>
        </v-form>
        <div class="my-5">
          이미 회원이신가요?
          <v-btn
            class="mx-2" 
            text color="#FEA601"
            @click="goLogin"
          >로그인
          </v-btn>
        </div>
      </v-card>
    </v-container>

    <!-- 이메일 중복 모달창 -->
    <v-dialog
      v-model="emailDialog"
      max-width="300"
    >
      <v-card>
        <v-card-title style="justify-content: center; color: #FEA601;">
          <h2 class="modalFont my-2"></h2>
        </v-card-title>
        <v-card-text 
          class="modalFont my-2" 
          style="text-align-last: center;"
        >
          <h4>중복된 이메일 주소입니다.</h4>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="modalFont"
            color="#FEA601"
            dark
            block
            @click="notSignUp"
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 회원가입 실패 모달창 -->
    <v-dialog
      v-model="notSignUpDialog"
      max-width="300"
    >
      <v-card>
        <v-card-title style="justify-content: center; color: #FEA601;">
          <h2 class="modalFont my-2"></h2>
        </v-card-title>
        <v-card-text 
          class="modalFont my-2" 
          style="text-align-last: center;"
        >
          <h4>회원가입을 실패하였습니다.</h4>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="modalFont"
            color="#FEA601"
            dark
            block
            @click="notSignUp"
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </v-app>
</template>

<script>
import axios from "@/service/axios.service.js";
import FamilyAppBar from '@/components/family/FamilyAppBar.vue'

export default {
  name: "SignUp",
  components: {
    FamilyAppBar,
  },
  data() {
    return {
      valid: true,
      emailRules: [
          (v) => !!v || "이메일을 입력해주세요",
          (v) => /.+@.+\..+/.test(v) || "유효한 이메일이 아닙니다",
      ],
      passwordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        // (v) => (v && v.length >= 8 && v.length <= 20) || "8~20자리 입력",
        (v) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(v) || "문자, 숫자 조합 8~20자리 입력",
      ],
      nameRules: [
        (v) => !!v || "이름을 입력해주세요",
        (v) => (v && v.length <= 10) || "10자리 이하 입력",
      ],
      phoneRules: [
        (v) => !!v || "전화번호를 입력해주세요",
        (v) => (v && v.length >= 7 && v.length <= 11) || "7~11자리 입력",
      ],
      member: {
        email: '',
        password: '',
        name: '',
        phone: '',
      },
      emailDialog: false,
      notSignUpDialog: false,
    };
  },
  methods: {
    signUp(){
      if(this.$refs.form.validate()) {
        // 중복 유뮤
        axios.get("/admin/email",{
          params:{
            adminEmail: this.member.email
          }
        }).then((res) => {
          if(res.data.adminId > 0) {
            // 이메일 중복
            this.emailDialog = true
          }
          else {
            axios.post("/admin/signup",{
              adminEmail:this.member.email,
              adminPwd:this.member.password,
              adminName:this.member.name,
              adminPhone:this.member.phone,
            }).then((res2) => {
              if(res2.data == "success"){
                // 회원 가입 완료
                this.$router.go(this.$router.push({name: 'Login'}))
              }
              else 
                // 회원 가입 실패
                this.notSignUpDialog = true
            })
          }
        })
      } 
    },
    goLogin() {
      this.$router.go(this.$router.push({name: 'Login'}))
    },
    notSignUp() {
      this.emailDialog = false
    },
  },
}
</script>

<style>
  .signup-card {
    width: 450px;
    height: 500px;
  }
</style>