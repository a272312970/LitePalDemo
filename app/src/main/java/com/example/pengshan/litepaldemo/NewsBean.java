package com.example.pengshan.litepaldemo;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 /**
 * 定义数据库中的表和属性，要继承DataSupper.
 * 这时不用声明表名和列名，
 * 它会帮你创建，生成的表名是类名的小写，列名是各属性的值，
 * 但是要注意这里不能搞内部类，否则它不能识别！
 * 这里属性也可以是一个类对象，但是这个类对象也是必须要继承了DataSupport才行
 * <p/>
 * 这个类虽然框架要调调用，但是也是可以当普通的Bean类来使用的
 */
public class NewsBean extends DataSupport{

    private int id;

    private String title;

    private String content;

    private Date publishDate;

    private int commentCount;

    private  String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
