package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.utils.Encrypt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper interface
 * </p>
 *
 * @author Depeng Zhang and Yitian Guo
 * @since 2023-09-28
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    @Insert("insert into user (name,icon,add_time) values (#{name},#{icon},now())")
    boolean save(@Param("name") Encrypt name, @Param("icon") int icon);
    @Select("select * from user where name=#{name}")
    UserEntity getOne(@Param("name") Encrypt name);
}
