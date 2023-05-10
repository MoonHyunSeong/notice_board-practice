package practice.notice_board.domain;

import lombok.Getter;

@Getter
public class Category {
    private int id;
    private String categoryName;

    public Category() {
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
