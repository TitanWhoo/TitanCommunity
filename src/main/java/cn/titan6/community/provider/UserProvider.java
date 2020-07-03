package cn.titan6.community.provider;

import cn.titan6.community.bean.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String findUser(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            WHERE("1 = 2");
            if (user.getUsername() != null) {
                OR();
                WHERE("username =#{username}");
            }
            if (user.getEmail() != null) {
                OR();
                WHERE("email = #{email}");
            }
            if (user.getPhone() != null) {
                OR();
                WHERE("phone = #{phone}");
            }
            LIMIT(1);
        }}.toString();
    }
}
