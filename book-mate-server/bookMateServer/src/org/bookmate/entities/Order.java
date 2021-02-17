package org.bookmate.entities;

import java.util.Date;

/**
 * 图书订购实体类
 *
 * @author Tiger
 */
public class Order {

    private Integer id;
    private Integer bookId;
    private Integer userId;
    private Date createTime;
    /**
     * 借阅状态
     * 0：删除
     * 1：正常
     * 2：待审批
     * 3: 拒绝
     */
    private Integer status;
    /**
     * 订书数量
     */
    private Integer  num;
    //映射用户与预定一对多关联关系
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //映射图书与预定一对多关联关系
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", num=" + num +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
