package org.bookmate.service.impl;

import org.bookmate.dao.IsbnDao;
import org.bookmate.dao.impl.IsbnDaoImpl;
import org.bookmate.entities.Isbn;
import org.bookmate.service.IsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Tiger
 * @Date: 2020/1/11 16:05
 * @Description:
 */
@Service
public class IsbnServiceImpl implements IsbnService {
    @Autowired
    IsbnDao dao;

    @Override
    public List<Isbn> findAllIsbnByStatus(int status) {
        return dao.findAllIsbnByStatus(status);
    }

    @Override
    public void insertIsbn(Isbn isbn) {
        dao.insertIsbn(isbn);
    }

    @Override
    public List<Isbn> findByUserid(String userid) {
        return dao.findByUserid(userid);
    }
}
