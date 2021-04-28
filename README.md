![ITda_MainImage](https://i.imgur.com/NAWl4Ym.png)

# 홀로 어르신과 가족들을 잇는 다리, 잇다👨‍👧‍👦

- 홀로 어르신의 정신·인지·생활 건강을 책임지는 감성 돌봄 AIoT
- 홀로 어르신의 일상, 건강, 추억을 가족들과 공유할 수 있는 서비스



## 팀 소개 - 404 All Found

- SSAFY 4기 2학기 특화프로젝트 (도메인: AI - 음성) 1등 🏆


| 팀원   | 역할        | Github                                           |
| ------ | ----------- | ------------------------------------------------ |
| 김현지 | 팀장, FE    | [@khyunji](https://github.com/khyunji)           |
| 신가영 | FE          | [@rkdud5253](https://github.com/rkdud5253)       |
| 이경섭 | AI, IoT     | [@Kyungsub-Lee](https://github.com/Kyungsub-Lee) |
| 이병희 | BE, AI, UCC | [@bhlee](https://github.com/mrcodemania)         |
| 최다애 | BE          | [@DAAECHOI](https://github.com/DAAECHOI)         |
| 홍진표 | AI, IoT     | [@hojp7874](https://github.com/hojp7874)         |





## 프로젝트 개요

<div align=center>
    <img src="https://img.shields.io/badge/Platform-Web, IoT-red">
    <img src="https://img.shields.io/badge/OS-Raspbian-orange">
    <img src="https://img.shields.io/badge/AI accelerater-RaspberryPi, Coral-yellow">
    <img src="https://img.shields.io/badge/Framework-Vue.js-green">
    <img src="https://img.shields.io/badge/Framework-SpringBoot-darkgreen">
    <img src="https://img.shields.io/badge/Framework-TensorflowLite-darkgreen">
    <img src="https://img.shields.io/badge/Database-MySQL-blue">
    <img src="https://img.shields.io/badge/Server-AWS-navy">
    <img src="https://img.shields.io/badge/Language-Java, Python-purple">
    <img src="https://img.shields.io/badge/Language-Javascript, SCSS-purple">
    <img src="https://img.shields.io/badge/Swagger-valid-indigo">
</div>

```markdown
'잇다'를 통해 어르신들의 일상, 건강, 추억을 저장할 수 있습니다.
특별한 물리적 조작 없이 음성으로만 페이지를 이용하면서, 가족들과 일상을 간편하게 공유하세요.
```



- #### **프로젝트 기간**: 2021.03.01 ~ 2021.04.09



- #### **기획 배경**

  > 노인 1인 가구 수가 점점 증가하고 있습니다. 이에 따라 노인 우울증이 사회적인 문제로 떠올랐습니다. 가족은 부모님과 함께할 시간을 내기가 어렵고 이는 어르신 케어 서비스의 수요로 이어집니다. 또한 앙케이트 설문조사 결과, 휴대폰 갤러리에 부모님의 사진 또는 동영상이 없는 사람들이 상당 부분을 차지합니다. 그래서 저희는 어르신을 케어하는 서비스, 그리고 어르신을 추억할 서비스를 통합해서 시니어 토탈 케어 서비스 `잇다`를 만들었습니다.

  


- #### **[와이어프레임](https://www.figma.com/file/oRAxUpZxWwNsctq0feSBpY/ITda?node-id=860%3A2)**

  <img src="https://i.imgur.com/QAog2BM.png" alt="와이어프레임" style="zoom:86%;" />
  <img src="https://i.imgur.com/YeQy3At.png" alt="가족들화면" style="zoom: 64.5%;" />




- #### [기능 명세서](https://docs.google.com/spreadsheets/d/1c1KCKVqxDb3XeEOA17qSMu9jqhYNPk5nlKfdFXYaOXo/edit?usp=sharing)

  ![기능 명세서](https://i.imgur.com/nct8IKB.png)



- #### ERD

  ![ERD](https://i.imgur.com/4oFfpie.png)

  

- #### 프로젝트 관련 영상

  - <a href="https://youtu.be/OH7Z2PVdVEY">시연 영상 바로가기</a>
  - <a href="https://youtu.be/PuwgGawG1Ms">UCC 바로가기</a>





## 📑 목차

#### 💻 [프로젝트 소개](#프로젝트-소개)

#### ✨ [주요 기능](#주요-기능)

- 👵 어르신
- 👨‍👨‍👧 어르신의 가족

#### 💡 [서비스 아키텍쳐](#서비스-아키텍쳐)

#### 🕹 [구성 요소](#구성-요소)

- Frontend
- Backend
- Raspberry Pi

#### 🛠 [코드 실행 방법](#코드-실행-방법)

- Raspberry Pi
- Web

#### 📌 [Git Branch 전략](#Git-Branch-전략)

- branch
- commit
- merge





## 프로젝트 소개

> **홀로 어르신과 가족들을 잇는 다리, 잇다**

```markdown
- 어르신들께서는 소극적인 정보 수용자에서 벗어나 참여의 주체가 되어 여가시간을 유익하게 보낼 수 있습니다. 
- 어르신이 잇다를 이용하시는 동안 기록된 일상 및 사진은 가족들에게 공유됩니다.
- 가족들은 부모님, 할머니, 할아버지의 일상을 확인하고 어르신이 푸실 문제를 직접 출제할 수 있습니다.
```

생각의 틀을 깨는 서비스,
할머니 할아버지를 생각하며 손주들이 직접 만든 서비스,
홀로 어르신의 정신·인지·생활 건강을 책임지는 감성 돌봄 AIoT 서비스 `잇다` 입니다.




## 주요 기능

> 잇다 서비스 대상자는 홀로 어르신과 어르신의 가족으로 나뉘어집니다.

### 👵 어르신

```
STT를 통해 자유롭게 화면을 전환, 조작 가능
각 화면에서는 TTS를 통해 사용자에게 정보를 제공
```

#### 1. 오늘의 체조

- `매일 제공되는 체조 동작을 따라하며 운동하는 기능`
- 운동을 하는 동안 pose estimation을 이용하여 신체의 각 관절 위치를 출력하고, 관절마다 각도를 계산하여 예시 동작과 얼마나 차이가 있는지 수치화합니다.
- 이를 통해 어르신은 실시간으로 자신의 자세가 정확한지 파악하여 교정할 수 있습니다.

#### 2. 사진 일기장

- `매일 웃는 사진을 기록하는 기능`
- Smile Detection으로 어르신의 미소를 감지할 때, 촬영하고 촬영된 사진을 일기장에 저장합니다.
- 어르신의 웃음만으로 간편히 일기장에 사진을 남기고, 즐거움과 함께 가족분들에게 추억을 선사합니다. 

#### 3. 가족 오락관

- `매일 주어지는 다섯 문제를 푸는 기능`
- TTS로 문제를 읽어주면 STT로 답을 맞힐 수 있습니다.
- 가족들이 퀴즈잇다 메뉴에서 직접 출제하는 문제가 제공됩니다.




### 👨‍👨‍👧 어르신의 가족

#### 1. 보고잇다

- `어르신의 기능 사용 데이터가 담긴 일일 보고서`
- 캘린더 형태의 보고서에서 확인하고 싶은 날짜를 선택하면 해당 날짜의 오늘의 체조, 사진 일기장, 가족 오락관 어르신의 서비스 이용 보고서를 볼 수 있습니다.
- 오늘의 체조: 신체 부위별 동작 정확도와 제공된 체조 사진 제공
- 사진 일기장: 어르신의 웃는 모습이 담긴 사진 제공
- 가족 오락관: 풀이 문제와 문제별 정답 여부 제공

#### 2. 퀴즈잇다

- `어르신에게 제공되는 문제를 직접 출제하는 기능`
- 사용자가 직접 문제를 만들 수 있으며, 출제한 문제 목록을 확인할 수 있습니다.
- 각 문제는 수정, 삭제가 가능합니다.




## 서비스 아키텍쳐

![](https://i.imgur.com/lagJ7pZ.png)



## 구성 요소

### Frontend

|   Vue 라이브러리    | Version | Comment                                |
| :-----------------: | :-----: | :------------------------------------- |
|        axios        | 0.21.1  | 서버로 요청을 보내기 위해 사용         |
|     vue-router      |  3.2.0  | 요청 URL에 따라 브라우저에서 돔을 변경 |
|        vuex         |  3.6.2  | 상태 관리를 위해 사용                  |
| vuex-persistedstate |  4.0.0  | 접속 기록을 저장하기 위한 라이브러리   |
|    sockjs-client    |  1.5.0  | 소켓을 구축하기 위한 라이브러리        |
|   webstomp-client   |  1.2.6  | 소켓을 구축하기 위한 라이브러리        |


### Backend

| 기술 스택  |   Version   | Comment                               |
| :--------: | :---------: | :------------------------------------ |
|  OpenJDK   |     1.8     | Java                                  |
|   Python   |     3.7     | Python                                |
| SpringBoot |    2.4.2    | 웹 애플리케이션 개발                  |
|   Maven    |    4.0.0    | 프로젝트 빌드 및 라이브러리 관리 도구 |
|   MySQL    |    4.4.3    | DB                                    |
|  Mybatis   |    2.1.2    | 서버와 DB의 연동을 위해 사용          |
|   Ubuntu   | 20.04.2 LTS | 서비스 제공을 위한 리눅스 서버 구축   |
|   Docker   |   20.10.2   | 컨테이너화 된 애플리케이션 관리       |
|   Nginx    |   1.19.6    | Vue 클라이언트 웹 서버 사용           |

| Spring 라이브러리 | Version | Comment                     |
| :---------------: | :-----: | :-------------------------- |
|    Spring Web     |    -    | Rest API 서버 구축          |
|     WebSocket     |    -    | 소켓 서버 구축              |
| Spring Data MySQL |    -    | MySQL 연결                  |
|  Spring Data JPA  |    -    | DB와의 연결 관리            |
|      Lombok       |    -    | Getter, Setter 등 빠른 구현 |

### Raspberry Pi

|     Python 라이브러리     | Version |                     Comment                      |
| :-----------------------: | :-----: | :----------------------------------------------: |
|       opencv-python       |  4.5.1  |         비디오 서비스를 위한 라이브러리          |
|        HaarCascade        |    -    |      머신 러닝 기반의 스마일 검출 알고리즘       |
|         websocket         |  0.2.1  |         소켓을 구축하기 위한 라이브러리          |
|          tflite           |  2.5.0  |        openpose 실행 머신러닝 라이브러리         |
|    google-cloud-speech    |  2.2.1  | 구글 클라우드 플랫폼의 Speech-To-Text 라이브러리 |
| google-cloud-texttospeech |  2.3.0  | 구글 클라우드 플랫폼의 Text-To-Speech 라이브러리 |
|          pyaudio          | 0.2.11  |        TTS binary 파일을 읽는 라이브러리         |




## 코드 실행 방법

### Raspberry Pi

#### 선행 작업

```bash
git clone https://lab.ssafy.com/s04-ai-speech-sub3/s04p23a404.git
cd s04p23a404/Edge/
```



#### 하드웨어 설정

- Raspberry Pi 4 Model B Rev 1.2
  - Linux raspberrypi 5.10.17-v7l+ #1403 SMP Mon Feb 22 11:33:35 GMT 2021 armv7l GNU/Linux
  - Chromium 88.0.4324.187
  - python3 -V: 3.7.3
- USB Accelerator
- 초음파 센서(HC-SR04)
  ![](https://i.imgur.com/08BFW4Q.png)



#### 라즈베리파이 설정

- I2C 사용

```
sudo raspi-config
```

위 명령어를 실행하면 설정창이 뜬다.
아래의 순서대로 진행한다.

> 3 Interface Options
> P5 I2C
> OK



#### 설치

- 요구 라이브러리 설치

```bash
sh install_requirements.sh
pip3 install requirements.txt
```

- 구글 클라우드 API 키 입력

```
export GOOGLE_APPLICATION_CREDENTIALS="/home/pi/coral/project-posenet/rare-mender-309911-7a13b00184e0.json"
```

위 코드는 임시 키이며 TTS, STT를 이용하기 위해서는 자신의 [google cloud platform](https://console.cloud.google.com/) 에 계정을 등록하여 키를 발급 받아야 한다.



#### 실행

```
python3 app.py
```



### Web

#### 선행 작업

- 프로젝트는 Java 8 버전에서 테스트 진행
- Node.js / NPM 설치
- Docker 설치
- Clone Repository

```bash
git clone https://lab.ssafy.com/s04-ai-speech-sub3/s04p23a404.git
cd s04p23a404
```



#### Frontend 설정

- 설정 파일 생성

```javascript
// frontend/src/store/Config.js
class Config {
    static ServerURL = "http://localhost:8080" // Backend Server URL
    static HostURL = "http://localhost" // Share에서 보여주는 URL 
}

export default Config
```

- 빌드

```bash
cd s04p23a404/frontend
yarn install
yarn serve
```

- Nginx 실행 (frontend)

```
docker run -p 80:80 -p 443:443 --network my-net -v /<Vue dist 디렉토리>:/home -v /etc/letsencrypt/:/etc/letsencrypt/ --name frontend nginx/frontend
```

위의 예시는 SSL 설정이 되어있는 nginx.conf 파일을 필요로 한다.  
필요할 경우 nginx.conf 파일 수정하여 Dockerfile 생성  

- nginx.conf 예시

```
user       nginx;  ## Default: nobody
worker_processes  auto;  ## Default: 1
pid        /var/run/nginx.pid;
worker_rlimit_nofile 8192;

events {
  worker_connections  4096;  ## Default: 1024
}

http {
  include    /etc/nginx/mime.types;
  include    /etc/nginx/proxy.conf;
  include    /etc/nginx/fastcgi.conf;

  default_type application/octet-stream;
  access_log   /var/log/nginx/access.log;
  error_log  /var/log/nginx/error.log;
  sendfile     on;
  tcp_nopush   on;

  server {
    listen       80;
    server_name  musicshareroom.tk www.musicshareroom.tk;
    root         /home;
    index        index.html;

    location / {
      return 301 https://$server_name$request_uri;
    }
  }

  server {
    listen       80;
    server_name  i4a105.p.ssafy.io;

    location / {
      return 301 $scheme://musicshareroom.tk$request_uri;
    }
  }

  server {
    listen       443 ssl;
    server_name  musicshareroom.tk www.musicshareroom.tk;
    root         /home;
    index        index.html;

    ssl_certificate /etc/letsencrypt/live/musicshareroom.tk/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/musicshareroom.tk/privkey.pem;

    location / {
      try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://backend:8080/;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        add_header 'Access-Control-Allow-Origin' '*';
    }
  }
}
```



#### Backend 설정

- 데이터베이스 설치, 실행

```bash
docker run --name my-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=비밀번호 -d mariadb
```

- Docker 네트워크 생성

```bash
docker network create my-net
```

- Dockerfile 작성

```
FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD target/itda-0.0.1-SNAPSHOT.jar itda.jar
ENTRYPOINT ["java", "-jar", "/itda.jar"]
```

- 빌드 및 실행

```bash
# .jar file build
./mvnw package

cp target/*.jar <Dockerfile 경로>/target/itda.jar

cd <Dockerfile 경로>

# docker image build
docker build -t springboot .

# docker run
docker run -p 8000:8000 --name itda --network my-net -v springboot
```



## Git Branch 전략

### branch

```markdown
(master) -> (develop) -> (feature/[frontend/backend]/feature name)
```

* master : 배포 가능한 상태로 유지
* develop : 개발용 최상위 branch
* fetaure : 기능별 branch



### commit

: commit message를 정형화하고 효율적으로 관리하기 위하여 **commit 자동화 라이브러리 `git-cz`** 도입

```javascript
"types": {
  "feat": {
    "description": "새로운 기능 추가",
    "emoji": "✨",
    "value": "feat"
  },
  "chore": {
      "description": "자잘한 작업",
      "emoji": "🧵",
      "value": "chore"
  },
  "fix": {
      "description": "버그 수정",
      "emoji": "👾",
      "value": "fix"
    },
  "refactor": {
    "description": "성능 개선, 리팩토링",
    "emoji": "🐙",
    "value": "refactor"
  },
  "style": {
    "description": "코드에 영향을 주지 않는 UI/UX/디자인 변경사항",
    "emoji": "🎨",
    "value": "style"
  },
  "docs": {
    "description": "문서 수정",
    "emoji": "📝",
    "value": "docs"
  },
  "build": {
    "description": "시스템 또는 외부 종속성에 영향을 미치는 변경사항 (npm, gulp, yarn 레벨)",
    "emoji": "🧱",
    "value": "build"
  }
}
```

1. git-cz 설치

```bash
npm install -g git-cz
```

2. `changelog.config.js` 파일을 생성한 뒤 커스텀

> 이 때, 파일이 부모 폴더에 위치하여야 한다.

3. commit 시 git add 후 git commit 대신 `git cz` 명령어 사용




### merge

```markdown
- Merge Request 요청 전 현재 작업 진행 상황 공유
- 권한은 모두가 가지고 있지만 웬만하면 담당자가 직접 merge
- 다른 사람의 MR에 적극적으로 리뷰하고, 댓글 남기기 (ex. LGTM😊)
- merge 후 불필요한 branch 지우기 (✅ Remove source branch when merge request is accepted. 옵션 체크하기)
```