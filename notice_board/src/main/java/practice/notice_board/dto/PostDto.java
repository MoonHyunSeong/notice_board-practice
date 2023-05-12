package practice.notice_board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private LocalDateTime createDate;

    public PostDto() {
    }

    public PostDto(String title, String content, LocalDateTime createDate) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }
}
