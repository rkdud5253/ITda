[TOC]

# study

## Pose estimation

google coral에서 지원하는 샘플 pose estimation을 colab에서 실행해보려는 시도를 했다.

[google-coral/project-posenet: Human Pose Detection on EdgeTPU (github.com)](https://github.com/google-coral/project-posenet)

[Software | Coral](https://coral.ai/software#pycoral-api)

[Get started with the Dev Board | Coral](https://coral.ai/docs/dev-board/get-started/#run-pycoral)

colab에 TPU로 런타임을 연결한다.

설치를 모두 하고 simple_pose.py를 실행하는데 오류가 났다.

```python
!python simple_pose.py
```

```
Traceback (most recent call last):
  File "simple_pose.py", line 15, in <module>
    from pose_engine import PoseEngine
  File "/content/gdrive/MyDrive/SSAFY/project-posenet/pose_engine.py", line 15, in <module>
    from pycoral.utils import edgetpu
  File "/usr/local/lib/python3.7/dist-packages/pycoral/utils/edgetpu.py", line 24, in <module>
    from pycoral.pybind._pywrap_coral import GetRuntimeVersion as get_runtime_version
ImportError: libedgetpu.so.1: cannot open shared object file: No such file or directory
```

`libedgetpu.so.1`는 so확장자인데, 이건 window 환경에서는 실행할 수 없다(?)는 듯 하다.

그래서 google coral 또는 리눅스 환경에서 실행해야할 것 같다.

그리고, colab의 TPU세션은 coral의 HTPU와는 달라서 사용할 수 없을지도 모른다.

지금 라즈베리파이가 다른 팀원에게 있는데, 이걸 vnc를 이용하면 원격으로 라즈베리파이 환경에 접속할 수 있다고 한다.

팀원이 이 환경에 연결할 때 까지 기다려야겠다.







### 0318

라즈베리파이, coral은 일단 보류하기로 하고 노트북을 임베디드장치로 사용해서 TV에 띄우기로 했다...

Pose estimation 코드를 받아서 실행을 해보려고 했다.

microsoft에서 만든 pose estimation 모델이다.

[microsoft/human-pose-estimation.pytorch: The project is an official implement of our ECCV2018 paper "Simple Baselines for Human Pose Estimation and Tracking(https://arxiv.org/abs/1804.06208)" (github.com)](https://github.com/microsoft/human-pose-estimation.pytorch)

이 모델을 실행하는데 여러가지 obstacle이 있었다.

1. Pretrain되지 않은 모델을 받으려다보니 데이터 용량이 자그마치 40gb
2. Linux에서 테스트된 코드라서 window에서 실행하기 까다로웠다.
3. 리눅스 가상환경을 설치해서 다운로드를 하고 training을 시도하니 gpu가 설정이 안돼있어서 cuda를 사용하지 못했다.

해결 방법은

1. 끈기있게 기다린다.
2. build하는 부분에서 문제가 발생하는데, `python setup.py build_ext` 명령어를 사용하니까 cpu_nms.pyx파일을 실행하려고 하는데 이 파일이 없다.  
   추측은.. build를 하면 pyx파일을 생성해야 하는데 잘못된 path설정으로 파일 생성이 안되는 것?  
   linux에서는 build가 됐는데 cuda를 사용하지 못한 build일 것 같아서 참고하는게 도움이 될지 잘 모르겠다..
3. 사실 2번을 하기 전에 먼저 3번을 진행했는데, 데이터 받는데 거의 한나절이 걸렸다.  
   모든 환경설정이 끝나고 이제 `python valid.py`, `python train.py` 명령어로 훈련을 시작하면 되는데 작동이 안됐다.  
   알고보니 virtual box 가상환경을 사용하여 linux를 돌렸는데, 가상환경 안에서는 gpu또한 가상이었던 것.  
   구글링 해보면 virtualbox에서 현재 pc의 gpu를 사용할 수 있는  방법이 있겠지만 그 방법보다 window에서 모델을 돌리는게 더 빠를 것 같아서 그렇게 했다. 그런데 그것도 쉽진 않다.



생각한 것 보다 데이터 용량이 크고, 환경설정이 힘들어서 진도가 느리다.

그래도 오늘 새벽에는 2번이나 3번 중 적어도 한 방법으로 해결해보려고 한다.



### 0319

alphapose라는 새로운 pose estimation를 찾았다. 이건 가장 좋은 점이 colab으로 preview를 할 수 있다는 점.

[MVIG-SJTU/AlphaPose: Real-Time and Accurate Full-Body Multi-Person Pose Estimation&Tracking System (github.com)](https://github.com/MVIG-SJTU/AlphaPose)

colab으로 돌려보니 포즈를 잘 찾는다. 그런데 input을 사진으로 받는 코드라서 webcam을 사용하려면 조금 수정해야겠다.

colab에서 webcam을 이용하는 방법은 google에서 지원하는 스니펫을 활용하면 된다. colab은 서버인데 webcam은 로컬에 연결되어있어서 colab이 바로 영상을 읽을 수 없다. 그래서 스니펫에서 사용하는 방법은, javascript를 사용해서 로컬의 영상데이터를 서버로 전송해 읽어들이는 방법이다.



갑자기 javascript하니까 tensorflow.js가 생각났다. 그래서 공식 홈페이지에 들어가봤더니

[All Experiments | Experiments with Google](https://experiments.withgoogle.com/experiments?tag=PoseNet)

human pose estimation을 활용한 여러가지 프로그램을 오픈소스로 공개한다.

PoseNet은 coral할때 봤던 pose estimation 이다. 추측하건데, 그 때 봤던건 coral의 HTPU를 사용하도록 일부 수정되어 제공된 코드이고, 오리지날 PoseNet이 존재하는 것 같다.

영상데이터는 용량이 크니까 back까지 안가고 front단에서 tensorflow.js로 가동시킬 수 있다면 데이터 전송 측면에서 큰 이익을 볼 것 같다.



### 0320

PoseNet은 javascript에서 돌아가는 거라서 서버에 올릴 수 있다.

그런데 문제점이 좀 있다.

- 서버에 올려서 여러 실호를 받다보면 속도가 좀 느려진다. (사실 별로 안느려질거같음)
- 임베디드를 활용하는 도전적인 면이 없다.

2번 때문에 컨설턴트님께서는 임베디드단에서 돌리라고 하시는 것 같다.

사실 서비스 측면에서는 빠르게 만들 수 있는 서버에 올리는 방법을 택하는게 좋겠지만 지금 프로젝트는 학습에 초점이 맞춰져있기 때문에 임베디드를 사용하는 것이 의의가 있을 것 같다.

그리고 PoseNet은 사용을 해봤는데... google에서 만든 것 치고는 성능이 영 별로다.

그래서 AlphaPose를 사용하기로 마음 먹었다.