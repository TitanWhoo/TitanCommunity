package cn.titan6.community.service;

import cn.titan6.community.bean.Article;
import cn.titan6.community.bean.Comment;
import cn.titan6.community.dao.CommentDao;
import cn.titan6.community.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> find(Article article) {
        return commentDao.findCommentByAid(article.getId());
    }

    public boolean post(Comment comment) {
        if (comment == null) {
            return false;
        }
        String content = comment.getContent();
        comment.setContent(StringUtil.escapeScript(content));
        comment.setTime(LocalDateTime.now());
        return commentDao.post(comment);
    }
}
