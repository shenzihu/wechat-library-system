package org.bookmate.service;

import org.bookmate.entities.Isbn;

import java.util.List;

/**
 * @Author: Tiger
 * @Date: 2020/1/11 16:04
 * @Description:
 */
public interface IsbnService {
    public List<Isbn> findAllIsbnByStatus(int status);

    public void insertIsbn(Isbn isbn);

    public List<Isbn> findByUserid(String userid);
}
