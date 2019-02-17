package com.lantian.servlet;

import com.lantian.pojo.Message;
import com.lantian.service.MessageService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 设置编码
         * */
        req.setCharacterEncoding("utf-8");
        /*
         * 接受前台参数
         * */
        String command=req.getParameter("command");
        String description=req.getParameter("description");
        req.setAttribute("command",command);
        req.setAttribute("description",description);
        /*
          * 调用services实现业务逻辑代码
          * */
        MessageService messageService=new MessageService();
        List<Message>messageList=messageService.queryMessageList(command,description);
        /*
        * 将业务逻辑返回的值送到req里面
        * */
        req.setAttribute("messageList",messageList);
        /*
        * 界面跳转到list.jsp页面
        * */
        req.getRequestDispatcher("/WEB-INF/jsp/background/list.jsp").forward(req,resp);
//        try {
//            /*
//            * 建立与数据库的链接
//            * */
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn= (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message","root","19980707");
//            /*
//            * 这里将String 改成StringBuilder，防止sql连加，增加JVM负担
//            * */
//            StringBuilder sql= new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from  message where 1=1");
//            /*
//            * 预编译,防止sql注入
//            * */
//            List<String>paramList=new ArrayList<String>();
//            if (command!=null&&!"".equals(command.trim())){
//                //and前面留个空格,sql语法
//              sql.append(" and COMMAND=?");
//              paramList.add(command);
//            }
//            if (description!=null&&!"".equals(description.trim())){
//                //%和？之间用空格拼接
//                sql.append(" and DESCRIPTION like '%' ? '%'");
//                paramList.add(description);
//            }
//            PreparedStatement statement= conn.prepareStatement(sql.toString());
//            for (int i=0;i<paramList.size();i++){
//                statement.setString(i+1,paramList.get(i));
//            }
//            ResultSet rs=statement.executeQuery();
//            List<Message>messageList=new ArrayList<Message>();
//            while (rs.next()){
//                Message message=new Message();
//                messageList.add(message);
//                message.setId(rs.getInt("ID"));
//                message.setCommand(rs.getString("COMMAND"));
//                message.setDescription(rs.getString("DESCRIPTION"));
//                message.setContent(rs.getString("CONTENT"));
//            }
//            req.setAttribute("messageList",messageList);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
