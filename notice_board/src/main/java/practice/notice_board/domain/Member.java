package practice.notice_board.domain;


import lombok.Getter;

@Getter
public class Member {

    private String id;
    private String username;
    private String userId;
    private String password;
    private String tel;
    private String email;

    public Member() {
    }

    public Member(String id, String username, String userId,
                  String password, String tel, String email) {
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }
}
