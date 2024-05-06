package cn.xwlin.tio.server.msg;

import cn.xwlin.task.core.enums.C2SMsgType;
import org.tio.core.ChannelContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MsgHandler<T> {
    private final Class<T> paramsClass;

    public MsgHandler() {
        Type t = this.getClass().getGenericSuperclass();
        for (Class superClass = this.getClass(); t instanceof Class; t = superClass.getGenericSuperclass()) {
            superClass = superClass.getSuperclass();
        }
        Type[] actualTypeArguments = ((ParameterizedType) t).getActualTypeArguments();
        this.paramsClass = (Class) actualTypeArguments[0];


        ServerHanderRegister.addHandler(getHandlerMsgType(), this);
    }

    public abstract C2SMsgType getHandlerMsgType();

    public abstract void handler(T t, ChannelContext channelContext) throws Exception;

    public Class<T> getParamsClass() {
        return this.paramsClass;
    }

}
