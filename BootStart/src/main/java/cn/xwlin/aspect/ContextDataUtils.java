package cn.xwlin.aspect;

import java.util.Map;

public class ContextDataUtils {
    private ThreadLocal<Map<String, Object>> localMap = new ThreadLocal();
    private static ContextDataUtils local = new ContextDataUtils();
    public static final String CONTEXT_ID = "contextId";
    public static final String TRACE_ID = "traceId";
    public static final String CONTROLLER_METHOD_NAME = "controllerMethodName";
    public static final String CONTROLLER_CLASS_NAME = "controllerClassName";

    private ContextDataUtils() {
    }

    public static ContextDataUtils getInstance() {
        return local;
    }

    public Map<String, Object> getLocalMap() {
        return (Map) this.localMap.get();
    }

    public void add(Map<String, Object> map) {
        this.localMap.set(map);
    }

    public void remove() {
        this.localMap.remove();
    }
}
