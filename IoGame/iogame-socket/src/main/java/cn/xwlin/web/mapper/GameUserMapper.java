package cn.xwlin.web.mapper;

import cn.xwlin.web.entity.GameUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameUser record);

    int insertSelective(GameUser record);

    GameUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameUser record);

    int updateByPrimaryKey(GameUser record);
    GameUser selectAccountName(@Param("accountName") String accountName);
}