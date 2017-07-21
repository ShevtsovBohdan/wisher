package com.springboot.interfaces;

import com.springboot.domain.User;
import com.springboot.domain.Wish;

import java.math.BigInteger;
import java.util.List;

/**
 * ManageWish is a base interface class for database connection and working with Wish object
 */
public interface ManageWish {
    public Integer addWish(String wishName, String wishLink, User activeUser);

    public void deleteWish(int wishID);

    public List<Wish> listWishes();

    public List<Wish> listWishes(int setStartValue, int userID);

    public List<Wish> listAllUsersWishes(int startValue, int maxValue);

    public long numberOfRows(int userID);

    public long numberOfRows();

    public List<Wish> search(int userID, String searchStr);

    public List<Wish> searchAllWishes(String searchStr);
}
