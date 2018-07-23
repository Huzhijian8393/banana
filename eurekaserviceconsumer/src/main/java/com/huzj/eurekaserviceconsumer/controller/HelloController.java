package com.huzj.eurekaserviceconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author HuZJ
 */
@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        String result = restTemplate.getForEntity("http://eurekadiscovery/hello", String.class).getBody();
        logger.info("result :" + result);
        return "response form server :" + result;
    }
}
