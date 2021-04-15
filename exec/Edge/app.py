
import mypose
import mysmile
import STT
import TTS

import random
import stomper
import websocket
import time
import RPi.GPIO as GPIO
from requests import get
import hashlib
import threading
import multiprocessing
from multiprocessing import freeze_support

from requests import get, delete
import requests

import pyglet
from google.cloud import texttospeech

import cv2
import gi
gi.require_version('Gst', '1.0')
gi.require_version('GstBase', '1.0')
gi.require_version('Gtk', '3.0')
from gi.repository import GLib, GObject, Gst, GstBase, Gtk

# # TTS
# client = texttospeech.TextToSpeechClient()
# voice = texttospeech.VoiceSelectionParams(
#     language_code="ko-kr", ssml_gender=texttospeech.SsmlVoiceGender.NEUTRAL
# )
# audio_config = texttospeech.AudioConfig(
#     audio_encoding=texttospeech.AudioEncoding.MP3
# )


# # function
# def read():
#     # 읽기
#     pyglet.resource.media('output.mp3').play()
#     pyglet.app.run()


# def tts(message):
#     # 저장
#     synthesis_input = texttospeech.SynthesisInput(text=message)
#     response = client.synthesize_speech(
#         input=synthesis_input, voice=voice, audio_config=audio_config
#     )
#     with open("output.mp3", "wb") as out:
#         # Write the response to the output file.
#         out.write(response.audio_content)
#     time.sleep(1)
#     try:
#         read_tts.terminate()
#     except:
#         read_tts = multiprocessing.Process(target=read)
#     read_tts.start()


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

    while GPIO.input(echo) == 0 : 
        pulse_start = time.time()
    while GPIO.input(echo) == 1 :
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
        res = requests.get('http://j4a404.p.ssafy.io:8000/itda/order', params=data)
        # print(res.json()["command"])
        try:
            res_delete = requests.delete('http://j4a404.p.ssafy.io:8000/itda/order', params=data)
            print(res_delete)
            return res.json()["command"]
        except:
            print(res)
        time.sleep(1)


# def pose_close():
#     global pose_close_button
#     pose_status = getOrder(hash_ip)
#     if pose_status == "poseClose":
#         pose_close_button = True
pose_close = False

def timer():
    time.sleep(35)
    ws.send(stomper.send(f"/socket/{hash_ip}/send", "그만"))
    global pose_close
    pose_close = True
    return


# pose_close_button = False


if __name__ == '__main__':
    # Connecting to websocket
    print("START FUNCTION")
    ws = websocket.create_connection("ws://j4a404.p.ssafy.io:8000/itda/python")
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

        if distance < 20:
            print("RUN SENSOR")
            ws.send(stomper.send(f"/socket/{hash_ip}/send", "ready"))
            time.sleep(5)
            user_id = int(getOrder(hash_ip)[7:])
            print(user_id)
            # 로그인이 안돼있을 때
            if not user_id:
                # TTS.main("안녕하세요 어르신! 성함을 말씀해 주세요!")
                tts = multiprocessing.Process(target=TTS.main, args=("안녕하세요 어르신! 성함을 말씀해 주세요!",))
                tts.start()
                message = STT.run()
                ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                user_id = int(getOrder(hash_ip)[7:])
                tts.terminate()


            # 로그인 돼있고, 메인화면 들어왔을 때
            TTS.main("안녕하세요! 나리를 불러서 원하는 기능을 실행하세요!")
            while True:
                # distance = sensor()
                if distance < 20:
                    message = STT.run()
                    enter_time = time.time()
                    if message == "나리야":
                        ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                        TTS.main("네, 말씀하세요")
                        while time.time() - enter_time < 10:
                            elapsed_time = time.time() - enter_time
                            message = STT.run()
                            # if message == "나리야":
                            #     enter_time = time.time()
                            # 오늘의 체조 기능
                            if message == "오늘의 체조":
                                ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                TTS.main("잠시 후 오늘의 체조가 시작됩니다. 왼쪽 그림을 보고 동작을 따라 해 보세요.")
                                exercise = getOrder(hash_ip)
                                if exercise == "exercise":
                                    a = posenet()
                                    Gtk.main_quit()
                                    time.sleep(9)
                                    TTS.main("나리를 불러서 원하는 기능을 실행하세요!")
                                    break
                            elif message == "사진 일기장":
                                b = True
                                while b:
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    capture = getOrder(hash_ip)
                                    TTS.main(capture)
                                    capture = getOrder(hash_ip)
                                    if capture == "smileNetRun":
                                        smile_detection()
                                        ws.send(stomper.send(f"/socket/{hash_ip}/send", "찰칵"))
                                        while True:
                                            print("사진일기장 결과창")
                                            message = STT.run()
                                            if message == '저장':
                                                ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                                TTS.main("나리를 불러서 원하는 기능을 실행하세요!")
                                                b = False
                                                break
                                            elif message == "다시":
                                                ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                                break
                                break
                            elif message in ("가족오락관"):
                                ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                cnt = 0
                                # 가족오락관
                                while True:
                                    time.sleep(2)
                                    quiz = getOrder(hash_ip)
                                    TTS.main(quiz)
                                    message = STT.run()
                                    while not message in (
                                    '일번', '이번', '삼번', '사번', '1번', '2번', '3번', '4번', '다음', '그만', '1', '2', '3', '4', '일',
                                    '이', '삼', '사'):
                                        message = STT.run()
                                    if message in (
                                            '일번', '이번', '삼번', '사번', '1번', '2번', '3번', '4번', '다음', '그만', '1', '2',
                                            '3', '4', '일',
                                            '이', '삼', '사'):
                                        print(cnt)
                                        ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                        cnt += 1
                                        if cnt == 5:
                                            TTS.main("오늘의 두뇌 운동이 모두 끝났습니다. 참 잘하셨습니다!")
                                            time.sleep(1)
                                            TTS.main("나리를 불러서 원하는 기능을 실행하세요!")
                                            break
                                        if message in ("그만"):
                                            ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                            TTS.main("나리를 불러서 원하는 기능을 실행하세요!")
                                            break
                                break