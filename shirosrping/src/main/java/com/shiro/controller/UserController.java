package com.shiro.controller;

import com.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(User user){
        //1 获取subject主体
        Subject subject= SecurityUtils.getSubject();
        //2 创建令牌
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getPassword());
        Session session=subject.getSession();
        try {
            //3 登录
            subject.login(token);
            //4 成功则保存在session,保存当前登录用户
            session.setAttribute("currentUser",user);
            // 返回逻辑视图名
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            //4 失败则返回错误信息，保存在session
            session.setAttribute("errorMsg","用户名密码错误");
            return "login";
        }

    }
}
