package cn.titan6.community.dao;

import cn.titan6.community.bean.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDao {
    @Results(id = "articleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "time", property = "time"),
            @Result(column = "content", property = "content"),
            @Result(column = "uid", property = "author", one = @One(select = "cn.titan6.community.dao.UserDao.findById"))
    })
    @Select("SELECT * FROM article ORDER BY pr,time DESC")
    List<Article> findAll();

    @ResultMap("articleMap")
    @Select("SELECT * FROM article WHERE title LIKE #{title} ORDER BY time DESC")
    List<Article> findByTitle(String title);

    @ResultMap("articleMap")
    @Select("SELECT * FROM article WHERE id = #{id}")
    Article findById(int id);

    @Insert("INSERT INTO article (title,content,uid,time,pr) VALUES(#{title},#{content},#{author.id},#{time},0)")
    boolean post(Article article);
}
