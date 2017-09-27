package service;

public class AppTest {
    public static void main(String[] args) {
        UserService userService = new UserService(new UserAdmin());
        userService.addUser("hahaha");
    }
}
