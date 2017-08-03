package com.springboot.interfaces;

import com.springboot.domain.User;
import com.springboot.domain.Wish;

import java.math.BigInteger;
import java.util.List;

/**
 * ManageWish is a base interface class for database connection and working with Wish object
 */
public interface ManageWish {
    Integer addWish(String wishName, String wishLink, User activeUser);

    void deleteWish(int wishID);

    List<Wish> listWishes();

    List<Wish> listWishes(int setStartValue, int userID);

    List<Wish> listAllUsersWishes(int startValue, int maxValue);

    long numberOfRows(int userID);

    long numberOfRows();

    List<Wish> search(int userID, String searchStr);

    List<Wish> searchAllWishes(String searchStr);

    void saveWishLocalLink(int wishID, String wishLocalLink);

    String getWishLocalLink(int wishID);

    void saveOriginalImageWish(Integer wishID, String originalName);
}
