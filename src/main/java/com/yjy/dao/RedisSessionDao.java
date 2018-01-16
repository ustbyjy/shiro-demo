package com.yjy.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.opsForValue().set(sessionId.toString(), session);

        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(sessionId.toString());
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisTemplate.opsForValue().set(session.getId().toString(), session);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisTemplate.delete(session.getId().toString());
    }
}
