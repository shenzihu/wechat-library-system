# -*- coding: utf-8 -*-
import scrapy
import re
from copy import deepcopy
from selenium import webdriver
from selenium.webdriver import ChromeOptions
import MySQLdb


class doubanSpider(scrapy.Spider):
    name = 'doubanSpider'
    allowed_domains = ['book.douban.com', 'douban.com']
    # 这是由于后期使用有使用json解析，使用的域名为p.3.cn
    start_urls = []

    def __init__(self):
        super(doubanSpider, self).__init__("doubanSpider")
        option = ChromeOptions()
        option.set_headless(False)
        self.driver = webdriver.Chrome(options=option)
        # base_url = 'https://book.douban.com/subject_search?search_text='
        base_url = 'https://search.douban.com/book/subject_search?search_text='
        # 查询ISBN
        db = MySQLdb.connect("localhost", "root", "123456", "book_mate", charset='utf8')
        cursor = db.cursor()
        cursor.execute("select isbn from b where status = 0 ")

        # cursor.execute("select book_number FROM book b left JOIN isbn i on b.book_number = i.isbn where i.source = '京东' and (b.book_name = '' OR b.book_author ='' or b.book_desc = '')")
        results = cursor.fetchall()
        self.start_urls = [(base_url + row[0]) for row in results]
        cursor.close()

        # with open("rescources/ISBN1.json", 'r') as file:
        #     ibsns = json.load(file)
        #     self.start_urls = [(base_url + i) for i in ibsns]

    def parse(self, response):
        item = dict()  # 创建字典，存储相关信息
        item['originalISBN'] = response.url.split("=")[1]
        find = response.xpath('//a[@class="title-text"]/@href').extract()

        item['book_name'] = response.xpath(
            '//*[@id="root"]/div/div[2]/div[1]/div[1]/div/div/div/div[1]/a/text()').extract_first()
        find = find[0]
        # 用来保持登录状态，可把chrome上拷贝下来的字符串形式cookie转化成字典形式，粘贴到此处
        headers = {
            'Connection': 'keep - alive',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36',
            'Accept': '*/*',
            'Accept-Encoding': 'gzip, deflate, br',
            'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8,en-US;q=0.7',
        }

        meta = {
            'dont_redirect': True,  # 禁止网页重定向
            'handle_httpstatus_list': [301, 302],  # 对哪些异常返回进行处理
            'item': deepcopy(item)
        }
        yield scrapy.Request(find,
                             callback=self.parse_book_list, headers=headers, meta=meta)

    def parse_book_list(self, response):
        # book_des = scrapy.Field()
        item = response.meta['item']
        try:
            item['book_name'] = response.xpath('//*[@id="wrapper"]/h1/span/text()').extract_first()
        except Exception:
            print("有问题")
        item['book_image_big'] = response.xpath('//*[@id="mainpic"]/a/img/@src').extract_first()  # 书名前后有换行符，修掉
        # 获取作者信息列表，注意部分没有
        try:
            item['book_author'] = response.xpath('//*[@id="info"]/span[1]/a/text()').extract_first().strip()
        except Exception:
            return ""
        item['book_press'] = self.get_press(response)
        item['book_number'] = item['originalISBN']
        item['book_desc'] = self.get_desc(response)
        item['book_classify_one'] = self.get_book_classify(response)
        item['book_classify_two'] = item['book_classify_one']
        yield item

    def get_press(self, response):
        try:
            press = response.xpath('/html/body/div[3]/div[2]/div/div[1]/div[1]/div[1]/div[1]/div[2]/text()').extract()[
                2]
            if len(press.replace("\n", "")) != 0:
                return press
        except Exception:
            return ""

    def get_desc(self, response):
        try:
            list = response.xpath('//*[@class="intro"]/p')
            descs = [(row.xpath("text()").extract_first()) for row in list]
            return "\n".join(descs)
        except Exception:
            return ""

    def get_book_classify(self, response):
        try:
            classify = response.xpath(
                '//*[@id="head-breadcrumb"]/div/div/a[2]/span/span/text()').extract_first()
            if len(classify.replace("\n", "")) != 0:
                return classify
        except Exception:
            return ""
