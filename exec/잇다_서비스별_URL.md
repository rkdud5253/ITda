## 잇다 :: 홀로 어르신과 가족들을 이어주는 다리



### 서비스 URL : http://j4a404.p.ssafy.io/

가족들 화면 - 테스트 아이디 : itda@naver.com (김현지)
가족들 화면 - 테스트 비밀번호: dkssudgktpdy12

어르신 화면 - 테스트 아이디 : userId: 1 (김순자) - 현재 itda@naver.com 와 연결된 상태



가족들은 http://j4a404.p.ssafy.io/ 에서 메인 화면 시작

어르신은 http://j4a404.p.ssafy.io/senior/main 에서 메인 화면 시작





-------

### [가족들화면]



#### 메인화면

- /



#### 회원

- 로그인 : /login
- 회원가입 : /signup

- 어르신 등록 : /mypage



#### 보고잇다 (어르신 기능 사용 결과 확인 보고서_오늘의 체조, 사진 일기장, 가족 오락관)

- 보고잇다_달력 : /family/report
- 보고잇다_일일보고서 : /family/report/daily



#### 퀴즈잇다 (가족들이 직접 출제한 가족 오락관 문제)

- 퀴즈잇다_내가 낸 문제 목록 : /family/quiz

- 퀴즈잇다_문제 내기 : /family/quiz/make
- 퀴즈잇다_문제 보기 : /family/quiz/detail/:questionId

- 퀴즈잇다_문제 수정 : /family/quiz/modify/:questionId



------------------------------

### [어르신 화면]

```markdown
- 저희 서비스는 IoT와 연동하여 화면 전환시 STT를 이용하기 때문에, 버튼을 눌러 화면을 전환할 수 없습니다. 번거로우시겠지만, GET 방식으로 URL을 변경하여 화면을 이동해주시기를 부탁드립니다.
- 2018년 1월부터 시행된 크롬의 자동재생 정책 변경으로 인하여 크롬 환경에서는 어르신 화면 TTS가 제대로 작동하지 않을 수 있습니다. Microsoft Edge 브라우저를 이용해주시기를 부탁드립니다.
```



#### 메인 화면

기존 로그인 사용자

- Splash : /senior/splash

- 메인 화면 : /senior/main

비로그인 사용자

- Splash : /senior/splash

- 로그인 : /senior/login (가족들 화면에서 어르신을 등록하기를 통해 어르신 성함을 입력하고 연결하기를 누른 후, 어르신 화면 로그인 페이지에서 10분 이내에 STT로 성함을 말씀하시면 연결 완료)
- 메인 화면 : /senior/main



#### 오늘의 체조

- 오늘의 체조_준비 화면 : /senior/exercise/loading

- 오늘의 체조 : /senior/exercise

- 오늘의 체조_결과 화면 : /senior/exercise/result



#### 사진 일기장

- 사진 일기장_준비 화면 : /senior/photodiary/loading
- 사진 일기장 : /senior/photodiary
- 사진 일기장_결과 화면 : /senior/photodiary/result



#### 가족 오락관

- 가족 오락관_준비 화면 : /senior/quiz/loading
- 가족 오락관 : /senior/quiz
- 가족 오락관_결과 화면 : /senior/quiz/result



------------

### [IoT] - Coral, Raspberry Pi

```markdown
- Python 3.7
```

