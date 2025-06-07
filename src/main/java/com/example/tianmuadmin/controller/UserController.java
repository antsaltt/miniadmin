package com.example.tianmuadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tianmuadmin.entity.User;
import com.example.tianmuadmin.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Map<String, Object> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return response(200, created, "ok");
    }

    @PutMapping
    public Map<String, Object> updateUser(@RequestBody User user) {
        return userService.updateUser(user)
                .map(updated -> response(200, updated, "ok"))
                .orElseGet(() -> response(404, null, "User not found"));
    }

    @GetMapping("/{uid}")
    public Map<String, Object> getUserById(@PathVariable Long uid) {
        return userService.getUserById(uid)
                .map(user -> response(200, user, "ok"))
                .orElseGet(() -> response(404, null, "User not found"));
    }

    @GetMapping("/page")
    public Map<String, Object> getUserPage(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) Integer role) {

        User filter = new User();
        filter.setPhone(phone);
        filter.setNickname(nickname);
        filter.setState(state);
        filter.setRole(role);
        IPage<User> page = userService.getUserPage(new Page<>(current, size), filter);

        Map<String, Object> data = new HashMap<>();
        data.put("records", page.getRecords());
        data.put("total", page.getTotal());
        data.put("size", page.getSize());
        data.put("current", page.getCurrent());
        data.put("pages", page.getPages());
        return response(200, data, "ok");
    }

    @DeleteMapping("/{uid}")
    public Map<String, Object> deleteUser(@PathVariable Long uid) {
        boolean deleted = userService.deleteUser(uid);
        return deleted ? response(200, null, "ok") : response(404, null, "User not found");
    }

    private Map<String, Object> response(int code, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("data", data);
        map.put("message", message);
        return map;
    }
}