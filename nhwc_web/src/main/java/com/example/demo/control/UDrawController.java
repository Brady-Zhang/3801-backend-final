package com.example.demo.control;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.UDrawMapper;
import com.example.demo.service.UDrawService;
import com.example.demo.service.UGroupService;
import com.example.demo.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/u_draw")
public class UDrawController {
    public static class AddRequestDTO {
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;
        private String base64;
        private Integer gId;
        private Integer toId;
        private Integer uId;
        private String name;
        private String tip;

        public String getBase64() {
            return base64;
        }

        public void setBase64(String base64) {
            this.base64 = base64;
        }

        public Integer getgId() {
            return gId;
        }

        public void setgId(Integer gId) {
            this.gId = gId;
        }

        public int getToId() {
            return toId;
        }

        public void setToId(int toId) {
            this.toId = toId;
        }

        public Integer getuId() {
            return uId;
        }

        public void setuId(Integer uId) {
            this.uId = uId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
    @Autowired
    private UDrawService service;
    @Resource
    private UDrawMapper mapper;

@PostMapping("/add")
@ResponseBody
public Object updateIcon(@RequestBody AddRequestDTO request) {
    Map<String, Object> result = new HashMap<>(); // Define the result

    try {
        UDrawEntity u = new UDrawEntity();
        u.setgId(request.getgId());
        u.setAddTime(ApiUtils.getCurrentTime());
        u.setAnswer(request.getName());
        u.setuId(request.getuId());
        u.setToId(request.getToId());
        u.setTip(request.getTip());
        u.setBase64(request.getBase64());  // Set the baseImage
        service.save(u);
        System.out.println("Uploaded successfully");
        result.put("code", 0);
        result.put("msg", "Sharing Success");
        result.put("data", u);

        return result;
    } catch (Exception e) {
        System.out.println("Upload Failed");
        result.put("code", 1);
        result.put("msg", "Share Failure");
        e.printStackTrace();
        return result;
    }
}


    @PostMapping("/save")
    @ResponseBody
    public Map<String,Object> save(UDrawEntity entity){
        entity.setAddTime(ApiUtils.getCurrentTime());
        boolean re = service.save(entity);
        Map<String,Object> map = new HashMap<>();
        if (re){
            map.put("code",0);
            map.put("msg","Save Successful");
            map.put("data",null);
        }else {
            map.put("code",0);
            map.put("msg","fail to save");
            map.put("data",null);
        }
        return map;
    }
    @GetMapping("/getOwnsDraw")
    @ResponseBody
    public Map<String,Object> getOwnsDraw(int uId,int gId){
        QueryWrapper<UDrawEntity> q = new QueryWrapper<>();
        q.eq("u_id",uId);
        q.eq("g_id",gId);
        q.groupBy("answer");
        List<UDrawEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Getting Successful");
        map.put("data",al);
        return map;
    }

    @GetMapping ("/getOtherDraw")
    @ResponseBody
    public Map<String,Object> getOtherDraw(int toId,int gId,int uId){
        List<UDrawEntity> al = mapper.getOther(toId,gId,uId);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Getting Successful");
        map.put("data",al);
        return map;
    }

    @GetMapping ("/getGuessList")
    @ResponseBody
    public Map<String,Object> getGuessList(int uId,int gId,String answer){
        List<UDrawEntity2> al = mapper.getGuessList(uId, gId, answer);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Getting Successful");
        map.put("data",al);
        return map;
    }

    @GetMapping ("/getOtherDrawInfo")
    @ResponseBody
    public Map<String,Object> getOtherDrawInfo(int uId,int gId,String time){
        QueryWrapper<UDrawEntity> q = new QueryWrapper<>();
        q.eq("to_id",uId);
        q.eq("g_id",gId);
        q.like("add_time",time);
        List<UDrawEntity> al = service.list(q);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","Getting Successful");
        map.put("data",al);
        return map;
    }

    @PostMapping("/guess")
    @ResponseBody
    public Map<String,Object> guess(@RequestParam int id,@RequestParam String guess){
        UDrawEntity entity = new UDrawEntity();
        entity.setId(id);
        entity.setGuess(guess);
        boolean re = service.updateById(entity);
        Map<String,Object> map = new HashMap<>();
        if (re){
            map.put("code",0);
            map.put("msg","successful answer");
            map.put("data",null);
        }else {
            map.put("code",1);
            map.put("msg","successful answer");
            map.put("data",null);
        }
        return map;
    }

}
