# -*- coding: utf-8 -*-
import scrapy
import re
from copy import deepcopy
from selenium import webdriver
from selenium.webdriver import ChromeOptions
import MySQLdb
import random


class jdSpider(scrapy.Spider):
    name = 'jdSpider'
    allowed_domains = ['search.jd.com', 'jd.com', 'p.3.cn']
    # 这是由于后期使用有使用json解析，使用的域名为p.3.cn
    start_urls = []

    def __init__(self):
        super(jdSpider, self).__init__("jdSpider")
        option = ChromeOptions()
        option.set_headless(False)
        self.driver = webdriver.Chrome(options=option)
        base_url = 'https://search.jd.com/Search?keyword='
        # 查询ISBN
        db = MySQLdb.connect("localhost", "root", "123456", "book_mate", charset='utf8')
        cursor = db.cursor()
        # cursor.execute("select isbn from isbn where status = 0 order by id desc limit  50")
        cursor.execute("select book_number FROM book where book_image_big =''")
        results = cursor.fetchall()
        self.start_urls = [(base_url + row[0]) for row in results]
        cursor.close()

        # with open("rescources/ISBN1.json", 'r') as file:
        #     ibsns = json.load(file)
        #     self.start_urls = [(base_url + i) for i in ibsns]

    def parse(self, response):
        item = dict()  # 创建字典，存储相关信息
        item['originalISBN'] = response.url.split("=")[1]
        find = response.xpath('//div[@class="p-img"]/a/@href').extract()
        find = find[1]
        item['book_price'] = response.xpath('//div[@class="p-price"]/strong/i/text()').extract_first()
        item['book_price'] = response.xpath('//div[@class="p-price"]/strong/i/text()').extract_first()
        # 用来保持登录状态，可把chrome上拷贝下来的字符串形式cookie转化成字典形式，粘贴到此处
        cookies = {
            'Cookie': """__jdv=122270672|direct|-|none|-|1596952473476; __jdc=122270672; 3AB9D23F7A4B3C9B=XWHLM5V5RSQOZFH7YEOOSEYWNZXSMKME3HXV2Z6HJASOJSLUTFCGE6TYLXAG6YYAGXMHR3R4KMLJEKOAO7DUIIZBAY; wlfstk_smdl=7bzhva42zf7ajk0fx4x1ggxqsi9qy0s6; TrackID=1FVwi1j-6xwhHHEJtpysvdOghbopHBmZMrCi2-frGYqzooGoy2aQdEgjVakCz0krpiVX-E8yWbhEky8DyWsraHTVQUZ_PsuAKsZV2kyGkf-M; pinId=g2QifGuLs6-1ffsfn98I-w; pin=shenzihu; unick=shenzihu; ceshi3.com=103; _tp=uzVK4fULAkEJ1WrZ9F1yxw%3D%3D; _pst=shenzihu; areaId=15; __jda=122270672.15969524734751626540253.1596952473.1596952473.1596966805.2; shshshfpb=hHLXfyDeWtgnlvlStIFUX4g%3D%3D; __jdu=15969524734751626540253; shshshfpa=e5627d43-b9c2-1c7c-0821-2b6b5a6cb76d-1550748716; thor=A6B61647D63F7169FE27C53B82ED6BAB16DBC759CF019F74A3AEE8DF5AB8F2F103874D0C7AC48F0ED4B379116C8D2AA4DAD743465307CB607E5975BE9C7762F0133F302F7C410146FC31C40F9368D382CDEE0A48A97FAA4EF7D6F30190C6AC594772832ACF4D715BD87FDE55E1B77BDFE51E18C829E05522D1DB463C49ECCA0B1EC172433F1C399A261C65A78561F818; shshshfp=412331367ae52c114c69c0f927527962; ipLoc-djd=15-1213-3038-59931; shshshsID=bd812d591e2f8cf27ac4203bab7bf3bc_4_1596966880544; __jdb=122270672.4.15969524734751626540253|2.1596966805"""
        }
        headers = {
            'Connection': 'keep - alive',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36',
            'Accept': '*/*',
            'Accept-Encoding': 'gzip, deflate, br',
            'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8,en-US;q=0.7',
            'Host': 'gia.jd.com'

        }

        meta = {
            'dont_redirect': True,  # 禁止网页重定向
            'handle_httpstatus_list': [301, 302],  # 对哪些异常返回进行处理
            'item': deepcopy(item)
        }
        yield scrapy.Request('https:' + find,
                             callback=self.parse_book_list, headers=headers, cookies=cookies, meta=meta)

    def parse_book_list(self, response):
        # book_des = scrapy.Field()
        item = response.meta['item']
        try:
            item['book_name'] = handleBookName(response.xpath('//div[@class= "sku-name"]/text()').extract_first().strip())
        except Exception:
            item['book_name'] = response.xpath('//div[@class= "sku-name"]/text()').extract_first().strip()
        item['book_image_big'] = 'http:' + response.xpath(
            '//*[@id="spec-img"]/@src').extract_first().strip()  # 书名前后有换行符，修掉
        # 补全地址
        tmp_authers = response.xpath('.//div[@class="p-author"]/a/text()').extract()
        # 获取作者信息列表，注意部分没有
        item['book_author'] = ', '.join(tmp_authers)
        list = response.xpath('.//ul[@id="parameter2"]/li')
        for l in list:
            current = l.xpath("text()").extract_first()
            print(current)
            if 'ISBN' in current:
                print(current.split("：")[-1])
                item['book_number'] = current.split("：")[-1]
                continue
            if '出版社' in current:
                item['book_press'] = l.xpath("a/text()").extract_first()
        item['book_desc'] = "\n".join(response.xpath('//*[@id="detail-tag-id-3"]/div[2]/div/p/text()').extract())

        item['book_classify_one'] = response.xpath(
            './/div[@class="crumb fl clearfix"]/div[@class="item"][1]/a/text()').extract_first()
        item['book_classify_two'] = response.xpath(
            './/div[@class="crumb fl clearfix"]/div[@class="item"][2]/a/text()').extract_first()
        yield item


def handleBookName(name):
    print(name)
    result = re.sub(
        r'(《.*?》|【.*?】|正版|正版图书|现货|新华书店|商务印书馆|\d{12,13}|((\(|\[)[法德美德意日韩英澳].*?(\)|\]).*)|(\s((?!\s).)+出版社)|（.*?）|现货|全新|(上海(文艺|译文)))',
        "", name).strip()
    print(result)
    return result
