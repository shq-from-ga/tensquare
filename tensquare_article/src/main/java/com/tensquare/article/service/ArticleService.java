package com.tensquare.article.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public List<Article> findAll() {
        return articleDao.selectList();
    }

    public Article findById(String id) {
        return articleDao.selectById(id);
    }

    public void add(Article article) {
        article.setId(idWorker.nextId() + "");

        //初始化数据
        article.setVisits(0);  //浏览量
        article.setThumbup(0); //点赞数
        article.setComment(0); //评论数
        articleDao.insert(article);
    }

    public void update(Article article) {
        //根据id号更新
        //方法1
        articleDao.updateById(article);
        //方法2
//        EntityWrapper wrapper = new EntityWrapper<Article>();
//        wrapper.eq("id", article.getId());
//        articleDao.update(article, wrapper);
    }

    public void delete(String id) {
        articleDao.deleteById(id);
    }

    public Page<Article> findByPaging(Map param){
        return articleDao.findByPaging(param);
    }
}
