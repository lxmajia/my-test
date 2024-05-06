package cn.xwlin.tio.server.msg;

import cn.xwlin.task.core.enums.C2SMsgType;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHanderRegister {
    private static ConcurrentHashMap<C2SMsgType, MsgHandler> clientHandlerMapping =
            new ConcurrentHashMap<>();


    public synchronized static void addHandler(C2SMsgType c2SMsgType, MsgHandler handler) {
        MsgHandler abstractClientHandler = clientHandlerMapping.get(c2SMsgType);
        if (abstractClientHandler != null) {
            throw new RuntimeException("已经存在任务解析类:" + c2SMsgType);
        }
        clientHandlerMapping.put(c2SMsgType, handler);
    }

    public static Optional<MsgHandler> getHandler(C2SMsgType c2SMsgType) {
        MsgHandler abstractClientHandler = clientHandlerMapping.get(c2SMsgType);
        return Optional.ofNullable(abstractClientHandler);
    }

    public static void printAll() {
        for (C2SMsgType s : clientHandlerMapping.keySet()) {
            MsgHandler abstractClientHandler = clientHandlerMapping.get(s);
            System.out.println("key:" + s);
            System.out.println("class:" + abstractClientHandler.getClass().getName());
            System.out.println("#####################");
        }
    }
}
