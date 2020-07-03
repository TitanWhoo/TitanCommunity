package cn.titan6.community.controller;

import cn.titan6.community.bean.Article;
import cn.titan6.community.bean.Comment;
import cn.titan6.community.bean.User;
import cn.titan6.community.service.ArticleService;
import cn.titan6.community.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @RequestMapping(path = "/articles/", method = RequestMethod.GET)
    public ModelAndView showArticle(ModelMap modelMap, int id) {
        Article article = articleService.findById(id);
        ModelAndView view;
        if (article != null) {
            List<Comment> comments = commentService.find(article);
            view = new ModelAndView("article");
            view.addObject("comments", comments);
            view.addObject("article", article);
        } else {
            view = new ModelAndView("tips", HttpStatus.NOT_FOUND);
            view.addObject("errorCode", "404");
            view.addObject("errorReason", "找不到您要查看的文章");
        }
        return view;
    }

    @RequestMapping(path = "/write", method = RequestMethod.GET)
    public ModelAndView write(ModelMap modelMap, HttpSession session) {
        User user = (User) modelMap.get("user");
        ModelAndView view;
        if (user == null) {
            view = new ModelAndView("tips", HttpStatus.FORBIDDEN);
            view.addObject("errorCode", "403");
            view.addObject("errorReason", "您需要登录后才可以进行此操作");
        } else {
            view = new ModelAndView("write");
        }
        return view;
    }
}
