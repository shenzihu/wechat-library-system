package org.bookmate.entities;

import java.util.Date;

/**
 * ISBN 实体类
 *
 * @Author: Tiger
 * @Date: 2020/1/11 12:01
 * @Description:
 */
public class Isbn {
    private Integer id;
    private String isbn;
    private Integer userid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 爬取时间
     */
    private Date getTime;
    private String bookName;
    private Integer status = 1;
    private String comment;

    private User user;

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }
}
