package com.pqanh95.app.aibithnai.entity;

public class Question {
    private int id; // hiểu đây là câu hỏi
    private String linkImg1; // link hình ảnh 1
    private String linkImg2; // link hình ảnh 2
    private String resultVI; // đáp án bằng tiếng việt
    private String resultEN; // đáp án bằng tiếng anh

    public Question(int id, String linkImg1, String linkImg2, String resultVI, String resultEN) {
        this.id = id;
        this.linkImg1 = linkImg1;
        this.linkImg2 = linkImg2;
        this.resultVI = resultVI;
        this.resultEN = resultEN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkImg1() {
        return linkImg1;
    }

    public void setLinkImg1(String linkImg1) {
        this.linkImg1 = linkImg1;
    }

    public String getLinkImg2() {
        return linkImg2;
    }

    public void setLinkImg2(String linkImg2) {
        this.linkImg2 = linkImg2;
    }

    public String getResultVI() {
        return resultVI;
    }

    public void setResultVI(String resultVI) {
        this.resultVI = resultVI;
    }

    public String getResultEN() {
        return resultEN;
    }

    public void setResultEN(String resultEN) {
        this.resultEN = resultEN;
    }
}
