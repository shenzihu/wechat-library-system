package org.bookmate.dao.impl;

import org.bookmate.dao.IsbnDao;
import org.bookmate.entities.Admin;
import org.bookmate.entities.Isbn;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Tiger
 * @Date: 2020/1/11 15:50
 * @Description:
 */
@Repository
public class IsbnDaoImpl extends SqlSessionDaoSupport implements IsbnDao {
    @Override
    public List<Isbn> findAllIsbnByStatus(int status) {
        List<Isbn> result = (List<Isbn>) this.getSqlSession()
                .selectList(ISBN_NAMESPACE + "findAllIsbn", status);
        return result;
    }

    @Override
    public void insertIsbn(Isbn isbn) {
        this.getSqlSession().insert(ISBN_NAMESPACE + "insertIsbn", isbn);
    }

    @Override
    public List<Isbn> findByUserid(String userid) {
        List<Isbn> result = (List<Isbn>) this.getSqlSession()
                .selectList(ISBN_NAMESPACE + "findIsbnByUserid", userid);
        return result;
    }
}
