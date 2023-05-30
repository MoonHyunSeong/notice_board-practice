package practice.notice_board.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Post {

    private int id;
    private String title;
    private String content;
    private int likes;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String memberId;
    private int categoryId;


    public Post() {
    }

    public Post(String title, String content, int likes,
                LocalDateTime createDate, LocalDateTime updateDate,
                String memberId, int categoryId) {
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.memberId = memberId;
        this.categoryId = categoryId;
    }

    public Post(int id, String title, String content, int likes,
                LocalDateTime createDate, LocalDateTime updateDate,
                String memberId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.memberId = memberId;
        this.categoryId = categoryId;
    }
}

