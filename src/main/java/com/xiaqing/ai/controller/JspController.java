package com.xiaqing.ai.controller;

/**
 * @author Yezhike
 * @Description TODO
 * @Date 2024/4/19 13:44
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Boot中支持jsp功能的实现
 */
@Controller
public class JspController {

    @GetMapping("/index1")
    public String index(Model model) {
        System.out.println("jinlaile");
        model.addAttribute("msg","跟一一哥学习SpringBoot中使用JSP功能!");
        //要跳转到的页面视图名称
        return "/WEB-INF/index.jsp";
    }

}

