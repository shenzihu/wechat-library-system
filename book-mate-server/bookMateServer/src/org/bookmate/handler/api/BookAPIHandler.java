package org.bookmate.handler.api;

import org.bookmate.entities.Book;
import org.bookmate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书相关操作API
 * @author Tiger
 */
@Controller
public class BookAPIHandler {

	@Autowired
	private BookService bookService;

	/**
	 * 获取全部图书一级分类接口
	 * @return 分类集合yang
	 */
	@ResponseBody
	@RequestMapping(value="api-book-classifyone-all")
	public ArrayList<String> getAllClassifyOne() {
		ArrayList<String> list = (ArrayList<String>) bookService.getAllClassifyOne();
		return list;
	}

	/**
	 * 根据一级分类获取对应图书列表接口
	 * @return 图书集合
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-byclassifyone/{classifyOne}/{page}")
	public ArrayList<Book> getBookByClassifyOne(@PathVariable("classifyOne") String classifyOne,@PathVariable("page") int page) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByClassifyOne(classifyOne,page);
		return books;
	}

	/**
	 * 根据id获取图书信息接口
	 * @return 图书信息
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-byid/{id}")
	public Book getBookById(@PathVariable("id") Integer id) {
		Book book = bookService.getBookById(id);
		return book;
	}

	/**
	 * 根据图书名模糊查询符合的图书
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-bynamelike/{name}")
	public ArrayList<Book> getBookByNameLike(@PathVariable("name") String name) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByNameLike(name);
		return books;
	}

	@ResponseBody
	@RequestMapping(value="api-book-book-bybookids",method = RequestMethod.POST )
	public ArrayList<Book> getBookByids(@RequestBody List<String> list) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByids(list);
		return books;
	}
}
