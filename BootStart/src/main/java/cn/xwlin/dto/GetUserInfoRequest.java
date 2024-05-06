package cn.xwlin.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GetUserInfoRequest {

  @Min(value = 0, message = "ID必须大于0")
  @NotNull(message = "参数不能为空")
  private Long id;

}
