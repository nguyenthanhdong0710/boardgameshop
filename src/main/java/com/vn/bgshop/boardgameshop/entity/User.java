package com.vn.bgshop.boardgameshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_del")
    private boolean status;


    /*
    *** @ManyToMany và @JoinTable xác định các thành phần tham gia vào liên kết Many to Many của 2 bảng User và Role.
        *** name: Tên của table trung gian
        *** joinColumns: Tên khóa chính của bảng này mà sẽ là khóa ngoại của bảng trung gian
        *** inverseJoinColumns: Tên khóa chính của bảng còn lại mà sẽ là khóa ngoại của bảng trung gian
    */

    /*This happens because you have a collection in your entity, and that collection has one or more items which are
    not present in the database. By specifying the above options you tell hibernate to save them to the database when
    saving their parent.*/
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles;

    public User() {
    }

    public User(String userName, String email, String password, String phone, String avatar, boolean status) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
