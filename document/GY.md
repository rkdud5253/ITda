# STUDY



## 0310

### [Pytorch] torch.nn.Conv2d

> Sub1 명세서를 보다가 이해가 잘 안돼서 찾아본 자료

```python
class torch.nn.Conv2d(in_channels, out_channels, kernel_size, stride=1, padding=0, dilation=1, groups=1, bias=True)
```

- 필수요소 : `in_channels`, `out_channels`, `kernel_size`

**in_channels**(int) : input image의 channel수. rgb이미지라면 3이 됨.

**out_channels**(int) : convolution에 의해서 생성된 channel의 수.

**kernel_size**(int or tuple) : convoling_kenel 의 크기. 보통은 filter라고 부르는 것과 동일.

**stride**(int or tuple) : convolution의 stride를 얼만큼 줄 것인가. Default는 1임.

**padding**(int or tuple) : zero padding을 input의 양쪽에 인자만큼 줌. Default는 0이라서 기본적으로 설정해주지 않으면 zero padding은 하지 않음.

[출처] https://hichoe95.tistory.com/14



ex) `nn.Conv2d(16, 32, 4)` -> 16개의 채널을 32개의 채널로 Convolution할 것, 4x4필터로!



##### 0309_아이디어 회의 중 혼자 간단하게 만들어 본 홈화면

> Figma 첫 사용 테스트! 사용자가 어르신들이기 때문에 이런식으로 단순한 UI로 제작해야할 것 같다. 

![아이디어](GY.assets/아이디어.png)







## 0311

##### 기획

**영양채우자 (주기능)**

- 음식 사진을 찍으면 어떤 음식인지 알려주고, 식단을 영양에 맞게 잘 드셨는지 정보 제공

**자식아봐라**

- 1일 1사진 챌린지 : 하루에 한번 서비스가 제안하는 질문에 맞는 영상을 찍는 챌린지

**편지남겨라**

- 자식들이 남긴 간단 음성 편지 읽어주기 (TTS)

**일정읽어라**

- 자식들이 남긴 일정 읽어주기 (TTS)

~~**치매야가라**~~

- ~~AI와 대화하며 치매 예방 문제풀기~~

~~**추억은와라**~~

- ~~AI와 대화하며 가족간의 추억 되새김하기~~



- IoT 도입할 것인지 회의





### Jira&JQL활용

> 0311 Live 특화PJT 특별멘토링 강의 정리



### 1. Jira

- epic, story, task, bug ..



### 2. JQL

#### JQL

- Jira Query Language
- Jira Issue를 구조적으로 검색하기 위해 제공하는 언어
- SQL과 비슷한 문법
- Jira의 각 필드들에 맞는 특수한 예약어들을 제공
- 쌓인 Issue들을 재가공해 유의미한 데이터를 도출해내는데 활용(Gadget, Agile Board 등)



#### JQL Operators

- =, !=, >, >=
- in, not in
- ~ (contains), !~ (not contains)
- is empty, is not empty, is null, is not null
- was, was in, was not in
- changed (from xxx to xxx)



```
project = DP AND assignee in (currentUser()) AND status = "To Do"
```

```
project = DP AND assignee in (currentUser()) AND status changed
```



#### Relative Dates

- 상대적 날짜
- -2w, -1w, -6d, -5d, -4d, -3d, -2d, -1d, Current(Today), 1d, 2d, 3d, 4d, 5d, 6d, 1w, 2w
- 년 단위도 가능

```
project = DP AND assignee in (currentUser()) AND updated > -2d
```



#### JQL Keywords

- AND
- OR
- NOT
- EMPTY
- NULL
- ORDER BY (DESC, ASC)



#### JQL Functions

- 주로 쓰는 함수

- endOfDay(), starOfDay()
- endOfWeek() (Saturday), startOfWeek() (Sunday)
- endOfMonth(), startOfMonth(), endOfYear(), startOfYear()
- currentLogin()
- currentUser()
- updatedBy(user, dateFrom(optional), dateTo(optional))

```
project = DP AND assignee in (currentUser()) AND updated > startOfDay()
```

```
project = DP AND assignee in (currentUser()) AND updated > startOfDay(-1d)
```



#### JQL 활용예시 

##### 1. Filter share

- `Save as` 버튼 누르면 필터 저장 가능
- 필터의 `Details`에 들어가면 누구와 공유할지 설정 가능

```
project = "Demo Project" AND updated > startOfWeek(1d) AND updated < endOfWeek(-1d)
```



##### 2. Dashboard, Gadget

- Dashboard 만들기 가능, 팀원간 공유 및 즐겨찾기 가능

- Dashboard안에서 Gadget 추가하기
- 주로 쓰는 Gadget : Filter Results, Heat Map, Assigned to Me, Pie Chart



##### 3. Agile Board

- 기본으로 생성된 보드는 프로젝트에 포함된 이슈들이 보임
- 보드 생성도 가능 (Kanban)



##### 4. Issue Export

- Excel을 주로 씀
- 다양하게 활용 가능!



#### 현업에서의 Jira 활용

1. Issue Tracking (Jira Sortware)
2. Repo.Hosting (Bitbucket, gerrit, GitHub)
3. Code Review (Bitbucket, gerrit, GitHub)
4. Build & Deploy (Bamboo, Jenkins, circleci)

Knowledge Base(Confluence)





### 3. Q&A

- 팀원들이 모두 같은 일(ex. 기획 회의)을 할 때, 담당자를 여러명 설정할 수 없어서 같은 일을 팀원의 수 만큼 생성하고 있는데요! (ex. 기획회의 * 6개) 현업에서도 실제로 이렇게 사용하나요?

  -> 협업에서는 SubTask로! 

  -> 이슈를 연결시킬 수도 있음

- 소멸차트 롤러코스터(ㅎㅎ)

  -> 차트에 크게 신경안써도 될 것 같음

- JQL 작성 시 무조건 대문자만 가능한가요?

  -> 대소문자 가능

- 보통 현업에서 에픽의 리포터, 담당자는 누구로 설정하나요?

  -> 협업에서는 주로 PM분이 담당

- 실제 업무에서 지라가 빛을 발휘한 에피소드가 있다면 언제인지 공유 해주실 수 있나요?

  -> 어떻게 품질관리를 하냐할 때 지표를 활용하는데, 이때 지라 내용을 잘 정리해서 보여주면 편함



## 0312

- 기획회의
  - AI + 임베디드
  - 라즈베리파이와 웹을 사용해서 만들기로 확정!
  - 프론트(2) / 백(2) / 임베디드(2)로 담당자 정하기