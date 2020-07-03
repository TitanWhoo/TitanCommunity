import cn.titan6.community.bean.User;
import cn.titan6.community.provider.UserProvider;
import org.junit.Test;

public class SqlProviderTest {
    @Test
    public void testFindUser() {
        User user = new User();
        user.setEmail("titanhw@foxmail.com");
        user.setPhone("17633796666");
        user.setUsername("Titan");
        String result = new UserProvider().findUser(user);
        System.out.println(result);
    }
}
