
package org.springcrazy.modules.auth.granter;

import lombok.Data;
import org.springcrazy.core.tool.support.Kv;

/**
 * TokenParameter
 *
 * @author TongZhou
 */
@Data
public class TokenParameter {

	private Kv args = Kv.init();

}
