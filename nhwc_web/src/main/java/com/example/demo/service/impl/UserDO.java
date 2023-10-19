package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Service Implementation Class
 * </p>
 *
 * @author Depeng Zhang and Yitian Guo
 * @since 2023-09-28
 */
@Service
public class UserDO extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
