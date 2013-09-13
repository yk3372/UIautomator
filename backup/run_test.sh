#!/bin/bash

adb push ../bin/UIautomator.jar /data/local/tmp/
adb shell uiautomator runtest UIautomator.jar -c com.xianguo.wxui.UiTesting