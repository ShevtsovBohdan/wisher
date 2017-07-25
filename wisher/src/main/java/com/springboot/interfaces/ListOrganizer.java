package com.springboot.interfaces;


import com.springboot.domain.User;
import com.springboot.domain.Wish;

import java.util.List;

public interface ListOrganizer {
    public List<User> shape(List<Wish> wishList);
}
