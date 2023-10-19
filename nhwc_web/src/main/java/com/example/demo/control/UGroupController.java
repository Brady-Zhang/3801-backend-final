package com.example.demo.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.UGroupEntity;
import com.example.demo.entity.UGroupInfoEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UGroupInfoService;
import com.example.demo.service.UGroupService;
import com.example.demo.service.UserService;
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
@RequestMapping("/u_group")
public class UGroupController {
    @Autowired
    private UGroupService service;
    @Autowired
    private UGroupInfoService service2;
    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> login(UGroupEntity entity, int uIcon){
        entity.setAddTime(ApiUtils.getCurrentTime());
        QueryWrapper<UGroupEntity> q = new QueryWrapper<>();
        q.eq("name",entity.getName());
        q.eq("u_id",entity.getuId());
        UGroupEntity u = service.getOne(q);
        Map<String,Object> map = new HashMap<>();
        if (u==null){
            boolean re = service.save(entity);
            if (re){
                UGroupInfoEntity entity1 = new UGroupInfoEntity();
                entity1.setgName(entity.getName());
                entity1.setuId(entity.getuId());
                entity1.setgId(entity.getId());
                entity1.setAddTime(ApiUtils.getCurrentTime());
                entity1.setuName(entity.getuName());
                entity1.setuIcon(uIcon);
                service2.save(entity1);
                map.put("code",0);
                map.put("msg","Created Successfully");
                map.put("data",entity);
            }else {
                map.put("code",1);
                map.put("msg","Creation Failure");
                map.put("data",null);
            }
            return map;
        }
        map.put("code",1);
        map.put("msg","Creation failed, the group already exists!");
        map.put("data",u);
        return map;
    }

    @GetMapping ("/getList")
    @ResponseBody
    public Map<String,Object> getList(int uId){
        QueryWrapper<UGroupEntity> q = new QueryWrapper<>();
        q.eq("u_id",uId);
        List<UGroupEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Getting Successful");
        map.put("data",al);
        return map;
    }
}
