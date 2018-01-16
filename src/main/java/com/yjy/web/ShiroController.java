package com.yjy.web;

import com.yjy.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/shiro")
public class ShiroController {
    private static Logger logger = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/testShiroAnnotation")
    public String testShiroAnnotation(HttpSession session) {
        session.setAttribute("key", "123456");
        shiroService.testMethod();

        return "redirect:/list.jsp";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                logger.error("登录失败", e);
            }
        }

        return "redirect:/list.jsp";
    }
}
