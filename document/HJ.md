# git-cz란? commit 자동화 라이브러리

[git-cz](https://www.npmjs.com/package/git-cz)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bad5dfa2-b74e-4caf-8552-bba53e55842f/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bad5dfa2-b74e-4caf-8552-bba53e55842f/Untitled.png)

이 라이브러리를 사용하면 커밋 메세지를 예쁘게 관리할 수 있습니다!



## 설치 방법

1. -g 옵션으로 global 하게 설치

```jsx
npm install -g git-cz
```

**주의**: git bash에서는 조작이 안될 수도 있으니 CMD나 VSC 내의 터미널 사용을 권장합니다!

1. 프로젝트에 `changelog.config.js` 파일 작성하여 커스텀
2. 커밋할 때 git commit 대신 git cz 명령어를 써주면 형식에 맞춰 커밋이 가능