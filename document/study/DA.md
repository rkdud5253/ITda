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



### ERD

- SQL 미리보기

CREATE TABLE `Admin_Info` (
	`admin_id`	VARCHAR(40)	NOT NULL,
	`admin_e-mail`	VARCHAR(20)	NULL,
	`admin_pwd`	VARCHAR(20)	NOT NULL,
	`admin_name`	VARCHAR(20)	NOT NULL,
	`admin_phone_number`	VARCHAR(20)	NULL
);

CREATE TABLE `User_Info` (
	`user_id`	VARCHAR(100)	NOT NULL,
	`user_name`	VARCHAR(20)	NOT NULL,
	`user_phone_number`	VARCHAR(20)	NULL
);

CREATE TABLE `User_Admin` (
	`admin_id`	VARCHAR(100)	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `Question` (
	`question_id`	VARCHAR(10)	NOT NULL,
	`user_id`	VARCHAR(100)	NULL,
	`admin_id`	VARCHAR(100)	NULL,
	`question_content`	VARCHAR(100)	NOT NULL,
	`example_1`	VARCHAR(50)	NOT NULL,
	`example_2`	VARCHAR(50)	NOT NULL,
	`example_3`	VARCHAR(50)	NULL,
	`example_4`	VARCHAR(50)	NULL,
	`answer`	INT	NOT NULL
);

CREATE TABLE `user_wrong_question` (
	`user_id`	VARCHAR(100)	NOT NULL,
	`question_id`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `user_file_storage` (
	`file_id`	VARCHAR(100)	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`file_name`	VARCHAR(50)	NOT NULL,
	`file_type`	VARCHAR(20)	NOT NULL,
	`file_urls`	VARCHAR(100)	NOT NULL,
	`image_emotion`	VARCHAR(20)	NULL,
	`date`	DATE	NOT NULL
);

CREATE TABLE `Exercise` (
	`exercise_id`	VARCHAR(10)	NOT NULL,
	`exercise_name`	VARCHAR(20)	NOT NULL,
	`exercise_urls`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `user_result` (
	`today_date`	DATE	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`exercise_id`	VARCHAR(10)	NOT NULL,
	`exercise_accuracy`	FLOAT	NOT NULL,
	`question_1_id`	VARCHAR(10)	NOT NULL,
	`question_2_id`	VARCHAR(10)	NOT NULL,
	`question_3_id`	VARCHAR(10)	NOT NULL,
	`question_4_id`	VARCHAR(10)	NOT NULL,
	`question_5_id`	VARCHAR(10)	NOT NULL,
	`right_numbers`	VARCHAR(200)	NOT NULL,
	`wrong_numbers`	VARCHAR(200)	NOT NULL
);

ALTER TABLE `Admin_Info` ADD CONSTRAINT `PK_ADMIN_INFO` PRIMARY KEY (
	`admin_id`
);

ALTER TABLE `User_Info` ADD CONSTRAINT `PK_USER_INFO` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `User_Admin` ADD CONSTRAINT `PK_USER_ADMIN` PRIMARY KEY (
	`admin_id`,
	`user_id`
);

ALTER TABLE `Question` ADD CONSTRAINT `PK_QUESTION` PRIMARY KEY (
	`question_id`
);

ALTER TABLE `user_wrong_question` ADD CONSTRAINT `PK_USER_WRONG_QUESTION` PRIMARY KEY (
	`user_id`,
	`question_id`
);

ALTER TABLE `user_file_storage` ADD CONSTRAINT `PK_USER_FILE_STORAGE` PRIMARY KEY (
	`file_id`,
	`user_id`
);

ALTER TABLE `Exercise` ADD CONSTRAINT `PK_EXERCISE` PRIMARY KEY (
	`exercise_id`
);

ALTER TABLE `user_result` ADD CONSTRAINT `PK_USER_RESULT` PRIMARY KEY (
	`today_date`,
	`user_id`
);

ALTER TABLE `User_Admin` ADD CONSTRAINT `FK_Admin_Info_TO_User_Admin_1` FOREIGN KEY (
	`admin_id`
)
REFERENCES `Admin_Info` (
	`admin_id`
);

ALTER TABLE `User_Admin` ADD CONSTRAINT `FK_User_Info_TO_User_Admin_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User_Info` (
	`user_id`
);

ALTER TABLE `user_wrong_question` ADD CONSTRAINT `FK_User_Info_TO_user_wrong_question_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User_Info` (
	`user_id`
);

ALTER TABLE `user_wrong_question` ADD CONSTRAINT `FK_Question_TO_user_wrong_question_1` FOREIGN KEY (
	`question_id`
)
REFERENCES `Question` (
	`question_id`
);

ALTER TABLE `user_file_storage` ADD CONSTRAINT `FK_User_Info_TO_user_file_storage_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User_Info` (
	`user_id`
);

ALTER TABLE `user_result` ADD CONSTRAINT `FK_User_Info_TO_user_result_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User_Info` (
	`user_id`
);