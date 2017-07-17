package com.springboot.interfaces;

import com.springboot.domain.Wishes;
import java.util.List;

public interface ManageWish {
    public Wishes findbyWishername(String wName);
    public Integer addWish(Wishes wish);
    public void deleteWish(String wishName);
    public void deleteWish(int wishID);
    public List<Wishes> listWishes();
}
