package cn.titan6.community.bean;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private int status;
    private String text;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
