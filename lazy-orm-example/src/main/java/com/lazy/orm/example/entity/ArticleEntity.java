package com.lazy.orm.example.entity;

import com.lazy.orm.annotation.Column;

import java.sql.Timestamp;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class ArticleEntity {


    @Column("title")
    private String title;
    @Column("content")
    private String content;
    @Column("user_id")
    private Long userId;
    @Column("id")
    private Long id;
    @Column("update_time")
    private Timestamp updateTime;

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ArticleEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ArticleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public ArticleEntity setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
