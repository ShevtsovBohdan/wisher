package com.springboot.domain;

import javax.persistence.*;

/**
 * Java POJO class that will be persisted to the database
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

    @Column(name = "wishLocalLink")
    private String wishLocalLink;

    public String getWishLocalLink() {
        return wishLocalLink;
    }

    public void setWishLocalLink(String wishLocalLink) {
        this.wishLocalLink = wishLocalLink;
    }

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
