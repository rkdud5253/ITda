## 2021. 03. 11. git-cz

### git-cz란? commit 자동화 라이브러리

[git-cz](https://www.npmjs.com/package/git-cz)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bad5dfa2-b74e-4caf-8552-bba53e55842f/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bad5dfa2-b74e-4caf-8552-bba53e55842f/Untitled.png)

이 라이브러리를 사용하면 커밋 메세지를 예쁘게 관리할 수 있습니다!



### 설치 방법

1. -g 옵션으로 global 하게 설치

```jsx
npm install -g git-cz
```

**주의**: git bash에서는 조작이 안될 수도 있으니 CMD나 VSC 내의 터미널 사용을 권장합니다!

1. 프로젝트에 `changelog.config.js` 파일 작성하여 커스텀
2. 커밋할 때 git commit 대신 git cz 명령어를 써주면 형식에 맞춰 커밋이 가능





## 2021. 03. 12. 라즈베리파이 도입

### 라즈베리파이란?

라즈베리파이(영어: Raspberry Pi)는 영국 잉글랜드의 라즈베리파이 재단이 학교와 개발도상국에서 기초 컴퓨터 과학의 교육을 증진시키기 위해 개발한 신용카드 크기의 싱글 보드 컴퓨터이다.



### 우리 프로젝트에 임베디드(라즈베리파이)를 도입한다면?

라즈베리파이도 결국 컴퓨터니까 컴퓨터에 크롬을 넣는다.
공통프로젝트 때처럼 웹사이트는 Vue.js / React를 이용하여 개발한 뒤, AWS로 배포한다.
이를 통해 라즈베리파이와 우리가 그린 화면을 연결할 수 있다.
노트북 모니터 대신 TV가 화면이 될 뿐이다!