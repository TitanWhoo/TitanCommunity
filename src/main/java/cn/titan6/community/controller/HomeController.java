package cn.titan6.community.controller;

import cn.titan6.community.bean.Article;
import cn.titan6.community.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"user"})
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(path = {"/", "/home"})
    public String redirect() {
        return "redirect:index";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(required = false, defaultValue = "1") int page) {
        ModelAndView view = new ModelAndView("index");
        view.addObject("tip", "为你推荐");
        PageInfo<Article> articles = articleService.findPage(page);
        return getPageModelAndView(view, articles);
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView home(String keyword, @RequestParam(required = false, defaultValue = "1") int page) {
        ModelAndView view = new ModelAndView("index");
        view.addObject("tip", "搜索结果");
        PageInfo<Article> articles = articleService.findPageByTitle(keyword, page);
        return getPageModelAndView(view, articles);
    }

    private ModelAndView getPageModelAndView(ModelAndView view, PageInfo<Article> articles) {
        int pageNum = articles.getPageNum();
        int prePage = pageNum - 1;
        int nextPage = pageNum + 1;
        view.addObject("prePage", prePage);
        view.addObject("nextPage", nextPage);
        view.addObject("articles", articles);
        return view;
    }

}
