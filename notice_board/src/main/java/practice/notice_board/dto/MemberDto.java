package practice.notice_board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto<T> {

    /**
     * 1 = ok
     * 2 = wrong password
     * 3 = not found
     */
    private int statusCode;
    private T data;

    public MemberDto() {
    }

    public MemberDto(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
