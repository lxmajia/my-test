package cn.xwlin.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * ApiInvokeHandler实现.
 */
@Component
public class ApiInvokeHandlerImpl {

    @Autowired
    private HttpServletRequest request;

    public void initContext(String methodName, String className, Object[] args) {
        if (ContextDataUtils.getInstance().getLocalMap() == null) {
            ContextDataUtils.getInstance().add(new HashMap<String, Object>());
        }
        if (ContextDataUtils.getInstance().getLocalMap().get(ContextDataUtils.CONTEXT_ID) == null) {
            String contextId = UUID.randomUUID().toString();
            ContextDataUtils.getInstance().getLocalMap().put(ContextDataUtils.CONTEXT_ID, contextId);
        }
        if (ContextDataUtils.getInstance().getLocalMap().get(ContextDataUtils.TRACE_ID) == null) {
            String productTraceId = request.getHeader(ContextDataUtils.CONTEXT_ID);
            if (productTraceId == null || productTraceId.isEmpty()) {
                productTraceId = UUID.randomUUID().toString();
            }
            ContextDataUtils.getInstance().getLocalMap().put(ContextDataUtils.TRACE_ID, productTraceId);
        }
        if (StringUtils.hasLength(methodName) && StringUtils.hasLength(className)) {
            ContextDataUtils.getInstance().getLocalMap().put(ContextDataUtils.CONTROLLER_CLASS_NAME, className);
            ContextDataUtils.getInstance().getLocalMap().put(ContextDataUtils.CONTROLLER_METHOD_NAME, methodName);
        }
    }
}
