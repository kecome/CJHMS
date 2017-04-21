package com.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuke
 * @create 2017-04-14 下午 18:11
 **/
@RestController
@RequestMapping(value = "/file",produces="application/json;charset=UTF-8")
public class AttachmentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @RequestMapping()
//    public Object upload() {
//
//    }
}
