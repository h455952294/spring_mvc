package com.newSpring.controller;

import com.newSpring.domain.User;
import com.newSpring.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by admin on 2016/8/14.
 */
@Controller
@RequestMapping("sysUser")
public class Test {

    @Autowired(required=true) @Qualifier("userService")
    private IUserServices userServices;

        @RequestMapping("userlistM")
    public ModelAndView userlistM() {
        List<User> user = userServices.getUser();
        if(user !=null && user.size() > 0){
            System.out.println(user.size() > 0);
        }
        ModelAndView modelAndView = new ModelAndView("userListM");
        modelAndView.addObject("userList", user);
        //modelAndView.getModelMap().put("userList", user);
        return modelAndView;
    }

    @RequestMapping("userList")
    public String test() {
        List<User> user = userServices.getUser();
        if(user !=null && user.size() > 0){
            System.out.println(user.size() > 0);
        }
        return "userList";
    }
}
