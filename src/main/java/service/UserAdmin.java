package service;

import java.util.LinkedList;
import java.util.List;

public class UserAdmin {
    private List<String> userList = new LinkedList<>();

    public String addUser(String userName) {
        userList.add(userName);
        return  userName;
    }
}
