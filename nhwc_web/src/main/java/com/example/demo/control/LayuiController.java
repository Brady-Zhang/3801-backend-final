package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LayuiController {
    @Autowired
    HttpServletRequest httpServletRequest;
    @RequestMapping("/share")
    public String share(){
        return "share";
    }
}
