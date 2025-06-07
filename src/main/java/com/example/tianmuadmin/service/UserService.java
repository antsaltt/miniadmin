package com.example.tianmuadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tianmuadmin.entity.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> updateUser(User user);
    Optional<User> getUserById(Long uid);
    boolean deleteUser(Long uid);
    IPage<User> getUserPage(Page<User> page, User filter);
}