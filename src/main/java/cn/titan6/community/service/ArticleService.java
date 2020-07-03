package cn.titan6.community.service;

import cn.titan6.community.bean.Article;
import cn.titan6.community.dao.ArticleDao;
import cn.titan6.community.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    private void setShortContent(List<Article> articles) {
        for (Article article : articles) {
            String content = article.getContent();
            content = StringUtil.deleteHtml(content);
            if (content.length() > 200) {
                content = content.substring(0, 200);
                content = content.replaceAll("[\n\r]", "  ");
            }
            article.setShortContent(content);
        }
    }

    private void setShortContent(Article article) {
        String content = article.getContent();
        content = StringUtil.deleteHtml(content);
        if (content.length() > 200) {
            article.setShortContent(content.substring(0, 200) + "...");
        } else {
            article.setShortContent(content + "...");
        }
    }

    public List<Article> findAll() {
        List<Article> articles = articleDao.findAll();
        setShortContent(articles);
        return articles;
    }

    public PageInfo<Article> findPageByTitle(String title, int page) {
        if (title == null) {
            return findPage(1);
        } else if ("".equals(title.trim())) {
            return findPage(1);
        }
        PageHelper.startPage(page, 10);
        List<Article> articleList = articleDao.findByTitle("%" + title + "%");
        setShortContent(articleList);
        return new PageInfo<>(articleList);
    }

    public PageInfo<Article> findPage(int page) {
        PageHelper.startPage(page, 10);
        List<Article> articleList = articleDao.findAll();
        setShortContent(articleList);
        return new PageInfo<>(articleList);
    }

    public List<Article> findByTitle(String title) {
        if (title == null) {
            return null;
        } else if ("".equals(title.trim())) {
            return null;
        }
        List<Article> articles = articleDao.findByTitle("%" + title + "%");
        setShortContent(articles);
        return articles;
    }

    public boolean post(Article article) {
        String content = article.getContent();
        String title = article.getTitle();
        article.setContent(StringUtil.escapeScript(content));
        article.setTitle(StringUtil.escapeScript(title));
        article.setTime(LocalDateTime.now());
        return articleDao.post(article);
    }

    public Article findById(int id) {
        Article article = articleDao.findById(id);
        if (article == null) {
            return null;
        }
        setShortContent(article);
        return article;
    }
}
