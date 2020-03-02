package com.tensquare.article.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List list = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Article Article = articleService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功", Article);
    }

                       //新增标签数据接口
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //修改标签数据接口
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        articleService.update(article);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    //删除文章数据接口
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        articleService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "findBypaging/{id}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public Result findByPaging(@PathVariable String id,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map param = new HashMap();
        param.put("id",id);
        Page<Article> data = articleService.findByPaging(param);
        return new Result(true, StatusCode.OK,"查询成功",data);
    }

//    @RequestMapping(value="/exception", method = RequestMethod.GET)
//    public Result exception() throws Exception {
//        throw new Exception("测试统一异常处理");
//    }

    @RequestMapping(value="/exception", method = RequestMethod.GET)
    public Result test() {
        int a=1/0;
        return null;
    }
}