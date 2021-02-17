package org.bookmate.dao;

import org.bookmate.entities.Isbn;

import java.util.List;

/**
 * @Author: Tiger
 * @Date: 2020/1/11 15:47
 * @Description:
 */
public interface IsbnDao {
    public final String ISBN_NAMESPACE = "org.bookmate.mapper.IsbnMapper.";

    public List<Isbn> findAllIsbnByStatus(int status);

    public void insertIsbn(Isbn isbn);

    List<Isbn> findByUserid(String userid);
}
