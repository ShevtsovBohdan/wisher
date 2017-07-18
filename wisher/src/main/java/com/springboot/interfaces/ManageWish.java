package com.springboot.interfaces;

import com.springboot.domain.User;
import com.springboot.domain.Wish;

import java.util.List;

/**
 * ManageWish is a base interface class for database connection and working with Wish object
 */
public interface ManageWish {
    public Integer addWish(String wishName, String wishLink, User activeUser);

    public void deleteWish(int wishID);

    public List<Wish> listWishes();
}
