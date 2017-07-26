package com.springboot.interfaces;

import com.springboot.domain.User;

public interface PageNumberCounter {

    public long countForAllUsersPage();
    public long countForMainPage();
}
