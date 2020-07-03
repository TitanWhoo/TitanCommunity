import cn.titan6.community.bean.Article;
import cn.titan6.community.bean.User;
import cn.titan6.community.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class ArticleTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void testFindAll() {
        PageHelper.startPage(1, 10);
        List<Article> articles = articleService.findAll();
        PageInfo<Article> articleInfo = new PageInfo<>(articles);
        System.out.println(articleInfo);
    }

    @Test
    public void testFindTitle() {
        PageInfo<Article> articles = articleService.findPageByTitle("随机", 1);
        System.out.println(articles);
    }

    @Test
    public void testPost() {
        Article article = new Article();
        User user = new User();
        user.setId(10);
        article.setAuthor(user);
        article.setTitle("Test123");
        article.setContent("good to see you");
        articleService.post(article);
    }
}
