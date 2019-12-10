package com.shiro.service;

import com.shiro.dao.UserMapper;
import com.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper uMapper;

    @Override
    public User findUserByName(String userName) throws Exception {
        return uMapper.findUserByName(userName);
    }

    @Override
    public Set<String> getRoles(String userName) throws Exception {
        return uMapper.getRoles(userName);
    }

    @Override
    public Set<String> getPermissions(String userName) throws Exception {
        return uMapper.getPermissions(userName);
    }
}
