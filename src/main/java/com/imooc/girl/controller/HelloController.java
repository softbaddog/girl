package com.imooc.girl.controller;

import com.imooc.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Value("${message}")
    private String message;

    @Autowired
    private GirlProperties girlProperties;

//    @RequestMapping(value = "/say/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/say")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id: " + myId;
    }

    @PostMapping(value = "/good/{id}")
    public String good(@PathVariable("id") Integer myId) {
        return "id: " + myId;
    }
}
