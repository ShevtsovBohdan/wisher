package com.springboot.interfaces;

import com.springboot.domain.User;

public interface PageNumberCounter {
    public User getActiveUser();
    public long countForAllUsersPage();
    public long countForMainPage();
}
