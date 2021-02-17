package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;

/**
 * 图书数据访问接口层
 * @author Tiger
 */
public interface BookDao {

	public final static String BOOK_NAMESPACE = "org.bookmate.mapper.BookMapper.";
	public final static String BOOK_CLASSIFY_TWO_NAMESPACE = "org.bookmate.mapper.BookClassifyTwoMapper.";

	/**
	 * 通过图书id获取图书实体类
	 * @param id 图书id
	 * @return book 图书实体类
	 */
	public Book selectBookById(Integer id);

	/**
	 * 插入新图书记录
	 */
	public void insertBook(Book book);

	/**
	 * 查询所有图书的一级分类
	 * @return list 分类集合
	 */
	public List<String> selectAllClassifyOne();

	/**
	 * 根据图书一级分类查找对应的图书
	 * @param classifyOne 一级分类
	 * @return list 图书实体类集合
	 */
	public List<Book> selectBookByClassifyOne(String classifyOne,int page);

	/**
	 * 更新图书信息
	 */
	public void updateBook(Book book);

	/**
	 * 获取全部图书实体类
	 * @return books 实体类集合
	 */
	public List<Book> selectAllBook();

	/**
	 * 通过图书名模糊查询图书
	 * @param bookName 图书名
	 * @return list 图书实体类集合
	 */
	public List<Book> selectBookByNameLike(String bookName);

	/**
	 * 获取全部的图书二级分类
	 * @return
	 */
	public List<String> selectAllClassifyTwo();

	/**
	 * 插入二级分类记录
	 * @param bookClassifyTwo
	 */
	public void insertClassifyTwo(BookClassifyTwo bookClassifyTwo);

	/**
	 * 通过二级分类查找对应二级分类id
	 * @param classifyTwoName
	 * @return
	 */
	public Integer selectClassifyIdByBame(String classifyTwoName);

	/**
	 * 查找图书数量
	 * @return
	 */
	public Integer selectBookCount();

	/**
	 * 通过二级分类id查找二级分类实体类
	 * @param id
	 * @return
	 */
	public BookClassifyTwo selectClassifyTwoById(Integer id);

	/**
	 * 通过二级分类名获取图书
	 * @return
	 */
	public List<Book> selectBookByClassifyTwoName(String classifyTwoName);

	public List<Book> selectBookByStatus(int status);

    List<Book> getBookByids(List<String> numbers);
}
