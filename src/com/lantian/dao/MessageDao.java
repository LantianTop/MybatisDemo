package com.lantian.dao;
import com.lantian.db.DBAccess;
import com.lantian.pojo.Message;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    /*
    * 和message表相关的数据库操作
    * */
public class MessageDao {
        /*
         * 根据查询条件查询消息列表
         * */
        public List<Message> queryMessageList(String command, String description) {
            DBAccess dbAccess = new DBAccess();
            SqlSession sqlSession = null;
            List<Message> messageList = new ArrayList<Message>();
            try {
                sqlSession = dbAccess.getSqlSession();
                //通过sqlSession执行sql语句
                Message message = new Message();
                message.setCommand(command);
                message.setDescription(description);
                messageList = sqlSession.selectList("Message.queryMessageList", message);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
            return messageList;
        }

        /*
         * 实现信息单条删除
         * */
        public void deleteOne(int id) {
            DBAccess dbAccess = new DBAccess();
            SqlSession sqlSession = null;

            try {
                sqlSession = dbAccess.getSqlSession();
                /*调用配置文件中的delete语句*/
                sqlSession.delete("Message.deleteOne", id);
                /*Mybatis不会自动将数据进行更新，需要手工提交*/
                sqlSession.commit();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        }

        /*
         * 实现信息批量删除
         * */
        public void deleteBatch(List<Integer> ids) {
            DBAccess dbAccess = new DBAccess();
            SqlSession sqlSession = null;

            try {
                sqlSession = dbAccess.getSqlSession();
                /*调用配置文件中的delete语句*/
                sqlSession.delete("Message.deleteBatch", ids);
                /*Mybatis不会自动将数据进行更新，需要手工提交*/
                sqlSession.commit();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        }
    }
        /*
        * 简单单元测试看DBAccess的getSqlSession数据库会话连接是否能连接到数据库，如果没有异常，则表示连接成功！
        * */
//        public static void main(String[] args) {
//            MessageDao messageDao=new MessageDao();
//            messageDao.queryMessageList("","");
//        }

            /*
            * JDBC连接数据库方式
            * */
//    public List<Message> queryMessageList(String command,String description){
//        List<Message>messageList=new ArrayList<Message>();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn= (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message","root","19980707");
//            StringBuilder sql= new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from  message where 1=1");
//            List<String> paramList=new ArrayList<String>();
//            if (command!=null&&!"".equals(command.trim())){
//                //and前面留个空格,sql语法
//                sql.append(" and COMMAND=?");
//                paramList.add(command);
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
//            ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    Message message = new Message();
//                    messageList.add(message);
//                    message.setId(rs.getInt("ID"));
//                    message.setCommand(rs.getString("COMMAND"));
//                    message.setDescription(rs.getString("DESCRIPTION"));
//                    message.setContent(rs.getString("CONTENT"));
//                }
//
//
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return messageList;
//    }

