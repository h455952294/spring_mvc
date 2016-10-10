package com.newSpring.services;

import com.newSpring.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 45595 on 2016/8/31.
 */
@Service
public interface IUserServices {

    List<User> getUser();
}
