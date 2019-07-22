package com.lazy.orm.example.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class QueryUserArticleDto {

    private String name;
    private int age;
    private List<Long> ids;
    private Timestamp createTime;

    private Timestamp updateTime;

    private Long userId;

    private Long articleId;


    private String content;
    private String title;

    public String getName() {
        return name;
    }

    public QueryUserArticleDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public QueryUserArticleDto setAge(int age) {
        this.age = age;
        return this;
    }

    public List<Long> getIds() {
        return ids;
    }

    public QueryUserArticleDto setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public QueryUserArticleDto setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public QueryUserArticleDto setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QueryUserArticleDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getArticleId() {
        return articleId;
    }

    public QueryUserArticleDto setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QueryUserArticleDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QueryUserArticleDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
