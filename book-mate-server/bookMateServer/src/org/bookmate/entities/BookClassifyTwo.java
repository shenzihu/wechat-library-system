package org.bookmate.entities;

/**
 * 图书二级分类
 * @author Tiger
 */
public class BookClassifyTwo {

    //图书二级分类id
    private Integer bookClassifyTwoId;
    //图书二级分类名
    private String bookClassifyTwoName;

    public Integer getBookClassifyOneId() {
        return bookClassifyTwoId;
    }
    public void setBookClassifyOneId(Integer bookClassifyTwoId) {
        this.bookClassifyTwoId = bookClassifyTwoId;
    }
    public String getBookClassifyOneName() {
        return bookClassifyTwoName;
    }
    public void setBookClassifyOneName(String string) {
        this.bookClassifyTwoName = string;
    }
}
