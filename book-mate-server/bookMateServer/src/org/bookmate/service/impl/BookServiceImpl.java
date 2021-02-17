package org.bookmate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bookmate.dao.BookDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.bookmate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图书相关业务逻辑实现类
 *
 * @author Tiger
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getBookById(Integer id) {
        return bookDao.selectBookById(id);
    }

    @Override
    public void addBook(String bookNumber, String bookName, String bookAuthor, String bookPress, String bookImageBig,
                        String bookImageSmall, String bookClassifyOne, String bookClassifyTwo, String bookDesc) {
        Book book = new Book();
        book.setBookNumber(bookNumber);
        book.setBookName(bookName);
        book.setBookAuthor(bookAuthor);
        book.setBookPress(bookPress);
        book.setBookImageBig(bookImageBig);
        book.setBookImageSmall(bookImageSmall);
        book.setBookClassifyOne(bookClassifyOne);
        book.setBookClassifyTwo(bookClassifyTwo);
        book.setBookDesc(bookDesc);
        bookDao.insertBook(book);
    }

    @Override
    public List<String> getAllClassifyOne() {
        return bookDao.selectAllClassifyOne();
    }

    @Override
    public List<Book> getBookByClassifyOne(String classifyOne,int page) {
        return bookDao.selectBookByClassifyOne(classifyOne, page);
    }

    @Override
    public List<Book> getBookByNameLike(String bookName) {
        return bookDao.selectBookByNameLike(bookName);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDao.selectAllBook();
    }

    @Override
    public void insertBookClassifyTwo() {
        ArrayList<String> list = (ArrayList<String>) bookDao.selectAllClassifyTwo();
        for (int i = 0; i < list.size(); ++i) {
            BookClassifyTwo bookClassifyTwo = new BookClassifyTwo();
            bookClassifyTwo.setBookClassifyOneId(i + 1);
            bookClassifyTwo.setBookClassifyOneName(list.get(i));
            bookDao.insertClassifyTwo(bookClassifyTwo);
            System.out.println(i);
        }
    }

    @Override
    public Integer getBookCount() {
        return bookDao.selectBookCount();
    }

    @Override
    public void updateBook(Integer bookId, String bookNumber, String bookName, String bookAuthor, String bookPress,
                           String bookImageBig, String bookImageSmall, String bookClassifyOne, String bookClassifyTwo, String bookDesc,
                           int status) {
        Book book = bookDao.selectBookById(bookId);
        book.setBookNumber(bookNumber);
        book.setBookName(bookName);
        book.setBookAuthor(bookAuthor);
        book.setBookPress(bookPress);
        book.setBookImageBig(bookImageBig);
        book.setBookImageSmall(bookImageSmall);
        book.setBookClassifyOne(bookClassifyOne);
        book.setBookClassifyTwo(bookClassifyTwo);
        book.setBookDesc(bookDesc);
        book.setStatus(status);
        bookDao.updateBook(book);
    }

    @Override
    public void deleteOneBook(Integer id) {
        Book book = bookDao.selectBookById(id);
        book.setStatus(0);
        bookDao.updateBook(book);
    }

    @Override
    public List<Book> selectBookByStatus(int status) {
        return bookDao.selectBookByStatus(status);
    }

    @Override
    public List<Book> getBookByids(List<String> list) {
        return bookDao.getBookByids(list);
    }

    @Override
    public void checkOneBook(Integer id, Integer status) {
        Book book = bookDao.selectBookById(id);
        book.setStatus(status);
        bookDao.updateBook(book);
    }

}
