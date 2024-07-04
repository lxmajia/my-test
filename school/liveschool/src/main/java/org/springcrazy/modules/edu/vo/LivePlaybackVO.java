
package org.springcrazy.modules.edu.vo;

import org.springcrazy.modules.edu.entity.LivePlayback;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 直播回放管理视图实体类
 *
 * @author TongZhou
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LivePlaybackVO对象", description = "直播回放管理")
public class LivePlaybackVO extends LivePlayback {
	private static final long serialVersionUID = 1L;

}
