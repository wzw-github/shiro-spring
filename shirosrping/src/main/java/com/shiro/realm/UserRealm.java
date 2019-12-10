package com.shiro.realm;

import com.shiro.entity.User;
import com.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权：为当前登录的用户授予权限及角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1、获取当前登录的用户信息
        String userName = (String) principals.getPrimaryPrincipal();
        //2、创建授权验证对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        try {
            //3、授予角色
            authorizationInfo.setRoles(userService.getRoles(userName));
            //4、授予权限
            authorizationInfo.setStringPermissions(userService.getPermissions(userName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5、返回授权信息
        return authorizationInfo;
    }

    /**
     * 验证：为当前登录的用户进行身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取当前用户信息
        String userName = (String) token.getPrincipal();
        try {
            //根据用户名查询用户信息
            User user = userService.findUserByName(userName);
            //如果结果不为空，表示这个用户是存在的
            if (user != null) {
                //验证用户名和密码
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "");
                //返回验证信息
                return authenticationInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证失败
        return null;
    }
}
