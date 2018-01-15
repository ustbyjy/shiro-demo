package com.yjy.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class FirstRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(FirstRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }

        Object credentials = null;
        if ("admin".equals(username)) {
            credentials = "9aa75c4d70930277f59d117ce19188b0";
        } else if ("user".equals(username)) {
            credentials = "dd957e81b004227af3e0aa4bde869b25";
        }

        Object principal = username;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        String realmName = getName();
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
//        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        ByteSource credentialsSalt = ByteSource.Util.bytes("user");
        int hashIterations = 3;

        Object credentials = "123456";

        Object result = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        logger.info(result.toString());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo");
        Object principal = principals.getPrimaryPrincipal();

        Set<String> roles = new HashSet<String>();
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        return info;
    }
}
