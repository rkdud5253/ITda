### 인공지능(음성) 명세서

- TTS
- RNN
- LSTM
- Attention mechanism

1. 데이터셋 : {(음성, 텍스트)}의 데이터 쌍 -> train, validate, test 데이터
2. 데이터 전처리 : character embedding -> STFT -> spectrogram -> mel filter bank -> mel-spectrogram

3. Tacotron2 : 대표적인 TTS 모델

   a. 인코더 : text -> character embedding vector -> CNN -> LSTM -> feature vector

   b. 디코더 : Attention mechanism -> feature vector -> LSTM -> mel-spectrogram

   c. 보코더 : mel-spectrogram -> waveform

4. validate 데이터셋으로 평가

5. 모델 선정



TTS 재미있게 이해하기

https://www.kakaobrain.com/blog/109

Tacorton2 쉽게 이해하기

https://medium.com/wasd/%EA%B3%A0%EB%93%B1%ED%95%99%EC%83%9D-%EC%8B%9C%EC%A0%90%EC%9C%BC%EB%A1%9C-%EB%85%BC%EB%AC%B8%EC%9D%84-%EC%9D%BD%EC%96%B4%EB%B3%B4%EC%9E%90-e6953caf4bf8

병희님의 참조 링크

https://www.youtube.com/watch?v=klnfWhPGPRs

https://carpedm20.github.io/tacotron/

http://melonicedlatte.com/machinelearning/2018/07/09/141346.html



- BackEnd 기초 작업

  프로젝트 구조

  API 구조

  클래스 및 변수 명명

- DataBase 기초 작업

  테이블 구조

  테이블 및 컬럼 명명