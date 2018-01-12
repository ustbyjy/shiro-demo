package com.yjy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017-01-02
 * Time: 15:41
 */
@Controller
public class IndexController {

    @GetMapping(value = {"", "/"})
    public String home() {
        return "index";
    }

}
