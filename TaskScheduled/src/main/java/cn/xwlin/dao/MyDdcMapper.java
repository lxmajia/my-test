package cn.xwlin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.xwlin.entity.MyDdc;

import java.util.Date;
import java.util.List;

@Mapper
public interface MyDdcMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(MyDdc record);

  int insertSelective(MyDdc record);

  MyDdc selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(MyDdc record);

  int updateByPrimaryKey(MyDdc record);

  List<MyDdc> queryBeforeDateToExecute(@Param("beforeDate") Date beforeDate);
}