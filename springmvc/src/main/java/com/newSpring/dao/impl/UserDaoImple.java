package com.newSpring.dao.impl;

import com.core.dao.impl.HibernateDaoImpl;
import com.newSpring.dao.UserDao;
import com.newSpring.domain.User;

import java.util.List;

/**
 * Created by 45595 on 2016/8/31.
 */
public class UserDaoImple extends HibernateDaoImpl implements UserDao {
    @Override
    public List<User> getUser() {

        StringBuilder hql = new StringBuilder();
        hql.append("select u from User as u where 1=1 ");
        return this.find(hql.toString());
    }
}
