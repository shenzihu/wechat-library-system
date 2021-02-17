package org.bookmate.dao.impl;

import org.bookmate.dao.BookDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图书数据访问实现类
 *
 * @author Tiger
 */
@Repository
public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {
    private final static int pageSize = 20;

    @Override
    public Book selectBookById(Integer id) {
        return (Book) this.getSqlSession().selectOne(BOOK_NAMESPACE + "selectBookById", id);
    }

    @Override
    public void insertBook(Book book) {
        this.getSqlSession().insert(BOOK_NAMESPACE + "insertBook", book);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<String> selectAllClassifyOne() {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllClassifyOne");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> selectBookByClassifyOne(String classifyOne, int page) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("classifyOne", classifyOne);
        queryMap.put("pageIndex", (page - 1) * pageSize);
        queryMap.put("pageSize", pageSize);
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByClassifyOne", queryMap);
    }

    @Override
    public void updateBook(Book book) {
        this.getSqlSession().update(BOOK_NAMESPACE + "updateBook", book);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> selectAllBook() {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllBook");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> selectBookByNameLike(String bookName) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("book_name", bookName);
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByNameLike", queryMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> selectAllClassifyTwo() {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllClassifyTwo");
    }

    @Override
    public void insertClassifyTwo(BookClassifyTwo bookClassifyTwo) {
        this.getSqlSession().insert(BOOK_CLASSIFY_TWO_NAMESPACE + "insertBookClassifyTwo", bookClassifyTwo);
    }

    @Override
    public Integer selectClassifyIdByBame(String classifyTwoName) {
        Object result = this.getSqlSession().selectOne(BOOK_CLASSIFY_TWO_NAMESPACE + "selectClassifyIdByBame", classifyTwoName);
        return result == null ? 0 : (Integer) result;
    }

    @Override
    public Integer selectBookCount() {
        Object result = this.getSqlSession().selectOne(BOOK_NAMESPACE + "selectBookCount");
        return result == null ? 0 : (Integer) result;
    }

    @Override
    public BookClassifyTwo selectClassifyTwoById(Integer id) {
        return (BookClassifyTwo) this.getSqlSession()
                .selectOne(BOOK_CLASSIFY_TWO_NAMESPACE + "selectClassifyById", id);
    }

    @Override
    public List<Book> selectBookByClassifyTwoName(String classifyTwoName) {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByClassifyTwoName", classifyTwoName);
    }

    @Override
    public List<Book> selectBookByStatus(int status) {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByStatus", status);
    }

    @Override
    public List<Book> getBookByids(List<String> list) {
        return this.getSqlSession().selectList(BOOK_NAMESPACE + "getBookByids", list);
    }


}
