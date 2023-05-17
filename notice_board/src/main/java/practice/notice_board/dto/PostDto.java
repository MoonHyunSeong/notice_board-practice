package practice.notice_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDto {

    private String title;
    private String content;
    private String categoryName;

    public PostDto() {
    }

    public PostDto(String categoryName, String title, String content) {
        this.categoryName = categoryName;
        this.title = title;
        this.content = content;
    }
}
