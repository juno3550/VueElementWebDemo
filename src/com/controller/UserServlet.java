package com.controller;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 封装User对象
        User user = new User(username, password);
        // 调用业务层的登录方法
        List<User> list = service.login(user);
        // 判断查询结果
        if (list.size()!=0) {
            // 将用户名存入会话域当中
            req.getSession().setAttribute("username", username);
            // 影响给客户端表示成功
            resp.getWriter().write("true");
        } else {
            // 登录失败
            resp.getWriter().write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
