# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import json
import MySQLdb


class JdspiderPipeline(object):

    def process_item(self, item, spider):
        # with open("rescources/book_details1.json", 'r', encoding='utf-8') as load_f:
        #     self.load_dict = json.load(load_f)
        # self.load_dict[item['book_number']] = item
        # with open("rescources/book_details1.json", 'w', encoding='utf-8') as json_file:
        #     json.dump(self.load_dict, json_file, indent=4, ensure_ascii=False)
        # # 查询ISBN
        db = MySQLdb.connect("localhost", "root", "123456", "book_mate", charset='utf8')
        cursor = db.cursor()
        # cursor.execute("select book_number FROM book where book_image_big like '%gif'")
        # results = cursor.fetchall()
        # allISBN = [row[0] for row in results]
        # try:
        # if (allISBN.index(item["book_number"].strip()) != -1):
        # sql = "insert into book(book_name,book_image_big,book_author,book_number,book_desc,book_press,book_classify_one,book_classify_two, status)values(%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        # cursor.execute(sql, (
        #     item["book_name"], item["book_image_big"], item["book_author"], item["originalISBN"],
        #     item["book_desc"], "", item["book_classify_one"], item["book_classify_two"], 2))
        sql = "update book set book_image_big = '%s' where book_number = '%s'" %(item["book_image_big"], item["originalISBN"])
        cursor.execute(sql)
        sql2 = "update isbn set status = 1, get_time =now(),source ='京东' where isbn = '%s'" % (
            item["originalISBN"].strip())
        print(sql2)
        cursor.execute(sql2)
        db.commit()

        # except Exception as e:
        #     print("===============")
        #     print(e)
        #     sql2 = "update isbn set status = 2,comment = '%s' where isbn = '%s'" % (
        #     "数据不一致，爬取ISBN为%s，用户上传ISBN为%s" % (item["originalISBN"], item["originalISBN"]), item["originalISBN"])
        #     cursor.execute(sql2)
        #     db.commit()
        cursor.close()
        return item
