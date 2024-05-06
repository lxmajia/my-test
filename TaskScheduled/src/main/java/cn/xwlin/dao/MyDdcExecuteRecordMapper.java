package cn.xwlin.dao;

import cn.xwlin.entity.MyDdcExecuteRecord;

public interface MyDdcExecuteRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyDdcExecuteRecord record);

    int insertSelective(MyDdcExecuteRecord record);

    MyDdcExecuteRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyDdcExecuteRecord record);

    int updateByPrimaryKey(MyDdcExecuteRecord record);
}