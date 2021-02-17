# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class JdspiderItem(scrapy.Item):

    def __init__(self):
        # 如果实现了子类的构造，则必须声明父类构造，
        # 否则无法执行ItemProcess的process_item方法
        super().__init__()
        print('<INFO> TestSpiderItem is instancing.')

    # define the fields for your item here like:
    book_name = scrapy.Field()
    book_image_big = scrapy.Field()
    book_author = scrapy.Field()
    book_desc = scrapy.Field()
    book_price = scrapy.Field()
    book_number = scrapy.Field()
    book_press = scrapy.Field()
    book_classify_one = scrapy.Field()
    book_classify_two = scrapy.Field()
    originalISBN = scrapy.Field()
    pass
