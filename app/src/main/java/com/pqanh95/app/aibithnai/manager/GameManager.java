package com.pqanh95.app.aibithnai.manager;

import com.pqanh95.app.aibithnai.App;
import com.pqanh95.app.aibithnai.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Question> questions;
    private int level;

    public GameManager() {
        level = 0;
        questions = new ArrayList<>();
        questions.add(new Question(
                1,
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36720970_230354674447727_6517472232975892480_n.jpg?_nc_cat=0&oh=f60132da7f86b1006f05fd3482f37c62&oe=5BE978EF",
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36675862_230354657781062_1116111693554909184_n.jpg?_nc_cat=0&oh=568403e0fe7a9573e86c0d54dd9ab607&oe=5BDF861F",
                "THÔNG BÁO",
                "THONGBAO")
        );
        questions.add(new Question(
                2,
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36725195_230354694447725_2087243463203487744_n.jpg?_nc_cat=0&oh=c9526e60e6f1dd877802a6f3f1bf26e9&oe=5BEC0598",
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36776547_230354707781057_9012194915886563328_n.jpg?_nc_cat=0&oh=105148d61c04d67bb81c08c8b33eeaf3&oe=5B9FDF5E",
                "TIỀN ĐỒ",
                "TIENDO")
        );
        questions.add(new Question(
                3,
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36725195_230354694447725_2087243463203487744_n.jpg?_nc_cat=0&oh=c9526e60e6f1dd877802a6f3f1bf26e9&oe=5BEC0598",
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-9/36675862_230354657781062_1116111693554909184_n.jpg?_nc_cat=0&oh=568403e0fe7a9573e86c0d54dd9ab607&oe=5BDF861F",
                "TIỀN ĐỒ",
                "TIENDO")
        );
    }

    // trả về đáp án EN
    public String getAnswer() {
        return questions.get(level).getResultEN();
    }

    // trả về đáp án VI
    public String getAnswerVI() {
        return questions.get(level).getResultVI();
    }


    // trả về độ dài đáp án
    public int getLengthAnswer() {
        return questions.get(level).getResultEN().length();
    }

    // trả về đường linkimg1
    public String getLink1() {
        return questions.get(level).getLinkImg1();
    }

    // trả về đường linkimg2
    public String getLink2() {
        return questions.get(level).getLinkImg2();
    }

    // trả về đáp án tiếng việt 1
    public String getAnswer1() {
        return questions.get(level).getResultVI().substring(0, questions.get(level).getResultVI().indexOf(" "));
    }

    // trả về đáp án tiếng việt 1
    public String getAnswer2() {
        return questions.get(level).getResultVI().substring(questions.get(level).getResultVI().indexOf(" "));
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
