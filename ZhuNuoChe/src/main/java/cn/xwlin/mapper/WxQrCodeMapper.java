package cn.xwlin.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.xwlin.entity.WxQrCode;

@Mapper
public interface WxQrCodeMapper {

  int deleteByPrimaryKey(Long id);


  int insert(WxQrCode record);


  int insertSelective(WxQrCode record);


  WxQrCode selectByPrimaryKey(Long id);


  int updateByPrimaryKeySelective(WxQrCode record);


  int updateByPrimaryKey(WxQrCode record);
}