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



인공지능을 담당하게 되었지만 기초지식 부족으로  큰 난관이 예상

명세서 기반으로 학습할지

사용할 기술의 코드를 기반으로 학습할지

그외 오픈된 자료(영상,블로그)로 학습할지 고민

팀원에 도움요청..!