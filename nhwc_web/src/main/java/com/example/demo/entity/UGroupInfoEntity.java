package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Depeng Zhang and Yitian Guo
 * @since 2023-09-20
 */
@TableName("u_group_info")
@ApiModel(value="UGroupInfoEntity object", description="")
public class UGroupInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer gId;

    private Integer uId;

    private String uName;
    private String gName;

    private String uNickName;

    private String addTime;

    private Integer uIcon;

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    public Integer getuIcon() {
        return uIcon;
    }

    public void setuIcon(Integer uIcon) {
        this.uIcon = uIcon;
    }

    @Override
    public String toString() {
        return "UGroupInfoEntity{" +
            "id=" + id +
            ", gId=" + gId +
            ", uId=" + uId +
            ", uName=" + uName +
            ", uNickName=" + uNickName +
            ", addTime=" + addTime +
            ", uIcon=" + uIcon +
        "}";
    }
}
