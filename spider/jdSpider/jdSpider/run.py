#!/usr/bin/env python
# -*- coding:utf-8 -*-
from scrapy import cmdline
import time
import subprocess
import sched, os

if __name__ == "__main__":
    cmdline.execute('scrapy crawl jdSpider'.split())

# 初始化sched模块的scheduler类
# 第一个参数是一个可以返回时间戳的函数，第二个参数可以在定时未到达之前阻塞。
schedule = sched.scheduler(time.time, time.sleep)


# 被周期性调度触发的函数
def func():
    print("爬虫开始")
    subprocess.Popen("scrapy crawl jdSpider")


def perform1(inc):
    schedule.enter(inc, 0, perform1, (inc,))
    func()  # 需要周期执行的函数


def mymain():
    schedule.enter(0, 0, perform1, (6*3600,))

# if __name__ == "__main__":
#     mymain()
#     schedule.run()  # 开始运行，直到计划时间队列变成空为止
