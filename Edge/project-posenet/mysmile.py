import cv2

#
import requests
import base64
import numpy as np

import datetime

import copy
#

def main():
    video = cv2.VideoCapture(0)
    faceCascade = cv2.CascadeClassifier("dataset/haarcascade_frontalface_default.xml")
    smileCascade = cv2.CascadeClassifier("dataset/haarcascade_smile.xml")

    while True:
        success,img = video.read()
        grayImg = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        faces = faceCascade.detectMultiScale(grayImg,1.1,4)
        img_copy = copy.deepcopy(img)
        cv2.imshow('live video',img_copy)
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

                # 
                with open(r'/home/pi/coral/project-posenet/media/smile/500.jpg', "rb") as f:
                    str1 = base64.b64encode(f.read())
                    print(str1[:20])
                    print("저장된 이미지")
                # 
                #  사진을 몇장 저장할 지 설정 cnt>= 501이면 한 장
                if(cnt>=501):   
                    break
        

        if(keyPressed & 0xFF==ord('q')):
            break

        # 원하는 만큼 사진을 저장하면 종료 cnt>= 501이면 한 장 저장 후 종료
        if(cnt>=501):   
            break

    # 
    # files = open(r'C:/Users/power/Desktop/deepface/SmileCapture', 'rb')
    # files={'upload': open(str,'rb')}
    # upload = {'file':str1}
    userId = 3
    fileUrl = "data:image/jpg;base64,"+str(str1)[2:-1]
    print(fileUrl[:40])
    print("전송하는 이미지")
    obj={
    "fileDate": str(datetime.datetime.now())[:10],
    #   "fileId": 0,
    # userId 넣어야 함
    "fileName": f"{str(userId)}-{str(datetime.datetime.now())[:10]}.jpg",
    #   "fileType": "111",
    "fileUrl": fileUrl,
    "fileUse": "Diary",
    #   "imageEmotion": "111",
    "userId": userId
    }
    # print(obj)
    res = requests.post('http://j4a404.p.ssafy.io:8000/itda/files/image', json = obj)
    print(res)
    # 

    # 
    # with open("t.png", "rb") as img:
    #     str = base64.b64encode(img.read())
    #     print str
    # 

    img_decode = base64.decodebytes(str1)

    # img_result = open('image500_decode.jpg', 'wb')
    # img_result = img_decode
    # img_result.write(r'C:/Users/power/Desktop/deepface/SmileCapture/images/image500_decode.jpg', "wb")
    img_result = open(r'/home/pi/coral/project-posenet/media/smile/500_decode.jpg', "wb")
    img_result.write(img_decode)
    # print("/n/n/n"+ img_decode+"/n/n/n")
    img_result.close()
    # print(img_decode)

    video.release()                                  
    cv2.destroyAllWindows() 

if __name__ == "__main__":
    main()