package com.newSpring.services.impl;

import com.newSpring.dao.UserDao;
import com.newSpring.domain.User;
import com.newSpring.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly=false, rollbackFor=RuntimeException.class)
public class UserService implements IUserServices {
    //@Autowired(required=true) // byType
    //@Qualifier("userDao")// byName
    @Autowired(required=true)
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getUser() {
        return userDao.getUser();
    }
}
