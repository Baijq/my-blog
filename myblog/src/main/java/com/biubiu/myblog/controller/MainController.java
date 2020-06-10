package com.biubiu.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面入口MainController
 *
 * @author biubiu
 */
@Controller
public class MainController {

    @GetMapping(path = {"/", "/index"})
    public String toIndex() {
        return "fore/index";
    }
}
