package com.example.tianmuadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO) // 主键自增
    private Long uid;

    private String phone;
    private String password;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String description;
    private Integer coin;
    private Integer state;
    private Integer role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer isDelete;
    private Integer fans;
    private Integer follow;
    private Integer videoCount;
}
