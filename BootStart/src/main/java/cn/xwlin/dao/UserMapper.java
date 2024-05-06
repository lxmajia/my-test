package cn.xwlin.dao;

import cn.xwlin.entity.UserInfo;
import cn.xwlin.vo.UserFullInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

    String findOneName();

    UserFullInfo findById(Long id);

}
