package com.springboot.components

import com.springboot.domain.User
import com.springboot.domain.Wish
import com.springboot.interfaces.ListOrganizer
import spock.lang.*
import sun.security.provider.SHA

import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;


class ListOrganizerSpec extends Specification{
    @Shared
    User user = new User()
    @Shared
    ListOrganizerImpl listOrganizer = new ListOrganizerImpl()
    @Shared
    List<Wish> list = new LinkedList<>()

    def "testing User List by giving 1 element size Wish List"() {
        given:
        Wish wish = new Wish(wishID : 1, user : user, wishName: 'wdw', link : 'sdwdwd')
        list.add(wish)
        expect:
        listOrganizer.shape(list).size() == 1
    }
    def "testing User List by giving 2 elements size Wish List with the same Users"() {
        given:
        Wish wish1 = new Wish(wishID : 1, user : user, wishName: 'wdw', link : 'sdwdwd')
        Wish wish2 = new Wish(wishID : 2, user : user, wishName: 'wdw', link : 'sdwdwd')
        list.add(wish1)
        list.add(wish2)
        expect:
        listOrganizer.shape(list).size() == 1
    }
    def "testing User List by giving 2 elements size Wish List"() {
        given:

        User user1 = new User()
        user1.setUsername("wefwe")
        Wish wish1 = new Wish(wishID : 1, user : user, wishName: 'wdw', link : 'sdwdwd')
        Wish wish2 = new Wish(wishID : 2, user : user1, wishName: 'wdw', link : 'sdwdwd')
        list.add(wish1)
        list.add(wish2)
        expect:
        listOrganizer.shape(list).size() == 2
    }


}