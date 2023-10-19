package com.example.demo.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserVO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  Front controller
 * </p>
 *
 * @author Yitian Guo and Depeng Zhang
 * @since 2023-09-28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody UserEntity user){
        user.setAddTime(ApiUtils.getCurrentTime());
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("name",user.getName());
        UserEntity u = userMapper.getOne(user.getName());
        Map<String,Object> map = new HashMap<>();
        if (u==null){
            log.info("new user login");
            boolean re = userMapper.save(user.getName(),user.getIcon());
            if (re){
                user.setAddTime(String.valueOf(LocalDateTime.now()));
                log.info("new user login successfully");
                map.put("code",0);
                map.put("msg","registered successfully");
                map.put("data", UserVO.translateFromEntity(user));
                log.info(UserVO.translateFromEntity(user).toString());
            }else {
                log.info("new user login fail");
                map.put("code",1);
                map.put("msg","fail to register");
                map.put("data",null);
            }
            return map;
        }
        map.put("code",0);
        map.put("msg","Validation successful");
        map.put("data",UserVO.translateFromEntity(u));
        return map;
    }

    @GetMapping  ("/getInfoByName")
    @ResponseBody
    public Map<String,Object> getInfoByName(@RequestParam("name") Encrypt name){
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("name",name);
        log.info(name.getValue());
        UserEntity u = userMapper.getOne(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Retrieved successfully");
        map.put("data", UserVO.translateFromEntity(u));
        return map;
    }
}
