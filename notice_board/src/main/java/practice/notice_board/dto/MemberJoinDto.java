package practice.notice_board.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
    private String username;
    private String userId;
    private String password;
    private String tel;
    private String email;
    private String address;
}
