package com.huzj.eurekaserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuZJ
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "hello spring boot";
    }
}
