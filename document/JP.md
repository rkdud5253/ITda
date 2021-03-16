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

