package cn.titan6.community.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment implements Serializable {
    private int id;
    @Length(min = 5, message = "回复长度需要大于5个字符!")
    private String content;
    @NotNull(message = "ArticleID 不正确!")
    private int aid;
    private User user;
    private LocalDateTime time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOriginTime() {
        return time;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getOriginTime().format(formatter);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
