
import mypose
import mysmile
import STT

import random
import stomper
from websocket import create_connection
import time
import RPi.GPIO as GPIO
from requests import get
import hashlib
import threading
import multiprocessing
from multiprocessing import freeze_support


# Sending some message

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
    

if __name__ == '__main__':
    # Connecting to websocket
    print("START FUNCTION")
    ws = create_connection("ws://j4a404.p.ssafy.io:8000/itda/python")
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
            user_id = ws.recv()
            if not user_id:
                pass
            else:
                # 로그인 확인 후 메인페이지로 이동
                while True:
                    distance = sensor()
                    if distance < 50:
                        print("LOGIN")
                        message = STT.run()
                        print(message)
                        enter_time = time.time()
                        if message == "나리야":
                            elapsed_time = time.time() - enter_time
                            while elapsed_time < 5:
                                elapsed_time = time.time() - enter_time
                                message = STT.run()
                                if message == "오늘의 체조":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # MYPOSE
                                    pose = multiprocessing.Process(target=posenet)
                                    pose.start()
                                    while True:
                                        message = STT.run()
                                        if message == "그만":
                                            pose.terminate()
                                            break
                                        stop_signal = ws.recv()
                                        if stop_signal:
                                            break
                                    break
                                elif message == "가족오락관":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # 가족오락관
                                    while True:
                                        stop_signal = ws.recv()
                                        if stop_signal:
                                            break
                                    break
                                elif message == "사진 일기장":
                                    ws.send(stomper.send(f"/socket/{hash_ip}/send", message))
                                    # MYSMILE
                                    smile = multiprocessing.Process(target=smile)
                                    smile.start()
                                    while True:
                                        message = STT.run()
                                        if message == "그만":
                                            smile.terminate()
                                            break
                                        stop_signal = ws.recv()
                                        if stop_signal:
                                            break
                                    break

                                # 분기