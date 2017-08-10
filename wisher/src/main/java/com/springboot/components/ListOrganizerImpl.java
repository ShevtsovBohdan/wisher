package com.springboot.components;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.components.interfaces.ListOrganizer;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class is used for organizing List of Users using Wish List from the database.
 */
@Component
public class ListOrganizerImpl implements ListOrganizer{

    /**
     * Returns List of users according specified Wish List.
     *
     * @param wishList gets List of wishes from the database
     * @return List of Users
     */
    @Override
    public List<User> shape(List<Wish> wishList) {
        List<User> userList = new LinkedList<>();

        Iterator<Wish> iterator = wishList.iterator();

        while (iterator.hasNext()) {
            Wish wish = iterator.next();
            if (!userList.contains(wish.getUser())) {
                userList.add(wish.getUser());
            }
        }
        return userList;
    }
}
