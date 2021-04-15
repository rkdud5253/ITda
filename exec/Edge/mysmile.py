import cv2

import requests
import base64
import numpy as np

import datetime

import copy

import STT
import multiprocessing

isStop = False

def main(ws="dummy"):
    video = cv2.VideoCapture(0)
    faceCascade = cv2.CascadeClassifier("dataset/haarcascade_frontalface_default.xml")
    smileCascade = cv2.CascadeClassifier("dataset/haarcascade_smile.xml")
    # global isStop

    while True:
        success,img = video.read()
        print(success)
        if success:
            grayImg = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
            faces = faceCascade.detectMultiScale(grayImg,1.1,4)
            img_copy = copy.deepcopy(img)
            cv2.namedWindow('video')  # create a named window
            cv2.moveWindow('video', 960, 320)   # Move it to (40, 30)
            cv2.imshow('video',img_copy)
            cnt=500
            keyPressed = cv2.waitKey(1)
            print(faces)
            for x,y,w,h in faces:
                img2 = img[y:y + w, x:x + w]
                img = cv2.rectangle(img, (x, y), (x + w, y + h), (0, 0, 0), 3)
                roi_gray = cv2.cvtColor(img2, cv2.COLOR_BGR2GRAY)
                smiles = smileCascade.detectMultiScale(roi_gray, 1.5, 15)
                for x2, y2, w2, h2 in smiles:
                    img = cv2.rectangle(img, (x2 + x, y2 + y), (x2 + w2, y2 + h2), (100, 100, 100), 5)

                    print("Image "+str(cnt)+"Saved")
                    path=r'/home/pi/coral/project-posenet/media/smile/'+str(cnt)+'.jpg'
                    cv2.imwrite(path,img_copy)
                    cnt +=1

                    with open(r'/home/pi/coral/project-posenet/media/smile/500.jpg', "rb") as f:
                        str1 = base64.b64encode(f.read())
                        print(str1[:20])
                        print("저장된 이미지")
                    if(cnt>=501):   
                        break

        if(keyPressed & 0xFF==ord('q')):
            break

        if(cnt>=501):   
            break


    # if not isStop:
    userId = 1
    fileUrl = "data:image/jpg;base64,"+str(str1)[2:-1]
    print(fileUrl[:40])
    print("전송하는 이미지")
    obj={
    "fileDate": str(datetime.datetime.now())[:10],
    "fileName": f"{str(userId)}-{str(datetime.datetime.now())[:10]}.jpg",
    "fileUrl": fileUrl,
    "fileUse": "Diary",
    "userId": userId
    }
    res = requests.put('http://j4a404.p.ssafy.io:8000/itda/files/image', json = obj)
    print(res)

    # ws.send(stomper.send(f"/socket/{hash_ip}/send", "찰칵"))

    img_decode = base64.decodebytes(str1)

    img_result = open(r'/home/pi/coral/project-posenet/media/smile/500_decode.jpg', "wb")
    img_result.write(img_decode)
    img_result.close()

    video.release()                                  
    cv2.destroyAllWindows()

def smile_stt():
    global isStop
    while not isStop:
        run
        message = STT.run()
        if message == "그만":
            
            isStop = True
            break

def main2():
    global isStop
    run()
    while not isStop:
        #run()
        message = multiprocessing.Process(target=STT.run())
        if message == "그만":
            isStop = True
            break

if __name__ == "__main__":
    main()