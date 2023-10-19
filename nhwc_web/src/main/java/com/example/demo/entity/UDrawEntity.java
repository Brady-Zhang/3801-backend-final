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
 * @author Yitian Guo and Depeng Zhang
 * @since 2023-09-20
 */
@TableName("u_draw")
@ApiModel(value="UDrawEntity subject", description="")
public class UDrawEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer toId;

    private String answer;

    private String addTime;

    private String path;

    private String base64;
    private String tip;

    private String guess;
    private Integer gId;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    public String getPath() {
        return path;
    }

    public String getBase64() {
        return base64;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public String toString() {
        return "UDrawEntity{" +
                "id=" + id +
                ", uId=" + uId +
                ", toId=" + toId +
                ", answer=" + answer +
                ", addTime=" + addTime +
                ", path=" + path +
                ", guess=" + guess +
                ", baseImage=" + base64  + // Only show the first 30 characters for brevity
                "}";
    }
}
