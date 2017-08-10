package com.springboot.components.interfaces;


import com.springboot.domain.User;
import com.springboot.domain.Wish;

import java.util.List;

public interface ListOrganizer {
    List<User> shape(List<Wish> wishList);
}
