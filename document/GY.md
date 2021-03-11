# STUDY



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