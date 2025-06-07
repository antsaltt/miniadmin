package com.example.tianmuadmin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tianmuadmin.entity.User;
import com.example.tianmuadmin.service.UserService;
import com.example.tianmuadmin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setCoin(0);
        user.setIsDelete(0);
        userMapper.insert(user);
        return user;
    }

    @Override
    public Optional<User> updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        int updated = userMapper.updateById(user);
        return updated > 0 ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long uid) {
        return Optional.ofNullable(userMapper.selectById(uid));
    }

    @Override
    public boolean deleteUser(Long uid) {
        return userMapper.deleteById(uid) > 0;
    }

    @Override
    public IPage<User> getUserPage(Page<User> page, User filter) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (filter.getPhone() != null) wrapper.like("phone", filter.getPhone());
        if (filter.getNickname() != null) wrapper.like("nickname", filter.getNickname());
        if (filter.getState() != null) wrapper.eq("state", filter.getState());
        if (filter.getRole() != null) wrapper.eq("role", filter.getRole());
        return userMapper.selectPage(page, wrapper);
    }
}
