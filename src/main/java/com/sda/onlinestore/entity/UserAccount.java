package com.sda.onlinestore.entity;

import com.sda.onlinestore.enums.PreferredChannel;

import javax.persistence.*;


@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private PreferredChannel preferredChannel;

    @OneToOne(mappedBy = "userAccount")
    private Order order;

    public UserAccount() {
    }

    public UserAccount(String email, String password, Address address, Role role, String thumbnail, PreferredChannel preferredChannel, String username) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.thumbnail = thumbnail;
        this.preferredChannel = preferredChannel;
        this.username = username;
    }

    public Long getId() {
        return id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public PreferredChannel getPreferredChannel() {
        return preferredChannel;
    }

    public void setPreferredChannel(PreferredChannel preferredChannel) {
        this.preferredChannel = preferredChannel;
    }

    public Order getOrder(){
        return order;
    }

    public void setOrder (Order order){
        this.order = order;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }
}