package com.example.demo.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.utils.Encrypt;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Depeng Zhang and Yitian Guo
 * @since 2023-10-05
 */
@Data
@ApiModel(value="UserVO object", description="")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String addTime;

    private int icon;

    public static UserVO translateFromEntity(UserEntity userEntity) {
        UserVO userVO = BeanUtil.copyProperties(userEntity, UserVO.class);
        if (userEntity!=null) {
            userVO.name = userEntity.getName().getValue();
        }
        return userVO;
    }

    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name=" + name +
                ", addTime=" + addTime +
                "}";
    }
}
