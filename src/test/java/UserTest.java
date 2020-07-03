import cn.titan6.community.bean.User;
import cn.titan6.community.dao.UserDao;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testFindUser() {
        User user = new User();
        user.setEmail("titanhw@foxmail.com");
        user.setPhone("17633796666");
        user.setUsername("Titan");
        User result = userDao.findByUser(user);
        System.out.println(result);
    }
    @Test
    public void testValidate(){
        User user = new User();
        user.setEmail("titanhw@foxmail.com");
        user.setPhone("17633796666");
        user.setUsername("Titan");
        System.out.println(StringEscapeUtils.escapeHtml3("<script>你好!</script>"));
    }
}
