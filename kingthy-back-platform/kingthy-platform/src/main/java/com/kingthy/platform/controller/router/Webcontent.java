package com.kingthy.platform.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name Webcontent
 * @description 路由控制
 * @create 2017/7/7
 */
@Controller
@RequestMapping(value = "/index")
public class Webcontent
{
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String start()
    {
        return "index";
    }
}
