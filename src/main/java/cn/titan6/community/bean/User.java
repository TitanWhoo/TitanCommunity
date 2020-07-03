package cn.titan6.community.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {
    private int id;
    @Length(min = 5, max = 20, message = "用户名必须大于5位且小于20位长度!")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "用户名只能为数字与字母组合!")
    private String username;
    @Length(min = 5, message = "密码必须大于5位")
    private String password;
    @Email(message = "邮箱格式不正确")
    private String email;
    @Length(min = 11, max = 11, message = "手机号格式不正确!")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
