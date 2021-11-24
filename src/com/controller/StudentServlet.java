package com.controller;

import com.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {

    private StudentService service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 1. 获取请求参数：方法名
        String method = req.getParameter("method");
        if (method.equals("selectByPage")) {
            // 分页查询功能
            selectByPage(req, resp);
        } else if (method.equals("addStu")) {
            // 添加学生数据
            addStu(req, resp);
        } else if (method.equals("updateStu")) {
            // 修改学生数据
            updateStu(req, resp);
        } else if (method.equals("deleteStu")) {
            // 删除学生数据
            deleteStu(req, resp);
        }
    }

    // 添加学生数据
    private void addStu(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求参数
        Map<String, String[]> map = req.getParameterMap();
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        // 封装Student对象
        Student stu = new Student();
        // 注册日期格式转换
        dateConvert();
        try {
            // 封装Student对象
            BeanUtils.populate(stu, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 调用业务层的添加方法
        service.addStu(stu);
        // 添加完数据后，重定向分页查询功能
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&currentPage="
                    +currentPage+"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 修改学生数据
    private void updateStu(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求参数
        Map<String, String[]> map = req.getParameterMap();
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        // 封装Student对象
        Student stu = new Student();
        // 注册日期格式转换
        dateConvert();
        try {
            // 封装Student对象
            BeanUtils.populate(stu, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 调用业务层的添加方法
        service.updateStu(stu);
        // 添加完数据后，重定向分页查询功能
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&currentPage="
                    +currentPage+"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 分页功能
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求参数
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        // 调用业务层的查询方法
        Page page = service.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        // 封装PageInfo
        PageInfo info = new PageInfo(page);
        try {
            // 将info转成json，响应给客户端
            String json = new ObjectMapper().writeValueAsString(info);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 日期格式转换：将日期/时间格式的字符串对象解析成日期对象
    private void dateConvert() {
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }

    // 删除学生数据
    private void deleteStu(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求参数
        String number = req.getParameter("number");
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        // 调用业务层的添加方法
        service.deleteStu(number);
        // 添加完数据后，重定向分页查询功能
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&currentPage="
                    +currentPage+"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
