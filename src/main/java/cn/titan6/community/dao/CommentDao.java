package cn.titan6.community.dao;

import cn.titan6.community.bean.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentDao {
    @Results(id = "commentMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "time"),
            @Result(property = "aid", column = "aid"),
            @Result(property = "user", column = "uid", one = @One(select = "cn.titan6.community.dao.UserDao.findById"))
    })
    @Select("SELECT * FROM comment WHERE aid = #{aid}")
    List<Comment> findCommentByAid(int aid);

    @Insert("INSERT INTO comment(uid,aid,time,content) VALUES(#{user.id},#{aid},#{time},#{content})")
    boolean post(Comment comment);
}
