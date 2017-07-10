package com.springboot.Controller;

import com.springboot.dao.ManageUser;
import com.springboot.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public ResponseEntity<User> getMainPage(){
        User user = new ManageUser().findbyUsername("gg");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
