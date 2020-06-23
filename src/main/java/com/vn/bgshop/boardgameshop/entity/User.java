package com.vn.bgshop.boardgameshop.entity;

import com.vn.bgshop.boardgameshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name",columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar",columnDefinition = "nvarchar(50)")
    private String avatar;

    @Column(name = "address",columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "is_del")
    private String status;

    @Transient
    private boolean isAdmin;

    @Transient
    private boolean isManager;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @OneToMany(mappedBy = "user")
    private Set<CartDetail> cartDetails;

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public User(String userName, String email, String password, String phone, String avatar, String address, String status) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.address = address;
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

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
