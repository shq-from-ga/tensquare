package com.tensquare.article.dao;

import com.github.pagehelper.Page;
import com.tensquare.article.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ArticleDao {
    List<Article> selectList();
    Article selectById(String id);
    void insert(Article article);
    Article selectById(Serializable id);
    void updateById(Article article);
    void deleteById(String id);

    Page<Article> findByPaging(Map param);
}
