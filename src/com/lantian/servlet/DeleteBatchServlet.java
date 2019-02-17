package com.lantian.servlet;

import com.lantian.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 设置编码
         * */
        req.setCharacterEncoding("utf-8");
        /*
         * 接受前台传过来的ids数组
         * */
        String []ids=req.getParameterValues("id");
        /*
         * 调用services实现业务逻辑代码
         * */
        MaintainService maintainService=new MaintainService();
        maintainService.deleteBatch(ids);
        /*
         * 界面跳转到ListServlet页面执行初始化
         * */
        req.getRequestDispatcher("/List").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
