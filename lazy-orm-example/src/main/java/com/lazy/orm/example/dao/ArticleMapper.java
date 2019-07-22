package com.lazy.orm.example.dao;

import com.lazy.orm.annotation.Mapper;
import com.lazy.orm.annotation.Sql;
import com.lazy.orm.example.dto.QueryUserArticleDto;

import java.util.List;

/**
 * <p>
 *     文章Mapper
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
@Mapper
public interface ArticleMapper {


    @Sql(
            value = "select t.id as userId, tt.id as articleId, tt.content, t.age from t_user t, t_article tt where t.id = tt.user_id " +
                    "and t.id = #{userId} and tt.id = #{articleId} and t.name like #{name} and tt.content like #{content} and t.age > #{age}",
            itemType = QueryUserArticleDto.class
    )
    List<QueryUserArticleDto> findUserArticleByCondition(QueryUserArticleDto dto);

}
