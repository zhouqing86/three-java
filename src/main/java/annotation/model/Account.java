package annotation.model;

import annotation.annotations.*;

@Table(name="Accounts")
public class Account {

    @TableId
    private String accountid;

    @CheckPhoneNumber
    private String phoneNumber;

    @CheckName
    private String name;

    @CheckAddress
    private String address;

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
