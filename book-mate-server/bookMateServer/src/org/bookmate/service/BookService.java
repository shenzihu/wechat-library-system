package org.bookmate.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.Literal;
import org.bookmate.entities.Book;

/**
 * 图书相关业务逻辑接口层
 * @author Tiger
 */
public interface BookService {

	/**
	 * 通过图书id获取图书信息
	 * @param id
	 * @return
	 */
	public Book getBookById(Integer id);

	/**
	 * 添加一本新书
	 * @param bookNumber 图书编号
	 * @param bookName 图书名
	 * @param bookAuthor 图书作者
	 * @param bookPress 图书出版社
	 * @param bookImageBig 图书封皮图
	 * @param bookImageSmall 图书封皮缩略图
	 * @param bookClassifyOne 图书一级分类
	 * @param bookClassifyTwo 图书二级分类
	 * @param bookDesc 图书内容概述
	 */
	public void addBook(String bookNumber, String bookName, String bookAuthor,
			String bookPress, String bookImageBig, String bookImageSmall,
			String bookClassifyOne, String bookClassifyTwo, String bookDesc);

	/**
	 * 获取全部图书分类
	 * @return list 分类集合
	 */
	public List<String> getAllClassifyOne();

	/**
	 * 根据一级分类获取图书列表
	 * @param classifyOne 一级分类
	 * @return list 图书列表集合
	 */
	public List<Book> getBookByClassifyOne(String classifyOne,int page);

	/**
	 * 根据图书名模糊获取查询图书
	 * @param bookName 图书名
	 * @return list 图书实体类集合
	 */
	public List<Book> getBookByNameLike(String bookName);

	/**
	 * 获取全部图书信息
	 * @return list 图书实体类集合
	 */
	public List<Book> getAllBook();

	/**
	 * 添加所有二级分类信息到二级分类表
	 */
	public void insertBookClassifyTwo();

	/**
	 * 获取图书数量
	 * @return
	 */
	public Integer getBookCount();

	/**
	 * 更新图书信息
	 * @param bookId
	 * @param bookNumber
	 * @param bookName
	 * @param bookAuthor
	 * @param bookPress
	 * @param bookImageBig
	 * @param bookImageSmall
	 * @param bookClassifyOne
	 * @param bookClassifyTwo
	 * @param bookDesc
	 */
	public void updateBook(Integer bookId, String bookNumber, String bookName, String bookAuthor,
			String bookPress, String bookImageBig, String bookImageSmall,
			String bookClassifyOne, String bookClassifyTwo, String bookDesc,int status);

	/**
	 * 移除一本书
	 * @param id
	 */
	public void deleteOneBook(Integer id);

	public List<Book> selectBookByStatus(int status);

	List<Book> getBookByids(List<String> list);

    void checkOneBook(Integer id, Integer status);
}
