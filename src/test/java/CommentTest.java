import cn.titan6.community.service.CommentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class CommentTest {
    @Autowired
    private CommentService commentService;

    public void testFind() {

    }

    private void testPost() {

    }
}
