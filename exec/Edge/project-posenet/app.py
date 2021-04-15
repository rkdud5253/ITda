import mypose
import mysmile
import STT
import requests

import random
import stomper
import websocket
import time
import RPi.GPIO as GPIO
from requests import get, delete
import hashlib
import threading
import multiprocessing
from multiprocessing import freeze_support

import pyglet
from google.cloud import texttospeech

# TTS
client = texttospeech.TextToSpeechClient()
voice = texttospeech.VoiceSelectionParams(
    language_code="ko-kr", ssml_gender=texttospeech.SsmlVoiceGender.NEUTRAL
)
audio_config = texttospeech.AudioConfig(
    audio_encoding=texttospeech.AudioEncoding.MP3
)


# function
# def play():
#     # 읽기


def tts(message):
    # 저장
    synthesis_input = texttospeech.SynthesisInput(text=message)
    response = client.synthesize_speech(
        input=synthesis_input, voice=voice, audio_config=audio_config
    )
    with open("output.wav", "wb") as out:
        # Write the response to the output file.
        out.write(response.audio_content)

    pyglet.resource.media('output.wav').play()
    pyglet.app.run()


def posenet():
    return mypose.main()


def smile_detection():
    return mysmile.main()


def sensor():
    GPIO.output(trig, False)
    time.sleep(1)

    GPIO.output(trig, True)
    time.sleep(0.0001)
    GPIO.output(trig, False)

    while GPIO.input(echo) == 0:
        pulse_start = time.time()
    while GPIO.input(echo) == 1:
        pulse_end = time.time()

    pulse_duration = pulse_end - pulse_start
    distance = pulse_duration * 17000
    distance = round(distance, 2)
    print(f"{distance}cm")

    return distance

def getOrder(hash_ip):
    data = {
        "hashIp": hash_ip
    }

    while True:
        res = requests.get('http://j4a404.p.ssafy.io:8000/itda/report/exercise', json=data)

        if res.data.command != None:
            requests.delete('http://j4a404.p.ssafy.io:8000/itda/report/exercise', json=data)
            return res.data.command

if __name__ == '__main__':
    # Connecting to websocket
    print("START FUNCTION")
    ws = websocket.create_connection("ws://j4a404.p.ssafy.io:8000/itda/python")
    # ws = create_connection("ws://localhost:8000/itda/python")
    print("CONNECTION OK")

    # Subscribing to topic
    client_id = str(random.randint(0, 1000))
    ip = get("https://api.ipify.org").text
    hash_ip = hashlib.sha256(ip.encode())
    hash_ip = hash_ip.hexdigest()
    sub = stomper.subscribe(f"/socket/{hash_ip}/send", client_id, ack='auto')
    ws.send(sub)

    # 초음파
    GPIO.setmode(GPIO.BCM)

    trig = 2
    echo = 3

    GPIO.setup(trig, GPIO.OUT)
    GPIO.setup(echo, GPIO.IN)

    while True:
        distance = sensor()

        if distance < 50:
            print("RUN SENSOR")
            ws.send(stomper.send(f"/socket/{hash_ip}/send", "ready"))
            # user_id = ws.recv(stomper.send(f"/socket/{hash_ip}/receive"))
            user_id = getOrder(hash_ip)
            if not user_id:
                pass
            else:
                # 로그인 확인 후 메인페이지로 이동
                time.sleep(3)
                multiprocessing.Process(target=tts("안녕하세요! 나리를 불러서 원하는 기능을 실행하세요!")).start()
                print("1")
                time.sleep(5)
                print("5")
                while True:
                    if distance < 50:
                        print("LOGIN")
                        message = STT.run()
                        print(message)
                        enter_time = time.time()
                        if message == "나리야":
                            multiprocessing.Process(target=tts("네, 말씀하세요!")).start()
                            elapsed_time = time.time() - enter_time
                            while elapsed_time < 5:
                                elapsed_time = time.time() - enter_time
                                message = STT.run()
                                if message == "나리야":
                                    enter_time = time.time()
                                if message == "오늘의 체조":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # MYPOSE
                                    time.sleep(8)
                                    pose = multiprocessing.Process(target=posenet)
                                    pose.start()
                                    while True:
                                        # next_pose = ws.recv()
                                        # if next_pose == "nextPose":
                                        #     break
                                        # distance = sensor()
                                        # if distance < 50:
                                        #     break

                                        message = STT.run()
                                        if message in ("그만", "나리야"):
                                            pose.terminate()
                                            break
                                    break
                                elif message == "가족오락관":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # 가족오락관
                                    while True:
                                        # stop_signal = ws.recv()
                                        # if stop_signal:
                                        #     break
                                        message = STT.run()
                                        cnt = 0
                                        if message in (
                                        '1번', '이번', '3번', '4번', '다음', '그만', '1', '2', '3', '4', '일', '이', '삼', '사'):
                                            print("번호")
                                            ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                            cnt += 1
                                            if cnt == 5:
                                                break
                                            if message in ("그만", "나리야"):
                                                break

                                    break
                                elif message == "사진 일기장":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # MYSMILE
                                    smile = multiprocessing.Process(target=smile_detection())
                                    smile.start()
                                    b = False
                                    while not b:
                                        message = STT.run()
                                        if message == "그만":
                                            ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                            smile.terminate()
                                            break
                                        elif message == "다시":
                                            ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                        elif message == "찰칵":
                                            ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                            while True:
                                                message = STT.run()
                                                if message == '저장':
                                                    b = True
                                                    break
                                        # stop_signal = ws.recv()
                                        # if stop_signal:
                                        #     break
                                        # distance = sensor()
                                        # if distance < 50:
                                        #     break
                                    break

                    # distance = sensor()