package com.springboot.domain;

import javax.persistence.*;

/**
 * Persistent class that would be saved to the database
 */
@Entity
@Table(name = "wishes")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishID")
    private int wishID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userID")
    public User user;

    @Column(name = "wishName")
    private String wishName;
    @Column(name = "link")
    private String link;

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return wishID + " " + wishName + " " + link;
    }
}