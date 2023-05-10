package practice.notice_board.domain;

import lombok.Getter;

@Getter
public class Comment {
    private int id;
    private String comment;
    private String memberId;
    private int postId;

    public Comment() {
    }

    public Comment(int id, String comment, String memberId, int postId) {
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.postId = postId;
    }
}
