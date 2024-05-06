package cn.xwlin.dao;

import cn.xwlin.entity.MyDdcExecute;

public interface MyDdcExecuteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyDdcExecute record);

    int insertSelective(MyDdcExecute record);

    MyDdcExecute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyDdcExecute record);

    int updateByPrimaryKey(MyDdcExecute record);
}