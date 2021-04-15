# Copyright 2019 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import argparse
import collections
from functools import partial
import re
import time, datetime

import numpy as np
from PIL import Image
import svgwrite
import gstreamer

from pose_engine import PoseEngine
from pose_engine import KeypointType

import math
import threading, multiprocessing

from requests import get, post, put
import requests

import os


# from flask import Flask
# app = Flask(__name__)

t = 0

EDGES = (
    (KeypointType.NOSE, KeypointType.LEFT_EYE),
    (KeypointType.NOSE, KeypointType.RIGHT_EYE),
    (KeypointType.NOSE, KeypointType.LEFT_EAR),
    (KeypointType.NOSE, KeypointType.RIGHT_EAR),
    (KeypointType.LEFT_EAR, KeypointType.LEFT_EYE),
    (KeypointType.RIGHT_EAR, KeypointType.RIGHT_EYE),
    (KeypointType.LEFT_EYE, KeypointType.RIGHT_EYE),
    (KeypointType.LEFT_SHOULDER, KeypointType.RIGHT_SHOULDER),
    (KeypointType.LEFT_SHOULDER, KeypointType.LEFT_ELBOW),
    (KeypointType.LEFT_SHOULDER, KeypointType.LEFT_HIP),
    (KeypointType.RIGHT_SHOULDER, KeypointType.RIGHT_ELBOW),
    (KeypointType.RIGHT_SHOULDER, KeypointType.RIGHT_HIP),
    (KeypointType.LEFT_ELBOW, KeypointType.LEFT_WRIST),
    (KeypointType.RIGHT_ELBOW, KeypointType.RIGHT_WRIST),
    (KeypointType.LEFT_HIP, KeypointType.RIGHT_HIP),
    (KeypointType.LEFT_HIP, KeypointType.LEFT_KNEE),
    (KeypointType.RIGHT_HIP, KeypointType.RIGHT_KNEE),
    (KeypointType.LEFT_KNEE, KeypointType.LEFT_ANKLE),
    (KeypointType.RIGHT_KNEE, KeypointType.RIGHT_ANKLE),
)
report = {}
report['LEFT_SHOULDER - RIGHT_SHOULDER'] = 0
report['LEFT_SHOULDER - LEFT_ELBOW'] = 0
report['LEFT_SHOULDER - LEFT_HIP'] = 0
report['RIGHT_SHOULDER - RIGHT_ELBOW'] = 0
report['RIGHT_SHOULDER - RIGHT_HIP'] = 0
report['LEFT_ELBOW - LEFT_WRIST'] = 0
report['RIGHT_ELBOW - RIGHT_WRIST'] = 0
report['LEFT_HIP - RIGHT_HIP'] = 0
report['LEFT_HIP - LEFT_KNEE'] = 0
report['RIGHT_HIP - RIGHT_KNEE'] = 0
report['LEFT_KNEE - LEFT_ANKLE'] = 0
report['RIGHT_KNEE - RIGHT_ANKLE'] = 0
report['TOTAL_TIME'] = 0



# custom
def joint_degree(joint_b, joint_a, threshold=0.3):
    if joint_a[2] < threshold or joint_b[2] < threshold:
        return "NaN"
    x = joint_a[0] - joint_b[0]
    y = joint_a[1] - joint_b[1]
    if x == 0:
        if y > 0:
            return 90
        else:
            return 270
    result = math.tanh(y / x) * 90
    if x >= 0 and y >= 0:
        return result
    elif x < 0:
        return result + 180
    else:
        return result + 360

# custom
def check_posture(target, correct, allow=20):
    if target == "NaN" or correct == "NaN":
        return "No data"
    if abs(correct - target) < allow:
        return "correct"
    elif target < allow and correct > target + 360 - allow:
        return "correct"
    elif target > 360 - allow and correct < target - 360 + allow:
        return "correct"
    return "wrong"


def shadow_text(dwg, x, y, text, font_size=16):
    dwg.add(dwg.text(text, insert=(x + 1, y + 1), fill='black',
                     font_size=font_size, style='font-family:sans-serif'))
    dwg.add(dwg.text(text, insert=(x, y), fill='white',
                     font_size=font_size, style='font-family:sans-serif'))


def draw_pose(dwg, pose, src_size, inference_box, targets_list, color='yellow', threshold=0.3):
    global t
    if t >= 3:
        return

    box_x, box_y, box_w, box_h = inference_box
    scale_x, scale_y = src_size[0] / box_w, src_size[1] / box_h
    box_x, box_y, scale_x, scale_y = 0, 0, 1, 1
    xys = {}
    cnt = 0
    for label, keypoint in pose.keypoints.items():
        cnt += 1
        if cnt < 6: continue
        # if keypoint.score < threshold: continue
        # Offset and scale to source coordinate space.
        kp_x = int((keypoint.point[0] - box_x) * scale_x)
        kp_y = int((keypoint.point[1] - box_y) * scale_y)
        score = keypoint.score

        xys[label] = (kp_x, kp_y, score)
        dwg.add(dwg.circle(center=(int(kp_x), int(kp_y)), r=5,
                           fill='cyan', fill_opacity=keypoint.score, stroke=color))

    i = -1
    global report
    report['TOTAL_TIME'] += 1
    print("targets_list", targets_list)
    print("t", t)
    for a, b in EDGES[7:]:
        i += 1
        print(i)
        if a not in xys or b not in xys: continue
        ax, ay = xys[a][:2]
        bx, by = xys[b][:2]
        mypose = joint_degree(xys[a], xys[b])
        print('  %-15s - %-15s : %s' %
            (str(a)[13:], str(b)[13:], mypose))
        check = check_posture(targets_list[t][i], mypose)
        frame = f'{str(a)[13:]} - {str(b)[13:]}'
        if check == 'correct':
            color = "green"
            report[frame] += 1
        elif check == 'wrong':
            color = "red"
        #     report[frame] -= 1
        elif check == 'No data':
            color = "yellow"
            report[frame] += 0.5
        dwg.add(dwg.line(start=(ax, ay), end=(bx, by), stroke=color, stroke_width=2))
    print(report)
    print("---------------------------------------------------------")
    print(t)


def avg_fps_counter(window_size):
    window = collections.deque(maxlen=window_size)
    prev = time.monotonic()
    yield 0.0  # First fps value.

    while True:
        curr = time.monotonic()
        window.append(curr - prev)
        prev = curr
        yield len(window) / sum(window)


def run(inf_callback, render_callback):
    global t
    if t >= 3:
        return
    parser = argparse.ArgumentParser(formatter_class=argparse.ArgumentDefaultsHelpFormatter)
    parser.add_argument('--mirror', help='flip video horizontally', action='store_true')
    parser.add_argument('--model', help='.tflite model path.', required=False)
    parser.add_argument('--res', help='Resolution', default='640x480',
                        choices=['480x360', '640x480', '1280x720'])
    parser.add_argument('--videosrc', help='Which video source to use', default='/dev/video0')
    parser.add_argument('--h264', help='Use video/x-h264 input', action='store_true')
    parser.add_argument('--jpeg', help='Use image/jpeg input', action='store_true')
    args = parser.parse_args()

    default_model = 'models/mobilenet/posenet_mobilenet_v1_075_%d_%d_quant_decoder_edgetpu.tflite'
    if args.res == '480x360':
        src_size = (480, 360)
        appsink_size = (480, 360)
        model = args.model or default_model % (353, 481)
    elif args.res == '640x480':
        src_size = (640, 480)
        appsink_size = (640, 480)
        model = args.model or default_model % (481, 641)
    elif args.res == '1280x720':
        src_size = (1280, 720)
        appsink_size = (1280, 720)
        model = args.model or default_model % (721, 1281)

    print('Loading model: ', model)
    engine = PoseEngine(model)
    input_shape = engine.get_input_tensor_shape()
    inference_size = (input_shape[2], input_shape[1])

    gstreamer.run_pipeline(partial(inf_callback, engine), partial(render_callback, engine),
                        src_size, inference_size,
                        #    mirror=args.mirror,
                        mirror=True,
                        videosrc=args.videosrc,
                        h264=args.h264,
                        jpeg=args.jpeg
                        )


def main():

    n = 0
    sum_process_time = 0
    sum_inference_time = 0
    ctr = 0
    fps_counter = avg_fps_counter(30)
    targets = []

    def run_inference(engine, input_tensor):
        return engine.run_inference(input_tensor)

    def render_overlay(engine, output, src_size, inference_box):
        global t
        if t >= 3:
            return
        print("RENDER OVERLAY")
        nonlocal n, sum_process_time, sum_inference_time, fps_counter

        svg_canvas = svgwrite.Drawing('', size=src_size)
        start_time = time.monotonic()
        outputs, inference_time = engine.ParseOutput()
        end_time = time.monotonic()
        n += 1
        sum_process_time += 1000 * (end_time - start_time)
        sum_inference_time += inference_time * 1000

        avg_inference_time = sum_inference_time / n
        text_line = 'PoseNet: %.1fms (%.2f fps) TrueFPS: %.2f Nposes %d' % (
            avg_inference_time, 1000 / avg_inference_time, next(fps_counter), len(outputs)
        )

        shadow_text(svg_canvas, 10, 20, text_line)
        for pose in outputs:
            draw_pose(svg_canvas, pose, src_size, inference_box, targets_list)
            # for label, keypoint in pose.keypoints.items():
            #     print('  %-20s x=%-4d y=%-4d score=%.1f' %
            #         (label.name, keypoint.point[0], keypoint.point[1], keypoint.score))
        return (svg_canvas.tostring(), False)

    pil_image1 = Image.open('./media/pose1.jpg').convert('RGB')
    pil_image2 = Image.open('./media/pose4.jpg').convert('RGB')
    # pil_image3 = Image.open('./media/pose3.jpg').convert('RGB')
    pil_image3 = Image.open('./media/yoga.jpg').convert('RGB')
    pil_list = [pil_image1, pil_image2, pil_image3]
    engine = PoseEngine(
        'models/mobilenet/posenet_mobilenet_v1_075_481_641_quant_decoder_edgetpu.tflite')
    poses, inference_time = engine.DetectPosesInImage(pil_image1)
    poses_list = [engine.DetectPosesInImage(pil_image)[0] for pil_image in pil_list]
    print(poses_list)
    # print('Inference time: %.f ms' % (inference_time * 1000))
    targets_list = []
    for poses in poses_list:
        targets = []
        for pose in poses:
            if pose.score < 0.4: continue
            print('\nPose Score: ', pose.score)
            # for label, keypoint in pose.keypoints.items():
            #     print('  %-20s x=%-4d y=%-4d score=%.1f' %
            #         (label.name, keypoint.point[0], keypoint.point[1], keypoint.score))

            threshold = 0.3
            box_x = box_y = 0
            scale_x = scale_y = 1

            xys = {}
            for label, keypoint in pose.keypoints.items():
                # if keypoint.score < threshold: continue
                # Offset and scale to source coordinate space.
                kp_x = int((keypoint.point[0] - box_x) * scale_x)
                kp_y = int((keypoint.point[1] - box_y) * scale_y)
                score = keypoint.score

                xys[label] = (kp_x, kp_y, score)

            for a, b in EDGES[7:]:
                # if a not in xys or b not in xys: continue
                target = joint_degree(xys[a], xys[b])
                targets.append(target)
                print('  %-15s - %-15s : %s' %
                    (str(a)[13:], str(b)[13:], target))
        # print(targets)
        targets_list.append(targets)
    print(targets_list)


    def pose_run():
        run(run_inference, render_overlay)
        return


    def next_pose():
        global t
        for _ in range(3):
            time.sleep(10)
            t += 1
        # time.sleep(3)
    
    pose_thread = threading.Thread(target=pose_run)
    pose_thread.daemon = True
    pose_thread.start()
    # pose_thread = multiprocessing.Process(target=pose_run)
    # pose_thread.start()

    # nxt = threading.Thread(target=next_pose())
    # nxt.daemon = True
    # nxt.start()
    next_pose()
    # time.sleep(10)
    # pose_thread.terminate()
    print("쓰레드종료")
    print(report)
    data = ""
    for val in report.values():
        data += str(int(val*100/(0.001 + report["TOTAL_TIME"])))
        data += "/"
    data = {
        "exerciseAccuracy": data[:-5],
        "exerciseId": 1,
        "reportDate": str(datetime.datetime.now())[:10],
        "userId": 1
    }
    res = requests.put('http://j4a404.p.ssafy.io:8000/itda/report/exercise', json=data)
    print(res)
    # os._exit(os.EX_OK)
    # gtk_main_quit()
    


# @app.route('/')
# def index():
#     return "Hello Flask"

# @app.route('/posenet')
# def posenet():
#     return main()

if __name__ == '__main__':
    main()
    # app.run(debug=True)
