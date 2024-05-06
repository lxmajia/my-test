package cn.xwlin.vo;

import cn.xwlin.dao.typehandler.ListToStringHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserFullInfo {

    private Long id;

    private String openId;

    private Integer qrCodeId;
    private String sceneCode;

    private String wxName;

    private String mobilePhone;

    private Integer wxNoticeFlag;

    private Integer smsNoticeFlag;

    private Integer telNoticeFlag;

    private String scanNotice;

    //@TableField(typeHandler = ListToStringHandler.class)
    private List<Integer> roleIdList;

    private Date created;

    private UserCarInfo userCarInfo;

}