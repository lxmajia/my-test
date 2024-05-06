package cn.xwlin.mapper;

import cn.xwlin.entity.WxQrCodeViewHis;

public interface WxQrCodeViewHisMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(WxQrCodeViewHis record);
    WxQrCodeViewHis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxQrCodeViewHis record);

    int updateByPrimaryKey(WxQrCodeViewHis record);
}