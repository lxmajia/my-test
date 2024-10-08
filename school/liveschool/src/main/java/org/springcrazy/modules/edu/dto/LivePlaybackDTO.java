
package org.springcrazy.modules.edu.dto;

import org.springcrazy.modules.edu.entity.LivePlayback;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 直播回放管理数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LivePlaybackDTO extends LivePlayback {
	private static final long serialVersionUID = 1L;

}
