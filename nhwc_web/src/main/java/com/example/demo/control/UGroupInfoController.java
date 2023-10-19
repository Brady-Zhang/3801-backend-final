package com.example.demo.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.UGroupEntity;
import com.example.demo.entity.UGroupInfoEntity;
import com.example.demo.entity.UserVO;
import com.example.demo.service.UDrawService;
import com.example.demo.service.UGroupInfoService;
import com.example.demo.service.UGroupService;
import com.example.demo.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Front controller
 * </p>
 *
 * @author Yitian Guo and Depeng Zhang
 * @since 2023-09-25
 */
@RestController
@RequestMapping("/u_group_info")
public class UGroupInfoController {
    @Autowired
    private UGroupInfoService service;
    @Autowired
    private UGroupService service1;

    @Autowired
    private UDrawService service3;
    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> login(@RequestBody UGroupInfoEntity entity){
        entity.setAddTime(ApiUtils.getCurrentTime());
        QueryWrapper<UGroupInfoEntity> q = new QueryWrapper<>();
        q.eq("g_id",entity.getgId());
        q.eq("u_id",entity.getuId());
        UGroupInfoEntity u = service.getOne(q);
        Map<String,Object> map = new HashMap<>();
        if (u==null){
            UGroupEntity groupBean = service1.getById(entity.getgId());
            entity.setgName(groupBean.getName());
            boolean re = service.save(entity);
            if (re){
                map.put("code",0);
                map.put("msg","Joined successfully");
                map.put("data",entity);
            }else {
                map.put("code",1);
                map.put("msg","Failed to join");
                map.put("data",null);
            }
            return map;
        }
        map.put("code",1);
        map.put("msg","Failed to join, already in the group!");
        map.put("data",u);
        return map;
    }


    @GetMapping ("/getList")
    @ResponseBody
    public Map<String,Object> getList(int uId){
        QueryWrapper<UGroupInfoEntity> q = new QueryWrapper<>();
        q.eq("u_id",uId);
        List<UGroupInfoEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Retrieved successfully");
        map.put("data",al);
        return map;
    }

    @GetMapping ("/group_info")
    @ResponseBody
    public Map<String,Object> groupInfo(int gId){
        QueryWrapper<UGroupInfoEntity> q = new QueryWrapper<>();
        q.eq("g_id",gId);
        List<UGroupInfoEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Retrieved successfully");
        map.put("data",al);
        return map;
    }

    @GetMapping ("/group_info_without")
    @ResponseBody
    public Map<String,Object> groupInfo( int gId, int uId){
        QueryWrapper<UGroupInfoEntity> q = new QueryWrapper<>();
        q.eq("g_id",gId);
        q.notIn("u_id",uId);
        List<UGroupInfoEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Retrieved successfully");
        map.put("data",al);
        return map;
    }

    @PostMapping("/change_nick")
    @ResponseBody
    public Map<String,Object> changeNick( int id, String nick){
        UGroupInfoEntity e = new UGroupInfoEntity();
        e.setId(id);
        e.setuNickName(nick);
        boolean re = service.updateById(e);
        Map<String,Object> map = new HashMap<>();
        if (re){
            map.put("code",0);
            map.put("msg","Modified successfully");
            map.put("data",null);
            return map;
        }
        map.put("code",1);
        map.put("msg","Failed to modify");
        map.put("data",null);
        return map;
    }
}
