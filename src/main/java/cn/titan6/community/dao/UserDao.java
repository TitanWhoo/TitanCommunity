package cn.titan6.community.dao;

import cn.titan6.community.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface UserDao {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM user WHERE (username = #{account} OR email = #{account} OR phone = #{account})" +
            " AND password = #{password}")
    User find(@Param("account") String account, @Param("password") String password);

    @SelectProvider(value = cn.titan6.community.provider.UserProvider.class, method = "findUser")
    User findByUser(User user);

    @Insert("INSERT INTO user (username,password,email,phone) VALUES (#{username},#{password},#{email},#{phone})")
    boolean save(User user);
}
