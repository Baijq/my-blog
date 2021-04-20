package com.biubiu.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * nacos
 *
 * @author wbbaijq
 */
@Controller
@RequestMapping("config")
//@NacosPropertySource(dataId = "test001", autoRefreshed = true)
public class NacosConfigController {

//    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return test;
    }
}
