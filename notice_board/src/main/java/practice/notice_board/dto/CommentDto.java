package practice.notice_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {

    private String comment;
    private String memberId;
    private int postId;

}
