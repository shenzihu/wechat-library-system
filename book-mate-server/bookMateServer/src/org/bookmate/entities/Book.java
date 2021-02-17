package org.bookmate.entities;

/**
 * 图书实体类
 *
 * @author Tiger
 */
public class Book {

    //书籍id
    private Integer bookId;
    //书籍编号
    private String bookNumber;
    //书籍名称
    private String bookName;
    //书籍作者
    private String bookAuthor;
    //书籍出版社
    private String bookPress;
    //书籍封皮大图
    private String bookImageBig;
    //书籍封皮缩略图
    private String bookImageSmall;
    //书籍一级分类
    private String bookClassifyOne;
    //书籍二级分类
    private String bookClassifyTwo;
    //书籍简介
    private String bookDesc;

    /**
     * 0:删除
     * 1：正常
     * 2:待审批
     * 3:驳回'
     */
    private Integer status;

    public Book() {

    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookImageBig() {
        return bookImageBig;
    }

    public void setBookImageBig(String bookImageBig) {
        this.bookImageBig = bookImageBig;
    }

    public String getBookImageSmall() {
        return bookImageSmall;
    }

    public void setBookImageSmall(String bookImageSmall) {
        this.bookImageSmall = bookImageSmall;
    }

    public String getBookClassifyOne() {
        return bookClassifyOne;
    }

    public void setBookClassifyOne(String bookClassifyOne) {
        this.bookClassifyOne = bookClassifyOne;
    }

    public String getBookClassifyTwo() {
        return bookClassifyTwo;
    }

    public void setBookClassifyTwo(String bookClassifyTwo) {
        this.bookClassifyTwo = bookClassifyTwo;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
