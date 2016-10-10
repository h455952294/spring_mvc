package com.newSpring.dao;

import com.core.dao.HibernateDao;
import com.newSpring.domain.User;

import java.util.List;

/**
 * Created by 45595 on 2016/8/31.
 */
public interface UserDao extends HibernateDao {
    List<User> getUser();
}
