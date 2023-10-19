package com.example.demo.mapper;

import com.example.demo.entity.UDrawEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UDrawEntity2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper interface
 * </p>
 *
 * @author Depeng Zhang and Yitian Guo
 * @since 2023-09-28
 */
public interface UDrawMapper extends BaseMapper<UDrawEntity> {
//    @Select("select * FROM u_draw where to_id=#{toId} and g_id=#{gId} GROUP BY DATE_FORMAT(add_time,'%Y-%M-%D')")
//    public List<UDrawEntity> getOther(@Param("toId") int toId,@Param("gId") int gId);

    @Select("select * FROM u_draw where to_id=#{uId} and g_id=#{gId} and u_id=#{toId}")
    public List<UDrawEntity> getOther(@Param("toId") int toId,@Param("gId") int gId, @Param("uId") int uId);

    @Select("select * FROM u_draw a left join user b on a.to_id = b.id where a.u_id=#{uId} and a.g_id=#{gId} and a.answer=#{answer}")
    public List<UDrawEntity2> getGuessList(@Param("uId") int uId, @Param("gId") int gId, @Param("answer") String answer);

}
