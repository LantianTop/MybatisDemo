package com.lantian.service;

import com.lantian.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/*
* 维护相关的业务功能
* */
public class MaintainService {
    /*
    * 单条数据删除
    * */
    public void deleteOne(String  id){
        if(id!=null&&!"".equals(id.trim())) {
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }
    /*
    * 批量删除
    * */
    public void deleteBatch(String []ids){
        MessageDao messageDao = new MessageDao();
        List<Integer>IdList=new ArrayList<Integer>();
        for ( String id :ids){
            /*类型转换*/
            IdList.add(Integer.valueOf(id));
        }

        messageDao.deleteBatch(IdList);
    }
}
