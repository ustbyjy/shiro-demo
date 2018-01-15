package com.yjy.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShiroService {
    private static Logger logger = LoggerFactory.getLogger(ShiroService.class);

    @RequiresRoles("admin")
    public void testMethod() {
        logger.info("testMethod，time={}", new Date());
        Session session = SecurityUtils.getSubject().getSession();
        Object value = session.getAttribute("key");
        logger.info("service session value={}", value);
    }
}
