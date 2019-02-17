package com.lantian.service;

import com.lantian.dao.MessageDao;
import com.lantian.pojo.Message;

import java.util.List;
    /*
     * 各种常见的业务逻辑
     * */
public class MessageService {
       /*
       * 通过指令名称和描述来进行检索
       * */
    public List<Message> queryMessageList(String command, String description){
        MessageDao messageDao=new MessageDao();
      return   messageDao.queryMessageList(command,description);

    }
}
