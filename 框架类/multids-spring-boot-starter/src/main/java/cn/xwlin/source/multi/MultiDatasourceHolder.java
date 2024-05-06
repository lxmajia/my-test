package cn.xwlin.source.multi;

public class MultiDatasourceHolder {

    private static String defaultDsName = "";
    /**
     * 动态数据源名称上下文
     */
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    /**
     * 设置/切换数据源
     */
    public static void setDsName(String key) {
        if (key != null && !"".equals(key)) {
            DATASOURCE_CONTEXT_KEY_HOLDER.set(key);
        }
    }

    /**
     * 获取数据源名称
     */
    public static String getDsName() {
        String key = DATASOURCE_CONTEXT_KEY_HOLDER.get();
        if (null == key && (defaultDsName == null || "".equals(defaultDsName))) {
            throw new RuntimeException("Not Find DatasourceKey Or No defaultName");
        }
        return key == null ? defaultDsName : key;
    }

    /**
     * 删除当前数据源名称
     */
    public static void removeContextKey() {
        DATASOURCE_CONTEXT_KEY_HOLDER.remove();
    }

    public static void setDefaultDsName(String defaultDsName) {
        MultiDatasourceHolder.defaultDsName = defaultDsName;
    }
}
