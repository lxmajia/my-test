
package org.springcrazy.modules.system.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import org.springcrazy.core.tool.utils.Func;

import java.io.Serializable;

/**
 * UserDTO
 *
 * @author TongZhou
 */
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CourseStudyhistoryExcel implements Serializable {
	private static final long serialVersionUID = 1L;

}
