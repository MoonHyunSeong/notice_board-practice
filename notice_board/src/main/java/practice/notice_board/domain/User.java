package practice.notice_board.domain;


import lombok.Getter;

@Getter
public class User {

    private String id;
    private String username;
    private String userId;
    private String password;
    private String tel;
    private String email;
    private String address;

    public User() {
    }

    public User(String id, String username, String userId, String password, String tel, String email, String address) {
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }
}
