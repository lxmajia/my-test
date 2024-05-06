package cn.xwlin.task.core.handler;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHanderRegister {
    private static ConcurrentHashMap<String, AbstractTaskHandler> clientHandlerMapping =
            new ConcurrentHashMap<>();


    public synchronized static void addHandler(String clazzName, AbstractTaskHandler handler) {
        AbstractTaskHandler abstractClientHandler = clientHandlerMapping.get(clazzName);
        if (abstractClientHandler != null) {
            throw new RuntimeException("已经存在任务解析类:" + clazzName);
        }
        clientHandlerMapping.put(clazzName, handler);
    }

    public static Optional<? extends AbstractTaskHandler> getHandler(String clazzName) {
        AbstractTaskHandler abstractClientHandler = clientHandlerMapping.get(clazzName);
        return Optional.ofNullable(abstractClientHandler);
    }

    public static void printAll() {
        for (String s : clientHandlerMapping.keySet()) {
            AbstractTaskHandler abstractClientHandler = clientHandlerMapping.get(s);
            System.out.println("key:" + s);
            System.out.println("class:" + abstractClientHandler.getClass().getName());
            System.out.println("#####################");
        }
    }
}
