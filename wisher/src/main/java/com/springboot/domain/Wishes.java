package com.springboot.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import javax.persistence.*;



@Entity
@Table(name = "wishes")
public class Wishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishID")
    private int wishID;


    @ManyToOne(optional = false)
    @JoinColumn(name = "userID")
//    @JsonIgnore
    private User user;

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

    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (wishID ^ (wishID >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Wishes))
            return false;
        Wishes other = (Wishes) obj;
        if (wishID != other.wishID)
            return false;
        return true;
    }
*/
    @Override
    public String toString() {
        return wishID + " " + wishName + " " + link;
    }
}
